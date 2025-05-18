package Study.matrix;

import Study.Helper;

/**
 * Game of Life.
 * According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton
 * devised by the British mathematician John Horton Conway in 1970."
 *.
 * The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1)
 * or dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal)
 * using the following four rules (taken from the above Wikipedia article):
 *      - Any live cell with fewer than two live neighbors dies as if caused by under-population.
 *      - Any live cell with two or three live neighbors lives on to the next generation.
 *      - Any live cell with more than three live neighbors dies, as if by over-population.
 *      - Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * .
 * The next state of the board is determined by applying the above rules simultaneously to every cell in the
 * current state of the m x n grid board. In this process, births and deaths occur simultaneously.
 *.
 * Given the current state of the board, update the board to reflect its next state.
 *.
 * Note that you do not need to return anything.
 */
public class GameOfLife {
    public static void main(String[] args) {
        GameOfLife gameOfLife = new GameOfLife();
        Helper helper = new Helper();
        int[][] board = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        helper.printMatrix(board, "Input matrix: ");
        gameOfLife.gameOfLife(board);
        helper.printMatrix(board, "Output matrix: ");
    }

    private void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;

        for (int i = 0 ; i < m ; i++) {
            for (int j = 0 ; j < n ; j++) {
                // to return number of live neighbors
                int lives = liveNeighbors(board, m, n, i, j);

                if (board[i][j] == 1 && (lives == 2 || lives == 3)) {
                    board[i][j] = 3; // Make the 2nd bit 1: 01 ---> 11
                }
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 2; // Make the 2nd bit 1: 00 ---> 10
                }
            }
        }

        for (int i = 0 ; i < m ; i++) {
            for (int j = 0 ; j < n ; j++) {
                // the >>= 1 operator means “right shift by 1 bit, and assign
                // the result back to the variable”.
                board[i][j] >>= 1;
            }
        }
    }

    /**
     Each cell might temporarily store two bits of information together:
     •	1st bit (the least significant bit) = current state (alive or dead now)
     •	2nd bit = next state (alive or dead in the next step)

     State                  Bits        Decimal
     alive  ->  dead        01          1
     dead   ->  dead        00          0
     alive  ->  alive       11          3
     dead   ->  dead        10          2

     In binary, the last bit (the least significant bit) tells you current state.
     & 1 means: only keep the last bit.
     */
    private int liveNeighbors(int[][] board, int m, int n, int i, int j) {
        int lives = 0;
        for (int x = Math.max(i - 1, 0) ; x <= Math.min(i + 1, m - 1) ; x++) {
            for (int y = Math.max(j - 1, 0) ; y <= Math.min(j + 1, n - 1) ; y++) {
                lives += board[x][y] & 1;
            }
        }

        // ignore the element itself
        lives -= board[i][j] & 1;
        return lives;
    }
}
