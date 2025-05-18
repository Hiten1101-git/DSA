package InterviewQuestions.IDFC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpecialBinaryString {
    /**
     * Special binary strings are binary strings with the following two properties:
     *.
     * The number of 0's is equal to the number of 1's.
     * Every prefix of the binary string has at least as many 1's as 0's.
     * You are given a special binary string s.
     *.
     * A move consists of choosing two consecutive, non-empty, special substrings of s, and swapping them. Two strings are consecutive if the last character of the first string is exactly one index before the first character of the second string.
     *.
     * Return the lexicographically largest resulting string possible after applying the mentioned operations on the string.
     */
    public static void main(String[] args) {
        String input = "1010111000";
        String output = makeLargestGoodBinaryString(input);
        System.out.println("Largest good binary string: " + output);
    }

    /**
     * Input: "1010111000"
     * Split → parts = [1010, 111000]
     * .
     * For "1010":
     * inner = 01
     * recursively solve 01 → returns 01 (already minimal)
     * wrap back → "1" + "01" + "0" = 1010
     * .
     * For "111000":
     * inner = 1100
     * recursively solve 1100:
     * split into [1100]
     * inner of 1100 is 10
     * recursively solve 10 → it's minimal
     * wrap back → 1 + 10 + 0 = 1100
     * wrapping back gives 111000
     *.
     * Sort: [111000, 1010]
     *.
     * Join and return → "1110001010"
     *
     * @param s - the input string
     * @return - the largest good binary string
     */
    public static String makeLargestGoodBinaryString(String s) {
        List<String> parts = splitToGoodSubstrings(s);
        List<String> transformed = new ArrayList<>();

        for (String part : parts) {
            // Remove outer 1 and 0 and recursively process inner substring
            String inner = part.substring(1, part.length() - 1);
            String transformedInner = makeLargestGoodBinaryString(inner);
            transformed.add("1" + transformedInner + "0");
        }

        // Sort in reverse lex order
        transformed.sort(Collections.reverseOrder());

        StringBuilder result = new StringBuilder();
        for (String str : transformed) {
            result.append(str);
        }
        return result.toString();
    }

    /**
     * Divides the input into the smallest good binary substrings.
     * These are substrings where:
     * - The number of 1s = number of 0s.
     * - At every position inside, number of 1s ≥ number of 0s (prefix check).
     *.
     * Example: For input 1010111000, this method splits it into two substrings:
     * "1010" and "111000"
     *
     * @param s - the input string
     * @return - a list of good binary substrings
     */
    private static List<String> splitToGoodSubstrings(String s) {
        List<String> result = new ArrayList<>();
        int count = 0;
        int start = 0;

        for (int i = 0; i < s.length(); i++) {
            count += s.charAt(i) == '1' ? 1 : -1;
            if (count == 0) {
                result.add(s.substring(start, i + 1));
                start = i + 1;
            }
        }

        return result;
    }
}
