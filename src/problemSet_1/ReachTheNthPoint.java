package problemSet_1;

import java.util.Scanner;

public class ReachTheNthPoint {
    public static  void main(String[] x){
        Scanner in = new Scanner(System.in);
        int testCases = Integer.valueOf(in.nextLine());
        int[] answers = new int[testCases];
        for(int t = 0; t < testCases; t++){
            int steps = Integer.valueOf(in.nextLine());
            answers[t] = numberOfWays(steps);
        }
        for(int i : answers)
            System.out.println(i);
    }

    private static int numberOfWays(int steps){
        int[] arr = new int[steps];
        arr[0] = 1;
        arr[1] = 2;
        for(int i = 2; i < steps; i++)
            arr[i] = arr[i-1] + arr[i-2];
        return arr[steps-1];
    }
}
