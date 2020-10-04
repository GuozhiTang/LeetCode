import java.util.Arrays;
import java.util.Comparator;

/*
 * @lc app=leetcode id=937 lang=java
 *
 * [937] Reorder Data in Log Files
 *
 * https://leetcode.com/problems/reorder-data-in-log-files/description/
 *
 * algorithms
 * Easy (53.62%)
 * Likes:    445
 * Dislikes: 1398
 * Total Accepted:    87.2K
 * Total Submissions: 162.7K
 * Testcase Example:  '["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]'
 *
 * You have an array of logs.  Each log is a space delimited string of words.
 * 
 * For each log, the first word in each log is an alphanumeric identifier.
 * Then, either:
 * 
 * 
 * Each word after the identifier will consist only of lowercase letters,
 * or;
 * Each word after the identifier will consist only of digits.
 * 
 * 
 * We will call these two varieties of logs letter-logs and digit-logs.  It is
 * guaranteed that each log has at least one word after its identifier.
 * 
 * Reorder the logs so that all of the letter-logs come before any digit-log.
 * The letter-logs are ordered lexicographically ignoring identifier, with the
 * identifier used in case of ties.  The digit-logs should be put in their
 * original order.
 * 
 * Return the final order of the logs.
 * 
 * 
 * Example 1:
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit
 * dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5
 * 1","dig2 3 6"]
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= logs.length <= 100
 * 3 <= logs[i].length <= 100
 * logs[i] is guaranteed to have an identifier, and a word after the
 * identifier.
 * 
 * 
 */

// @lc code=start
class Solution {
    // Time: O()
    // Space: O()
    public String[] reorderLogFiles(String[] logs) {
        // Corner Cases
        if (logs.length == 0 || logs == null) {
            return new String[0];
        }
        // scan to see if log contains letter
        boolean[] isLetterArray = new boolean[logs.length];

        int lenLetter = 0;

        for (int i = 0; i < logs.length; i++) {
            boolean isLetter = Character.isLetter(logs[i].charAt((logs[i].length() - 1)));
            if (isLetter) lenLetter++;
            isLetterArray[i] = isLetter;
        }
        
        String[] ans = new String[logs.length];
        // use two pointers to move log
        for (int i = 0, j = 0, k = lenLetter; i < logs.length; i++) {
            // move letter in position j
            if (isLetterArray[i]) {
                ans[j] = logs[i];
                j++;
            } else { // move digits in position k
                ans[k] = logs[i];
                k++;
            }
        }
        
        Comparator<String> customizedCom = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int i1 = s1.indexOf(' '), i2 = s2.indexOf(' ');
                String id1 = s1.substring(0, i1), id2 = s2.substring(0, i2);
                String word1 = s1.substring(i1), word2 = s2.substring(i2);
                // if words not the same, compre word1 and word2
                if (!word1.equals(word2)) {
                    return word1.compareTo(word2);
                }
                // if word is the same, then compare id1 and id2
                else {
                    return id1.compareTo(id2);
                }
            }
        };

        // Sort only the log contains letter
        Arrays.sort(ans, 0, lenLetter, customizedCom);

        return ans;
    }
}
// @lc code=end

