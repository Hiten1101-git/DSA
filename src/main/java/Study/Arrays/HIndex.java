package Study.Arrays;

import java.util.Arrays;

public class HIndex {

    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] citations = {3, 0, 6, 1, 5};
        int hIndex = obj.hIndex(citations);
        System.out.println("H-Index: " + hIndex);
    }

    static class Solution {
        public int hIndex(int[] citations) {
            return approach2(citations);
        }

        private int approach1(int[] citations) {
            Arrays.sort(citations);
            int hIndex = 0;
            for(int i = citations.length - 1 ; i >= 0 ; i--) {
                if (citations[i] > hIndex) hIndex++;
                else break;
            }

            return hIndex;
        }

        /**
             assume n is the total number of papers, if we have n+1 buckets, number from 0 to n,
             then for any paper with reference corresponding to the index of the bucket,
             we increment the count for that bucket.The only exception is that for any paper with
             larger number of reference than n, we put in the n-th bucket.

             Then we iterate from the back to the front of the buckets, whenever the total
             count exceeds the index of the bucket, meaning that we have the index number of papers
             that have reference greater than or equal to the index. Which will be our h-index result.
             The reason to scan from the end of the array is that we are looking for the greatest h-index.
             For example, given array [3,0,6,5,1], we have 6 buckets to contain how many papers have the
             corresponding index. Hope to image and explanation help.
         */
        private int approach2(int[] citations) {
            int n = citations.length;
            int[] arr = new int[n+1];

            for (int c : citations) {
                if (c > n) {
                    arr[n]++;
                } else {
                    arr[c]++;
                }
            }

            int count = 0;
            for (int i = n ; i >= 0 ; i--) {
                count += arr[i];
                if (count >= i) return i;
            }

            return 0;
        }
    }
}
