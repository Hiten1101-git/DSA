package Study.Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent
 * the start and the end of the ith interval and intervals is sorted in ascending order by starti.
 * You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 *.
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and
 * intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 *.
 * Return intervals after the insertion.
 *.
 * Note that you don't need to modify intervals in-place. You can make a new array and return it.
 * .
 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * .
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertIntervals {
    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};
        InsertIntervals ii = new InsertIntervals();
        int[][] result = ii.insert(intervals, newInterval);
        System.out.println(Arrays.deepToString(result));
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        return approach2(intervals, newInterval);
    }

    /**
     Total Time: O(n)
     Total Space: O(n)
     */
    private int[][] approach2(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();

        for (int[] interval : intervals) {
            if (newInterval == null || interval[1] < newInterval[0]) {
                // Case 1: No Overlap, interval is completely before newInterval
                result.add(interval);
            } else if (interval[0] > newInterval[1]) {
                // Case 2: No Overlap, interval is completely after newInterval
                result.add(newInterval);
                result.add(interval);
                newInterval = null; // newInterval inserted, no need to track it anymore
            } else {
                // Case 3: Overlapping intervals -> Merge them
                newInterval[0] = Math.min(newInterval[0], interval[0]);
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }
        }

        // If newInterval wasn't inserted yet, add it at the end
        if (newInterval != null) {
            result.add(newInterval);
        }

        return result.toArray(new int[0][]);
    }

    /**
     Total Time: O(n)
     Total Space: O(n)
     */
    private int[][] approach1(int[][] intervals, int[] newInterval) {
        int[][] newIntervals = insertInterval(intervals, newInterval);
        System.out.println(Arrays.deepToString(newIntervals));
        return mergeIntervals(newIntervals);
    }

    /**
     Time: O(n)
     Space: O(n)
     */
    private int[][] insertInterval(int[][] intervals, int[] newInterval) {
        int[][] newIntervals = new int[intervals.length + 1][2];
        int k = 0;
        boolean inserted = false;
        for (int i = 0 ; i < intervals.length ; i++) {
            if (!inserted && intervals[i][0] >= newInterval[0]) {
                newIntervals[k][0] = newInterval[0];
                newIntervals[k][1] = newInterval[1];
                k++;
                inserted = true;
            }
            newIntervals[k][0] = intervals[i][0];
            newIntervals[k][1] = intervals[i][1];
            k++;
        }

        if (!inserted) {
            newIntervals[k][0] = newInterval[0];
            newIntervals[k][1] = newInterval[1];
        }

        return newIntervals;
    }

    /**
     Time: O(n)
     Space: O(n)
     */
    private int[][] mergeIntervals(int[][] intervals) {
        int k = 0;
        for (int i = 1 ; i < intervals.length ; i++) {
            if (intervals[k][1] >= intervals[i][0]) {
                intervals[k][1] = Math.max(intervals[k][1], intervals[i][1]);
            } else {
                k++;
                intervals[k] = intervals[i];
            }
        }

        return Arrays.copyOfRange(intervals, 0, k + 1);
    }
}
