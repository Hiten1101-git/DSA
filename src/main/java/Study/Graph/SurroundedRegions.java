package Study.Graph;

import java.util.LinkedList;
import java.util.Queue;

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
        private int[] delRow = {-1, 0, 1, 0};
        private int[] delCol = {0, 1, 0, -1};
        private int[][] vis;

        public void solve(char[][] board) {
            this.board = board;
            this.n = board.length;
            this.m = board[0].length;

            // this is for bfs and dfs impl
            this.vis = new int[board.length][board[0].length];

            betterDFS();
        }

        private final int[][] DIRECTIONS = {
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };
        private final char X = 'X';
        private final char O = 'O';
        private final char TEMP = 'T';
        private char[][] board;
        private int n;
        private int m;

        private void betterDFS() {
            replaceAll(O, TEMP);

            for (int row = 0; row < n; row++) {
                betterDFS(row, 0);
                betterDFS(row, m - 1);
            }

            for (int col = 1; col < m - 1; col++) {
                betterDFS(0, col);
                betterDFS(n - 1, col);
            }

            replaceAll(TEMP, X);
        }

        private void replaceAll(char from, char to) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == from) board[i][j] = to;
                }
            }
        }

        private void betterDFS(int row, int col) {
            if (row < 0 || col < 0 || row >= n || col >= m) return;
            if (board[row][col] != TEMP) return;

            board[row][col] = O;
            for (int[] dir: DIRECTIONS) {
                betterDFS(row + dir[0], col + dir[1]);
            }
        }

        // BFS implementation

        private void bfs() {
            Queue<int[]> q = new LinkedList<>();

            for (int i = 0 ; i < m ; i++) {
                if (vis[0][i] == 0 && board[0][i] == 'O') {
                    q.offer(new int[]{0, i});
                    vis[0][i] = 1;
                }
                if (vis[n-1][i] == 0 && board[n-1][i] == 'O') {
                    q.offer(new int[]{n-1, i});
                    vis[n-1][i] = 1;
                }
            }

            for (int i = 0; i < n; i++) {
                // first column
                if (board[i][0] == 'O' && vis[i][0] == 0) {
                    q.add(new int[]{i, 0});
                    vis[i][0] = 1;
                }
                // last column
                if (board[i][m-1] == 'O' && vis[i][m-1] == 0) {
                    q.add(new int[]{i, m-1});
                    vis[i][m-1] = 1;
                }
            }

            // Step 2: BFS to mark all connected 'O's
            while (!q.isEmpty()) {
                int[] cell = q.poll();
                int row = cell[0];
                int col = cell[1];

                for (int i = 0; i < 4; i++) {
                    int nrow = row + delRow[i];
                    int ncol = col + delCol[i];

                    if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m &&
                            board[nrow][ncol] == 'O' && vis[nrow][ncol] == 0) {
                        q.add(new int[]{nrow, ncol});
                        vis[nrow][ncol] = 1;
                    }
                }
            }

            // Step 3: Flip all unvisited 'O' to 'X'
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 'O' && vis[i][j] == 0) {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        // DFS implementation

        private void dfs() {
            for (int i = 0 ; i < m ; i++) {
                // first row
                if (vis[0][i] == 0 && board[0][i] == 'O') {
                    dfs(0, i);
                }
                // last row
                if (vis[n-1][i] == 0 && board[n-1][i] == 'O') {
                    dfs(n-1, i);
                }
            }

            for (int j = 0 ; j < n ; j++) {
                // first column
                if (vis[j][0] == 0 && board[j][0] == 'O') {
                    dfs(j, 0);
                }
                // last column
                if (vis[j][m-1] == 0 && board[j][m-1] == 'O') {
                    dfs(j, m-1);
                }
            }

            for (int i = 0 ; i < n ; i++) {
                for (int j = 0 ; j < m ; j++) {
                    if (vis[i][j] == 0 && board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        private void dfs(int row, int col) {
            vis[row][col] = 1;
            int n = board.length;
            int m = board[0].length;

            for (int i = 0 ; i < 4 ; i++) {
                int nrow = row + delRow[i];
                int ncol = col + delCol[i];

                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m
                        && vis[nrow][ncol] == 0 && board[nrow][ncol] == 'O') {
                    dfs(nrow, ncol);
                }
            }
        }
    }
}
