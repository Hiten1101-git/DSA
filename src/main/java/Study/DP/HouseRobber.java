package Study.DP;

import java.util.Arrays;

/**
 * House Robber Problem.
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, and you cannot rob two adjacent houses.
 * Return the maximum amount of money you can rob tonight without alerting the police.
 */
public class HouseRobber {
    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] nums = {2, 7, 9, 3, 1}; // Example input
        int maxAmount = obj.rob(nums);
        System.out.println("Maximum amount that can be robbed: " + maxAmount);
    }

    static class Solution {
        public int rob(int[] nums) {
            return topDown(nums);
        }

        private int bottomUp(int[] nums) {
            if (nums.length == 0) return 0;
            if (nums.length == 1) return nums[0];

            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);

            for (int i = 2; i < nums.length; i++) {
                dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
            }

            return dp[nums.length - 1];
        }

        private int spaceOptimised(int[] nums) {
            int rob1 = 0, rob2 = 0;
            for (int num : nums) {
                int temp = Math.max(num + rob1, rob2);
                rob1 = rob2;
                rob2 = temp;
            }

            return rob2;
        }

        private int[] memo;
        private int topDown(int[] nums) {
            memo = new int[nums.length];
            Arrays.fill(memo, -1);
            return dfs(nums, 0);
        }

        private int dfs(int[] nums, int i) {
            if (i >= nums.length) return 0;
            if (memo[i] != -1) return memo[i];
            memo[i] = Math.max(dfs(nums, i + 1), nums[i] + dfs(nums, i + 2));
            return memo[i];
        }
    }

}
