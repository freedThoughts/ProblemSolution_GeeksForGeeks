package problemSet_1;

import java.util.*;

/**
 * Created by Prakash on 02-06-2018.
 * https://practice.geeksforgeeks.org/problems/solve-the-sudoku/0
 */
public class Sudoku {

    static int SIZE = 9;
    public static void main(String[] a) {
        Scanner in = new Scanner(System.in);
        int testCases = Integer.valueOf(in.nextLine());
        Cell[][][] answers = new Cell[testCases][SIZE][SIZE];
        for (int test = 0; test < testCases; test++) {
            String[] s = in.nextLine().split(" ");

            // Construction of matrix
            Cell[][] matrix = new Cell[SIZE][SIZE];
            int i = -1;
            for (int row = 0; row < SIZE; row++)
                for (int column = 0; column < SIZE; column++)
                    matrix[row][column] = new Cell(Integer.valueOf(s[++i]));

            // Calculate possibility of all Cells
            for (int row = 0; row < SIZE; row++)
                for (int column = 0; column < SIZE && matrix[row][column].value == 0; column++)
                    checkPossibilityOfCell(matrix, row, column);

            System.out.println(matrix[0][1].value);
            System.out.println(matrix[0][1].possibleValues.size());

            for (int row = 0; row < SIZE; row++) {
                for (int column = 0; column < SIZE && matrix[row][column].value == 0; column++) {
                    for(int possibleValue : matrix[row][column].possibleValues){
                        if(ifSamePossibleValueNotExistInAnyCellSameBlock(matrix, row, column, possibleValue)){
                            matrix[row][column].value = possibleValue;
                            checkPossibilityInSameBlockAndUpdate(matrix, row, column);
                            checkPossibilityInSameRowAndUpdate(matrix, row, column);
                            checkPossibilityInSameColumnAndUpdate(matrix, row, column);
                            continue;
                        }
                        if(ifSamePossibleValueNotExistInAnyCellSameRow(matrix, row, column, possibleValue)){
                            matrix[row][column].value = possibleValue;
                            checkPossibilityInSameBlockAndUpdate(matrix, row, column);
                            checkPossibilityInSameRowAndUpdate(matrix, row, column);
                            checkPossibilityInSameColumnAndUpdate(matrix, row, column);
                            continue;
                        }
                        if(ifSamePossibleValueNotExistInAnyCellSameColumn(matrix, row, column, possibleValue)){
                            matrix[row][column].value = possibleValue;
                            checkPossibilityInSameBlockAndUpdate(matrix, row, column);
                            checkPossibilityInSameRowAndUpdate(matrix, row, column);
                            checkPossibilityInSameColumnAndUpdate(matrix, row, column);
                            continue;
                        }
                    }
                }
            }
            answers[test] = matrix;
        }

/*        for(Cell[][] matrix : answers){
            for(Cell[] m : matrix){
                for(Cell n : m)
                    System.out.print(n.value + " ");
                System.out.println();
            }

        }*/
    }

    private static void checkPossibilityInSameColumnAndUpdate(Cell[][] matrix, int row, int column){
        for(int r = 0; r < SIZE && r != row && matrix[r][column].value == 0; r++){
            if(checkPossibilityOfCell(matrix, r, column)){
                checkPossibilityInSameBlockAndUpdate(matrix, r, column);
                checkPossibilityInSameRowAndUpdate(matrix, r, column);
                checkPossibilityInSameColumnAndUpdate(matrix, r, column);
            }
        }
    }

    private static void checkPossibilityInSameRowAndUpdate(Cell[][] matrix, int row, int column){
        for(int col = 0 ; col < SIZE && col != column && matrix[row][col].value == 0; col ++){
            if(checkPossibilityOfCell(matrix, row, col)){
                checkPossibilityInSameBlockAndUpdate(matrix, row, col);
                checkPossibilityInSameRowAndUpdate(matrix, row, col);
                checkPossibilityInSameColumnAndUpdate(matrix, row, col);
            }
        }
    }

