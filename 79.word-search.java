/*
 * @lc app=leetcode id=79 lang=java
 *
 * [79] Word Search
 *
 * https://leetcode.com/problems/word-search/description/
 *
 * algorithms
 * Medium (33.58%)
 * Likes:    2990
 * Dislikes: 152
 * Total Accepted:    412.3K
 * Total Submissions: 1.2M
 * Testcase Example:  '[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]\n"ABCCED"'
 *
 * Given a 2D board and a word, find if the word exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The
 * same letter cell may not be used more than once.
 * 
 * Example:
 * 
 * 
 * board =
 * [
 * ⁠ ['A','B','C','E'],
 * ⁠ ['S','F','C','S'],
 * ⁠ ['A','D','E','E']
 * ]
 * 
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * board and word consists only of lowercase and uppercase English letters.
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 * 
 * 
 */

// @lc code=start
class Solution {

    private int[][] DIRS = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // right-down-left-up

    // Time: O(row*col)
    // Space: O(row*col)
    // DFS
    public boolean exist(char[][] board, String word) {
        // Corner Cases
        if (board == null || board.length == 0 || board[0].length == 0 || word == null || word == "") {
            return false;
        }

        int rows = board.length, cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];

        int curIndex = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (dfs(board, visited, row, col, word, curIndex)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, boolean[][] visited, int row, int col, String word, int curIndex) {
        // breaching cases
        if (curIndex == word.length()) {
            return true;
        }

        if (!isValid(board, visited, word, row, col, curIndex)) {
            return false;
        }

        visited[row][col] = true;
        for (int[] DIR : DIRS) {
            int positionX = row + DIR[0];
            int positionY = col + DIR[1];

            if (dfs(board, visited, positionX, positionY, word, curIndex + 1)) {
                return true;
            }
        }

        // backtracking
        visited[row][col] = false;
        return false;
    }

    private boolean isValid(char[][] board, boolean[][] visited, String word, int row, int col, int curIndex) {
        if (row < 0 || row >= board.length
        || col < 0 || col >= board[0].length
        || visited[row][col] || board[row][col] != word.charAt(curIndex)) {
            return false;
        }
        return true;
    }

    // ==========================================================================

    // private final int[][] DIRS = new int[][] {{1,0},{0,1},{-1,0},{0,-1}}; // right-down-left-up

    // // Time: O(rows*cols)
    // // Space: O(row*cols)
    // // DFS
    // public boolean exist(char[][] board, String word) {
    //     // Corner Cases
    //     if (board.length == 0 || board == null || board[0].length == 0) {
    //         return false;
    //     }

    //     int rows = board.length, cols = board[0].length;
    //     boolean[][] visited = new boolean[rows][cols];
    //     int curIndex = 0;

    //     for (int row = 0; row < rows; row++) {
    //         for (int col = 0; col < cols; col++) {
    //             if (dfs(board, visited, word, row, col, rows, cols, curIndex)) {
    //                 return true;
    //             }
    //         }
    //     }
    //     return false;
    // }

    // private boolean dfs(char[][] board, boolean[][] visited, String word, int row, int col, int rows, int cols, int curIndex) {
    //     if (curIndex == word.length()) {
    //         return true;
    //     }

    //     if (!isValid(board, visited, word, row, col, rows, cols, curIndex)) {
    //         return false;
    //     }

    //     visited[row][col] = true;
    //     for (int[] dir : DIRS) {
    //         int positionX = row + dir[0];
    //         int positionY = col + dir[1];

    //         if (dfs(board, visited, word, positionX, positionY, rows, cols, curIndex + 1)) {
    //             return true;
    //         }
    //     }

    //     // backtracking
    //     visited[row][col] = false;
    //     return false;
    // }

    // private boolean isValid(char[][] board, boolean[][] visited, String word, int row, int col, int rows, int cols, int curIndex) {
    //     if (row < 0 || row >= rows || col < 0 || col >= cols 
    //     || visited[row][col] || board[row][col] != word.charAt(curIndex)) {
    //         return false;
    //     }
    //     return true;
    // }
}
// @lc code=end

