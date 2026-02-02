package Study.Arrays;

/**
 * Remove Element
 * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
 * The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.
 *.
 * Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do
 * the following things:
 * 1. Change the array nums such that the first k elements of nums contain the elements which are not equal to val.
 *    The remaining elements of nums are not important as well as the size of nums.
 * 2. Return k.
 */
public class RemoveElement {
    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] nums = {3,2,2,3};
        int val = 3;
        int k = obj.removeElement(nums, val);
        System.out.println("New length: " + k);
        System.out.print("Modified array: [ ");
        for (int i = 0 ; i < k ; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.print("]");
    }

    static class Solution {
        /**
         Time complexity: O(n)
         Space complexity: O(1)
         */
        public int removeElement(int[] nums, int val) {
            int k = 0;
            for (int i = 0 ; i < nums.length ; i++) {
                if (nums[i] != val) {
                    nums[k] = nums[i];
                    k++;
                }
            }
            return k;
        }
    }
}
