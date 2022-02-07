/*
 * @lc app=leetcode id=33 lang=java
 *
 * [33] Search in Rotated Sorted Array
 *
 * https://leetcode.com/problems/search-in-rotated-sorted-array/description/
 *
 * algorithms
 * Medium (34.94%)
 * Likes:    5906
 * Dislikes: 513
 * Total Accepted:    810.6K
 * Total Submissions: 2.3M
 * Testcase Example:  '[4,5,6,7,0,1,2]\n0'
 *
 * You are given an integer array nums sorted in ascending order, and an
 * integer target.
 * 
 * Suppose that nums is rotated at some pivot unknown to you beforehand (i.e.,
 * [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * 
 * If target is found in the array return its index, otherwise, return -1.
 * 
 * 
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * Example 3:
 * Input: nums = [1], target = 0
 * Output: -1
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * All values of nums are unique.
 * nums is guranteed to be rotated at some pivot.
 * -10^4 <= target <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {

    // Time: O(logn)
    // Space: O(1)
    // Binary Search (Find the pure ascending part)
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
            } else if (nums[start] < nums[mid]) { // `start -> mid` is ascending
                // start -> mid -> pivot -> end
                if (nums[mid] >= target && target >= nums[start]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                // start -> pivot -> mid -> end
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }

        if (nums[start] == target) {
            return start;
        } else if (nums[end] == target) {
            return end;
        }
        
        return -1;
    }

    /****************************************************/

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
    //         } else if (nums[start] < nums[mid]) {
    //             // the left part
    //             if (target >= nums[start] && target <= nums[mid]) {
    //                 end = mid;
    //             } else {
    //                 start = mid;
    //             }
    //         } else {
    //             // the right part
    //             if (target >= nums[mid] && target <= nums[end]) {
    //                 start = mid;
    //             } else {
    //                 end = mid;
    //             }
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

