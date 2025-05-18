package Study.Intervals;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a sorted unique integer array nums.
 *.
 * A range [a,b] is the set of all integers from a to b (inclusive).
 *.
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly.
 * That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such
 * that x is in one of the ranges but not in nums.
 *.
 * Each range [a,b] in the list should be output as:
 *.
 * "a->b" if a != b
 * "a" if a == b
 */
public class SummaryRange {
    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 4, 5, 7};
        SummaryRange sr = new SummaryRange();
        System.out.println(sr.summaryRanges(nums));
    }

    /**
     Contiguous Detection: The key is to check if the current number is not
     consecutive with the previous one (nums[i] != nums[i-1] + 1). If so, we
     finalize the current range.
     Efficient Range Storage: Directly push a single number or a range into the
     result list using minimal conditions. This ensures simplicity and clarity.

     Time Complexity: O(n) Each element is visited once.
     Space Complexity: O(1) for constant space usage apart from the result.
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        if (nums.length == 0) return list;

        int left = nums[0];
        int right = nums[0];

        for (int i = 1 ; i <= nums.length ; i++) {;
            if (i < nums.length && nums[i] == right + 1) {
                // keep on traversing till consecutives are found
                right = nums[i];
            } else {
                /*
                    if no consecutive element, just add the element
                    otherwise add start and end indices
                */
                list.add(left == right ? String.valueOf(left) : left + "->" + right);

                // update the left and right to the next element
                if (i < nums.length) {
                    left = nums[i];
                    right = nums[i];
                }
            }
        }

        return list;
    }
}
