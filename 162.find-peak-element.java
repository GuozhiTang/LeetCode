/*
 * @lc app=leetcode id=162 lang=java
 *
 * [162] Find Peak Element
 *
 * https://leetcode.com/problems/find-peak-element/description/
 *
 * algorithms
 * Medium (42.52%)
 * Likes:    1284
 * Dislikes: 1742
 * Total Accepted:    306.8K
 * Total Submissions: 721.5K
 * Testcase Example:  '[1,2,3,1]'
 *
 * A peak element is an element that is greater than its neighbors.
 * 
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element
 * and return its index.
 * 
 * The array may contain multiple peaks, in that case return the index to any
 * one of the peaks is fine.
 * 
 * You may imagine that nums[-1] = nums[n] = -∞.
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index
 * number 2.
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 1 or 5 
 * Explanation: Your function can return either index number 1 where the peak
 * element is 2, 
 * or index number 5 where the peak element is 6.
 * 
 * 
 * Note:
 * 
 * Your solution should be in logarithmic complexity.
 * 
 */

// @lc code=start
class Solution {

    // Time: O(logn)
    // Space: O(1)
    // Binary Search
    public int findPeakElement(int[] nums) {
        // Corner Case
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            // if ascending
            if (nums[mid] < nums[mid + 1]) {
                start = mid;
            } else { // if decending
                end = mid;
            }
        }

        if (nums[start] > nums[end]) {
            return start;
        } else {
            return end;
        }
    }



    // Binary Search
    // Time: O(logn)
    // Space: O(1)
    // public int findPeakElement(int[] nums) {
    //     // Corner Cases
    //     if (nums == null || nums.length == 0) return -1;

    //     int begin = 0, end = nums.length - 1;
    //     while (begin < end) {
    //         int mid = begin + (end - begin) / 2;
    //         // if ascending towrds the right
    //         if (nums[mid] < nums[mid + 1]) {
    //             begin = mid + 1;
    //         } else { // if ascending towards the left
    //             end = mid;
    //         }
    //     }
    //     return end;
    // }
}
// @lc code=end

