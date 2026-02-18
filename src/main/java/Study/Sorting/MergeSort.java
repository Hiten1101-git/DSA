package Study.Sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of integers, nums,sort the array in non-decreasing order using the merge sort algorithm.
 * Return the sorted array.
 * A sorted array in non-decreasing order is one in which each element is either greater than or equal to
 * all the elements to its left in the array.
 * .
 * Example 1
 * Input: nums = [7, 4, 1, 5, 3]
 * Output: [1, 3, 4, 5, 7]
 * Explanation: 1 <= 3 <= 4 <= 5 <= 7.
 * Thus, the array is sorted in non-decreasing order.
 */
public class MergeSort {

    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] nums = {38, 27, 43, 3, 9, 82, 10};
        int[] sorted = obj.mergeSort(nums);
        System.out.print("Sorted array: ");
        for (int num : sorted) {
            System.out.print(num + " ");
        }
    }

    static class Solution {
        /**
         Time: O(nlogn)
         Space: O(n)
         */
        public int[] mergeSort(int[] nums) {
            int n = nums.length;
            mergeSortHelper(nums, 0, n - 1);
            return nums;
        }

        /**
         This method is divide and merge method.
         we divide the list into halves until we have a atomic list. then we merge these atomic lists while comparing them.
         */
        private void mergeSortHelper(int[] arr, int low, int high) {
            if (low >= high) return;

            int mid = (low + high) / 2;
            mergeSortHelper(arr, low, mid);
            mergeSortHelper(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }

        /**
         This method is merge method.
         compare and merge basis values.
         */
        private void merge(int[] arr, int low, int mid, int high) {
            List<Integer> temp = new ArrayList<>();
            int left = low;
            int right = mid + 1;

            while (left <= mid && right <= high) {
                if (arr[left] <= arr[right]) {
                    temp.add(arr[left]);
                    left++;
                } else {
                    temp.add(arr[right]);
                    right++;
                }
            }

            while (left <= mid) {
                temp.add(arr[left]);
                left++;
            }

            while (right <= high) {
                temp.add(arr[right]);
                right++;
            }

            for (int i = low; i <= high; i++) {
                arr[i] = temp.get(i - low);
            }
        }
    }
}
