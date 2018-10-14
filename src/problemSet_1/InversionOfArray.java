package problemSet_1;
import java.util.Scanner;

// Time complexity - O(n log n)
// Space complexity - O(n)

public class InversionOfArray {
    public static void main(String[] a){
        Scanner in = new Scanner(System.in);
        int testCases = Integer.valueOf(in.nextLine());
        int[] answers = new int[testCases];
        for(int test = 0; test < testCases; test++){
            int sizeOfArray = Integer.valueOf(in.nextLine());
            String[] s = in.nextLine().split(" ");
            int[] arr = new int[sizeOfArray];
            for(int i = 0; i < sizeOfArray; i++)
                arr[i] = Integer.valueOf(s[i]);

            for(int k = 0; k < arr.length; k++)
                System.out.print(arr[k] + " ");
            System.out.println();

            answers[test] = inversionCount(arr, 0);
            for(int k = 0; k < arr.length; k++)
                System.out.print(arr[k] + " ");
            System.out.println();
        }

        for(int k = 0; k < testCases; k++)
            System.out.println(answers[k]);
    }

    static int inversionCount(int[] arr, int inversionCount){
        if(arr.length <= 1)
            return inversionCount;
        int[] leftArray = new int[arr.length/2];
        System.arraycopy(arr, 0, leftArray, 0, leftArray.length);

        int[] rightArray = new int[arr.length - leftArray.length];
        System.arraycopy(arr, leftArray.length, rightArray, 0, rightArray.length);

        int inversionFromLeft = inversionCount(leftArray, inversionCount);
        int inversionFromRight = inversionCount(rightArray, inversionCount);

        return inversionFromLeft + inversionFromRight + mergeReturnInversion(leftArray, rightArray, arr);
    }

    static int mergeReturnInversion(int[] leftArray, int[] rightArray, int[] arr){
        int inversionCount = 0;
        int leftArrayIndex = 0;
        int rightArrayIndex = 0;
        int arrIndex = 0;
        int mid = leftArray.length;

        while(leftArrayIndex < leftArray.length && rightArrayIndex < rightArray.length){
            if(leftArray[leftArrayIndex] <= rightArray[rightArrayIndex]){
                arr[arrIndex++] = leftArray[leftArrayIndex++];
            } else {
                inversionCount = inversionCount + mid - leftArrayIndex;
                arr[arrIndex++] = rightArray[rightArrayIndex++];
            }
        }

        if(leftArrayIndex == leftArray.length){
            while(rightArrayIndex < rightArray.length)
                arr[arrIndex++] = rightArray[rightArrayIndex++];
        }

        if(rightArrayIndex == rightArray.length) {
            while (leftArrayIndex < leftArray.length)
                arr[arrIndex++] = leftArray[leftArrayIndex++];
        }
        return inversionCount;
    }
}
