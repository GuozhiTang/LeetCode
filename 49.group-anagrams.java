import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * @lc app=leetcode id=49 lang=java
 *
 * [49] Group Anagrams
 *
 * https://leetcode.com/problems/group-anagrams/description/
 *
 * algorithms
 * Medium (63.10%)
 * Likes:    8740
 * Dislikes: 302
 * Total Accepted:    1.3M
 * Total Submissions: 2M
 * Testcase Example:  '["eat","tea","tan","ate","nat","bat"]'
 *
 * Given an array of strings strs, group the anagrams together. You can return
 * the answer in any order.
 * 
 * An Anagram is a word or phrase formed by rearranging the letters of a
 * different word or phrase, typically using all the original letters exactly
 * once.
 * 
 * 
 * Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 * Example 3:
 * Input: strs = ["a"]
 * Output: [["a"]]
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= strs.length <= 10^4
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {

    // Time: O(m*n)
    // Space: O(n)
    // HashMap
    // https://leetcode.com/problems/group-anagrams/discuss/19176/Share-my-short-JAVA-solution
    public List<List<String>> groupAnagrams(String[] strs) {
        // Corner Cases
        if (strs == null || strs.length == 0) {
            return new ArrayList<List<String>>();
        }

        // uniqueString (for each group) -> the word list of this group
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] lettersInOrder = new char[26];
            // all occurred letters would be calculated in a fixed order
            for (char character : str.toCharArray()) {
                lettersInOrder[character - 'a']++;
            }
            String uniqueGroupString = String.valueOf(lettersInOrder);

            if (!map.containsKey(uniqueGroupString)) {
                map.put(uniqueGroupString, new ArrayList<String>());
            }
            map.get(uniqueGroupString).add(str);
        }

        // ArrayList<>(map.values());
        return new ArrayList<>(map.values());
    }
}
// @lc code=end

