package Study.SlidingWindow;

/**
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray
 * whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 */
public class MinSizeSubArray {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 3, 1, 2, 4, 3};
        int target = 7;
        int result = solution.minSubArrayLen(target, nums);
        System.out.println("Minimum size of subarray: " + result); // Output: 2
    }

    static class Solution {
        /**
         A sliding window problem
         calculating cur sum and min len, for achieving the desired result.
         Time complexity: O(n)
         Space complexity: O(1)
         */
        public int minSubArrayLen(int target, int[] nums) {
            int minLen = nums.length + 1;
            int left = 0;
            int curSum = 0;

            for (int right = 0 ; right < nums.length ; right++) {
                curSum += nums[right];
                while (curSum >= target) {
                    minLen = Math.min(right - left + 1, minLen);
                    curSum -= nums[left];
                    left++;
                }
            }

            return minLen == nums.length + 1 ? 0 : minLen;
        }
    }
}
