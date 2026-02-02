package Study.Arrays;

/**
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 */
public class ReverseArray {

    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;
        obj.rotate(nums, k);
        System.out.print("Rotated array: [ ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.print("]");
    }

    static class Solution {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            reverse(nums, 0, n-k-1);
            reverse(nums, n-k, n-1);
            reverse(nums, 0, n-1);
        }

        private void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }
    }
}
