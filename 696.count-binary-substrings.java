/*
 * @lc app=leetcode id=696 lang=java
 *
 * [696] Count Binary Substrings
 *
 * https://leetcode.com/problems/count-binary-substrings/description/
 *
 * algorithms
 * Easy (64.07%)
 * Likes:    2541
 * Dislikes: 525
 * Total Accepted:    129.6K
 * Total Submissions: 201.5K
 * Testcase Example:  '"00110011"'
 *
 * Give a binary string s, return the number of non-empty substrings that have
 * the same number of 0's and 1's, and all the 0's and all the 1's in these
 * substrings are grouped consecutively.
 * 
 * Substrings that occur multiple times are counted the number of times they
 * occur.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "00110011"
 * Output: 6
 * Explanation: There are 6 substrings that have equal number of consecutive
 * 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
 * Notice that some of these substrings repeat and are counted the number of
 * times they occur.
 * Also, "00110011" is not a valid substring because all the 0's (and 1's) are
 * not grouped together.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "10101"
 * Output: 4
 * Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal
 * number of consecutive 1's and 0's.
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
    // Two Pointers
    public int countBinarySubstrings(String s) {
        // Corner Cases
        if (s == null || s.length() == 0) {
            return 0;
        }

        int preRunLength = 0, curRunLength = 1, result = 0;

        for (int index = 1; index < s.length(); index++) {
            // judge if current digit is the same as the previous one
            // if so, current running length +1
            // if not, it means digit changes, and pass curRunLength to preRunLength and reset curRunLength to 1
            if (s.charAt(index) == s.charAt(index - 1)) {
                curRunLength++;
            } else {
                preRunLength = curRunLength;
                curRunLength = 1;
            }

            // Once if pre >= cur, which means there exists enough consecutive same digits comparing to current
            if (preRunLength >= curRunLength) {
                result++;
            }
        }
        return result;
    }
}
// @lc code=end

