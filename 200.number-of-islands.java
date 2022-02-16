import java.util.LinkedList;
import java.util.Queue;

/*
 * @lc app=leetcode id=200 lang=java
 *
 * [200] Number of Islands
 *
 * https://leetcode.com/problems/number-of-islands/description/
 *
 * algorithms
 * Medium (44.67%)
 * Likes:    4294
 * Dislikes: 158
 * Total Accepted:    567.7K
 * Total Submissions: 1.3M
 * Testcase Example:  '[["1","1","1","1","0"],["1","1","0","1","0"],["1","1","0","0","0"],["0","0","0","0","0"]]'
 *
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of
 * islands. An island is surrounded by water and is formed by connecting
 * adjacent lands horizontally or vertically. You may assume all four edges of
 * the grid are all surrounded by water.
 * 
 * Example 1:
 * 
 * 
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 * 
 * Output: 1
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 * 
 * Output: 3
 * 
 */

// @lc code=start
class Solution {

    private final char ISLAND = '1';
    private final char WATER = '0';
    private final int[][] DIRS = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // right-down-left-up

    // Time: O(rows*cols)
    // Space: O(rows*cols)
    // BFS
    public int numIslands(char[][] grid) {
        // Corner Cases
        if (grid.length == 0 || grid == null || grid[0].length == 0) {
            return -1;
        }

        int rows = grid.length, cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int countIsland = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == ISLAND) {
                    queue.offer(new int[] {row, col});
                    grid[row][col] = WATER;
                    bfs(queue, grid, rows, cols);
                    countIsland++;
                }
            }
        }
        return countIsland;
    }

    private void bfs(Queue<int[]> queue, char[][] grid, int rows, int cols) {
        while(!queue.isEmpty()) {
            int[] position = queue.poll();

            for (int[] DIR : DIRS) {
                int positionX = position[0] + DIR[0];
                int positionY = position[1] + DIR[1];

                if (!isValid(grid, positionX, positionY, rows, cols)) {
                    continue;
                }

                grid[positionX][positionY] = WATER;
                queue.offer(new int[] {positionX, positionY});
            }
        }
    }

    private boolean isValid(char[][] grid, int positionX, int positionY, int rows, int cols) {
        if (positionX < 0 || positionX >= rows
        || positionY < 0 || positionY >= cols
        || grid[positionX][positionY] == WATER) {
            return false;
        }
        return true;
    }

    /*************************************************************/

    // private final char ISLAND = '1';
    // private final char WATER = '0';
    // private final int[][] DIRS = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // right-down-left-up

    // // Time: O(row*col)
    // // Space: O(row*col)
    // // BFS
    // public int numIslands(char[][] grid) {
    //     // Corner Cases
    //     if (grid.length == 0 || grid == null || grid[0].length == 0) {
    //         return 0;
    //     }

    //     int rows = grid.length, cols = grid[0].length;
    //     Queue<int[]> queue = new LinkedList<>();
    //     int countIslands = 0;

    //     for (int row = 0; row < rows; row++) {
    //         for (int col = 0; col < cols; col++) {
    //             if (grid[row][col] == ISLAND) {
    //                 queue.offer(new int[] {row, col});
    //                 grid[row][col] = WATER;
    //                 bfs(queue, grid, rows, cols);
    //                 countIslands++;
    //             }
    //         }
    //     }
    //     return countIslands;
    // }

    // private void bfs(Queue<int[]> queue, char[][] grid, int rows, int cols) {
    //     while (!queue.isEmpty()) {
    //         int[] position = queue.poll();

    //         for (int[] dirs : DIRS) {
    //             int positionX = position[0] + dirs[0];
    //             int positionY = position[1] + dirs[1];

    //             if (!isValid(grid, positionX, positionY, rows, cols)) {
    //                 continue;
    //             }
    //             grid[positionX][positionY] = WATER;
    //             queue.offer(new int[] {positionX, positionY});
    //         }
    //     }
    // }

    // private boolean isValid(char[][] grid, int positionX, int positionY, int rows, int cols) {
    //     if (positionX < 0 || positionX >= rows 
    //     || positionY < 0 || positionY >= cols
    //     || grid[positionX][positionY] == WATER) {
    //         return false;
    //     }
    //     return true;
    // }
}
// @lc code=end

