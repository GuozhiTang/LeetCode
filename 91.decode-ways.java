/*
 * @lc app=leetcode id=91 lang=java
 *
 * [91] Decode Ways
 *
 * https://leetcode.com/problems/decode-ways/description/
 *
 * algorithms
 * Medium (23.59%)
 * Likes:    2248
 * Dislikes: 2486
 * Total Accepted:    357.4K
 * Total Submissions: 1.5M
 * Testcase Example:  '"12"'
 *
 * A message containing letters from A-Z is being encoded to numbers using the
 * following mapping:
 * 
 * 
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 
 * 
 * Given a non-empty string containing only digits, determine the total number
 * of ways to decode it.
 * 
 * Example 1:
 * 
 * 
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2
 * 6).
 * 
 */

// @lc code=start
class Solution {
    public int numDecodings(String s) {
        // Corner Cases
        if (s.length() == 0 || s == null) {
            return 0;
        }

        int len = s.length();
        int[] dp = new int[len + 1];

        dp[0] = 1;
        int firstChar = s.charAt(0);
        if (firstChar == 0) {
            dp[1] = 0;
        } else {
            dp[1] = 1;
        }

        for (int charNum = 2; charNum <= len; charNum++) {
            // one-digit
            int oneDigit = s.charAt(charNum - 1);
            int twoDigit = Integer.parseInt(s.substring(charNum - 2, charNum));

            if (oneDigit <= 9 && oneDigit >= 1) {
                dp[charNum] = dp[charNum] + dp[charNum - 1];
            }
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[charNum] = dp[charNum] + dp[charNum - 2];
            }
        }
        return dp[len];
    }
}
// @lc code=end

