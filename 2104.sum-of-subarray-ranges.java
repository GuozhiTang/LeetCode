import java.util.Stack;

/*
 * @lc app=leetcode id=2104 lang=java
 *
 * [2104] Sum of Subarray Ranges
 *
 * https://leetcode.com/problems/sum-of-subarray-ranges/description/
 *
 * algorithms
 * Medium (57.90%)
 * Likes:    500
 * Dislikes: 19
 * Total Accepted:    18K
 * Total Submissions: 30.1K
 * Testcase Example:  '[1,2,3]'
 *
 * You are given an integer array nums. The range of a subarray of nums is the
 * difference between the largest and smallest element in the subarray.
 * 
 * Return the sum of all subarray ranges of nums.
 * 
 * A subarray is a contiguous non-empty sequence of elements within an
 * array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3]
 * Output: 4
 * Explanation: The 6 subarrays of nums are the following:
 * [1], range = largest - smallest = 1 - 1 = 0 
 * [2], range = 2 - 2 = 0
 * [3], range = 3 - 3 = 0
 * [1,2], range = 2 - 1 = 1
 * [2,3], range = 3 - 2 = 1
 * [1,2,3], range = 3 - 1 = 2
 * So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,3,3]
 * Output: 4
 * Explanation: The 6 subarrays of nums are the following:
 * [1], range = largest - smallest = 1 - 1 = 0
 * [3], range = 3 - 3 = 0
 * [3], range = 3 - 3 = 0
 * [1,3], range = 3 - 1 = 2
 * [3,3], range = 3 - 3 = 0
 * [1,3,3], range = 3 - 1 = 2
 * So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [4,-2,-3,4,1]
 * Output: 59
 * Explanation: The sum of all subarray ranges of nums is 59.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 1000
 * -10^9 <= nums[i] <= 10^9
 * 
 * 
 * 
 * Follow-up: Could you find a solution with O(n) time complexity?
 * 
 */

// @lc code=start
class Solution {


    public long subArrayRanges(int[] nums) {
        // return twoLoops(nums);
        return monotonousIncreaseStack(nums);
    }

    // Time: O(n)
    // Space: O(n)
    // Monotonous Increase Stack
    // No.907
    // https://leetcode.com/problems/sum-of-subarray-ranges/discuss/1624222/JavaC%2B%2BPython-O(n)-solution-detailed-explanation
    private long monotonousIncreaseStack(int[] nums) {
        // Corner Cases
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }

        int length = nums.length;
        int j, k;
        long result = 0;
        Stack<Integer> stack = new Stack<>();

        // find the sum(min)
        // k < j > index
        for (int index = 0; index <= length; index++) {
            while (!stack.isEmpty() && nums[stack.peek()] > (index == length ? Integer.MIN_VALUE : nums[index])) {
                j = stack.pop();
                k = stack.isEmpty() ? -1 : stack.peek();
                result -= (long)nums[j] * (index - j) * (j - k);
            }
            stack.push(index);
        }

        stack.clear();
        // find the sum(max)
        // k > j < index
        for (int index = 0; index <= length; index++) {
            while (!stack.isEmpty() && nums[stack.peek()] < (index == length ? Integer.MAX_VALUE : nums[index])) {
                j = stack.pop();
                k = stack.isEmpty() ? -1 : stack.peek();
                result += (long)nums[j] * (index - j) * (j - k);
            }
            stack.push(index);
        }

        return result;
    }

    // Time: O(n^2)
    // Space: O(1)
    // Two loops solution
    private long twoLoops(int[] nums) {
        // Corner Cases
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }

        long result = 0;
        for(int start = 0; start < nums.length - 1; start++) {
            int max = nums[start], min = nums[start];
            for (int end = start + 1; end < nums.length; end++) {
                max = Math.max(max, nums[end]);
                min = Math.min(min, nums[end]);
                result += (max - min);
            }
        }

        return result;
    }
}
// @lc code=end

