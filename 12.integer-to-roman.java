/*
 * @lc app=leetcode id=12 lang=java
 *
 * [12] Integer to Roman
 *
 * https://leetcode.com/problems/integer-to-roman/description/
 *
 * algorithms
 * Medium (58.82%)
 * Likes:    2979
 * Dislikes: 3923
 * Total Accepted:    664.6K
 * Total Submissions: 1.1M
 * Testcase Example:  '3'
 *
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D
 * and M.
 * 
 * 
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 
 * For example, 2 is written as II in Roman numeral, just two one's added
 * together. 12 is written as XII, which is simply X + II. The number 27 is
 * written as XXVII, which is XX + V + II.
 * 
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII. Instead, the number four is
 * written as IV. Because the one is before the five we subtract it making
 * four. The same principle applies to the number nine, which is written as IX.
 * There are six instances where subtraction is used:
 * 
 * 
 * I can be placed before V (5) and X (10) to make 4 and 9. 
 * X can be placed before L (50) and C (100) to make 40 and 90. 
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * 
 * 
 * Given an integer, convert it to a roman numeral.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: num = 3
 * Output: "III"
 * Explanation: 3 is represented as 3 ones.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: num = 58
 * Output: "LVIII"
 * Explanation: L = 50, V = 5, III = 3.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: num = 1994
 * Output: "MCMXCIV"
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= num <= 3999
 * 
 * 
 */

// @lc code=start
class Solution {
    // Time: O(1)
    // Space: O(n)
    // General Logic
    public String intToRoman(int num) {
        // Corner Cases
        if (num == 0) {
            return " ";
        }

        StringBuilder finalString = new StringBuilder();

        while (num > 0) {
            if (1000 <= num) {
                int count = num / 1000;
                num -= count * 1000;
                while (count > 0) {
                    finalString.append("M");
                    count--;
                }
            } else if (500 <= num && num <= 1000) {
                if (900 <= num && num < 1000) {
                    finalString.append("CM");
                    num -= 900;
                    continue;
                }
                int count = num / 500;
                num -= count * 500;
                while (count > 0) {
                    finalString.append("D");
                    count--;
                }
            } else if (100 <= num && num < 500) {
                if (400 <= num && num < 500) {
                    finalString.append("CD");
                    num -= 400;
                    continue;
                }
                int count = num / 100;
                num -= count * 100;
                while (count > 0) {
                    finalString.append("C");
                    count--;
                }
            } else if (50 <= num && num < 100) {
                if (90 <= num && num < 100) {
                    finalString.append("XC");
                    num -= 90;
                    continue;
                }
                int count = num / 50;
                num -= count * 50;
                while (count > 0) {
                    finalString.append("L");
                    count--;
                }
            } else if (10 <= num && num < 50) {
                if (40 <= num && num < 50) {
                    finalString.append("XL");
                    num -= 40;
                    continue;
                }
                int count = num / 10;
                num -= count * 10;
                while (count > 0) {
                    finalString.append("X");
                    count--;
                }
            } else if (5 <= num && num < 10) {
                if (num == 9) {
                    finalString.append("IX");
                    num -= 9;
                    continue;
                }
                int count = num / 5;
                num -= count * 5;
                while (count > 0) {
                    finalString.append("V");
                    count--;
                }
            } else if (1 <= num && num < 5) {
                if (num == 4) {
                    finalString.append("IV");
                    num -= 4;
                    continue;
                }
                int count = num / 1;
                num -= count * 1;
                while (count > 0) {
                    finalString.append("I");
                    count--;
                }
            }
        }

        return finalString.toString();
    }
}
// @lc code=end

