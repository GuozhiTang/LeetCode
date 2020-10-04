/*
 * @lc app=leetcode id=64 lang=java
 *
 * [64] Minimum Path Sum
 *
 * https://leetcode.com/problems/minimum-path-sum/description/
 *
 * algorithms
 * Medium (50.76%)
 * Likes:    2278
 * Dislikes: 50
 * Total Accepted:    324K
 * Total Submissions: 630.7K
 * Testcase Example:  '[[1,3,1],[1,5,1],[4,2,1]]'
 *
 * Given a m x n grid filled with non-negative numbers, find a path from top
 * left to bottom right which minimizes the sum of all numbers along its path.
 * 
 * Note: You can only move either down or right at any point in time.
 * 
 * Example:
 * 
 * 
 * Input:
 * [
 * [1,3,1],
 * ⁠ [1,5,1],
 * ⁠ [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 * 
 * 
 */

// @lc code=start
class Solution {
    // Time: O(rows*cols)
    // Space: O(rows*cols)
    // DP
    // dist(cur)= min(dist(upper of cur), dist(left of cur)) + grid(cur)
    public int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid == null || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length, cols = grid[0].length;
        int[][] dist = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (row == 0 && col == 0) {
                    dist[0][0] = grid[0][0];
                } else {
                    dist[row][col] = getMin(getDist(dist, row - 1, col), getDist(dist, row, col - 1)) + grid[row][col];
                }
            }
        }

        return dist[rows - 1][cols - 1];
    }

    private int getDist(int[][] dist, int row, int col) {
        if (row < 0 || col < 0) {
            return Integer.MAX_VALUE;
        }
        return dist[row][col];
    }

    private int getMin(int para1, int para2) {
        if (para1 < para2) {
            return para1;
        }
        return para2;
    }
}
// @lc code=end

