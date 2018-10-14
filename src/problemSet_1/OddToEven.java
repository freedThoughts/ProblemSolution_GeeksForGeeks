package problemSet_1;

import java.util.Scanner;

public class OddToEven {
    public static  void main(String[] a){
        Scanner in = new Scanner(System.in);
        int testCases = Integer.valueOf(in.nextLine().split(" ")[0]);
        String[] answers = new String[testCases];
        for(int i = 0; i < testCases ; i++){
            String stringNumber = in.nextLine().split(" ")[0];
            int digits = stringNumber.length();
            int number = Integer.valueOf(stringNumber);
            int[] arr = new int[digits];
            for(int k = digits -1; k >=0; k--){
                arr[k] = number % 10;
                number = number /10;
            }

            boolean isSwaped = false;
            Integer lastEvenDigitIndex = null;

            for(int k = 0; k < arr.length -1; k++){
                if(arr[k] % 2 == 0){
                    lastEvenDigitIndex = k;
                    if(arr[k] <= arr[arr.length -1]){
                        swap(arr, k, arr.length -1);
                        isSwaped = true;
                        break;
                    }
                }
            }
            if(!isSwaped && lastEvenDigitIndex != null)
                swap(arr, lastEvenDigitIndex, arr.length -1);

            StringBuilder s = new StringBuilder();
            for(int k : arr){
                s.append(k);
            }

            answers[i] = s.toString();
        }

        for(String s : answers)
            System.out.println(s);


    }

    private static void swap(int[] a, int i, int j){
        int k = a[i];
        a[i] = a[j];
        a[j] = k;
    }
}

//Time Complexity = O(n)
//Space Complexity = O(1)
