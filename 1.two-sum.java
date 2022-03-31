import java.util.HashMap;

/*
 * @lc app=leetcode id=1 lang=java
 *
 * [1] Two Sum
 *
 * https://leetcode.com/problems/two-sum/description/
 *
 * algorithms
 * Easy (48.15%)
 * Likes:    30673
 * Dislikes: 961
 * Total Accepted:    6.3M
 * Total Submissions: 13M
 * Testcase Example:  '[2,7,11,15]\n9'
 *
 * Given an array of integers nums and an integer target, return indices of the
 * two numbers such that they add up to target.
 * 
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 * 
 * You can return the answer in any order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 * Only one valid answer exists.
 * 
 * 
 * 
 * Follow-up: Can you come up with an algorithm that is less than O(n^2) time
 * complexity?
 */

// @lc code=start
class Solution {

    public int[] twoSum(int[] nums, int target) {
        // int[] result = bruteForceSol(nums, target);
        int[] result = hashMapSolution(nums, target);
        return result;
    }

    // Time: O(n)
    // Space: O(n)
    // HashMap solution
    private int[] hashMapSolution(int[] nums, int target) {
        // Corner Cases
        if (nums == null || nums.length == 0) {
            return new int[] {};
        }

        // nums[index] - index
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int index = 0; index < nums.length; index++) {
            if (map.containsKey(target - nums[index])) {
                return new int[] {index, map.get(target - nums[index])};
            }
            map.put(nums[index], index);
        }

        return new int[] {};
    }

    // Time: O(n^2)
    // Space: O(1)
    // Brute Force solution
    private int[] bruteForceSol(int[] nums, int target) {
        // Corner Cases
        if (nums == null || nums.length == 0) {
            return new int[] {};
        }

        for (int pointer1 = 0; pointer1 < nums.length - 1; pointer1++) {
            for (int pointer2 = pointer1 + 1; pointer2 < nums.length; pointer2++) {
                if (nums[pointer1] + nums[pointer2] == target) {
                    return new int[] {pointer1, pointer2};
                }
            }
        }

        return new int[] {};
    }
}
// @lc code=end

