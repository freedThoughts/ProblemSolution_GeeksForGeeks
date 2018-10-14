package problemSet_1;

import java.util.Scanner;

public class MinimumElementInASortedAndRotatedArray {
    public static void main(String[] a){
        Scanner in = new Scanner(System.in);
        int testCases = Integer.valueOf(in.nextLine());
        int[] answers = new int[testCases];
        for(int t = 0; t < testCases; t++){
            int size = Integer.valueOf(in.nextLine());
            String[] s = in.nextLine().split(" ");
            int[] arr = new int[size];
            for(int i = 0; i < size; i++)
                arr[i] = Integer.valueOf(s[i]);
            answers[t] = getMinimum(arr, 0, size -1);
        }

        for(int i : answers)
            System.out.println(i);

    }

    private static int getMinimum(int[] arr, int startIndex, int endIndex){
        if(startIndex > endIndex)
            return arr[startIndex];

        int mid = startIndex + (endIndex - startIndex)/2;
        if(mid -1 <0 || mid +1 > arr.length -1)
            return arr[mid];

        if(arr[mid] < arr[mid -1] && arr[mid] < arr[mid +1])
            return arr[mid];

        if(arr[mid] > arr[arr.length-1])
            return getMinimum(arr, mid+1, endIndex);
        return getMinimum(arr, startIndex, mid -1);
    }
}
