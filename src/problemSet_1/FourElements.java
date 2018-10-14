package problemSet_1;

import java.util.Scanner;

public class FourElements {
    public static void main(String[] a){
        Scanner in = new Scanner(System.in);
        int testCases = Integer.valueOf(in.nextLine());
        int[] answers = new int[testCases];
        for(int t = 0; t < testCases; t++){
            int size = Integer.valueOf(in.nextLine());
            int[] sequence = new int[size];
            String[] s = in.nextLine().split(" ");
            for(int i = 0; i < size; i++)
                sequence[i] = Integer.valueOf(s[i]);
            int total = Integer.valueOf(in.nextLine());

            answers[t] = isSumPossible(sequence, total);
        }
        for(int i : answers)
            System.out.println(i);
    }

    private static int isSumPossible(int[] input, int total) {
        int[][] output = new int[input.length + 1][total + 1];
        for (int row = 1; row <= input.length; row++) {
            //System.out.println( "row : " + row);
            for (int column = 0; column <= total; column++) {
                //System.out.println("Column :  " + column);
                if(input[row-1] > column){
                    output[row][column] = output[row - 1][column];
                    continue;
                }
                if (column == input[row - 1]) {
                    output[row][column] = Math.max(1, output[row - 1][column]);
                    continue;
                }

                if(column == 0){
                    if(input[row-1] == 0)
                        output[row][column] = Math.max(1, 1 + output[row-1][column]);
                    else
                        output[row][column] = output[row-1][column];
                    continue;
                }
                if ((column - input[row - 1]) >=0 && output[row - 1][column - input[row - 1]] > 0) {
                    output[row][column] = Math.max(output[row - 1][column], 1 + output[row - 1][column - input[row - 1]]);
                    continue;
                }

                output[row][column] = output[row - 1][column];
            }

            for(int i : output[row])
                System.out.print(i + " ");
            System.out.println();
            if (output[row][total] == 4)
                return 1;
        }
        return output[input.length][total] == 4 ? 1 : 0;
    }

}
