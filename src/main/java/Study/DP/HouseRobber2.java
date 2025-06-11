package Study.DP;

import java.util.Arrays;

public class HouseRobber2 {
    public static void main(String[] args) {
        int[] nums = {2, 3, 2};
        System.out.println(new Solution().rob(nums)); // Output: 3

        nums = new int[]{1, 2, 3, 1};
        System.out.println(new Solution().rob(nums)); // Output: 4

        nums = new int[]{1, 2, 3};
        System.out.println(new Solution().rob(nums)); // Output: 3
    }

    static class Solution {
        public int rob(int[] nums) {
            // Recursion object = new Recursion();
            // TopDown object = new TopDown();
            // BottomUp object = new BottomUp();
            SpaceOptimised object = new SpaceOptimised();
            return object.rob(nums);
        }

        /**
         Time: O(2^n)
         Space: O(n)
         */
        static class Recursion {
            public int rob(int[] nums) {
                if (nums.length == 0) return 0;
                if (nums.length == 1) return nums[0];
                return Math.max(dfs(0, true, nums), dfs(1, false, nums));
            }

            private int dfs(int i, boolean flag, int[] nums) {
                if (i >= nums.length || (flag && i == nums.length - 1)) {
                    return 0;
                }

                return Math.max(
                        dfs(i + 1, flag, nums),
                        nums[i] + dfs(i + 2, flag || i == 0, nums)
                );
            }
        }

        /**
         Time: O(n)
         Space: O(n)
         */
        static class TopDown {
            private int[][] memo;
            public int rob(int[] nums) {
                if (nums.length == 0) return 0;
                if (nums.length == 1) return nums[0];
                memo = new int[nums.length][2];
                for (int i = 0 ; i < nums.length ; i++) {
                    memo[i][0] = -1;
                    memo[i][1] = -1;
                }

                return Math.max(dfs(0, 1, nums), dfs(1, 0, nums));
            }

            private int dfs(int i, int flag, int[] nums) {
                if (i >= nums.length || (flag == 1 && i == nums.length - 1)) return 0;
                if (memo[i][flag] != -1) return memo[i][flag];

                memo[i][flag] = Math.max(
                        dfs(i + 1, flag, nums),
                        nums[i] + dfs(i + 2, flag | (i == 0 ? 1 : 0), nums)
                );

                return memo[i][flag];
            }
        }

        /**
         Time: O(n)
         Space: O(n)
         */
        static class BottomUp {
            public int rob(int[] nums) {
                if (nums.length == 0) return 0;
                if (nums.length == 1) return nums[0];

                return Math.max(
                        helper(Arrays.copyOfRange(nums, 1, nums.length)),
                        helper(Arrays.copyOfRange(nums, 0, nums.length - 1))
                );
            }

            private int helper(int[] nums) {
                if (nums.length == 0) return 0;
                if (nums.length == 1) return nums[0];

                int[] dp = new int[nums.length];
                dp[0] = nums[0];
                dp[1] = Math.max(nums[0], nums[1]);

                for (int i = 2 ; i < nums.length ; i++) {
                    dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
                }

                return dp[nums.length - 1];
            }
        }

        /**
         Time: O(n)
         Space: O(1)
         */
        static class SpaceOptimised {
            public int rob(int[] nums) {
                return Math.max(
                        nums[0],
                        Math.max(
                                helper(Arrays.copyOfRange(nums, 1, nums.length)),
                                helper(Arrays.copyOfRange(nums, 0, nums.length - 1))
                        )
                );
            }

            private int helper(int[] nums) {
                int rob1 = 0, rob2 = 0;

                for (int num : nums) {
                    int newRob = Math.max(rob1 + num, rob2);
                    rob1 = rob2;
                    rob2 = newRob;
                }

                return rob2;
            }
        }
    }

}
