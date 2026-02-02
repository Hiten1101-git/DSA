package Study.Arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 */
public class MajorityElement {

    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] nums = {2,2,1,1,1,3,2,2};
        int majorityElement = obj.majorityElement(nums);
        System.out.println("Majority Element: " + majorityElement);
    }

    static class Solution {
        public int majorityElement(int[] nums) {
            return mooreVotingAlgo(nums);
        }

        private int mapApproach(int[] nums) {
            int n = nums.length;
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0 ; i < n ; i++) {
                map.put(
                        nums[i],
                        map.getOrDefault(nums[i], 0) + 1
                );
            }

            n = n/2;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() > n) return entry.getKey();
            }

            return 0;
        }

        private int mooreVotingAlgo(int[] nums) {
            int count = 0;
            int candidate = 0;
            for (int num : nums) {
                if (count == 0) {
                    candidate = num;
                }

                if (num == candidate) count++;
                else count--;
            }

            return candidate;
        }
    }
}
