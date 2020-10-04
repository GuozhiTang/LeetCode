import java.util.LinkedList;
import java.util.Queue;

/*
 * @lc app=leetcode id=994 lang=java
 *
 * [994] Rotting Oranges
 *
 * https://leetcode.com/problems/rotting-oranges/description/
 *
 * algorithms
 * Easy (47.09%)
 * Likes:    981
 * Dislikes: 148
 * Total Accepted:    59.1K
 * Total Submissions: 125.6K
 * Testcase Example:  '[[2,1,1],[1,1,0],[0,1,1]]'
 *
 * In a given grid, each cell can have one of three values:
 * 
 * 
 * the value 0 representing an empty cell;
 * the value 1 representing a fresh orange;
 * the value 2 representing a rotten orange.
 * 
 * 
 * Every minute, any fresh orange that is adjacent (4-directionally) to a
 * rotten orange becomes rotten.
 * 
 * Return the minimum number of minutes that must elapse until no cell has a
 * fresh orange.  If this is impossible, return -1 instead.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation:  The orange in the bottom left corner (row 2, column 0) is
 * never rotten, because rotting only happens 4-directionally.
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: [[0,2]]
 * Output: 0
 * Explanation:  Since there are already no fresh oranges at minute 0, the
 * answer is just 0.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * grid[i][j] is only 0, 1, or 2.
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    // Time: O(row*col)
    // Space: O(row*col)
    // BFS
    public int orangesRotting(int[][] grid) {
        // Corner Cases
        if (grid.length == 0 || grid == null || grid[0].length == 0) return -1;

        int row = grid.length, col = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int count_fresh = 0;

        // put the rotten oranges' positions into the queue
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 2) queue.offer(new int[] {i, j});
                else if (grid[i][j] == 1) count_fresh++;
            }
        }
        // if there is no fresh orange
        if (count_fresh == 0) return 0;

        int count = 0;
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        // BFS starts from the initially rotten oranges
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // get the position of the rotten orange
                int[] rotten = queue.poll();
                for (int[] dir : dirs) {
                    int x = rotten[0] + dir[0];
                    int y = rotten[1] + dir[1];
                    // check the corner cases
                    if (x < 0 || y < 0 || x >= row || y >= col || grid[x][y] == 2 || grid[x][y] == 0) continue;

                    // change status of current orange to rotten
                    grid[x][y] = 2;
                    // put the new rotten orange into the queue
                    queue.offer(new int[] {x, y});
                    // there decreases one fresh orange
                    count_fresh--;
                }
            }
        }
        // becase when count == 1, we pull the first rotten orange
        return count_fresh == 0 ? count - 1 : -1;
    }
}
// @lc code=end

