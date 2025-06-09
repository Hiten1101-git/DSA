package Study.DP;

import java.util.Arrays;

/**
 * Minimum Cost to Climb Stairs.
 * .
 * You are given an array of integers cost where cost[i] is the cost of taking a step from the ith floor of a staircase.
 * After paying the cost, you can step to either the (i + 1)th floor or the (i + 2)th floor.
 * You may choose to start at the index 0 or the index 1 floor.
 * Return the minimum cost to reach the top of the staircase, i.e. just past the last index in cost.
 * .
 * Example:
 * Input: cost = [10, 15, 20]
 * Output: 15
 * Explanation: Start at index 1, pay 15, and then step to the top.
 */
public class MinCostToClimbStairs {
    public static void main(String[] args) {
        int[] cost = {10, 15, 20};
        minCostClimbingStairs(cost);
    }

    public static void minCostClimbingStairs(int[] cost) {
        // TopDown object = new TopDown();
        // Recursion object = new Recursion();
        // BottomUp object = new BottomUp();
        SpaceOptimised object = new SpaceOptimised();
        int minCost = object.minCost(cost);
        System.out.println("Minimum cost to climb stairs: " + minCost);
    }

    static class TopDown {
        int[] memo;
        public int minCost(int[] cost) {
            memo = new int[cost.length];
            Arrays.fill(memo, -1);
            return Math.min(dfs(cost, 0), dfs(cost, 1));
        }

        private int dfs(int[] cost, int i) {
            if (i >= cost.length) return 0;
            if (memo[i] != -1) return memo[i];
            memo[i] = cost[i] + Math.min(dfs(cost, i + 1), dfs(cost, i + 2));
            return memo[i];
        }
    }

    static class Recursion {
        public int minCost(int[] cost) {
            return Math.min(dfs(cost, 0), dfs(cost, 1));
        }

        private int dfs(int[] cost, int i) {
            if (i >= cost.length) return 0;
            return cost[i] + Math.min(dfs(cost, i + 1), dfs(cost, i + 2));
        }
    }

    static class BottomUp {
        public int minCost(int[] cost) {
            int n = cost.length;
            int[] dp = new int[n + 1];

            for (int i = 2 ; i <= n ; i++) {
                dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]);
            }
            return dp[n];
        }
    }

    static class SpaceOptimised {
        public int minCost(int[] cost) {
            for (int i = cost.length - 3 ; i >= 0 ; i--) {
                cost[i] += Math.min(cost[i + 1], cost[i + 2]);
            }
            return Math.min(cost[0], cost[1]);
        }
    }
}
