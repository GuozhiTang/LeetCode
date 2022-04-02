import java.util.HashSet;
import java.util.List;

/*
 * @lc app=leetcode id=139 lang=java
 *
 * [139] Word Break
 *
 * https://leetcode.com/problems/word-break/description/
 *
 * algorithms
 * Medium (38.14%)
 * Likes:    3579
 * Dislikes: 193
 * Total Accepted:    481K
 * Total Submissions: 1.2M
 * Testcase Example:  '"leetcode"\n["leet","code"]'
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of
 * non-empty words, determine if s can be segmented into a space-separated
 * sequence of one or more dictionary words.
 * 
 * Note:
 * 
 * 
 * The same word in the dictionary may be reused multiple times in the
 * segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet
 * code".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple
 * pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 * 
 * 
 */

// @lc code=start
class Solution {

    public boolean wordBreak(String s, List<String> wordDict) {
        // Corner Cases
        if (s == null || s.length() == 0) {
            return false;
        }

        HashSet<String> set = new HashSet<>(wordDict);

        // return bruteForceSolution(s, set);
        return dpSolution(s, wordDict);
    }

    // Time: O(n^3)
    // Space: O(n)
    // Dynamic Programming
    // https://leetcode.com/problems/word-break/discuss/43790/Java-implementation-using-DP-in-two-ways
    private boolean dpSolution(String s, List<String> wordDict) {
        boolean[] isSegmented = new boolean[s.length() + 1];

        isSegmented[0] = true;

        for (int curLength = 1; curLength <= s.length(); curLength++) {
            for (int index = 0; index < curLength; index++) {
                if (isSegmented[index] && wordDict.contains(s.substring(index, curLength))) {
                    isSegmented[curLength] = true;
                    break;
                }
            }
        }

        return isSegmented[s.length()];
    }

    // Time: O(2^n)
    // Space: O(n)
    // DP
    // https://leetcode.com/problems/word-break/discuss/169383/solved-The-Time-Complexity-of-The-Brute-Force-Method-Should-Be-O(2n)-and-Prove-It-Below
    private boolean bruteForceSolution(String s, HashSet<String> set) {
        int length = s.length();

        // break condition
        if (length == 0) {
            return true;
        }

        for (int index = 1; index < length; index++) {
            if (set.contains(s.substring(0, index)) && bruteForceSolution(s.substring(index), set)) {
                return true;
            }
        }

        return false;
    }

    // =====================================================================

    // public boolean wordBreak(String s, List<String> wordDict) {
    //     HashSet<String> set = new HashSet<>(wordDict);
    //     wbRecursion(s, set);
    //     wbDP(s, set);
    //     return wbDP2(s, wordDict);
    // }

    // // Time: O(mn) n: length of string, m: length of set
    // // Space: O(n)
    // // DP2
    // private boolean wbDP2(String s, List<String> wordDict) {
    //     // Corner Cases
    //     if (s.length() == 0 || s == null) {
    //         return true;
    //     }

    //     boolean[] segment = new boolean[s.length() + 1];
    //     segment[0] = true;

    //     for (int i = 1; i <= s.length(); i++) {
    //         for (String word : wordDict) {
    //             if (word.length() <= i && segment[i - word.length()] && s.substring(i - word.length(), i).equals(word)) {
    //                 segment[i] = true;
    //                 break;
    //             }
    //         }
    //     }

    //     return segment[s.length()];
    // }

    // // Time: O(n^3) n: length of string
    // // Space: O(n)
    // // DP
    // private boolean wbDP(String s, HashSet<String> set) {
    //     // Corner Cases
    //     if (s.length() == 0 || s == null) {
    //         return true;
    //     }

    //     boolean[] segment = new boolean[s.length() + 1];
    //     segment[0] = true;

    //     for (int i = 1; i <= s.length(); i++) {
    //         for (int j = 0; j < i; j++) {
    //             if (segment[j] && set.contains(s.substring(j, i))) {
    //                 segment[i] = true;
    //                 break;
    //             }
    //         }
    //     }
    //     return segment[s.length()];
    // }

    // // Time: O()
    // // Space: O()
    // // Recursion
    // private boolean wbRecursion(String s, HashSet<String> set) {
    //     // Corner Cases
    //     if (s.length() == 0 || s == null) {
    //         return true;
    //     }

    //     for (int index = 1; index <= s.length(); index++) {
    //         if (set.contains(s.substring(0, index))  && wbRecursion(s.substring(index), set)) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }
}
// @lc code=end

