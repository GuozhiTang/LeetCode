import java.util.HashSet;
import java.util.Set;

/*
 * @lc app=leetcode id=3 lang=java
 *
 * [3] Longest Substring Without Repeating Characters
 *
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 *
 * algorithms
 * Medium (29.53%)
 * Likes:    7825
 * Dislikes: 461
 * Total Accepted:    1.3M
 * Total Submissions: 4.5M
 * Testcase Example:  '"abcabcbb"'
 *
 * Given a string, find the length of the longest substring without repeating
 * characters.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: "abcabcbb"
 * Output: 3 
 * Explanation: The answer is "abc", with the length of 3. 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3. 
 * ⁠            Note that the answer must be a substring, "pwke" is a
 * subsequence and not a substring.
 * 
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    // Time: O(n)
    // Space: O(n)
    // Sliding Window
    public int lengthOfLongestSubstring(String s) {
        // Corner Cases
        if (s == null || s.length() == 0) return 0;

        Set<Character> set = new HashSet<>();
        // i, j  means the left and right range of the sliding window
        int i = 0, j = 0, lenRes = 0;
        int length = s.length();
        while (i < length && j < length) {
            // if there is no s[j] in the set, move j to the right
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                lenRes = Math.max(lenRes, j - i);
            } else { // if there exists, move i to the right
                set.remove(s.charAt(i++));
            }
        }
        return lenRes;
    }
}
// @lc code=end

