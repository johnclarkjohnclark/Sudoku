// John Clark
// CS 143
// HW #2: Sudoku #2 (Board Setup)
//
// This program will write code that imports an incomplete sudoku
// puzzle from a file and prints the board to the screen in a particular way.
// It can also determine if the board is valid or solved.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SudokuBoard {
    private int[][] board;

    public SudokuBoard(String filename) {
        board = new int[9][9]; //board
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            for (int row = 0; row < 9; row++) {
                String curRow = fileScanner.nextLine();
                for (int col = 0; col < 9; col++) {
                    if (curRow.charAt(col) == '.') {
                        board[row][col] = 0;
                    } else {
                        board[row][col] = Character.getNumericValue(curRow.charAt(col));
                    }
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find " + filename);
        }
    }

    //pre: there is no existing string to show the board
    //post: A string will be returned which shows the output of the Sudoku board
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

    //pre: there is no way to determine if a voard is valud or not
    //post: a boolean value will exist to determine whether or not a board is valid
    public boolean isValid() {
        // Check rows and columns
        for (int i = 0; i < 9; i++) {
            Set<Integer> rows = new HashSet<>();
            Set<Integer> cols = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                //check the rows to see if there are any invalid values
                if (board[i][j] != 0) {
                    if (board[i][j] < 1 || board[i][j] > 9 || rows.contains(board[i][j])){
                        return false;
                    }
                    rows.add(board[i][j]);
                }
                //check the columns to see if there are any invalid values
                if (board[j][i] != 0) {
                    if (board[j][i] < 1 || board[j][i] > 9 || cols.contains(board[j][i])){
                        return false;
                    }
                    cols.add(board[j][i]);
                }
            }
        }
        for (int i = 0; i < 9; i +=3) {
            for (int j = 0; j < 9; j+=3) {
                Set<Integer> s = new HashSet<>();
                for (int inner_i = i; inner_i < i+3; inner_i++) {
                    for (int inner_j = j; inner_j < j + 3; inner_j++) {
                        if (board[inner_i][inner_j] != 0) {
                            if (s.contains(board[inner_i][inner_j])){
                               return false;
                            }
                            s.add(board[inner_i][inner_j]);
                        }
                    }
                }
            }
        }

        return true;
    }

    //pre: There will not be an indicator showing whether or not the board has been solved
    //post: There will be a boolean value showing TRUE for solved, FALSE for not solved
    public boolean isSolved() {
        if (!isValid()){
            return false;
        }

        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 1; i <= 9; i++) {
            counts.put(i, 0);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!(board[i][j] >= 1 && board[i][j] <= 9)){
                   //either board is empty or invalid
                   return false;
                }
                counts.put(board[i][j], counts.get(board[i][j])+1);
            }
        }

        for (int count : counts.values()) {
            if (count != 9){
                return false;
            }
        }

        return true;
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