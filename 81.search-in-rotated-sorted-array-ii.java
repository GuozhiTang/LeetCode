/*
 * @lc app=leetcode id=81 lang=java
 *
 * [81] Search in Rotated Sorted Array II
 *
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/
 *
 * algorithms
 * Medium (33.02%)
 * Likes:    1519
 * Dislikes: 493
 * Total Accepted:    255.6K
 * Total Submissions: 773.9K
 * Testcase Example:  '[2,5,6,0,0,1,2]\n0'
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown
 * to you beforehand.
 * 
 * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
 * 
 * You are given a target value to search. If found in the array return true,
 * otherwise return false.
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 * 
 * Follow up:
 * 
 * 
 * This is a follow up problem to Search in Rotated Sorted Array, where nums
 * may contain duplicates.
 * Would this affect the run-time complexity? How and why?
 * 
 * 
 */

// @lc code=start
class Solution {
    // Time: O(logn)
    // Space: O(1)
    // Binary Search
    public boolean search(int[] nums, int target) {
        // Corner cases
        if (nums == null || nums.length == 0) {
            return false;
        }

        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[start] < nums[mid]) {
                // the left part
                if (target >= nums[start] && target <= nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else if (nums[start] > nums[mid]) { // two judge the nums[mid] and nums[start]
                // the right part
                if (target >= nums[mid] && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            } else {
                // if nums[start] == nums[end]
                start++;
            }
        }

        // Judge the adjacent ones
        if (nums[start] == target || nums[end] == target) {
            return true;
        }
        return false;
    }
}
// @lc code=end

