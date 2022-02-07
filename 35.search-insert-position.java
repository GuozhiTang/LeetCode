/*
 * @lc app=leetcode id=35 lang=java
 *
 * [35] Search Insert Position
 *
 * https://leetcode.com/problems/search-insert-position/description/
 *
 * algorithms
 * Easy (42.69%)
 * Likes:    2729
 * Dislikes: 265
 * Total Accepted:    695.4K
 * Total Submissions: 1.6M
 * Testcase Example:  '[1,3,5,6]\n5'
 *
 * Given a sorted array and a target value, return the index if the target is
 * found. If not, return the index where it would be if it were inserted in
 * order.
 * 
 * You may assume no duplicates in the array.
 * 
 * Example 1:
 * 
 * 
 * Input: [1,3,5,6], 5
 * Output: 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [1,3,5,6], 2
 * Output: 1
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: [1,3,5,6], 7
 * Output: 4
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: [1,3,5,6], 0
 * Output: 0
 * 
 * 
 */

// @lc code=start
class Solution {


    // Time: O(logn)
    // Space: O(1)
    // Binary Search
    public int searchInsert(int[] nums, int target) {
        // Corner Cases
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        // Then the start and end will be the two which are the closest to the target
        // If target is still smaller than the left
        if (nums[start] >= target) {
            return start;
        // If the target is smaller than the right
        } else if (nums[end] >= target) {
            return end;
        } else {
            return end + 1;
        }
    }


    // // Time: O(logn)
    // // Space: O(1)
    // // BinarySearch
    // public int searchInsert(int[] nums, int target) {
    //     // Corner cases
    //     if (nums == null || nums.length == 0) {
    //         return -1;
    //     }

    //     int start = 0, end = nums.length - 1;
    //     while (start + 1 < end) {
    //         int mid = start + (end - start) / 2;
    //         if (nums[mid] == target) {
    //             return mid;
    //         } else if (nums[mid] < target) {
    //             start = mid;
    //         } else {
    //             end = mid;
    //         }
    //     }

    //     // Then the start and end will be the two which are the closest to the target
    //     // If target is still smaller than the left
    //     if (nums[start] >= target) {
    //         return start;
    //     }
    //     // If the target is smaller than the right
    //     else if (nums[end] >= target) {
    //         return end;
    //     } else {
    //         return end + 1;
    //     }
    // }
}
// @lc code=end

