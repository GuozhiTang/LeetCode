/*
 * @lc app=leetcode id=744 lang=java
 *
 * [744] Find Smallest Letter Greater Than Target
 *
 * https://leetcode.com/problems/find-smallest-letter-greater-than-target/description/
 *
 * algorithms
 * Easy (45.50%)
 * Likes:    513
 * Dislikes: 606
 * Total Accepted:    88.2K
 * Total Submissions: 193.6K
 * Testcase Example:  '["c","f","j"]\n"a"'
 *
 * 
 * Given a list of sorted characters letters containing only lowercase letters,
 * and given a target letter target, find the smallest element in the list that
 * is larger than the given target.
 * 
 * Letters also wrap around.  For example, if the target is target = 'z' and
 * letters = ['a', 'b'], the answer is 'a'.
 * 
 * 
 * Examples:
 * 
 * Input:
 * letters = ["c", "f", "j"]
 * target = "a"
 * Output: "c"
 * 
 * Input:
 * letters = ["c", "f", "j"]
 * target = "c"
 * Output: "f"
 * 
 * Input:
 * letters = ["c", "f", "j"]
 * target = "d"
 * Output: "f"
 * 
 * Input:
 * letters = ["c", "f", "j"]
 * target = "g"
 * Output: "j"
 * 
 * Input:
 * letters = ["c", "f", "j"]
 * target = "j"
 * Output: "c"
 * 
 * Input:
 * letters = ["c", "f", "j"]
 * target = "k"
 * Output: "c"
 * 
 * 
 * 
 * Note:
 * 
 * letters has a length in range [2, 10000].
 * letters consists of lowercase letters, and contains at least 2 unique
 * letters.
 * target is a lowercase letter.
 * 
 * 
 */

// @lc code=start
class Solution {

    // Time: O(logn)
    // Space: O(1)
    // Binary Search
    public char nextGreatestLetter(char[] letters, char target) {
       // Corner Cases
       if (letters == null || letters.length == 0) {
           return ' ';
       }

       int start = 0, end = letters.length - 1;
       while (start + 1 < end) {
           int mid = start + (end - start) / 2;
           // if the mid <= target, then it is not the number we want
           if (letters[mid] <= target) {
               start = mid;
           } else { // if not, then it might be the number we want
               end = mid;
           }
       }

       // We should compare start and end
       if (letters[start] > target) {
           return letters[start];
       } else if (letters[end] > target) {
           return letters[end];
       } else { // this should be the scenario for the rotation
           return letters[0];
       }
    }
}
// @lc code=end

