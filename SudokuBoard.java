// John Clark
// CS 143
// HW #1: Sudoku #1 (Board Setup)
//
// This program will write code that imports an incomplete sudoku
// puzzle from a file and prints the board to the screen in a particular way.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class SudokuBoard {
    private int[][] board;

   // pre: "board" variable is null
   // post: "board" variable will be populated with data from a file whose name matches filename
    public SudokuBoard(String filename) {
        board = new int[9][9]; //board

        try {
            Scanner fileScanner = new Scanner(new File(filename));
            for (int row = 0; row < 9; row++) { //iterate thru each row
                String curRow = fileScanner.nextLine();
                for (int col = 0; col < 9; col++) { //iterate thru each column
                    if (curRow.charAt(col) == '.') {
                        board[row][col] = 0; //put 0 where there's a .
                    } else {
                        board[row][col] = Character.getNumericValue(curRow.charAt(col)); //if there's some number there, put that number in the board
                    }
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find " + filename);
        }
    }

    // pre: "board" variable exists but there will be no existing string to visualize the contents
    // post: a string will be returned which shows the contents of the "board" variable
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("+***********************+\n");
        output.append("|         SUDOKU        |\n");
        output.append("+***********************+\n");

        for (int row = 0; row < 9; row++) {
            if (row % 3 == 0 && row != 0) {
                output.append("+***********************+\n");
            }
            output.append("| ");
            for (int col = 0; col< 9; col++) {
                if (col%3 == 0 && col != 0) {
                    output.append("| ");
                }
                if (board[row][col] == 0) {
                    output.append(". ");
                } else {
                    output.append(board[row][col] + " ");
                }
            }
            output.append("|\n");
        }
        output.append("+***********************+\n");
        return output.toString();
    }
}

//output:
/*
  ----jGRASP exec: java PlaySudoku
 +***********************+
 |         SUDOKU        |
 +***********************+
 | 2 . . | 1 . 5 | . . 3 |
 | . 5 4 | . . . | 7 1 . |
 | . 1 . | 2 . 3 | . 8 . |
 +***********************+
 | 6 . 2 | 8 . 7 | 3 . 4 |
 | . . . | . . . | . . . |
 | 1 . 5 | 3 . 9 | 8 . 6 |
 +***********************+
 | . 2 . | 7 . 1 | . 6 . |
 | . 8 1 | . . . | 2 4 . |
 | 7 . . | 4 . 2 | . . 1 |
 +***********************+
 
 
  ----jGRASP: Operation complete.
*/