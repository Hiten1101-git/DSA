package Study.Graph;

import java.util.*;

/*
    You are given a rectangular island heights where heights[r][c] represents the height above sea level of the cell
    at coordinate (r, c).

    The islands border the Pacific Ocean from the top and left sides, and borders the Atlantic Ocean from the bottom
    and right sides.

    Water can flow in four directions (up, down, left, or right) from a cell to a neighboring cell with height equal
    or lower. Water can also flow into the ocean from cells adjacent to the ocean.

    Find all cells where water can flow from that cell to both the Pacific and Atlantic oceans. Return it as a 2D
    list where each element is a list [r, c] representing the row and column of the cell. You may return the answer
    in any order.
 */
public class PacificAtlanticWaterFlow {
    public static void main(String[] args) {
        Solution obj = new Solution();
        int[][] heights = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };

        List<List<Integer>> result = obj.pacificAtlantic(heights);
        System.out.println("Result: " + result);
    }

    static class Solution {
        private final int[][] directions = {{1,0}, {0,1}, {-1,0}, {0,-1}};

        private final List<List<Integer>> result = new ArrayList<>();
        private int ROWS;
        private int COLS;
        boolean[][] pac;
        boolean[][] atl;
        int[][] heights;

        public List<List<Integer>> pacificAtlantic(int[][] heights) {
            if (heights == null || heights.length == 0) return result;

            this.heights = heights;
            this.ROWS = heights.length;
            this.COLS = heights[0].length;
            this.pac = new boolean[ROWS][COLS];
            this.atl = new boolean[ROWS][COLS];

            return bfs();
        }

        /*
            Time complexity: O(m * n)
            Space complexity: O(m * n)
        */
        private List<List<Integer>> bfs() {
            Queue<int[]> pacQ = new LinkedList<>();
            Queue<int[]> atlQ = new LinkedList<>();

            for (int j = 0 ; j < COLS ; j++) {
                pacQ.add(new int[]{0, j});
                atlQ.add(new int[]{ROWS - 1, j});
            }
            for (int i = 0 ; i < ROWS ; i++) {
                pacQ.add(new int[]{i, 0});
                atlQ.add(new int[]{i, COLS - 1});
            }

            bfs(pacQ, pac);
            bfs(atlQ, atl);

            // add to the result list
            for (int i = 0 ; i < ROWS ; i++) {
                for (int j = 0 ; j < COLS ; j++) {
                    if (pac[i][j] && atl[i][j]) result.add(Arrays.asList(i, j));
                }
            }

            return result;
        }

        private void bfs(Queue<int[]> q, boolean[][] ocean) {
            while (!q.isEmpty()) {
                int[] curr = q.poll();
                int r = curr[0];
                int c = curr[1];

                // mark as visited
                ocean[r][c] = true;

                // iterate for all the directions
                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    /*
                        In this problem, BFS is reversed: Instead of starting at each cell and
                        trying to go to the ocean, we start from the ocean edges and mark all
                        cells that can flow to the ocean (i.e., where water can come from).
                    */
                    if (nr < 0 || nr >= ROWS || nc < 0 || nc >= COLS || ocean[nr][nc] || heights[nr][nc] < heights[r][c]) continue;

                    q.offer(new int[]{nr, nc});
                }
            }
        }

        /*
            Time complexity: O(m * n)
            Space complexity: O(m * n)
        */
        private List<List<Integer>> dfs() {
            for (int i = 0 ; i < ROWS ; i++) {
                dfs(i, 0, pac);
                dfs(i, COLS - 1, atl);
            }
            for (int j = 0 ; j < COLS ; j++) {
                dfs(0, j, pac);
                dfs(ROWS - 1, j, atl);
            }

            // add to the result list
            for (int i = 0 ; i < ROWS ; i++) {
                for (int j = 0 ; j < COLS ; j++) {
                    if (pac[i][j] && atl[i][j]) result.add(Arrays.asList(i, j));
                }
            }

            return result;
        }

        private void dfs(int r, int c, boolean[][] ocean) {
            // mark as visited
            ocean[r][c] = true;

            // iterate for all the directions
            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                /*
                    In this problem, DFS is reversed: Instead of starting at each cell and
                    trying to go to the ocean, we start from the ocean edges and mark all
                    cells that can flow to the ocean (i.e., where water can come from).
                */
                if (nr < 0 || nr >= ROWS || nc < 0 || nc >= COLS || ocean[nr][nc] || heights[nr][nc] < heights[r][c]) continue;

                dfs(nr, nc, ocean);
            }
        }
    }

}
