package Study.Graph;

import java.util.LinkedList;
import java.util.Queue;

/*
    You are given a 2-D matrix grid. Each cell can have one of three possible values:

    0 representing an empty cell
    1 representing a fresh fruit
    2 representing a rotten fruit

    Every minute, if a fresh fruit is horizontally or vertically adjacent to a rotten fruit, then the fresh fruit
    also becomes rotten.

    Return the minimum number of minutes that must elapse until there are zero fresh fruits remaining.
    If this state is impossible within the grid, return -1.
 */
public class RottenFruits {
    public static void main(String[] args) {
        Solution obj = new Solution();
        int[][] grid = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        System.out.println("Time taken to rot all oranges: " + obj.orangesRotting(grid));
    }

    static class Solution {
        private final int[][] directions = {{1,0}, {0,1}, {-1,0}, {0,-1}};

        /*
            Time complexity: O(m * n)
            Space complexity: O(m * n)
        */
        public int orangesRotting(int[][] grid) {
            if (grid == null || grid.length == 0) return -1;

            int n = grid.length;
            int m = grid[0].length;
            int freshCount = 0;
            Queue<int[]> q = new LinkedList<>();

            // Step 1: Initialize queue with rotten oranges and count fresh oranges
            for (int i = 0 ; i < n ; i++) {
                for (int j = 0 ; j < m ; j++) {
                    if (grid[i][j] == 2) {
                        q.offer(new int[]{i,j});
                    } else if (grid[i][j] == 1) {
                        freshCount++;
                    }
                }
            }

            if (freshCount == 0) return 0; // No fresh oranges to rot

            int minutes = 0;

            // Step 2: BFS traversal (level by level)
            while (!q.isEmpty()) {
                int size = q.size();
                boolean rotted = false;

                for (int i = 0; i < size; i++) {
                    int[] curr = q.poll();
                    int row = curr[0];
                    int col = curr[1];

                    for (int[] dir : directions) {
                        int nr = row + dir[0];
                        int nc = col + dir[1];

                        if (nr < 0 || nr >= n || nc < 0 || nc >= m || grid[nr][nc] != 1) continue;

                        grid[nr][nc] = 2;
                        q.offer(new int[]{nr, nc});
                        freshCount--;
                        rotted = true;
                    }
                }

                if (rotted) minutes++;  // only increase time if at least one orange was rotted
            }

            return freshCount == 0 ? minutes : -1;
        }
    }
}
