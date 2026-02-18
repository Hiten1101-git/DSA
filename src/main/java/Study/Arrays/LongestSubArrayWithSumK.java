package Study.Arrays;

public class LongestSubArrayWithSumK {

    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] nums = {10, 5, 2, 7, 1, 9};
        int k = 15;
        int length = obj.longestSubarray(nums, k);
        System.out.println("Length of longest subarray: " + length);
    }

    static class Solution {
        public int longestSubarray(int[] nums, int k) {
            return twoPointer(nums, k);
        }

        /**
         TC: O(n)
         SC: O(1)
         ~ Even though there is a nested while, each element is added and removed from sum at most once.
         */
        private int twoPointer(int[] nums, int target) {
            int n = nums.length;
            int res = 0;
            int l = 0, r = 0;
            int sum = nums[0];

            while (r < n) {
                while (l <= r && sum > target) {
                    sum -= nums[l];
                    l++;
                }

                if (sum == target) res = Math.max(res, r - l + 1);

                r++;
                if (r < n) sum += nums[r];
            }

            return res;
        }

        /**
         TC: O(n^3)
         SC: O(1)
         */
        private int bruteForce(int[] nums, int target) {
            int n = nums.length;
            int res = 0;
            for (int i = 0 ; i < n ; i++) {
                for (int j = i ; j < n ; j++) {
                    int sum = 0;
                    for (int k = i ; k <= j ; k++) {
                        sum += nums[k];
                    }
                    if (sum == target) res = Math.max(res, j - i + 1);
                }
            }

            return res;
        }
    }
}
