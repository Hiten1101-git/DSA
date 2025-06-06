package Study.Graph;

import java.util.LinkedList;
import java.util.Queue;

/*
    You are given a "m×n" 2D grid initialized with these three possible values:

    * -1    - A water cell that can not be traversed.
    * 0     - A treasure chest.
    * INF   - A land cell that can be traversed. We use the integer 2^31 - 1 = 2147483647 to represent INF.
    Fill each land cell with the distance to its nearest treasure chest. If a land cell cannot reach a treasure chest than the value should remain INF.

    Assume the grid can only be traversed up, down, left, or right.
    Modify the grid in-place.
 */
public class IslandAndTreasures {
    public static void main(String[] args) {
        Solution obj = new Solution();
        int[][] grid = {
                {2147483647, -1, 0, 2147483647},
                {2147483647, 2147483647, 2147483647, -1},
                {2147483647, -1, 2147483647, -1},
                {0, -1, 2147483647, 2147483647}
        };

        obj.islandsAndTreasure(grid);

        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    static class Solution {
        private final int[][] directions = {{1,0}, {0,1}, {-1,0}, {0,-1}};
        private final int INF = 2147483647;
        private boolean[][] vis;

        private int n;
        private int m;

        public void islandsAndTreasure(int[][] grid) {
            if (grid == null || grid.length == 0) return;

            this.n = grid.length;
            this.m = grid[0].length;

            bfs(grid);
        }

        /*
            Time: O(m * n * 4^(m*n))
            Space: O(m * n)
        */
        private void dfs(int[][] grid) {
            this.vis = new boolean[n][m];
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    if (grid[r][c] == INF) {
                        grid[r][c] = dfs(grid, r, c);
                    }
                }
            }
        }

        private int dfs(int[][] grid, int r, int c) {
            if (r < 0 || r >= n || c < 0 || c >= m || grid[r][c] == -1 || vis[r][c]) return INF;

            if (grid[r][c] == 0) return 0;

            vis[r][c] = true;
            int res = INF;
            for (int[] dir : directions) {
                int cur = dfs(grid, r + dir[0], c + dir[1]);
                if (cur != INF) res = Math.min(res, 1 + cur);
            }

            vis[r][c] = false;
            return res;
        }

        /*
            Time: O(m * n)
            Space: O(m * n)
        */
        private void bfs(int[][] grid) {
            Queue<int[]> queue = new LinkedList<>();

            // Step 1: Add all treasures (value = 0) to the queue
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    if (grid[r][c] == 0) {
                        queue.offer(new int[]{r, c});
                    }
                }
            }

            // Step 2: BFS from each treasure
            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int r = cell[0];
                int c = cell[1];

                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    // Skip out-of-bounds or treasure or already visited
                    if (nr < 0 || nr >= n || nc < 0 || nc >= m || grid[nr][nc] != INF) continue;

                    // Update the distance and add to queue
                    grid[nr][nc] = grid[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
    }
}
