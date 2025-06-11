package Study.DP;

import java.util.List;

/**
 * Word Break Problem.
 * Given a string s and a dictionary of words wordDict, determine if s can be segmented into a
 * space-separated sequence of one or more dictionary words.
 */
public class WordBreak {
    public static void main(String[] args) {
        Solution obj = new Solution();
        String s = "leetcode";
        List<String> wordDict = List.of("leet", "code");
        boolean canBreak = obj.wordBreak(s, wordDict);
        System.out.println("Can the string be segmented: " + canBreak);
    }

    static class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;

            for (int subLength = 1 ; subLength <= s.length() ; subLength++) {
                for (String word : wordDict) {
                    int startIndex = subLength - word.length();
                    if (startIndex >= 0 && dp[startIndex] && s.substring(startIndex, subLength).equals(word)) {
                        dp[subLength] = true;
                        break;
                    }
                }
            }

            return dp[s.length()];
        }
    }
}
