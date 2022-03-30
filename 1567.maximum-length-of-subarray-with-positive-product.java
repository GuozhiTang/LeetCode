/*
 * @lc app=leetcode id=1567 lang=java
 *
 * [1567] Maximum Length of Subarray With Positive Product
 *
 * https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product/description/
 *
 * algorithms
 * Medium (42.08%)
 * Likes:    1255
 * Dislikes: 19
 * Total Accepted:    42.2K
 * Total Submissions: 98.6K
 * Testcase Example:  '[1,-2,-3,4]'
 *
 * Given an array of integers nums, find the maximum length of a subarray where
 * the product of all its elements is positive.
 * 
 * A subarray of an array is a consecutive sequence of zero or more values
 * taken out of that array.
 * 
 * Return the maximum length of a subarray with positive product.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,-2,-3,4]
 * Output: 4
 * Explanation: The array nums already has a positive product of 24.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [0,1,-2,-3,-4]
 * Output: 3
 * Explanation: The longest subarray with positive product is [1,-2,-3] which
 * has a product of 6.
 * Notice that we cannot include 0 in the subarray since that'll make the
 * product 0 which is not positive.
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [-1,-2,-3,0,1]
 * Output: 2
 * Explanation: The longest subarray with positive product is [-1,-2] or
 * [-2,-3].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {

    // Time: O(n)
    // Space: O(1)
    // Once it meets negative value, the length +1, but convert from positive to negative.
    // Then if the next one is positive, +1 for both positive and negative length.
    // Negative starts to be considered when it > 0.
    // https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product/discuss/820072/EASY-soultion-with-DRY-RUN-JAVA
    public int getMaxLen(int[] nums) {
        // Corner Cases
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int positiveLength = 0, negativeLength = 0;
        int result = 0;

        for (int num : nums) {
            if (num == 0) {
                positiveLength = 0;
                negativeLength = 0;
            } else if (num > 0) {
                positiveLength++;
                negativeLength = negativeLength == 0 ? 0 : negativeLength + 1;
            } else if (num < 0) {
                int temp = positiveLength;
                positiveLength = negativeLength == 0 ? 0 : negativeLength + 1;
                negativeLength = temp + 1;
            }
            result = Math.max(result, positiveLength);
        }

        return result;
    }
}
// @lc code=end

