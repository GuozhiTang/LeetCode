/*
 * @lc app=leetcode id=121 lang=java
 *
 * [121] Best Time to Buy and Sell Stock
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
 *
 * algorithms
 * Easy (49.30%)
 * Likes:    4211
 * Dislikes: 192
 * Total Accepted:    754.9K
 * Total Submissions: 1.5M
 * Testcase Example:  '[7,1,5,3,6,4]'
 *
 * Say you have an array for which the i^th element is the price of a given
 * stock on day i.
 * 
 * If you were only permitted to complete at most one transaction (i.e., buy
 * one and sell one share of the stock), design an algorithm to find the
 * maximum profit.
 * 
 * Note that you cannot sell a stock before you buy one.
 * 
 * Example 1:
 * 
 * 
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit
 * = 6-1 = 5.
 * Not 7-1 = 6, as selling price needs to be larger than buying price.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * 
 * 
 */

// @lc code=start
class Solution {
    // Time: O(n)
    // Space: O(1)
    // kadane-algorithm?
    public int maxProfit(int[] prices) {
        // Corner Cases
        if (prices.length == 0 || prices == null) {
            return 0;
        }

        int curMax = 0, diffMax = 0;
        for (int index = 1; index < prices.length; index++) {
            // get the sum of each two adjacent prices
            // if it is less than 0, it means current is the new minimum prices, start again
            curMax = Math.max(0, curMax += prices[index] - prices[index - 1]);
            // track the maximum difference
            diffMax = Math.max(curMax, diffMax);
        }
        return diffMax;
    }
}
// @lc code=end

