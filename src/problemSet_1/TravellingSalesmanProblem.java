package problemSet_1;
//https://practice.geeksforgeeks.org/problems/travelling-salesman-problem/0

import java.util.*;

public class TravellingSalesmanProblem {
    public static void main(String[] a){
        Scanner in = new Scanner(System.in);
        int testCases = Integer.valueOf(in.nextLine());
        int[] answers = new int[testCases];
        for(int test = 0; test < testCases; test ++){
            int numberOfCities = Integer.valueOf(in.nextLine());
            int[][] matrix = new int[numberOfCities][numberOfCities];
            String[] s = null;
            for(int i = 0; i < numberOfCities; i++){
                s = in.nextLine().split(" ");
                for(int j = 0; j < numberOfCities; j++)
                    matrix[i][j] = Integer.valueOf(s[j]);
            }

            Map<SubTravel, Integer> costMap = new HashMap<>();
            Set<Integer> set = new HashSet<>();
            for(int i = 1; i < numberOfCities; i++)
                set.add(i);
            answers[test] = getMinCost(costMap, new SubTravel(0, set), matrix);
        }
        in.close();
        for(int i : answers)
            System.out.println(i);
    }

    private static int getMinCost(Map<SubTravel, Integer> costMap, SubTravel subTravel, int[][] matrix){
        if(costMap.containsKey(subTravel))
            return costMap.get(subTravel);
        if(subTravel.pathTraveled.isEmpty()){
            costMap.put(subTravel, matrix[0][subTravel.destinationIndex]);
            return matrix[0][subTravel.destinationIndex];
        }

        int[] intermittentCitiesIndexes = new int[subTravel.pathTraveled.size()];
        int temp = 0;
        for(Integer cityIndex : subTravel.pathTraveled)
            intermittentCitiesIndexes[temp++] = cityIndex;

        int[] costForIntermittentCityTravel = new int[subTravel.pathTraveled.size()];
        for(int i = 0; i < intermittentCitiesIndexes.length; i++){
            Set<Integer> s = new HashSet<>();
            s.addAll(subTravel.pathTraveled);
            s.remove(intermittentCitiesIndexes[i]);
            SubTravel intermittentTravel = new SubTravel(intermittentCitiesIndexes[i], s);
            int intermittentCost = getMinCost(costMap, intermittentTravel, matrix);
            costMap.put(intermittentTravel, intermittentCost);
            costForIntermittentCityTravel[i] = matrix[intermittentCitiesIndexes[i]][subTravel.destinationIndex] + intermittentCost;
        }

        int cost = Arrays.stream(costForIntermittentCityTravel).min().getAsInt();
        costMap.put(subTravel, cost);
        return cost;
    }

    private static class SubTravel {
        int destinationIndex;
        Set<Integer> pathTraveled = new HashSet<Integer>();

        public SubTravel(int destinationIndex, Set<Integer> pathTraveled) {
            this.destinationIndex = destinationIndex;
            this.pathTraveled = pathTraveled;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SubTravel subTravel = (SubTravel) o;

            if (destinationIndex != subTravel.destinationIndex) return false;
            return pathTraveled.equals(subTravel.pathTraveled);
        }

        @Override
        public int hashCode() {
            int result = destinationIndex;
            result = 31 * result + pathTraveled.hashCode();
            return result;
        }
    }
}
