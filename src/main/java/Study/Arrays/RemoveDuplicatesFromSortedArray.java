package Study.Arrays;

import java.util.Arrays;
import java.util.HashSet;

public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int k = obj.removeDuplicates(nums);
        System.out.println("OG Array: " + Arrays.toString(nums));
        System.out.print("Modified array: [ ");
        for (int i = 0 ; i < k ; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.print("]");
    }
    static class Solution {
        public int removeDuplicates(int[] nums) {
            return approach2(nums);
        }

        /*
         * Time: O(n)
         * Space: O(n)
         */
        private int approach1(int[] nums) {
            HashSet<Integer> hs = new HashSet<>();
            int index = 0;
            for (int i = 0 ; i < nums.length ; i++) {
                if (!hs.contains(nums[i])) {
                    hs.add(nums[i]);
                    nums[index] = nums[i];
                    index++;
                }
            }
            return index;
        }

        /*
         * Time: O(n)
         * Space: O(1)
         */
        private int approach2(int[] nums) {
            int unique = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > nums[unique - 1]) {
                    nums[unique] = nums[i];
                    unique++;
                }
            }
            return unique;
        }
    }
}
