import java.util.LinkedList;
import java.util.Queue;

/*
 * @lc app=leetcode id=733 lang=java
 *
 * [733] Flood Fill
 *
 * https://leetcode.com/problems/flood-fill/description/
 *
 * algorithms
 * Easy (52.96%)
 * Likes:    812
 * Dislikes: 157
 * Total Accepted:    94.8K
 * Total Submissions: 178.1K
 * Testcase Example:  '[[1,1,1],[1,1,0],[1,0,1]]\n1\n1\n2'
 *
 * 
 * An image is represented by a 2-D array of integers, each integer
 * representing the pixel value of the image (from 0 to 65535).
 * 
 * Given a coordinate (sr, sc) representing the starting pixel (row and column)
 * of the flood fill, and a pixel value newColor, "flood fill" the image.
 * 
 * To perform a "flood fill", consider the starting pixel, plus any pixels
 * connected 4-directionally to the starting pixel of the same color as the
 * starting pixel, plus any pixels connected 4-directionally to those pixels
 * (also with the same color as the starting pixel), and so on.  Replace the
 * color of all of the aforementioned pixels with the newColor.
 * 
 * At the end, return the modified image.
 * 
 * Example 1:
 * 
 * Input: 
 * image = [[1,1,1],[1,1,0],[1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation: 
 * From the center of the image (with position (sr, sc) = (1, 1)), all pixels
 * connected 
 * by a path of the same color as the starting pixel are colored with the new
 * color.
 * Note the bottom corner is not colored 2, because it is not 4-directionally
 * connected
 * to the starting pixel.
 * 
 * 
 * 
 * Note:
 * The length of image and image[0] will be in the range [1, 50].
 * The given starting pixel will satisfy 0  and 0 .
 * The value of each color in image[i][j] and newColor will be an integer in
 * [0, 65535].
 * 
 */

// @lc code=start
class Solution {

    final int[][] DIRS = new int[][] {{0,1},{1,0},{0,-1},{-1,0}}; // right-down-left-up

    // Time: O(rows*cols)
    // Space: O(rows*cols)
    // BFS
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        // Corner Cases
        if (image.length == 0 || image == null || image[0].length == 0) {
            return new int[][] {};
        }

        int rows = image.length, cols = image[0].length;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];
        visited[sr][sc] = true;
        queue.offer(new int[] {sr, sc});
        int value = image[sr][sc];

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int index = 0; index < queueSize; index++) {
                int[] position = queue.poll();
                for (int[] dirs : DIRS) {
                    int positionX = position[0] + dirs[0];
                    int positionY = position[1] + dirs[1];

                    if (!isValid(positionX, positionY, rows, cols, visited, image, value)) {
                        continue;
                    }
                    visited[positionX][positionY] = true;
                    image[positionX][positionY] = newColor;
                    queue.offer(new int[] {positionX, positionY});
                }
            }
        }
        image[sr][sc] = newColor;
        return image;
    }

    private boolean isValid(int positionX, int positionY, int rows, int cols, boolean[][] visited, int[][] image, int value) {
        if (positionX < 0 || positionX >= rows || positionY < 0 || positionY >= cols
        || image[positionX][positionY] != value || visited[positionX][positionY]) {
          return false;
        }
        return true;
    }
}
// @lc code=end

