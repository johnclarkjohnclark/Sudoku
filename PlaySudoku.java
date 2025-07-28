// John Clark
// CS 143
// HW #1: Sudoku #1 (Board Setup)
//
// This program will write code that imports an incomplete sudoku
// puzzle from a file and prints the board to the screen in a particular way.

public class PlaySudoku {
    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard("data1.sdk");
        System.out.println(board); //print board
    }
}