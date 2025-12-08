package Study.SlidingWindow;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Given a string s and an integer k, find the length of the longest substring
 * that can be obtained by replacing at most k characters in s.
 * Example:
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: The longest substring is "ABAB" with length 4.
 */
public class CharacterReplacement {
    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;
        Solution solution = new Solution();
        int ans = solution.characterReplacement(s, k);
        System.out.println("Length of longest substring with at most " + k + " replacements: " + ans);
    }

    static public class Solution {
        public int characterReplacement(String s, int k) {
            return slidingWindow(s, k);
        }

        public int slidingWindow(String s, int k) {
            int res = 0;
            HashSet<Character> charSet = new HashSet<>();
            for (char c : s.toCharArray()) {
                charSet.add(c);
            }

            for (char c : charSet) {
                int count = 0, l = 0;
                for (int r = 0; r < s.length(); r++) {
                    if (s.charAt(r) == c) {
                        count++;
                    }

                    while ((r - l + 1) - count > k) {
                        if (s.charAt(l) == c) {
                            count--;
                        }
                        l++;
                    }

                    res = Math.max(res, r - l + 1);
                }
            }
            return res;
        }

        public int optimal(String s, int k) {
            HashMap<Character, Integer> count = new HashMap<>();
            int res = 0;

            int l = 0, maxf = 0;
            for (int r = 0; r < s.length(); r++) {
                count.put(s.charAt(r), count.getOrDefault(s.charAt(r), 0) + 1);
                maxf = Math.max(maxf, count.get(s.charAt(r)));

                while ((r - l + 1) - maxf > k) {
                    count.put(s.charAt(l), count.get(s.charAt(l)) - 1);
                    l++;
                }
                res = Math.max(res, r - l + 1);
            }

            return res;
        }
    }
}
