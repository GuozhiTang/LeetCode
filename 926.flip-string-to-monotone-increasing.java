/*
 * @lc app=leetcode id=926 lang=java
 *
 * [926] Flip String to Monotone Increasing
 *
 * https://leetcode.com/problems/flip-string-to-monotone-increasing/description/
 *
 * algorithms
 * Medium (57.79%)
 * Likes:    1702
 * Dislikes: 67
 * Total Accepted:    75.2K
 * Total Submissions: 129.6K
 * Testcase Example:  '"00110"'
 *
 * A binary string is monotone increasing if it consists of some number of 0's
 * (possibly none), followed by some number of 1's (also possibly none).
 * 
 * You are given a binary string s. You can flip s[i] changing it from 0 to 1
 * or from 1 to 0.
 * 
 * Return the minimum number of flips to make s monotone increasing.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "00110"
 * Output: 1
 * Explanation: We flip the last digit to get 00111.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "010110"
 * Output: 2
 * Explanation: We flip to get 011111, or alternatively 000111.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "00011000"
 * Output: 2
 * Explanation: We flip to get 00000000.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s[i] is either '0' or '1'.
 * 
 * 
 */

// @lc code=start
class Solution {

    // Time: O(n)
    // Space: O(1)
    // Dynamic Programming
    // https://leetcode.com/problems/flip-string-to-monotone-increasing/discuss/189751/C%2B%2B-one-pass-DP-solution-0ms-O(n)-or-O(1)-one-line-with-explaination.
    public int minFlipsMonoIncr(String s) {
        // Corner Cases
        if (s == null || s.length() == 0) {
            return 0;
        }

        int result = 0, countOnes = 0, length = s.length();
        for (int index = 0; index < length; index++) {
            char c = s.charAt(index);
            // if c is 1, then it will not impact the minFlips
            // if c is 0, then 2 options we can do to make it mono increase
            // 1. keep it as 0, and flip all the preceeding 1 to 0, need to know the count of "1"s so far
            // 2. flip it to 1, will not need to do anything.
            if (c == '1') {
                countOnes++;
            } else {
                int option1 = countOnes;
                int option2 = result + 1;
                result = Math.min(option1, option2);
            }
        }
        return result;
    }
}
// @lc code=end

