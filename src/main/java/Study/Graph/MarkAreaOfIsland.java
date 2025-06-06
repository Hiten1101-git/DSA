package Study.Graph;

import java.util.LinkedList;
import java.util.Queue;

public class MarkAreaOfIsland {
    public static void main(String[] args) {
        Solution obj = new Solution();
        int[][] grid = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 0}
        };
        System.out.println("Max area of island: " + obj.maxAreaOfIsland(grid));
    }

    static class Solution {
        private int result = 0;
        private final int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        private int[][] grid;
        private int n;
        private int m;

        public int maxAreaOfIsland(int[][] grid) {
            if (grid == null || grid.length == 0) return result;

            this.grid = grid;
            this.n = grid.length;
            this.m = grid[0].length;

            approach();

            return result;
        }

        private void approach() {
            for (int i = 0 ; i < n ; i++) {
                for (int j = 0 ; j < m ; j++) {
                    if (grid[i][j] == 1) {
                        int approachResult = bfs(i, j);
                        result = Math.max(result, approachResult);
                    }
                }
            }
        }

        private int dfs(int i, int j) {
            if (i < 0 || i >= n || j < 0 || j >= m || grid[i][j] != 1) return 0;

            // mark as visited
            grid[i][j] = 0;

            // calculate the area through recursive calls
            int area = 1;
            for (int[] dir : directions) {
                area += dfs(i + dir[0], j + dir[1]);
            }

            return area;
        }

        private int bfs(int i, int j) {
            Queue<int[]> q = new LinkedList<>();

            // mark as visited
            grid[i][j] = 0;
            q.offer(new int[]{i, j});
            int res = 1;

            while (!q.isEmpty()) {
                int[] curr = q.poll();
                int row = curr[0], col = curr[1];

                for (int[] dir : directions) {
                    int nr = row + dir[0], nc = col + dir[1];
                    if (nr >= 0 && nc >= 0 && nr < grid.length &&
                            nc < grid[0].length && grid[nr][nc] == 1) {
                        q.add(new int[]{nr, nc});
                        grid[nr][nc] = 0;
                        res++;
                    }
                }
            }

            return res;
        }
    }
}
