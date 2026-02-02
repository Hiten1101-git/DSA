package Study.Arrays;

/**
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 *.
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share
 * of the stock at any time. However, you can sell and buy the stock multiple times on the same day,
 * ensuring you never hold more than one share of the stock.
 *.
 * Find and return the maximum profit you can achieve.
 */
public class StocksII {

    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] prices = {7,1,5,3,6,4};
        int maxProfit = obj.maxProfit(prices);
        System.out.println("Max Profit: " + maxProfit);
    }

    static class Solution {
        public int maxProfit(int[] prices) {
            int maxP = 0;
            int buyingPrice = prices[0];
            for (int i = 1 ; i < prices.length ; i++) {
                if (buyingPrice < prices[i]) {
                    maxP += prices[i] - buyingPrice;
                }
                buyingPrice = prices[i];
            }

            return maxP;
        }
    }
}
