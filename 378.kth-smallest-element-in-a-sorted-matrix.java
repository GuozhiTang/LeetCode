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

    // Time: O(mnlog(mn))
    // Space: O(1)
    // Binary Search - Use binary search to get the mid, then compare mid's smaller num amount with k.
    public int kthSmallest(int[][] matrix, int k) {
        // Corner Cases
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return -1;
        }

        // Use binary search to get the mid, then compare mid's smaller num amount with k.
        int row = matrix.length, col = matrix[0].length;
        int start = matrix[0][0], end = matrix[row - 1][col - 1];
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int count = count(matrix, mid, row, col);
            if (count < k) {
                start = mid;
            } else {
                end = mid;
            }
        }

        // judge if right one is the target value
        if (count(matrix, end, row, col) <= k - 1) {
            return end;
        } else {
            return start;
        }
    }

    // To count the current mid's smaller num amount
    public int count(int[][] matrix, int target, int row, int col) {
        int result = 0;
        for (int rowCount = 0; rowCount < row; rowCount++) {
            int colCount = 0;
            while (colCount < col && matrix[rowCount][colCount] < target) {
                colCount++;
            }
            result += colCount;
        }
        return result;
    }




    // // Binary Search
    // // Time: O(mlogn), m = matrix.length, n = matrix.length * matrix[0].length
    // // Space: O(1)
    // public int kthSmallest(int[][] matrix, int k) {
    //     // Corner Cases
    //     if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return -1;

    //     int begin = matrix[0][0], end = matrix[matrix.length - 1][matrix[0].length - 1];
    //     while (begin <= end) {
    //         int mid = begin + (end - begin) / 2;
    //         int j = matrix[0].length - 1, count = 0;
    //         // count the numsber less that kth layer by layer
    //         for (int i = 0; i < matrix.length; i++) {
    //             while (j >= 0 && matrix[i][j] > mid) j--;
    //             count += (j + 1);
    //         }
    //         if (count < k) begin = mid + 1;
    //         else end = mid - 1;
    //     }
    //     return begin;
    // }
}
// @lc code=end

