package problemSet_1;

import java.util.*;

/**
 * Created by Prakash on 02-06-2018.
 * https://practice.geeksforgeeks.org/problems/word-boggle/0
 */
public class WordBoggle {
    public static void main(String[] a){
        Scanner in = new Scanner(System.in);
        int testCases = Integer.valueOf(in.nextLine());
        String[][] answers = new String[testCases][];
        for(int testCase = 0; testCase < testCases; testCase++){
            int numberOfWord = Integer.valueOf(in.nextLine());
            String[] wordsArr = in.nextLine().split(" ");
            String[] s = in.nextLine().split(" ");
            int rowLength = Integer.valueOf(s[0]);
            int columnLength = Integer.valueOf(s[1]);
            s = in.nextLine().split(" ");

            // Constructing matrix
            int i = -1;
            Cell[][] matrix = new Cell[rowLength][columnLength];
            for(int row = 0; row < rowLength; row++)
                for(int column = 0; column < columnLength; column++)
                    matrix[row][column] = new Cell(s[++i].charAt(0), row, column);

            // Adding neighbours for each Cell
            addNeighbours(matrix, rowLength, columnLength);

            String[] answer = new String[numberOfWord];
            for(int wordIndex = 0; wordIndex < numberOfWord; wordIndex++)
                answer[wordIndex] = getValue(wordsArr[wordIndex], matrix, rowLength, columnLength);

            answers[testCase] = answer;
        }

        // Print Answers as per required format
        for(int i = 0; i < testCases; i++){
            List<String> list = new ArrayList<>();
            for(String s : answers[i]){
                if(s != null)
                    list.add(s);
            }
            if(list.isEmpty())
                System.out.println(-1);
            else {
                for(String s : list)
                    System.out.print(s + " ");
                System.out.println();
            }
        }
    }

    private static String getValue(String word, Cell[][] matrix, int rowLength, int columnLength){
        Set<Cell> usedCells = new HashSet<>();
        Cell firstCell = null;
        for(int row = 0; row < rowLength; row++){
            boolean isFound = false;
            for(int column = 0; column< columnLength; column++ ){
                if(matrix[row][column].ch == word.charAt(0)){
                    firstCell = matrix[row][column];
                    usedCells.add(firstCell);
                    isFound = true;
                    break;
                }
            }
            if(isFound)
                break;
        }

        if(firstCell == null)
            return null;

        Cell previousCell = firstCell;

        for(int wordIndex = 1; wordIndex < word.length(); wordIndex++){
            boolean isFound = false;
            for(Cell cell : previousCell.adjacentCells){
                if(!usedCells.contains(cell) && cell.ch == word.charAt(wordIndex)){
                    usedCells.add(cell);
                    previousCell = cell;
                    isFound = true;
                    break;
                }
            }

            if(!isFound)
                return null;

        }
        return word;
    }

    private static void addNeighbours(Cell[][] matrix, int rowLength, int columnLength){
        int lastRowIndex = rowLength -1;
        int lastColumnIndex = columnLength -1;

        for(int row = 0; row < rowLength; row++){
            for(int column = 0; column < columnLength; column++){

                if(row > 0)
                    matrix[row][column].adjacentCells.add(matrix[row-1][column]);
                if(row < lastColumnIndex)
                    matrix[row][column].adjacentCells.add(matrix[row+1][column]);
                if(column > 0)
                    matrix[row][column].adjacentCells.add(matrix[row][column-1]);
                if(column < lastColumnIndex)
                    matrix[row][column].adjacentCells.add(matrix[row][column+1]);
                if(row > 0 && column >0)
                    matrix[row][column].adjacentCells.add(matrix[row-1][column-1]);
                if(row < lastRowIndex && column > 0)
                    matrix[row][column].adjacentCells.add(matrix[row+1][column -1]);
                if(row > 0 && column < lastColumnIndex)
                    matrix[row][column].adjacentCells.add(matrix[row-1][column+1]);
                if(row < lastRowIndex && column < lastColumnIndex)
                    matrix[row][column].adjacentCells.add(matrix[row+1][column+1]);

            }
        }
    }

    private static class Cell {
        char ch;
        int row;
        int column;
        Set<Cell> adjacentCells;

        Cell(char ch, int row, int column){
            this.ch = ch;
            this.row = row;
            this.column = column;
            adjacentCells = new HashSet<Cell>(8);
        }
    }
}
