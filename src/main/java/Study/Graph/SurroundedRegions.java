package Study.Graph;

import java.util.LinkedList;
import java.util.Queue;

/*
    * Surrounded Regions

    You are given a 2-D matrix board containing 'X' and 'O' characters.
    If a continuous, four-directionally connected group of 'O's is surrounded by 'X's, it is considered to be surrounded.
    Change all surrounded regions of 'O's to 'X's and do so in-place by modifying the input board.
 */
public class SurroundedRegions {
    public static void main(String[] args) {
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };

        Solution obj = new Solution();
        obj.solve(board);

        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    static class Solution {
        private final char O = 'O';
        private final char X = 'X';
        private final char T = 'T';

        private char[][] board;
        private int n;
        private int m;

        private final int[][] directions = {{0,1}, {1,0}, {-1,0}, {0,-1}};

        public void solve(char[][] board) {
            if (board == null || board.length == 0) return;

            this.board = board;
            this.n = board.length;
            this.m = board[0].length;

            dfs();
        }

        /**
         * BFS approach
         */
        private void bfs() {
            Queue<int[]> q = new LinkedList<>();

            // Step 1: Iterate through all the edges. Find for 'O's and offer to the Queue.
            for (int j = 0 ; j < m ; j++) {
                if (board[0][j] == O) q.offer(new int[]{0, j});
                if (board[n-1][j] == O) q.offer(new int[]{n - 1, j});
            }
            for (int i = 0 ; i < n ; i++) {
                if (board[i][0] == O) q.offer(new int[]{i, 0});
                if (board[i][m - 1] == O) q.offer(new int[]{i, m - 1});
            }

            // Step 2: BFS to mark all edged 'O's as 'T'
            while (!q.isEmpty()) {
                int[] curr = q.poll();
                int row = curr[0];
                int col = curr[1];

                if (row < 0 || row >= n || col < 0 || col >= m || board[row][col] != O) continue;

                board[row][col] = T;

                for (int[] dir : directions) {
                    q.add(new int[]{row + dir[0], col + dir[1]});
                }
            }

            // Step 3: Flip all 'O' to 'X' and 'T' to 'O'.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == O) {
                        board[i][j] = X;
                    } else if (board[i][j] == T) {
                        board[i][j] = O;
                    }
                }
            }
        }

        /**
         * DFS approach
         */
        private void dfs() {
            replace(O, T);

            for (int i = 0 ; i < n ; i++) {
                dfs(i, 0);
                dfs(i, m - 1);
            }

            for (int j = 1 ; j < m - 1 ; j++) {
                dfs(0, j);
                dfs(n - 1, j);
            }

            replace(T, X);
        }

        private void replace(char x, char y) {
            for (int i = 0 ; i < n ; i++) {
                for (int j = 0 ; j < m ; j++) {
                    if (board[i][j] == x) board[i][j] = y;
                }
            }
        }

        private void dfs(int start, int end) {
            if (start < 0 || start >= n || end < 0 || end >= m || board[start][end] != T) return;

            board[start][end] = O;
            for (int[] dir : directions) {
                dfs(start + dir[0], end + dir[1]);
            }
        }
    }
}
