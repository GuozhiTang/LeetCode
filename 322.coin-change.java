import java.util.Arrays;

/*
 * @lc app=leetcode id=322 lang=java
 *
 * [322] Coin Change
 *
 * https://leetcode.com/problems/coin-change/description/
 *
 * algorithms
 * Medium (33.49%)
 * Likes:    3244
 * Dislikes: 111
 * Total Accepted:    342.1K
 * Total Submissions: 1M
 * Testcase Example:  '[1,2,5]\n11'
 *
 * You are given coins of different denominations and a total amount of money
 * amount. Write a function to compute the fewest number of coins that you need
 * to make up that amount. If that amount of money cannot be made up by any
 * combination of the coins, return -1.
 * 
 * Example 1:
 * 
 * 
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3 
 * Explanation: 11 = 5 + 5 + 1
 * 
 * Example 2:
 * 
 * 
 * Input: coins = [2], amount = 3
 * Output: -1
 * 
 * 
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 * 
 */

// @lc code=start
class Solution {
    // Time: O(amount*coins.length)
    // Space: O(amount)
    // DP
    public int coinChange(int[] coins, int amount) {
        // Corner Cases
        if (amount < 1) {
            return 0;
        }
        
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int curAmount = 1; curAmount <= amount; curAmount++) {
            int minSteps = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (curAmount >= coin && dp[curAmount - coin] != -1) {
                    minSteps = Math.min(minSteps, dp[curAmount - coin]);
                }
            }

            if (minSteps != Integer.MAX_VALUE) {
                dp[curAmount] = minSteps + 1;
            }
        }
        return dp[amount];
    }
}
// @lc code=end

