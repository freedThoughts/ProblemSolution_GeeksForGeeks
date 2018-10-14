package problemSet_1;

import java.util.*;


// https://practice.geeksforgeeks.org/problems/overlapping-intervals/0

// Time complexity - O(log n)
public class OverlappingIntervals {
    public static void main(String[] a) {
        Scanner in = new Scanner(System.in);
        int testCases = Integer.valueOf(in.nextLine().split(" ")[0]);
        List<int[]>[] answers = new List[testCases];
        for(int test = 0; test < testCases; test++){
            int pairs = Integer.valueOf(in.nextLine().split(" ")[0]);
            String[] s = in.nextLine().split(" ");
            TreeMap<Integer, int[]> map = new TreeMap<>();
            for(int i = 0; i < s.length;){
                int startPoint = Integer.valueOf(s[i++]);
                int endPoint = Integer.valueOf(s[i++]);
                int[] ar = {startPoint, endPoint};
                map.put(startPoint, ar);
            }
            List<int[]> list = new ArrayList<>();
            int[] lastArray = null;
            for(Map.Entry<Integer, int[]> e: map.entrySet()){
                if(lastArray == null){
                    lastArray = e.getValue();
                    list.add(lastArray);
                    continue;
                }
                if(nextValueOverlaps(lastArray, e.getValue())){
                    list.remove(lastArray);
                    int[] nextArr = e.getValue();
                    int[] overLappedArray = {Math.min(lastArray[0], nextArr[0]), Math.max(lastArray[1], nextArr[1])};
                    lastArray = overLappedArray;
                    list.add(lastArray);
                    continue;
                }
                lastArray = e.getValue();
                list.add(lastArray);
            }
            answers[test] = list;
        }

        for(List<int[]> l : answers){
            for(int[] arrr : l){
                System.out.print(arrr[0] +" "+ arrr[1] + " ");
            }
            System.out.println();
        }
    }

    static boolean nextValueOverlaps(int[] lastArray, int[] nextArray){
        return isValueBetweenTheaseTwo(nextArray[0], lastArray[0], lastArray[1]) ||
                isValueBetweenTheaseTwo(nextArray[1], lastArray[0], lastArray[1]);
    }

    static boolean isValueBetweenTheaseTwo(int value, int startPoint, int endPoint){
        return value >= startPoint && value <= endPoint;
    }
}
