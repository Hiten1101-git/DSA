package Study.Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 *.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 */
public class NumberOfIslands {
    public static void main(String[] args) {
        Solution obj = new Solution();
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println("Number of islands: " + obj.numIslands(grid));
    }

    static class Solution {
        private int n;
        private int m;
        private char[][] grid;
        private int result;
        private final int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        /*
         * DFS and BFS both can be used to solve this problem.
         * The time complexity is O(n*m) for both approaches.
         * The space complexity is O(n*m) for DFS (due to recursion stack) and O(n*m) for BFS (due to queue).
         */
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) return 0;

            this.grid = grid;
            this.n = grid.length;
            this.m = grid[0].length;
            this.result = 0;

            // implementation method
            bfs();

            return result;
        }

        /**
         * DFS approach to count the number of islands.
         */
        private void dfs() {
            for (int i = 0 ; i < n ; i++) {
                for (int j = 0 ; j < m ; j++) {
                    if (grid[i][j] == '1') {
                        result++;
                        dfs(i, j);
                    }
                }
            }
        }

        /**
         * DFS helper function to explore the island.
         * @param i - first index
         * @param j - second index
         */
        private void dfs(int i, int j) {
            if (indexInvalid(i, j)) return;

            grid[i][j] = '0'; // mark this land as visited by setting to 0
            for (int[] dir : directions) { // iterate in all directions
                dfs(i + dir[0], j + dir[1]);
            }
        }

        /**
         * BFS approach to count the number of islands.
         */
        private void bfs() {
            Queue<int[]> q = new LinkedList<>();

            for (int i = 0 ; i < n ; i++) {
                for (int j = 0 ; j < m ; j++) {
                    if (grid[i][j] == '1') {
                        result++;
                        q.offer(new int[]{i, j});

                        while (!q.isEmpty()) {
                            int[] curr = q.poll();
                            int x = curr[0];
                            int y = curr[1];

                            if (indexInvalid(x, y)) continue;

                            grid[x][y] = '0'; // mark this land as visited by setting to 0

                            for (int[] dir : directions) { // iterate in all directions
                                q.offer(new int[]{x + dir[0], y + dir[1]});
                            }
                        }
                    }
                }
            }
        }

        /**
         * Check if the index is invalid (out of bounds or water)
         * @param x - first index
         * @param y - second index
         * @return true if invalid, false otherwise
         */
        private boolean indexInvalid(int x, int y) {
            return x < 0 || x >= n || y < 0 || y >= m || grid[x][y] != '1';
        }
    }
}
