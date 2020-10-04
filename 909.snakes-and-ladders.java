import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*
 * @lc app=leetcode id=909 lang=java
 *
 * [909] Snakes and Ladders
 *
 * https://leetcode.com/problems/snakes-and-ladders/description/
 *
 * algorithms
 * Medium (37.00%)
 * Likes:    271
 * Dislikes: 682
 * Total Accepted:    24K
 * Total Submissions: 64.3K
 * Testcase Example:  '[[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]'
 *
 * On an N x N board, the numbers from 1 to N*N are written boustrophedonically
 * starting from the bottom left of the board, and alternating direction each
 * row.  For example, for a 6 x 6 board, the numbers are written as
 * follows:
 * 
 * 
 * 
 * 
 * 
 * You start on square 1 of the board (which is always in the last row and
 * first column).  Each move, starting from square x, consists of the
 * following:
 * 
 * 
 * You choose a destination square S with number x+1, x+2, x+3, x+4, x+5, or
 * x+6, provided this number is <= N*N.
 * 
 * 
 * (This choice simulates the result of a standard 6-sided die roll: ie., there
 * are always at most 6 destinations, regardless of the size of the
 * board.)
 * 
 * 
 * If S has a snake or ladder, you move to the destination of that snake or
 * ladder.  Otherwise, you move to S.
 * 
 * 
 * A board square on row r and column c has a "snake or ladder" if board[r][c]
 * != -1.  The destination of that snake or ladder is board[r][c].
 * 
 * Note that you only take a snake or ladder at most once per move: if the
 * destination to a snake or ladder is the start of another snake or ladder,
 * you do not continue moving.  (For example, if the board is
 * `[[4,-1],[-1,3]]`, and on the first move your destination square is `2`,
 * then you finish your first move at `3`, because you do not continue moving
 * to `4`.)
 * 
 * Return the least number of moves required to reach square N*N.  If it is not
 * possible, return -1.
 * 
 * Example 1:
 * 
 * 
 * Input: [
 * [-1,-1,-1,-1,-1,-1],
 * [-1,-1,-1,-1,-1,-1],
 * [-1,-1,-1,-1,-1,-1],
 * [-1,35,-1,-1,13,-1],
 * [-1,-1,-1,-1,-1,-1],
 * [-1,15,-1,-1,-1,-1]]
 * Output: 4
 * Explanation: 
 * At the beginning, you start at square 1 [at row 5, column 0].
 * You decide to move to square 2, and must take the ladder to square 15.
 * You then decide to move to square 17 (row 3, column 5), and must take the
 * snake to square 13.
 * You then decide to move to square 14, and must take the ladder to square 35.
 * You then decide to move to square 36, ending the game.
 * It can be shown that you need at least 4 moves to reach the N*N-th square,
 * so the answer is 4.
 * 
 * 
 * Note:
 * 
 * 
 * 2 <= board.length = board[0].length <= 20
 * board[i][j] is between 1 and N*N or is equal to -1.
 * The board square with number 1 has no snake or ladder.
 * The board square with number N*N has no snake or ladder.
 * 
 * 
 */

// @lc code=start
class Solution {
    // Time: O(N^2)
    // Space: O(N^2)
    // BFS
    public int snakesAndLadders(int[][] board) {
        // Corner Cases
        if (board.length == 0 || board == null || board[0].length == 0) {
            return 0;
        }

        int N = board.length;

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N * N + 1];

        queue.offer(1);
        visited[1] = true;

        return bfs(queue, board, N, visited);
    }

    private int bfs(Queue<Integer> queue, int[][] board, int N, boolean[] visited) {
        int steps = 1;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int index = 0; index < queueSize; index++) {
                int curVal = queue.poll();
                for (int moves = 1; moves <= 6; moves++) {
                    int nextVal = curVal + moves;
                    int[] nextPos = numToPosition(nextVal, N);
                    int nextX = nextPos[0], nextY = nextPos[1];

                    if (board[nextX][nextY] > 0) {
                        nextVal = board[nextX][nextY];
                    }

                    if (nextVal > N * N || visited[nextVal]) {
                        continue;
                    }
                    if (nextVal == N * N) {
                        return steps;
                    }
                    visited[nextVal] = true;
                    queue.offer(nextVal);
                }
            }
            steps++;
        }
        return -1;
    }

    private int[] numToPosition(int val, int N) {
        Map<Integer, int[]> map = new HashMap<>();
        boolean reverse = false;
        int curNum = 0;
        for (int row = N - 1; row >= 0; row--) {
            if (reverse) {
                for (int col = N - 1; col >= 0; col--) {
                    curNum += 1;
                    map.put(curNum, new int[] {row, col});
                }
                reverse = !reverse;
            } else {
                for (int col = 0; col < N; col++) {
                    curNum += 1;
                    map.put(curNum, new int[] {row, col});
                }
                reverse = !reverse;
            }
        }

        return map.get(val);
    }
}
// @lc code=end

