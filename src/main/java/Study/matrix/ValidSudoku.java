package Study.matrix;

import java.util.HashSet;
import java.util.Set;

/**
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according
 * to the following rules:
 *.
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * Note:
 *.
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 * .
 * Input: board =
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: true
 * .
 * Input: board =
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8.
 * Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 */
public class ValidSudoku {
    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '6', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '2', '1', '9', '.', '.', '.'},
                {'.', '6', '.', '.', '.', '5', '9', '8', '.'},
                {'.', '5', '9', '7', '4', '2', '.', '.', '.'},
                {'.', '4', '1', '8', '5', '3', '.', '7'}
        };
        System.out.println(new ValidSudoku().isValidSudoku(board));
    }

    /**
     Make a hashset of string, and keep on checking if we can add the number
     while iterating through the matrix

     Time: O(9) * O(9) = O(81) i.e O(1)
     Space: O(1)
     */
    public boolean isValidSudoku(char[][] board) {
        Set<String> validSet = new HashSet<>();
        for (int i = 0 ; i < 9 ; i++) {
            for (int j = 0 ; j < 9 ; j++) {
                char number = board[i][j];
                if (number != '.' && (
                        !validSet.add(number + " in row " + i) ||
                                !validSet.add(number + " in column " + j) ||
                                !validSet.add(number + " in block " + i/3 + "-" + j/3)
                )) {
                    return false;
                }
            }
        }

        return true;
    }
}
