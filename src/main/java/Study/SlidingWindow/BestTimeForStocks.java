package Study.SlidingWindow;

/**
 * Best Time to Buy and Sell Stock
 * You are given an integer array prices where prices[i] is the price of NeetCoin on the ith day.
 * You may choose a single day to buy one NeetCoin and choose a different day in the future to sell it.
 * Return the maximum profit you can achieve. You may choose to not make any transactions, in which case the profit would be 0.
 */
public class BestTimeForStocks {
    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("Max Profit: " + obj.maxProfit(prices)); // Output: 5
        // Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6 - 1 = 5.
        // Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
    }

    /**
     * This problem is a variable window type of sliding window problem.
     */
    static class Solution {
        public int maxProfit(int[] prices) {
            return dp(prices);
        }

        /*
            * Time Complexity: O(n)
            * Space Complexity: O(1)
         */
        private int twoPointer(int[] prices) {
            int maxP = 0;
            int l = 0, r = 1;

            while (r < prices.length) {
                if (prices[l] < prices[r]) {
                    maxP = Math.max(maxP, prices[r] - prices[l]);
                } else {
                    l = r;
                }
                r++;
            }

            return maxP;
        }

        /*
         * Time Complexity: O(n)
         * Space Complexity: O(1)
         */
        private int dp(int[] prices) {
            int maxP = 0;
            int minBuy = prices[0];
            for (int sell : prices) {
                maxP = Math.max(maxP, sell - minBuy);
                minBuy = Math.min(minBuy, sell);
            }

            return maxP;
        }
    }
}
