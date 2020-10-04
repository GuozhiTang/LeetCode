/*
 * @lc app=leetcode id=378 lang=java
 *
 * [378] Kth Smallest Element in a Sorted Matrix
 *
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
 *
 * algorithms
 * Medium (52.09%)
 * Likes:    1769
 * Dislikes: 106
 * Total Accepted:    152.4K
 * Total Submissions: 292.5K
 * Testcase Example:  '[[1,5,9],[10,11,13],[12,13,15]]\n8'
 *
 * Given a n x n matrix where each of the rows and columns are sorted in
 * ascending order, find the kth smallest element in the matrix.
 * 
 * 
 * Note that it is the kth smallest element in the sorted order, not the kth
 * distinct element.
 * 
 * 
 * Example:
 * 
 * matrix = [
 * ⁠  [ 1,  5,  9],
 * ⁠  [10, 11, 13],
 * ⁠  [12, 13, 15]
 * ],
 * k = 8,
 * 
 * return 13.
 * 
 * 
 * 
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ n^2.
 */

// @lc code=start
class Solution {
    // Binary Search
    // Time: O(mlogn), m = matrix.length, n = matrix.length * matrix[0].length
    // Space: O(1)
    public int kthSmallest(int[][] matrix, int k) {
        // Corner Cases
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return -1;

        int begin = matrix[0][0], end = matrix[matrix.length - 1][matrix[0].length - 1];
        while (begin <= end) {
            int mid = begin + (end - begin) / 2;
            int j = matrix[0].length - 1, count = 0;
            // count the numsber less that kth layer by layer
            for (int i = 0; i < matrix.length; i++) {
                while (j >= 0 && matrix[i][j] > mid) j--;
                count += (j + 1);
            }
            if (count < k) begin = mid + 1;
            else end = mid - 1;
        }
        return begin;
    }
}
// @lc code=end

