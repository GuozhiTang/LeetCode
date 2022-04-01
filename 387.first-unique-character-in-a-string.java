import java.util.HashMap;

/*
 * @lc app=leetcode id=387 lang=java
 *
 * [387] First Unique Character in a String
 *
 * https://leetcode.com/problems/first-unique-character-in-a-string/description/
 *
 * algorithms
 * Easy (56.30%)
 * Likes:    4764
 * Dislikes: 190
 * Total Accepted:    1M
 * Total Submissions: 1.8M
 * Testcase Example:  '"leetcode"'
 *
 * Given a string s, find the first non-repeating character in it and return
 * its index. If it does not exist, return -1.
 * 
 * 
 * Example 1:
 * Input: s = "leetcode"
 * Output: 0
 * Example 2:
 * Input: s = "loveleetcode"
 * Output: 2
 * Example 3:
 * Input: s = "aabb"
 * Output: -1
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s consists of only lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {

    // Time: O(n)
    // Space: O(n)
    // HashMap
    public int firstUniqChar(String s) {
        // Corner Cases
        if (s == null || s.length() == 0) {
            return -1;
        }

        // letter - Index / -1
        HashMap<Character, Integer> map = new HashMap<>();

        char[] letterArray = s.toCharArray();
        for (int index = 0; index < letterArray.length; index++) {
            if (map.containsKey(letterArray[index])) {
                map.put(letterArray[index], -1);
            } else {
                map.put(letterArray[index], index);
            }
        }

        for (char letter : letterArray) {
            if (map.get(letter) != -1) {
                return map.get(letter);
            }
        }

        return -1;
    }
}
// @lc code=end

