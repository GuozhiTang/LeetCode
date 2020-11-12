/*
 * @lc app=leetcode id=74 lang=java
 *
 * [74] Search a 2D Matrix
 *
 * https://leetcode.com/problems/search-a-2d-matrix/description/
 *
 * algorithms
 * Medium (36.65%)
 * Likes:    2508
 * Dislikes: 177
 * Total Accepted:    386.6K
 * Total Submissions: 1M
 * Testcase Example:  '[[1,3,5,7],[10,11,16,20],[23,30,34,50]]\n3'
 *
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * 
 * 
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the
 * previous row.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,50]], target = 3
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,50]], target = 13
 * Output: false
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: matrix = [], target = 0
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == matrix.length
 * n == matrix[i].length
 * 0 <= m, n <= 100
 * -10^4 <= matrix[i][j], target <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {

    // Time: O(logmn)
    // Space: O(1)
    // Binary Search - We could regard the matrix as a linear 1D ascending array
    public boolean searchMatrix(int[][] matrix, int target) {
        // Corner Cases
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        // Try to use Binary Search to get the index of target if it exists
        // Try to convert linear index to 2D matrix
        int length = matrix[0].length, width = matrix.length;
        int start = 0, end = length * width - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[mid / length][mid % length] == target) {
                return true;
            } else if (matrix[mid / length][mid % length] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        // Right now start and end are two index in a linear array
        if (matrix[start / length][start % length] == target || matrix[end / length][end % length] == target) {
            return true;
        } else {
            return false;
        }
    }
}
// @lc code=end

