/*
 * @lc app=leetcode id=34 lang=java
 *
 * [34] Find First and Last Position of Element in Sorted Array
 *
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
 *
 * algorithms
 * Medium (35.02%)
 * Likes:    2609
 * Dislikes: 116
 * Total Accepted:    422.5K
 * Total Submissions: 1.2M
 * Testcase Example:  '[5,7,7,8,8,10]\n8'
 *
 * Given an array of integers nums sorted in ascending order, find the starting
 * and ending position of a given target value.
 * 
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * 
 * If the target is not found in the array, return [-1, -1].
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * 
 */

// @lc code=start
class Solution {

    // Time: O(logn)
    // Space: O(n)
    // Binary Search
    public int[] searchRange(int[] nums, int target) {
        // Corner Cases
        if (nums == null || nums.length == 0) {
            return new int[] {-1, -1};
        }

        // Seach for the left index of the range
        int leftIndex = -1;
        int startLeft = 0, endLeft = nums.length - 1;
        while (startLeft + 1 < endLeft) {
            int midLeft = startLeft + (endLeft - startLeft) / 2;
            if (nums[midLeft] < target) {
                startLeft = midLeft;
            } else {
                endLeft = midLeft;
            }
        }
        // Judge the left one first
        if (nums[startLeft] == target) {
            leftIndex = startLeft;
        } else if (nums[endLeft] == target) {
            leftIndex = endLeft;
        }

        // Search for the right index of the range
        int rightIndex = -1;
        int startRight = 0, endRight = nums.length - 1;
        while (startRight + 1 < endRight) {
            int midRight = startRight + (endRight - startRight) / 2;
            if (nums[midRight] > target) {
                endRight = midRight;
            } else {
                startRight = midRight;
            }
        }
        // Judge the right one first
        if (nums[endRight] == target) {
            rightIndex = endRight;
        } else if (nums[startRight] == target) {
            rightIndex = startRight;
        }

        return new int[] {leftIndex, rightIndex};
    }



    /* ============================== */

    // // Time: O(logn)
    // // Space: O(1)
    // // Binary Search
    // public int[] searchRange(int[] nums, int target) {
    //     // Corner cases
    //     if (nums == null || nums.length == 0) {
    //         return new int[] {-1, -1};
    //     }

    //     // Search for the leftIndex of range
    //     int leftIndex = -1;
    //     int startLeft = 0, endLeft = nums.length - 1;
    //     while (startLeft + 1 < endLeft) {
    //         int midleft = startLeft + (endLeft - startLeft) / 2;
    //         if (nums[midleft] < target) {
    //             startLeft = midleft;
    //         } else {
    //             endLeft = midleft;
    //         }
    //     }
    //     // Judge the left one first
    //     if (nums[startLeft] == target) {
    //         leftIndex = startLeft;
    //     } else if (nums[endLeft] == target) {
    //         leftIndex = endLeft;
    //     }

    //     // Search for the rightIndex of range
    //     int rightIndex = -1;
    //     int startRight = 0, endRight = nums.length - 1;
    //     while (startRight + 1 < endRight) {
    //         int midRight = startRight + (endRight - startRight) / 2;
    //         if (nums[midRight] > target) {
    //             endRight = midRight;
    //         } else {
    //             startRight = midRight;
    //         }
    //     }
    //     // Judge the right one first
    //     if (nums[endRight] == target) {
    //         rightIndex = endRight;
    //     } else if (nums[startRight] == target) {
    //         rightIndex = startRight;
    //     }
    //     return new int[] {leftIndex, rightIndex};
    // }

    /* ============================== */

    // // Time: O()
    // // Space: O()
    // // Binary Search
    // public int[] searchRange(int[] nums, int target) {
    //     int[] res = new int[2];
    //     // Corner Cases
    //     if (nums == null || nums.length == 0) {
    //         res[0] = -1;
    //         res[1] = -1;
    //         return res;
    //     }

    //     int right = findRightMost(nums, target);

    //     int left = findLeftMost(nums, target);

    //     res[0] = left;
    //     res[1] = right;

    //     return res;
    // }

    // private int findRightMost(int[] nums, int target) {
    //     int begin = 0, end = nums.length - 1;
    //     while (begin + 1 < end) {
    //         int mid = begin + (end - begin) / 2;
    //         if (nums[mid] == target) begin = mid;
    //         else if (nums[mid] < target) begin = mid;
    //         else end = mid;
    //     }
    //     if (nums[begin] == target) return begin;
    //     if (nums[end] == target) return end;

    //     return -1;
    // }

    // private int findLeftMost(int[] nums, int target) {
    //     int begin = 0, end = nums.length - 1;
    //     while (begin + 1 < end) {
    //         int mid = begin + (end - begin) / 2;
    //         if (nums[mid] == target) end = mid;
    //         else if (nums[mid] < target) begin = mid;
    //         else end = mid;
    //     }
    //     if (nums[begin] == target) return begin;
    //     if (nums[end] == target) return end;
    //     return -1;
    // }

}
// @lc code=end

