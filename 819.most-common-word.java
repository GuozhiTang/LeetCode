import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * @lc app=leetcode id=819 lang=java
 *
 * [819] Most Common Word
 *
 * https://leetcode.com/problems/most-common-word/description/
 *
 * algorithms
 * Easy (43.68%)
 * Likes:    509
 * Dislikes: 1294
 * Total Accepted:    120.4K
 * Total Submissions: 274.9K
 * Testcase Example:  '"Bob hit a ball, the hit BALL flew far after it was hit."\n["hit"]'
 *
 * Given a paragraph and a list of banned words, return the most frequent word
 * that is not in the list of banned words.  It is guaranteed there is at least
 * one word that isn't banned, and that the answer is unique.
 * 
 * Words in the list of banned words are given in lowercase, and free of
 * punctuation.  Words in the paragraph are not case sensitive.  The answer is
 * in lowercase.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Input: 
 * paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
 * banned = ["hit"]
 * Output: "ball"
 * Explanation: 
 * "hit" occurs 3 times, but it is a banned word.
 * "ball" occurs twice (and no other word does), so it is the most frequent
 * non-banned word in the paragraph. 
 * Note that words in the paragraph are not case sensitive,
 * that punctuation is ignored (even if adjacent to words, such as "ball,"), 
 * and that "hit" isn't the answer even though it occurs more because it is
 * banned.
 * 
 * 
 * 
 * 
 * Note: 
 * 
 * 
 * 1 <= paragraph.length <= 1000.
 * 0 <= banned.length <= 100.
 * 1 <= banned[i].length <= 10.
 * The answer is unique, and written in lowercase (even if its occurrences in
 * paragraph may have uppercase symbols, and even if it is a proper noun.)
 * paragraph only consists of letters, spaces, or the punctuation symbols
 * !?',;.
 * There are no hyphens or hyphenated words.
 * Words only consist of letters, never apostrophes or other punctuation
 * symbols.
 * 
 * 
 */

// @lc code=start
class Solution {
    // Time: O(n)
    // Space: O(n)
    // Hashmap + HashSet
    public String mostCommonWord(String paragraph, String[] banned) {
        // Corner Cases
        if (paragraph.length() == 0 || paragraph == null) {
            return "";
        }

        // delete the symbols of the string
        // W - 字符类P - 标点字符, Z - 分隔符, S - 符号 (数学符号, 货币符号), N - 数字, L - 字母, M - 标记符号, C - 其他字符
        // in regex, \W means word(alphanumeric and underscore) \s means whitespace(spaces tabs, line breaks). and the '+' means we match more than one of the pattern we have.
        // change the string to lowercase
        // split the string into an array
        String[] words = paragraph.replaceAll("\\W+", " ").toLowerCase().split("\\s+");

        // use hashmap to store the counts of the words
        Map<String, Integer> map = new HashMap<>();
        // use hashset to store the banned words
        Set<String> ban = new HashSet<>(Arrays.asList(banned));

        // traverse the words array
        for (String word : words) {
            if (!ban.contains(word)) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }

        int max = 0;
        String result = "";
        // get the most frequent word
        for (String fre_word : map.keySet()) {
            if (map.get(fre_word) > max) {
                max = map.get(fre_word);
                result = fre_word;
            }
        }
        return result;
    }
}
// @lc code=end

