package InterviewQuestions.Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AmazonItemsInContainer {
    /**
     * Items in container amazon
     *.
     * Amazon would like to know how much inventory exists in their closed inventory compartments.
     * Given a string s consisting of items as '*' and closed compartments as an open and close '|',
     * an array of starting indices startIndices and an array of ending indices as endIndices,
     * determine the number of items in closed compartments within the substring between 2 indices
     *.
     * Example:
     * s = "|**|*|*"
     * startIndices: [1,1]
     * endIndices: [5,6]
     *.
     * The string has a total of 2 closed compartments, one with 2 items and one with 1 item.
     * For the first pair of indices (1,5), the substring is |**|*, there are 2 items in closed compartment,
     * for second, (1,6), the sub string is |**|*|, there are 2 + 1 = 3 items in closed compartments.
     * Hence, the output is [2,3]
     *
     */
    public static void main(String[] args) {
        String s = "|**|*|*";
        List<Integer> startIndices = Arrays.asList(1, 1, 2, 1);
        List<Integer> endIndices = Arrays.asList(5, 6, 6, 3);

        List<Integer> result = numberOfItems(s, startIndices, endIndices);
        System.out.println(result);
    }

    /**
     * Function to find the number of items in closed compartments
     * We can do this by finding the left and right pipes for each index.
     * If the left pipe is less than the right pipe, we can find the number of items in the closed compartments.
     * If it is not, we can return 0.
     *
     * Time Complexity: O(n + q)
     * Space Complexity: O(n + q)
     *
     * @param s - the string
     * @param startIndices - the list of starting indices
     * @param endIndices - the list of ending indices
     * @return - a list of integers representing the number of items in closed compartments for each pair of indices
     */
    private static List<Integer> numberOfItems(String s, List<Integer> startIndices, List<Integer> endIndices) {
        int n = s.length();
        int[] prefixSum = new int[n];
        int count = 0;
        boolean insideCompartment = false;

        /*
            1. prefixSum array: how many items * exist inside compartments up to position i
                We walk through the string left to right.
                insideCompartment becomes true after the first |.
                count increments only after the first pipe and we see *.
                prefixSum[i] means: number of items (*) from start till position i.
         */
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '|') {
                insideCompartment = true;
            }
            if (insideCompartment && c == '*') {
                count++;
            }
            prefixSum[i] = count;
        }

        /*
            2. Finding nearest |s quickly
                We need to find for any index:
                The nearest | to the left (leftPipe array)
                The nearest | to the right (rightPipe array)
         */
        int[] leftPipe = getPipes(s, n, true);
        int[] rightPipe = getPipes(s, n, false);

        /*
            3. Finding the number of items in closed compartments
                For each pair of indices, we need to find the number of items in the closed compartments.
                We can do this by finding the left and right pipes for each index.
                If the left pipe is less than the right pipe, we can find the number of items in the closed compartments.
                If it is not, we can return 0.
         */
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < startIndices.size(); i++) {
            int start = startIndices.get(i) - 1;
            int end = endIndices.get(i) - 1;

            int left = rightPipe[start];
            int right = leftPipe[end];

            if (left != -1 && right != -1 && left < right) {
                result.add(prefixSum[right] - prefixSum[left]);
            } else {
                result.add(0);
            }
        }
        return result;
    }

    /**
     * Helper function to find the nearest |s to the left or right of the given index.
     * We can use this function to find the nearest |s to the left or right of the given index.
     *
     * @param s - the string
     * @param n - the length of the string
     * @param isLeftToRight - true if we want to find the nearest |s to the left, false if we want to find the nearest |s to the right
     * @return - an array of integers representing the nearest |s to the left or right of each index
     */
    private static int[] getPipes(String s, int n, boolean isLeftToRight) {
        int[] pipes = new int[n];
        int lastPipe = -1;

        int start = isLeftToRight ? 0 : n - 1;
        int end = isLeftToRight ? n : -1;
        int step = isLeftToRight ? 1 : -1;

        for (int i = start; i != end; i += step) {
            if (s.charAt(i) == '|') {
                lastPipe = i;
            }
            pipes[i] = lastPipe;
        }

        return pipes;
    }
}
