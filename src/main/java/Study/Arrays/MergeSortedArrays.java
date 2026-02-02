package Study.Arrays;

import java.util.Arrays;

/**
 * Merge Sorted Array
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n,
 * representing the number of elements in nums1 and nums2 respectively.
 * .
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 * .
 * The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
 * To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should
 * be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
 */
public class MergeSortedArrays {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        int m = 3;
        int n = 3;

        System.out.println("Before merging");
        System.out.println("Nums1: " + Arrays.toString(nums1));
        System.out.println("Nums2: " + Arrays.toString(nums2));

        solution.merge(nums1, m, nums2, n);
        System.out.println("After merging: " + Arrays.toString(nums1));
    }

    static class Solution {

        /**
         * Time complexity: O(m+n)
         * Space complexity: O(1)
         *
         * @param nums1 nums1
         * @param m     m
         * @param nums2 nums2
         * @param n     n
         */
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int i = m - 1;
            int j = n - 1;
            int k = m + n - 1;

            while (j >= 0) {
                if (i >= 0 && nums1[i] > nums2[j]) {
                    nums1[k--] = nums1[i--];
                } else {
                    nums1[k--] = nums2[j--];
                }
            }
        }
    }
}
