package Study.Intervals;

import java.util.Arrays;

public class MergeIntervals {
    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}, {17, 20}};
        System.out.print("Input: ");
        for (int[] interval : intervals) {
            System.out.print(Arrays.toString(interval) + " ");
        }
        System.out.println();
        MergeIntervals mi = new MergeIntervals();
        int[][] mergedIntervals = mi.merge(intervals);
        System.out.print("Output: ");
        for (int[] interval : mergedIntervals) {
            System.out.print(Arrays.toString(interval) + " ");
        }
    }

    /**
     - Time Complexity: O(n log n)
        Sorting the intervals dominates the complexity.
        Iterating through the intervals is O(n) .
     - Space Complexity: O(1)
        The merging is done in-place, so no extra memory is required.
     */
    private int[][] merge(int[][] intervals) {
        // sort the intervals based on start indices
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // maintain a pointer
        int k = 0;
        for (int i = 1 ; i < intervals.length ; i++) {
            if (intervals[k][1] >= intervals[i][0]) {
                // we need to merge the intervals
                intervals[k][1] = Math.max(intervals[k][1], intervals[i][1]);
            } else {
                // we must traverse further
                k++;
                intervals[k] = intervals[i];
            }
        }

        return Arrays.copyOfRange(intervals, 0, k + 1);
    }
}
