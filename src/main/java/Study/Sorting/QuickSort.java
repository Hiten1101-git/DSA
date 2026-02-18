package Study.Sorting;

import java.util.Random;

/**
 * Given an array of integers, nums,sort the array in non-decreasing order using the quick sort algorithm.
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
public class QuickSort {

    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] nums = {38, 27, 43, 3, 9, 82, 10};
        int[] sorted = obj.quickSort(nums);
        System.out.print("Sorted array: ");
        for (int num : sorted) {
            System.out.print(num + " ");
        }
    }

    static class Solution {
        /**
         worst Time: O(n^2)
         best Time: O(nlogn)
         space: O(n)
         */
        public int[] quickSort(int[] nums) {
            quickSortHelper(nums, 0, nums.length - 1);
            return nums;
        }

        private void quickSortHelper(int[] arr, int low, int high) {
            if (low < high) {
                int pIndex = partition(arr, low, high);
                quickSortHelper(arr, low, pIndex - 1);
                quickSortHelper(arr, pIndex + 1, high);
            }
        }

        private int partition(int[] arr, int low, int high) {
            int randomIndex = low + new Random().nextInt(high - low + 1);
            swap(arr, low, randomIndex);

            int pivot = arr[low];
            int i = low;
            int j = high;

            while (i < j) {
                while (arr[i] <= pivot && i <= high - 1) {
                    i++;
                }

                while (arr[j] > pivot && j >= low + 1) {
                    j--;
                }

                if (i < j) swap(arr, i, j);
            }

            swap(arr, low, j);
            return j;
        }

        // Custom swap function
        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
