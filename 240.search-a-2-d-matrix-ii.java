/*
 * @lc app=leetcode id=240 lang=java
 *
 * [240] Search a 2D Matrix II
 *
 * https://leetcode.com/problems/search-a-2d-matrix-ii/description/
 *
 * algorithms
 * Medium (42.32%)
 * Likes:    2524
 * Dislikes: 70
 * Total Accepted:    273.3K
 * Total Submissions: 642.9K
 * Testcase Example:  '[[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]\n5'
 *
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * 
 * 
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * 
 * 
 * Example:
 * 
 * Consider the following matrix:
 * 
 * 
 * [
 * ⁠ [1,   4,  7, 11, 15],
 * ⁠ [2,   5,  8, 12, 19],
 * ⁠ [3,   6,  9, 16, 22],
 * ⁠ [10, 13, 14, 17, 24],
 * ⁠ [18, 21, 23, 26, 30]
 * ]
 * 
 * 
 * Given target = 5, return true.
 * 
 * Given target = 20, return false.
 * 
 */

// @lc code=start
class Solution {
    // Time: O(rows+cols)
    // Space: O(1)
    // starts from the top-right corner
    public boolean searchMatrix(int[][] matrix, int target) {
        // Corner Cases
        if (matrix.length == 0 || matrix == null || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length, cols = matrix[0].length;
        int positionRow = 0, positionCol = cols - 1;

        while (positionRow < rows && positionCol >= 0) {
            if (matrix[positionRow][positionCol] > target) {
                positionCol--;
            } else if (matrix[positionRow][positionCol] < target) {
                positionRow++;
            } else {
                return true;
            }
        }
        return false;
    }
}
// @lc code=end

