/*
 * @lc app=leetcode id=69 lang=java
 *
 * [69] Sqrt(x)
 *
 * https://leetcode.com/problems/sqrtx/description/
 *
 * algorithms
 * Easy (34.27%)
 * Likes:    1594
 * Dislikes: 2097
 * Total Accepted:    625.4K
 * Total Submissions: 1.8M
 * Testcase Example:  '4'
 *
 * Implement int sqrt(int x).
 * 
 * Compute and return the square root of x, where x is guaranteed to be a
 * non-negative integer.
 * 
 * Since the return type is an integer, the decimal digits are truncated and
 * only the integer part of the result is returned.
 * 
 * Example 1:
 * 
 * 
 * Input: 4
 * Output: 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since 
 * the decimal part is truncated, 2 is returned.
 * 
 * 
 */

// @lc code=start
class Solution {

    // Time: O(logn)
    // Space: O(1)
    // Binary Search
    public int mySqrt(int x) {
       // Corner Cases
       if (x <= 0) {
           return 0;
       } 
       if (x == 1) {
           return 1;
       }

       int start = 0, end = x;
       while (start + 1 < end) {
           int mid = start + (end - start) / 2;
           System.out.println("mid = " + mid + ", when start = " + start + " and end = " + end);
           if (mid == x / mid) {
               return mid;
           } else if (mid < x / mid) {
               start = mid;
           } else {
               end = mid;
           }
       }

       if (end > x / end) { // if the larger one's square is larger than x
           return start;
       } else { // if the larger one's square is still smaller than x
           return end;
       }
    }
}
// @lc code=end

