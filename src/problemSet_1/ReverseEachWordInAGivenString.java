package problemSet_1;

import java.util.Scanner;

/**
 * Created by Prakash on 02-06-2018.
 * https://practice.geeksforgeeks.org/problems/reverse-each-word-in-a-given-string/0
 */
public class ReverseEachWordInAGivenString {
    public static void main(String[] a){
        Scanner in = new Scanner(System.in);
        int testCases = Integer.valueOf(in.nextLine());
        String[] answers = new String[testCases];
        for(int test = 0; test < testCases; test++){
            char[] charArray = in.nextLine().toCharArray();

            int startIndexOfWord = 0;
            int lastIndexOfWord = -1;
            for(int i = 0; i< charArray.length; i++){
                if(charArray[i] != '.'){
                    lastIndexOfWord = i;
                    continue;
                }

                int midIndexOfWord = startIndexOfWord + (lastIndexOfWord - startIndexOfWord)/2;
                for(int k = startIndexOfWord; k <= midIndexOfWord ; k++){
                    char c = charArray[lastIndexOfWord];
                    charArray[lastIndexOfWord] = charArray[startIndexOfWord];
                    charArray[startIndexOfWord] = c;
                    lastIndexOfWord --;
                    startIndexOfWord ++;
                }

                startIndexOfWord = i+1;
            }

            lastIndexOfWord = charArray.length -1;
            int midIndexOfWord = startIndexOfWord + (lastIndexOfWord - startIndexOfWord)/2;
            for(int k = startIndexOfWord; k <= midIndexOfWord ; k++){
                char c = charArray[lastIndexOfWord];
                charArray[lastIndexOfWord] = charArray[startIndexOfWord];
                charArray[startIndexOfWord] = c;
                lastIndexOfWord --;
                startIndexOfWord ++;
            }

            answers[test] = new String(charArray);
        }

        for (String s : answers)
            System.out.println(s);
    }
}
