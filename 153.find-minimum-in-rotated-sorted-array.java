/*
 * @lc app=leetcode id=153 lang=java
 *
 * [153] Find Minimum in Rotated Sorted Array
 *
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
 *
 * algorithms
 * Medium (44.15%)
 * Likes:    1582
 * Dislikes: 206
 * Total Accepted:    372.8K
 * Total Submissions: 844.2K
 * Testcase Example:  '[3,4,5,1,2]'
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown
 * to you beforehand.
 * 
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * 
 * Find the minimum element.
 * 
 * You may assume no duplicate exists in the array.
 * 
 * Example 1:
 * 
 * 
 * Input: [3,4,5,1,2] 
 * Output: 1
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [4,5,6,7,0,1,2]
 * Output: 0
 * 
 * 
 */

// @lc code=start
class Solution {

        
    // Time: O(logn)
    // Space: O(1)
    // Binary Search
    public int findMin(int[] nums) {
        // Corner cases
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < nums[end]) {
                end = mid;
            } else {
                start = mid;
            }
        }

        // Judge which one is smaller
        if (nums[start] < nums[end]) {
            return nums[start];
        }
        return nums[end];
    }




    // // Binary Search
    // // Time: O(logn)
    // // Space: O(1)
    // public int findMin(int[] nums) {
    //     // Corner Cases
    //     if (nums == null || nums.length == 0) return -1;

    //     int begin = 0, end = nums.length - 1;
    //     while (begin <= end) {
    //         int mid = begin + (end - begin) / 2;
    //         // if the right part is ascending, then min should be at the left
    //         // but the leftmost could be the min
    //         if (nums[mid] < nums[end]) end = mid;
    //         // else the min should be at the right
    //         else begin = mid + 1;
    //     }
    //     return nums[end];
    // }
}
// @lc code=end

