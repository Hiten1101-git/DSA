package Study.SlidingWindow;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 * Example:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 */
public class LongestSubStringWithoutRepeatingChar {
    public static void main(String[] args) {
        String s = "abcabcbb";
        Solution solution = new Solution();
        int ans = solution.lengthOfLongestSubstring(s);
        System.out.println("Length of longest substring without repeating characters: " + ans);
    }

    // variable window problem
    static class Solution {
        public int lengthOfLongestSubstring(String s) {
            return slidingWindow(s);
        }

        private int slidingWindow(String s) {
            HashSet<Character> hs = new HashSet<>();
            int l = 0;
            int res = 0;

            for (int r = 0 ; r < s.length() ; r++) {
                while(hs.contains(s.charAt(r))) {
                    hs.remove(s.charAt(l));
                    l++;
                }
                hs.add(s.charAt(r));
                res = Math.max(res, r - l + 1);
            }

            return res;
        }

        private int optimal(String s) {
            HashMap<Character, Integer> hm = new HashMap<>();
            int l = 0;
            int res = 0;

            for (int r = 0 ; r < s.length() ; r++) {
                if (hm.containsKey(s.charAt(r))) {
                    l = Math.max(hm.get(s.charAt(r)) + 1, l);
                }
                hm.put(s.charAt(r), r);
                res = Math.max(res, r - l + 1);
            }

            return res;
        }
    }
}
