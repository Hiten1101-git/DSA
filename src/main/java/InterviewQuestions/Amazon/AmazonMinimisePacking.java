package InterviewQuestions.Amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AmazonMinimisePacking {
    /**
     * The team at Amazon warehouse is given a task to optimise the packing a set of boxes with different ids.
     * Each box is labelled with an ID, and these boxes are currently arranged in a single row,
     * from left to right where the ID of ith box is represented by string s_id consisting of digits from 0 to 9 inclusive.
     * To make packing from efficient the team can perform the following operation any number of times
     * - Choose am index i and remove digit s_id[i] the insert the box with ID min(s_id[i] +1, 9) on any position in the row
     *.
     * Given the string s_id, find the lexicographically minimal string of boxes using these operations
     *.
     * Example:
     * s_id = “26547”
     * Delete 5 and insert 6 in 4th position
     * Delete 6 from 2nd position and insert 7 in. 4th position
     * Output: 24677
     */
    public static void main(String[] args) {
        String s_id = "26547"; // Test input
        String result = minimalID(s_id); // Call the function to minimize packing
        System.out.println(result); // Output the result
    }

    public static String minimalID(String s_id) {
        StringBuilder result = new StringBuilder();
        List<Character> transformed = new ArrayList<>();
        char minSoFar = '9';

        for (char ch : s_id.toCharArray()) {
            if (ch <= minSoFar) {
                result.append(ch);
                minSoFar = ch;
            } else {
                // Transform current digit and add to transformed list
                int newDigit = Math.min(ch - '0' + 1, 9);
                transformed.add((char) (newDigit + '0'));
            }
        }

        // Sort transformed digits to maintain lex order
        Collections.sort(transformed);

        for (char ch : transformed) {
            result.append(ch);
        }

        return result.toString();
    }

    public static String minimizePacking1(String s_id) {
        List<Character> list = new ArrayList<>();
        List<Integer> listInt = new ArrayList<>();
        list.add(s_id.charAt(0));

        for (int i = 1 ; i < s_id.length() ; i++) {
            char c = s_id.charAt(i);
            if (list.get(i-1) > c) {
                char prev = list.get(i-1);
                int prevInt = prev - '0';
                listInt.add(prevInt + 1);
                list.remove(i-1);
                list.add(c);
            } else {
                list.add(c);
            }
        }

//        list will have only ascending chars
        List<Integer> newList = new ArrayList<>();
        int j = 0;
        int i = 0;
        while (i < listInt.size()) {
            while (j < list.size() && listInt.get(i) >= list.get(j) - '0') {
                newList.add(list.get(i) - '0');
                j++;
            }
            newList.add(listInt.get(i));
            i++;
            // we must insert the new char at index j
        }
        return "";
    }

    // Function to return the lexicographically smallest string
    public static String minimizePacking(String s_id) {
        // Convert the input string into a list of characters to manipulate it
        char[] chars = s_id.toCharArray();

        // Stack to maintain the lexicographically smallest result
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            char currentChar = chars[i];

            // If the character is not '9', we increment it by 1
            if (currentChar != '9') {
                currentChar = (char) (currentChar + 1);
            }

            // Add the incremented character to the result
            result.append(currentChar);
        }

        return result.toString();
    }
}
