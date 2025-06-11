package Study.DP;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Coin Change Problem.
 * Given an array of coin denominations and a total amount, find the minimum number of coins
 * needed to make that amount. If it is not possible to make the amount, return -1.
 */
public class CoinChange {
    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] coins = {1, 2, 5}; // Example input
        int amount = 11; // Example amount
        int minCoins = obj.coinChange(coins, amount);
        System.out.println("Minimum number of coins required: " + minCoins);
    }

    static class Solution {
        public int coinChange(int[] coins, int amount) {
            // return bottomUp(coins, amount);
            return topDown(coins, amount);
        }

        /**
         Time complexity: O(n*t)
         Space complexity: O(t)
         */
        private int bottomUp(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, amount + 1);
            dp[0] = 0;
            for (int currAmount = 0 ; currAmount <= amount ; currAmount++) {
                for (int coin : coins) {
                    if (currAmount - coin >= 0) {
                        dp[currAmount] = Math.min(dp[currAmount], dp[currAmount - coin] + 1);
                    }
                }
            }

            return dp[amount] > amount ? -1 : dp[amount];
        }

        /**
         Time complexity: O(n*t)
         Space complexity: O(t)
         */
        HashMap<Integer, Integer> memo = new HashMap<>();
        private int topDown(int[] coins, int amount) {
            int minCoins = dfs(amount, coins);
            return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
        }

        public int dfs(int amount, int[] coins) {
            if (amount == 0) return 0;
            if (memo.containsKey(amount)) return memo.get(amount);

            int res = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (amount - coin >= 0) {
                    int result = dfs(amount - coin, coins);
                    if (result != Integer.MAX_VALUE) {
                        res = Math.min(res, 1 + result);
                    }
                }
            }

            memo.put(amount, res);
            return res;
        }
    }
}
