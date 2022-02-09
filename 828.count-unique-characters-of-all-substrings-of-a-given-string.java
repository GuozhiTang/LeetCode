import java.util.Arrays;

/*
 * @lc app=leetcode id=828 lang=java
 *
 * [828] Count Unique Characters of All Substrings of a Given String
 *
 * https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/description/
 *
 * algorithms
 * Hard (49.10%)
 * Likes:    1046
 * Dislikes: 132
 * Total Accepted:    29.7K
 * Total Submissions: 60.4K
 * Testcase Example:  '"ABC"'
 *
 * Let's define a function countUniqueChars(s) that returns the number of
 * unique characters on s.
 * 
 * 
 * For example if s = "LEETCODE" then "L", "T", "C", "O", "D" are the unique
 * characters since they appear only once in s, therefore countUniqueChars(s) =
 * 5.
 * 
 * 
 * Given a string s, return the sum of countUniqueChars(t) where t is a
 * substring of s.
 * 
 * Notice that some substrings can be repeated so in this case you have to
 * count the repeated ones too.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "ABC"
 * Output: 10
 * Explanation: All possible substrings are: "A","B","C","AB","BC" and "ABC".
 * Evey substring is composed with only unique letters.
 * Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "ABA"
 * Output: 8
 * Explanation: The same as example 1, except countUniqueChars("ABA") = 1.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "LEETCODE"
 * Output: 92
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s consists of uppercase English letters only.
 * 
 * 
 */

// @lc code=start
class Solution {

    // Time: O(n)
    // Space: O(1)
    // 对每一个字符 i ，向前找到相同的字符 j，向后找到相同的字符 k。当前字符对最终结果的贡献为 (i - j) * (k - i)。
    // 这相当于两种方案的拼接：在字符串 A（j到i）当中，字符 i 贡献的次数是（i - j）次。
    // 在字符串 B（i到k）当中，字符 i 贡献的次数是（k - i）次。
    // 那么当两者拼接的时候，字符 i 对子串（j到k）的贡献就是两种方案的乘积（符合乘法公式）。
    // https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/discuss/128952/C%2B%2BJavaPython-One-pass-O(N)
    public int uniqueLetterString(String s) {
        // Corner Cases
        if (s == null || s.length() == 0) {
            return 0;
        }

        // record last two occurrence index for every upper characters
        int[][] indexForLastTwoOccurence = new int[26][2];

        // Initialize all values in `index` to `-1`
        for (int i = 0; i < 26; ++i) {
            Arrays.fill(indexForLastTwoOccurence[i], -1);
        }

        // Loop on string s, for every character `c`, update its last two occurrence index to index[c]
        // Count when loop, if "A" appears at index 3/6/9, we need to count:
        // - For the 1st "A": (6-3)*(3-(-1))
        // - For the 2nd "A": (9-6)*(6-3)
        // - For the 3rd "A": (N-9)*(9-6)
        int result = 0, Length = s.length(), mod = (int)Math.pow(10, 9) + 7;
        for (int index = 0; index < Length; index++) {
            int letter = s.charAt(index) - 'A';
            // if [letter][0] == [letter][1] == -1, which means that there is no other occurence for now
            // add up all current possibilities together
            result = result + (index - indexForLastTwoOccurence[letter][1]) * (indexForLastTwoOccurence[letter][1] - indexForLastTwoOccurence[letter][0]);
            // [letter][0] -> [letter][1] -> [letter][1]
            indexForLastTwoOccurence[letter] = new int[] {indexForLastTwoOccurence[letter][1], index};
        }

        // add up all last two occurence possibilities
        for (int character = 0; character < 26; character++) {
            result = result + (Length - indexForLastTwoOccurence[character][1]) * (indexForLastTwoOccurence[character][1] - indexForLastTwoOccurence[character][0]);
        }

        return result;
    }

    // // Time: O(n^2)
    // // Space: O(n)
    // // Brute Force Solution
    // // https://leetcode.jp/leetcode-828-unique-letter-string-%E8%A7%A3%E9%A2%98%E6%80%9D%E8%B7%AF%E5%88%86%E6%9E%90/
    // public int uniqueLetterString(String s) {
    //     // Corner Cases
    //     if (s == null || s.length() == 0) {
    //         return 0;
    //     }

    //     int result = 0;
    //     for (int startIndex = 0; startIndex < s.length(); startIndex++) { // the start index of substring
    //         // Each letter has a corresponding index in this array
    //         int[] letterCount = new int[26];
    //         int uniqueSum = 0;
    //         // if all 26 letters are not unique, then break
    //         int uniqueLetter = 26;
    //         for (int subStringLength = startIndex; subStringLength < s.length(); subStringLength++) { // the length of substring
    //             if (uniqueLetter == 0) {
    //                 break;
    //             }
    //             int letter = s.charAt(subStringLength) - 'A';
    //             if (letterCount[letter] == 0) {
    //                 uniqueSum++;
    //             } else if (letterCount[letter] == 1) {
    //                 uniqueSum--;
    //                 uniqueLetter--;
    //             }
    //             // int mod = (int)Math.pow(10, 9) + 7;
    //             result += (uniqueSum % 1000000007);
    //             letterCount[letter]++;
    //         }
    //     }

    //     return result;
    // }
}
// @lc code=end

