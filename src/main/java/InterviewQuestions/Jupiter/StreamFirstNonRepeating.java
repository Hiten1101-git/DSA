package InterviewQuestions.Jupiter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Given an input stream s consisting only of lowercase alphabets. While reading characters from the stream,
 * you have to tell which character has appeared only once in the stream up to that point. If there are many
 * characters that have appeared only once, you have to tell which one of them was the first one to appear.
 * If there is no such character then append '#' to the answer.
 * NOTE:
 * 1. You need to find the answer for every i (0 <= i < n)
 * 2. In order to find the solution for every you need to consider the string from starting position till the ith
 * position.
 * .
 * Input: s = "aabc"
 * Output: "a#bb"
 * Explanation: For every ith character we will consider the string from index 0 till index i first non-repeating
 * character is as follows - "a" - first non-repeating character is 'a' "aa" - no non-repeating character so '#'
 * "aab" - first non-repeating character is 'b' "aabc" - there are two non-repeating characters 'b' and 'c',
 * first non-repeating character is 'b' because 'b' comes before 'c' in the stream.
 * .
 * Input: s = "zz"
 * Output: "z#"
 * .
 * Input: s = "aabcdb"
 * Output: "a#bbbc"
 */
public class StreamFirstNonRepeating {
    public static void main(String[] args) {
        String s = "aabcdb";
        StreamFirstNonRepeating sf = new StreamFirstNonRepeating();
        System.out.println("Input: " + s);
        System.out.println("Output: " + sf.FirstNonRepeating(s));
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param s - the input string
     * @return - the first non-repeating character for each index
     */
    private String FirstNonRepeating(final String s) {
        Queue<Character> q = new LinkedList<>();
        Map<Character, Integer> hm = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < s.length() ; i++) {
            char ch = s.charAt(i);

            hm.put(ch, hm.getOrDefault(ch, 0) + 1);
            q.add(ch);

            // remove all characters from the front which have freq > 1
            while (!q.isEmpty() && hm.get(q.peek()) > 1) {
                q.poll();
            }

            sb.append(q.isEmpty() ? '#' : q.peek());
        }

        return sb.toString();
    }

    /**
     * This approach is better in terms of space only if we are using only lowercase letters.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param s - the input string
     * @return - the first non-repeating character for each index
     */
    private String FirstNonRepeatingOnlyForLowerCaseLetters(final String s) {
        Queue<Character> q = new LinkedList<>();
        int[] freq = new int[26];
        StringBuilder sb = new StringBuilder();

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
            q.add(ch);

            while (!q.isEmpty() && freq[q.peek() - 'a'] > 1) {
                q.poll();
            }

            sb.append(q.isEmpty() ? '#' : q.peek());
        }

        return sb.toString();
    }
}
