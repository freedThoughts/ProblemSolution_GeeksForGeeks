package problemSet_1;

import java.util.Scanner;

/**
 * Created by Prakash on 28-06-2018.
 * https://practice.geeksforgeeks.org/problems/closest-palindrome/0
 */
public class ClosestPalindrome {
    public static void main(String[] a){
        Scanner in = new Scanner(System.in);
        int testCases = Integer.valueOf(in.nextLine());
        int[] answers = new int[testCases];
        for(int test = 0; test < testCases; test++){
            String stringValue = in.nextLine();
            char[] charValue = stringValue.toCharArray();
            int numberOfDigits = charValue.length;
            if(numberOfDigits <= 1){
                answers[test] = Integer.valueOf(stringValue);
                continue;
            }

            int firstHalfLowestIndex;
            int secondHalfHighestIndex;
            if(numberOfDigits % 2 == 0){
                secondHalfHighestIndex = numberOfDigits/2;
                firstHalfLowestIndex = secondHalfHighestIndex -1;
            } else {
                firstHalfLowestIndex = numberOfDigits/2 - 1;
                secondHalfHighestIndex = numberOfDigits/2 + 1;
            }

            // for case charValue[secondHalfHighestIndex] < charValue[firstHalfLowestIndex]
            Integer v1 = null;
            if(charValue[secondHalfHighestIndex] < charValue[firstHalfLowestIndex]){
                char[] va = new char[charValue.length];
                System.arraycopy(charValue, 0, va, 0, charValue.length);
                va[secondHalfHighestIndex -1] = (char)(va[secondHalfHighestIndex -1] - 1);

                int secondIndex = secondHalfHighestIndex;
                for(int i = firstHalfLowestIndex; i >=0; i--){
                    va[secondIndex] = va[i];
                    secondIndex++;
                }

                v1 = Integer.valueOf(new String(va));
            }

            // for case charValue[secondHalfHighestIndex] > charValue[firstHalfLowestIndex]
            Integer m1 = null;
            if(charValue[secondHalfHighestIndex] > charValue[firstHalfLowestIndex]){
                char[] ma = new char[charValue.length];
                System.arraycopy(charValue, 0, ma, 0, charValue.length);
                ma[secondHalfHighestIndex -1] = (char)(ma[secondHalfHighestIndex -1] + 1);

                int secondIndex = secondHalfHighestIndex;
                for(int i = firstHalfLowestIndex; i >=0; i--){
                    ma[secondIndex] = ma[i];
                    secondIndex++;
                }

                m1 = Integer.valueOf(new String(ma));
            }

            // For all cases
            for(int i = firstHalfLowestIndex; i >=0; i--){
                charValue[secondHalfHighestIndex] = charValue[i];
                secondHalfHighestIndex++;
            }
            int v2 = Integer.valueOf(new String(charValue));

            // select one of the two above mentioned cases and assign it's value to val
            int val = 0;
            if(v1 == null && m1 == null){
                answers[test] = v2;
                continue;
            }
            if(v1 == null){
                val = m1;
            } else
                val = v1;

            // Check the final result
            int originalInt = Integer.valueOf(stringValue);
            if(Math.abs(originalInt - val) < Math.abs(originalInt - v2))
                answers[test] = val;
            else if (Math.abs(originalInt - val) > Math.abs(originalInt - v2))
                answers[test] = v2;
            else
                answers[test] = Math.min(val, v2);
        }
        in.close();

        for(int j = 0; j < testCases; j++)
            System.out.println(answers[j]);
    }
}
