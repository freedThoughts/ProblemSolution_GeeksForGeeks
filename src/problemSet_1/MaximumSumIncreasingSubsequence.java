package problemSet_1;

import java.util.Scanner;
import java.util.Stack;

// https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence/0
public class MaximumSumIncreasingSubsequence {
    public static void main(String[] x){
        Scanner in = new Scanner(System.in);
        int testCases = Integer.valueOf(in.nextLine());
        int[] answers = new int[testCases];
        for(int t = 0; t < testCases; t++){
            int size = Integer.valueOf(in.nextLine());
            int[] sequence = new int[size];
            String[] s = in.nextLine().split(" ");
            for(int i = 0; i < size; i++)
                sequence[i] = Integer.valueOf(s[i]);

            answers[t] = getMax(sequence);
        }
        for(int i : answers)
            System.out.println(i);
    }

    private static int getMax(int[] arr){
        int maxSum = 0;
        int currentSum = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < arr.length; i++){
            currentSum += arr[i];
            maxSum = currentSum > maxSum ? currentSum : maxSum;
            stack.push(i);

            while(i < arr.length-1 && arr[i] <= arr[i+1]){
                currentSum += arr[i+1];
                maxSum = currentSum > maxSum ? currentSum : maxSum;
                stack.push(i+1);
                i++;
            }

            if(i == arr.length -1 )
                return maxSum;

            while(!stack.isEmpty() && arr[stack.peek()] >= arr[i+1])
                currentSum -= arr[stack.pop()];
        }

        return maxSum;
    }
}
