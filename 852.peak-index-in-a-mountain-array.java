/*
 * @lc app=leetcode id=852 lang=java
 *
 * [852] Peak Index in a Mountain Array
 *
 * https://leetcode.com/problems/peak-index-in-a-mountain-array/description/
 *
 * algorithms
 * Easy (70.83%)
 * Likes:    461
 * Dislikes: 956
 * Total Accepted:    126K
 * Total Submissions: 177.9K
 * Testcase Example:  '[0,1,0]'
 *
 * Let's call an array A a mountain if the following properties hold:
 * 
 * 
 * A.length >= 3
 * There exists some 0 < i < A.length - 1 such that A[0] < A[1] < ... A[i-1] <
 * A[i] > A[i+1] > ... > A[A.length - 1]
 * 
 * 
 * Given an array that is definitely a mountain, return any i such that A[0] <
 * A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].
 * 
 * Example 1:
 * 
 * 
 * Input: [0,1,0]
 * Output: 1
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [0,2,1,0]
 * Output: 1
 * 
 * 
 * Note:
 * 
 * 
 * 3 <= A.length <= 10000
 * 0 <= A[i] <= 10^6
 * A is a mountain, as defined above.
 * 
 * 
 */

// @lc code=start
class Solution {

    // Time: O(logn)
    // Space: O(1)
    // Binary Search
    public int peakIndexInMountainArray(int[] A) {
        // Corner Cases
        if (A == null || A.length == 0) {
            return -1;
        }

        int start = 0, end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            // if ascending
            if (A[mid] < A[mid + 1]) {
                start = mid;
            } else { // if decending
                end = mid;
            }
        }

        if (A[start] < A[end]) {
            return end;
        } else {
            return start;
        }
    }



    // // Binary Search
    // // Time: O(logn)
    // // Space: O(1)
    // public int peakIndexInMountainArray(int[] A) {
    //     // Corner Cases
    //     if (A == null || A.length == 0) return -1;

    //     int begin = 0, end = A.length - 1;
    //     while (begin < end) {
    //         int mid = begin + (end - begin) / 2;
    //         // if it is ascending towards the right
    //         if (A[mid] < A[mid + 1]) begin = mid + 1;
    //         else end = mid;
    //     }
    //     return end;
    // }
}
// @lc code=end

