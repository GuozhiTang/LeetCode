/*
 * @lc app=leetcode id=1492 lang=java
 *
 * [1492] The kth Factor of n
 *
 * https://leetcode.com/problems/the-kth-factor-of-n/description/
 *
 * algorithms
 * Medium (62.24%)
 * Likes:    624
 * Dislikes: 190
 * Total Accepted:    76.6K
 * Total Submissions: 123.3K
 * Testcase Example:  '12\n3'
 *
 * You are given two positive integers n and k. A factor of an integer n is
 * defined as an integer i where n % i == 0.
 * 
 * Consider a list of all factors of n sorted in ascending order, return the
 * k^th factor in this list or return -1 if n has less than k factors.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 12, k = 3
 * Output: 3
 * Explanation: Factors list is [1, 2, 3, 4, 6, 12], the 3^rd factor is 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 7, k = 2
 * Output: 7
 * Explanation: Factors list is [1, 7], the 2^nd factor is 7.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 4, k = 4
 * Output: -1
 * Explanation: Factors list is [1, 2, 4], there is only 3 factors. We should
 * return -1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= k <= n <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {

    // Time: O(n)
    // Space: O(1)
    // Mathmatics
    public int kthFactor(int n, int k) {
        // we only need to count till (n / 2) here
        // cuz except for (1, itself), the next possible factor pair is (2, itself / 2)
        for (int digit = 1; digit <= n / 2; digit++) {
            // each time only when n%d==0, then --k
            if ((n % digit) == 0 && --k == 0) {
                return digit;
            }
        }

        // if there is only one digit left, then it must be itself
        if (k == 1) {
            return n;
        } else {
            return -1;
        }
    }
}
// @lc code=end

