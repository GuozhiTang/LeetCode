/*
 * @lc app=leetcode id=238 lang=java
 *
 * [238] Product of Array Except Self
 *
 * https://leetcode.com/problems/product-of-array-except-self/description/
 *
 * algorithms
 * Medium (58.47%)
 * Likes:    3872
 * Dislikes: 329
 * Total Accepted:    409.8K
 * Total Submissions: 694.8K
 * Testcase Example:  '[1,2,3,4]'
 *
 * Given an array nums of n integers where n > 1,  return an array output such
 * that output[i] is equal to the product of all the elements of nums except
 * nums[i].
 * 
 * Example:
 * 
 * 
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * 
 * 
 * Constraint: It's guaranteed that the product of the elements of any prefix
 * or suffix of the array (including the whole array) fits in a 32 bit
 * integer.
 * 
 * Note: Please solve it without division and in O(n).
 * 
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does
 * not count as extra space for the purpose of space complexity analysis.)
 * 
 */

// @lc code=start
class Solution {
    // O(n)
    // O(1)
    // Math
    public int[] productExceptSelf(int[] nums) {
        // Corner Cases
        if (nums.length == 0 || nums == null) {
            return new int[] {};
        }

        int len = nums.length;
        int[] result = new int[len];

        // calculate from left -> right and store the results in result
        int leftToR = 1;
        for (int index = 0; index < len; index++) {
            if (index > 0) {
                leftToR *= nums[index - 1];
            }
            result[index] = leftToR;
        }

        // calculte from right -> left and store the final results in result
        int rightToL = 1;
        for (int index = len - 1; index >= 0; index--) {
            if (index < len - 1) {
                rightToL *= nums[index + 1];
            }
            result[index] *= rightToL;
        }

        return result;
    }
}
// @lc code=end

