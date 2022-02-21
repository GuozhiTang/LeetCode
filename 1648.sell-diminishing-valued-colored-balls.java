import java.util.Arrays;

/*
 * @lc app=leetcode id=1648 lang=java
 *
 * [1648] Sell Diminishing-Valued Colored Balls
 *
 * https://leetcode.com/problems/sell-diminishing-valued-colored-balls/description/
 *
 * algorithms
 * Medium (31.22%)
 * Likes:    650
 * Dislikes: 238
 * Total Accepted:    25.3K
 * Total Submissions: 81.5K
 * Testcase Example:  '[2,5]\n4'
 *
 * You have an inventory of different colored balls, and there is a customer
 * that wants orders balls of any color.
 * 
 * The customer weirdly values the colored balls. Each colored ball's value is
 * the number of balls of that color you currently have in your inventory. For
 * example, if you own 6 yellow balls, the customer would pay 6 for the first
 * yellow ball. After the transaction, there are only 5 yellow balls left, so
 * the next yellow ball is then valued at 5 (i.e., the value of the balls
 * decreases as you sell more to the customer).
 * 
 * You are given an integer array, inventory, where inventory[i] represents the
 * number of balls of the i^th color that you initially own. You are also given
 * an integer orders, which represents the total number of balls that the
 * customer wants. You can sell the balls in any order.
 * 
 * Return the maximum total value that you can attain after selling orders
 * colored balls. As the answer may be too large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: inventory = [2,5], orders = 4
 * Output: 14
 * Explanation: Sell the 1st color 1 time (2) and the 2nd color 3 times (5 + 4
 * + 3).
 * The maximum total value is 2 + 5 + 4 + 3 = 14.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: inventory = [3,5], orders = 6
 * Output: 19
 * Explanation: Sell the 1st color 2 times (3 + 2) and the 2nd color 4 times (5
 * + 4 + 3 + 2).
 * The maximum total value is 3 + 2 + 5 + 4 + 3 + 2 = 19.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= inventory.length <= 10^5
 * 1 <= inventory[i] <= 10^9
 * 1 <= orders <= min(sum(inventory[i]), 10^9)
 * 
 * 
 */

// @lc code=start
class Solution {

    // Time: O(nlogn)
    // Space: O(n)
    // Greedy + Math
    // https://leetcode.com/problems/sell-diminishing-valued-colored-balls/discuss/927509/Java-O(NlogN)-or-Detailed-Explanation-or-Runtime-Beats-100
    public int maxProfit(int[] inventory, int orders) {
        // Corner Cases
        if (orders == 0 || inventory == null || inventory.length == 0) {
            return 0;
        }

        // O(nlogn)
        Arrays.sort(inventory);

        int index = inventory.length - 1, typeOfBalls = 1;
        long sum = 0;
        while (orders > 0) {
            if (index > 0 && (inventory[index] - inventory[index - 1]) > 0 && orders >= typeOfBalls * (inventory[index] - inventory[index - 1])) {
                // System.out.println("index: " + index + ", typeOfBalls: " + typeOfBalls + ", preOrders: " + orders + ", preSum: " + sum);
                int curMaxOrders = typeOfBalls * (inventory[index] - inventory[index - 1]);
                // System.out.println("curMaxOrders: " + curMaxOrders);
                orders -= curMaxOrders;
                sum += (long)typeOfBalls * sumFromNToX((long)inventory[index], (long)(inventory[index - 1] + 1));
                // System.out.println("orders: " + orders);
                // System.out.println("sum: " + sum);
                // System.out.println("===============");
            } else if (index == 0 || (index > 0 && (inventory[index] - inventory[index - 1] > 0))) {
                // System.out.println("Last traverse:");
                // System.out.println("index: " + index + ", typeOfBalls: " + typeOfBalls + ", preOrders: " + orders + ", preSum: " + sum);
                long rounds = orders / typeOfBalls, rest = orders % typeOfBalls;
                // System.out.println("rounds: " + rounds + ", rest: " + rest);
                if (rounds == 0) {
                    sum += 0;
                } else {
                    sum += (long)typeOfBalls * sumFromNToX((long)inventory[index], (long)(inventory[index] - rounds + 1));
                }
                // System.out.println("sum after rounds: " + sum);
                sum += rest * ((long)inventory[index] - rounds);
                // System.out.println("final sum: " + sum);
                orders = 0;
            }
            sum %= (long)(1e9 + 7);
            index--;
            typeOfBalls++;
        }
        return (int)sum;
    }

    private long sumFromNToX(long n, long x) {
        long sum = (n + x) * (Math.abs(x - n) + 1) / 2;
        return sum;
    }
}
// @lc code=end