    private static void checkPossibilityInSameBlockAndUpdate(Cell[][] matrix, int row, int column){
        int startRowIndexForBlock = row / 3;
        int startColumnIndexForBlock = column / 3;
        for(int i = startRowIndexForBlock; i < startColumnIndexForBlock+3; i++){
            for(int j = startColumnIndexForBlock; j < startColumnIndexForBlock+3 && matrix[i][j].value == 0; j++){
                if(checkPossibilityOfCell(matrix, i, j)){
                    checkPossibilityInSameBlockAndUpdate(matrix, i, j);
                    checkPossibilityInSameRowAndUpdate(matrix, i, j);
                    checkPossibilityInSameColumnAndUpdate(matrix, i, j);
                }
            }
        }
    }

    private static boolean ifSamePossibleValueNotExistInAnyCellSameColumn(Cell[][] matrix, int row, int column, int possibleValue){
        for(int i = 0; i < SIZE && i != row && matrix[i][column].value == 0 && matrix[i][column].possibleValues.contains(possibleValue); i++)
            return false;
        return true;
    }

    private static boolean ifSamePossibleValueNotExistInAnyCellSameRow(Cell[][] matrix, int row, int column, int possibleValue){
        for(int i = 0; i < SIZE && i != column && matrix[row][i].value == 0 && matrix[row][i].possibleValues.contains(possibleValue); i++)
            return false;
        return true;
    }

    private static boolean ifSamePossibleValueNotExistInAnyCellSameBlock(Cell[][] matrix, int row, int column, int possibleValue){
        int startRowIndexForBlock = row / 3;
        int startColumnIndexForBlock = column / 3;

        for(int i = startRowIndexForBlock; i < startRowIndexForBlock+3 ; i++) {
            for (int j = startColumnIndexForBlock; j < startColumnIndexForBlock + 3 && matrix[i][j].value == 0 && !(i == row && j == column)
                    && matrix[i][j].possibleValues.contains(possibleValue); j++) {
                return false;
            }
        }
        return true;
    }


    private static boolean checkPossibilityOfCell(Cell[][] matrix, int row, int column) {

        // Reducing possibility based on 3 * 3 block of matrix
        int startRowIndexForBlock = row / 3;
        int startColumnIndexForBlock = column / 3;
        for (int i = startRowIndexForBlock; i < startRowIndexForBlock + 3; i++){
            for (int j = startColumnIndexForBlock; j < startColumnIndexForBlock + 3 && matrix[i][j].value != 0; j++){
                //System.out.println("Value " + matrix[i][j].value);
                //System.out.println(" Bofore remove size " + matrix[row][column].possibleValues.size());
                //System.out.println(" Bofore remove contains " + matrix[row][column].possibleValues.contains(matrix[i][j].value));
                matrix[row][column].possibleValues.remove(matrix[i][j].value);
                //System.out.println(" after remove size " + matrix[row][column].possibleValues.size());
            }
        }


/*        for(int i : matrix[0][1].possibleValues){
                System.out.print(i + " ");
        }*/
        System.out.println("   ************************    ");
        //System.out.println(matrix[0][1].possibleValues.size());

        // Reducing possibility based on given column
        for (int k = 0; k < SIZE; k++){
            if (matrix[k][column].value != 0){
                System.out.println(" i : " + k +" row: "+ row +" column : " + column);
                matrix[row][column].possibleValues.remove(matrix[k][column].value);
            }

        }


        // Reducing possibility based on given row
        for (int i = 0; i < SIZE && matrix[row][i].value != 0; i++)
            matrix[row][column].possibleValues.remove(matrix[row][i].value);

        if (matrix[row][column].possibleValues.size() == 1) {
            matrix[row][column].value = matrix[row][column].possibleValues.get(0);
            return true;
        }
        return false;
    }

    private static class Cell{
        int value;
        List<Integer> possibleValues;

        Cell(int value){
            if(value == 0){
                this.possibleValues = new ArrayList<>();
                for(int i = 1; i <= SIZE; i++)
                    this.possibleValues.add(i);
            }
            else
                this.value = value;
        }
    }
}
