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
        NumberOfIslands obj = new NumberOfIslands();
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println("Number of islands: " + obj.numIslands(grid));
    }

    public int numIslands(char[][] grid) {
        return dfs(grid);
    }

    private int dfs(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int numOfIslands = 0;
        for (int i = 0 ; i < grid.length ; i++) {
            for (int j = 0 ; j < grid[0].length ; j++) {
                if (grid[i][j] == '1') {
                    numOfIslands++;
                    dfs(grid, i, j);
                }
            }
        }

        return numOfIslands;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') return;

        grid[i][j] = '0'; // mark as visited
        dfs(grid, i + 1, j); // down
        dfs(grid, i - 1, j); // up
        dfs(grid, i, j + 1); // right
        dfs(grid, i, j - 1); // left
    }

    private int bfs(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int numOfIslands = 0;
        int m = grid.length;
        int n = grid[0].length;

        // checks all directions around the node - (up, down, left, right).
        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        Queue<int[]> q = new LinkedList<>();

        for (int i = 0 ; i < m ; i++) {
            for (int j = 0 ; j < n ; j++) {
                if (grid[i][j] == '1') {
                    numOfIslands++;
                    q.offer(new int[]{i, j});

                    while (!q.isEmpty()) {
                        int[] currList = q.poll();
                        int x = currList[0];
                        int y = currList[1];

                        // if the node is water (0), skip
                        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != '1') continue;

                        grid[x][y] = '0'; // marked visited

                        // for all the directions, if land is present, keep on offering to the queue
                        for (int[] direction : directions) {
                            int nx = x + direction[0];
                            int ny = y + direction[1];
                            if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == '1') {
                                q.offer(new int[]{nx, ny});
                            }
                        }
                    }
                }
            }
        }

        return numOfIslands;
    }
}
