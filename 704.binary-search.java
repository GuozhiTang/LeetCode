/*
 * @lc app=leetcode id=704 lang=java
 *
 * [704] Binary Search
 *
 * https://leetcode.com/problems/binary-search/description/
 *
 * algorithms
 * Easy (52.50%)
 * Likes:    822
 * Dislikes: 48
 * Total Accepted:    164.3K
 * Total Submissions: 312.9K
 * Testcase Example:  '[-1,0,3,5,9,12]\n9'
 *
 * Given a sorted (in ascending order) integer array nums of n elements and a
 * target value, write a function to search target in nums. If target exists,
 * then return its index, otherwise return -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * You may assume that all elements in nums are unique.
 * n will be in the range [1, 10000].
 * The value of each element in nums will be in the range [-9999, 9999].
 * 
 * 
 */

// @lc code=start
class Solution {

    // Time: O(logn)
    // Space: O(1)
    // Binary Serach
    public int search(int[] nums, int target) {
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

        if (nums[start] == target) {
            return start;
        } else if (nums[end] == target) {
            return end;
        }
        
        return -1;
    }


    // // Time: O(logn)
    // // Space: O(1)
    // // Binary Search
    // public int search(int[] nums, int target) {
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

    //     if (nums[start] == target) {
    //         return start;
    //     } else if (nums[end] == target) {
    //         return end;
    //     }
    //     return -1;
    // }
}
// @lc code=end

