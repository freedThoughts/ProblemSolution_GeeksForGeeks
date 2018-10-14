package problemSet_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//https://practice.geeksforgeeks.org/problems/n-queen-problem/0
public class NQueenProblem {
    public static void main(String[] a){
        Scanner in = new Scanner(System.in);
        int testCases = Integer.valueOf(in.nextLine());
        for(int test = 0; test < testCases; test++){
            int boardSize = Integer.valueOf(in.nextLine());
            List<Integer> attackingColumns = new ArrayList<>();
            List<Cell> attackingDiagonalCells = new ArrayList<>();
            List<Cell> selectedCells = new ArrayList<>();
            for(int row = 0; row < boardSize && row >= 0; row++){
                boolean isCellFound = false;
                for(int column = 0; column < boardSize && !attackingColumns.contains(column); column++){
                    Cell cell = new Cell(row, column, boardSize);
                    if(attackingDiagonalCells.contains(cell)){
                        continue;
                    }
                    selectedCells.add(cell);
                    attackingColumns.add(column);
                    attackingDiagonalCells.addAll(cell.diagonalAttackingCellsBelowThisRow);
                    isCellFound = true;
                    break;
                }
                if(!isCellFound){
                    row--;
                    Cell lastRowCell = selectedCells.get(row);
                    selectedCells.remove(row);
                    attackingColumns.remove(lastRowCell.column);
                    attackingDiagonalCells.removeAll(lastRowCell.diagonalAttackingCellsBelowThisRow);
                    row--;
                }
            }
        }

    }

    //private static Cell getSuitableCellForQueen(List<Integer> attackingColumns, List<Cell> attackingDiagonalCells, int )



    private static class Cell {
        int row;
        int column;
        int boardSize;
        List<Cell> diagonalAttackingCellsBelowThisRow = new ArrayList<>();
        Cell (int row, int column, int boardSize){
            this.row = row;
            this.column = column;
            this.boardSize = boardSize;
            for(int i = row +1, j = column +1; i < boardSize && j < boardSize; i++, j++)
                diagonalAttackingCellsBelowThisRow.add(new Cell(i, j, boardSize));
            for(int i = row +1, j = column -1; i < boardSize && j < boardSize; i++, j--)
                diagonalAttackingCellsBelowThisRow.add(new Cell(i, j, boardSize));

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Cell cell = (Cell) o;

            if (row != cell.row) return false;
            return column == cell.column;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + column;
            return result;
        }
    }
}
