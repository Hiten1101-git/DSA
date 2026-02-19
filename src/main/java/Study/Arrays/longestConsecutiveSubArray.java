package Study.Arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers, nums, return the length of the longest consecutive elements sequence.
 * The consecutive elements sequence is a sequence of integers where each integer is one more than the previous integer.
 * The longest consecutive elements sequence is the longest such sequence that can be found in nums.
 * .
 * Example 1
 * Input: nums = [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Thus its length is 4.
 */
public class longestConsecutiveSubArray {

    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] nums = {100, 4, 200, 1, 3, 2};
        int longest = obj.longestConsecutive(nums);
        System.out.println("Longest consecutive sequence length: " + longest);
    }

    static class Solution {
        public int longestConsecutive(int[] nums) {
            return optimal(nums);
        }

        /**
         Time: O(n^3)
         Space: O(1)
         */
        private int bruteForce(int[] nums) {
            if (nums.length == 0) return 0;
            int n = nums.length;
            int longest = 1;

            for (int i = 0 ; i < n ; i++) {
                int x = nums[i];
                int cnt = 1;
                while (linearSearch(nums, x + 1) == true) {
                    x += 1;
                    cnt += 1;
                }

                longest = Math.max(longest, cnt);
            }

            return longest;
        }

        /**
         Time: O(nlogn) + O(n)
         Space: O(1)
         */
        private int sortingApproach(int[] nums) {
            int n = nums.length;
            if (n == 0) return 0;

            Arrays.sort(nums);

            int lastSmaller = Integer.MIN_VALUE;
            int cnt = 0;
            int longest = 1;

            for (int i = 0 ; i < n ; i++) {
                if (nums[i] - 1 == lastSmaller) {
                    cnt += 1;
                    lastSmaller = nums[i];
                } else if (nums[i] != lastSmaller) {
                    cnt = 1;
                    lastSmaller = nums[i];
                }

                longest = Math.max(longest, cnt);
            }

            return longest;
        }

        /**
         Time: O(n) + O(2n) = O(3n)
         Space: O(n)
         */
        private int optimal(int[] nums) {
            int n = nums.length;
            if (n == 0) return 0;

            int longest = 1;
            Set<Integer> st = new HashSet<>();

            for (int i = 0 ; i < n ; i++) {
                st.add(nums[i]);
            }

            for (int it : st) {
                if (!st.contains(it - 1)) {
                    int cnt = 1;
                    int x = it;

                    while (st.contains(x + 1)) {
                        x += 1;
                        cnt += 1;
                    }

                    longest = Math.max(longest, cnt);
                }
            }

            return longest;
        }

        // HELPER METHOD
        private boolean linearSearch(int[] nums, int num) {
            int n = nums.length;
            for (int i = 0 ; i < n ; i++) {
                if (nums[i] == num) return true;
            }

            return false;
        }
    }
}
