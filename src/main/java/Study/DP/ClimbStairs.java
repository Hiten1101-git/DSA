package Study.DP;

import java.util.Arrays;

/**
 * Climb Stairs Problem.
 * You are given an integer n representing the number of steps to reach the top of a staircase.
 * You can climb with either 1 or 2 steps at a time.
 * Return the number of distinct ways to climb to the top of the staircase.
 */
public class ClimbStairs {
    public static void main(String[] args) {
        Solution obj = new Solution();
        int n = 5; // Example input
        int ways = obj.climbStairs(n);
        System.out.println("Number of distinct ways to climb " + n + " steps: " + ways);
    }

    static class Solution {
        public int climbStairs(int n) {
            // return recursion(n, 0);
            // return topDown(n);
            return spaceOptimised(n);
        }

        /**
         Time complexity: O(2^n)
         Space complexity: O(n)
         */
        private int recursion(int n, int i) {
            if (i >= n) return i == n ? 1 : 0;
            return recursion(n, i + 1) + recursion(n, i + 2);
        }

        /**
         Time complexity: O(n)
         Space complexity: O(n)
         */
        private int topDown(int n) {
            int[] dp = new int[n];
            Arrays.fill(dp, -1);
            return dfs(n, 0, dp);
        }

        private int dfs(int n, int i, int[] dp) {
            if (i >= n) return i == n ? 1 : 0;
            if (dp[i] != -1) return dp[i];
            return dp[i] = dfs(n, i + 1, dp) + dfs(n, i + 2, dp);
        }

        /**
         considering the number of ways to reach to the solution,
         we will try and store (memorize) the repeated ways in a dp array.
         In bottom-up approach, we will start with the base case and reach to the main.

         Eg: n = 5
         0 -> 1 -> 2 -> 3 -> 4 -> 5
         ways to reach from n and n-1 (i.e 5 and 4) is 1. Hence we initialize.
         We will calculate other ways by adding the number of ways till 0.

         Time complexity: O(n)
         Space complexity: O(n)
         */
        private int bottomUp(int n) {
            if (n <= 2) return n;

            int[] dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3 ; i <= n ; i++) {
                dp[i] = dp[i-1] + dp[i-2];
            }

            return dp[n];
        }

        /**
         Same as bottom up approach but optimsed in space.

         Time complexity: O(n)
         Space complexity: O(1)
         */
        private int spaceOptimised(int n) {
            int one = 1, two = 1;
            for (int i = 0 ; i < n - 1 ; i++) {
                int temp = one;
                one = one + two;
                two = temp;
            }
            return one;
        }
    }
}
