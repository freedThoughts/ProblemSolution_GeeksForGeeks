package problemSet_1;

import java.util.Scanner;

/**
 * Created by Prakash on 02-06-2018.
 * https://practice.geeksforgeeks.org/problems/save-ironman/0
 */
public class SaveIronman {
    public static void main(String[] a){
        Scanner in = new Scanner(System.in);
        int testCases = Integer.valueOf(in.nextLine());
        String[] answers = new String[testCases];
        for(int test = 0; test < testCases; test ++){
            String s = in.nextLine();
            int[] arr = new int[s.length()];
            int endIndex = -1;
            for(char c : s.toCharArray()){
                if(Character.isDigit(c)){
                    arr[++endIndex] = c;
                    continue;
                }
                if(c >= 65 && c <= 90){
                    arr[++endIndex] = (int) c;
                    continue;
                }
                if(c >=97 && c <= 122){
                    arr[++endIndex] = (int)c - 32;
                    continue;
                }
            }
            int startIndex = 0;
            while(startIndex <= endIndex){
                if(arr[startIndex++] != arr[endIndex--]){
                    answers[test] = "NO";
                    break;
                }
            }
            answers[test] = answers[test] == null ? "YES" : answers[test];
        }
        for(String d : answers)
            System.out.println(d);
    }
}
