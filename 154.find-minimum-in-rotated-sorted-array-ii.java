/*
 * @lc app=leetcode id=154 lang=java
 *
 * [154] Find Minimum in Rotated Sorted Array II
 *
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/description/
 *
 * algorithms
 * Hard (40.23%)
 * Likes:    656
 * Dislikes: 187
 * Total Accepted:    158.5K
 * Total Submissions: 393.9K
 * Testcase Example:  '[1,3,5]'
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown
 * to you beforehand.
 * 
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * 
 * Find the minimum element.
 * 
 * The array may contain duplicates.
 * 
 * Example 1:
 * 
 * 
 * Input: [1,3,5]
 * Output: 1
 * 
 * Example 2:
 * 
 * 
 * Input: [2,2,2,0,1]
 * Output: 0
 * 
 * Note:
 * 
 * 
 * This is a follow up problem to Find Minimum in Rotated Sorted Array.
 * Would allow duplicates affect the run-time complexity? How and why?
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
            } else if (nums[mid] > nums[end]){
                start = mid;
            } else {
                // Why here should use end to judge not the begin
                // Maybe because here's to ask for the minimum?
                end--;
            }
        }

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
    //     while (begin < end) {
    //         int mid = begin + (end - begin) / 2;
    //         if (nums[mid] == nums[end]) end--;
    //         else if (nums[mid] < nums[end]) end = mid;
    //         else begin = mid + 1;
    //     }
    //     return nums[begin];
    // }
}
// @lc code=end

