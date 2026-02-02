package Study.Arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode Problem 1: Two Sum
 *.
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 *.
 * Example:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 */
public class TwoSum {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2,7,11,15};
        int target = 9;
        int[] result = solution.twoSum(nums, target);
        System.out.println("Indices: " + Arrays.toString(result));
    }

    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            return sortingAndSearching(nums, target);
        }

        /**
         TC: O(nlogn)
         SC: O(n)
         ~ sort first, and then search for the solution
         */
        private int[] sortingAndSearching(int[] nums, int target) {
            int n = nums.length;
            int[][] numsWithIndex = new int[n][2];

            for (int i = 0 ; i < n ; i++) {
                numsWithIndex[i][0] = nums[i];
                numsWithIndex[i][1] = i;
            }

            // sort
            Arrays.sort(numsWithIndex, (a, b) -> Integer.compare(a[0], b[0]));

            int l = 0, r = n - 1;
            while (l < r) {
                int sum = numsWithIndex[l][0] + numsWithIndex[r][0];
                if (sum == target) return new int[]{numsWithIndex[l][1], numsWithIndex[r][1]};
                else if (sum < target) l++;
                else r--;
            }

            return new int[]{-1,-1};
        }

        /**
         TC: O(n)
         SC: O(n)
         ~ map approach, we use complement to find keys in map
         */
        private int[] mapSolution(int[] nums, int target) {
            Map<Integer, Integer> hm = new HashMap<>();

            for(int i = 0 ; i < nums.length ; i++) {
                int complement = target - nums[i];
                if (hm.containsKey(complement)) {
                    return new int[]{hm.get(complement), i};
                }
                hm.put(nums[i], i);
            }

            return new int[]{-1,-1};
        }

        /**
         TC: O(n^2)
         SC: O(1)
         ~ brute force, easiest solution, least optimised
         */
        private int[] bruteForce(int[] nums, int target) {
            for (int i = 0 ; i < nums.length - 1 ; i++) {
                for (int j = i + 1 ; j < nums.length ; j++) {
                    if (nums[i] + nums[j] == target) {
                        return new int[]{i, j};
                    }
                }
            }

            return new int[]{-1,-1};
        }
    }
}
