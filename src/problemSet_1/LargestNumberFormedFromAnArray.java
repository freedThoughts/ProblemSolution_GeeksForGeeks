package problemSet_1;

import java.util.*;

/**
 * Created by Prakash on 30-06-2018.
 * https://practice.geeksforgeeks.org/problems/largest-number-formed-from-an-array/0
 */
public class LargestNumberFormedFromAnArray {
    public static void main(String[] a){
        Scanner in = new Scanner(System.in);
        int testCases = Integer.valueOf(in.nextLine());
        String[] answers = new String[testCases];
        for(int test = 0; test < testCases; test++){
            int arrLength = Integer.valueOf(in.nextLine());
            String[] arr = in.nextLine().split(" ");
            char[][] charArr = new char[arrLength][];
            for(int i = 0; i < arrLength; i++)
                charArr[i] = arr[i].toCharArray();

            StringBuilder answer = new StringBuilder("");
            List<Integer> indexesToProcess = new ArrayList<>();
            for(int i = 0; i < arrLength; i++)
                indexesToProcess.add(i);

            getResult(answer, charArr, indexesToProcess, "");
            answers[test] = answer.toString();

        }
    }

    private static void getResult(StringBuilder answer, char[][] charArr, List<Integer> indexesToProcess, String pastUnprocessedString){
        int indexToCheckInThisMethod = pastUnprocessedString.length();
        Map<Character, List<Integer>> map = new TreeMap<>((Character c1, Character c2) -> {
            int result = Integer.valueOf(c2).compareTo(Integer.valueOf(c1));
            if(result == 0)
                return 1;
            return result;
        });

        for(int i = 0; i < charArr.length; i++){
            List<Integer> list = null;
            if(charArr[i].length <= indexToCheckInThisMethod){
                char firstCharOfPastString = pastUnprocessedString.charAt(0);
                list = map.get(firstCharOfPastString);
                if(list == null)
                    list = new ArrayList();
                list.add(i);
                map.put(firstCharOfPastString, list);
                continue;
            }
            list = map.get(charArr[i][indexToCheckInThisMethod]);
            if(list == null)
                list = new ArrayList();
            list.add(i);
            map.put(charArr[i][indexToCheckInThisMethod], list);
        }

        if(map.size() == 1 && map.containsKey(pastUnprocessedString.charAt(0))){
            //for()
        }
    }

}
