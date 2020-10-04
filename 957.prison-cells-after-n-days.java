import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=957 lang=java
 *
 * [957] Prison Cells After N Days
 *
 * https://leetcode.com/problems/prison-cells-after-n-days/description/
 *
 * algorithms
 * Medium (39.13%)
 * Likes:    349
 * Dislikes: 588
 * Total Accepted:    40.1K
 * Total Submissions: 102K
 * Testcase Example:  '[0,1,0,1,1,0,0,1]\n7'
 *
 * There are 8 prison cells in a row, and each cell is either occupied or
 * vacant.
 * 
 * Each day, whether the cell is occupied or vacant changes according to the
 * following rules:
 * 
 * 
 * If a cell has two adjacent neighbors that are both occupied or both vacant,
 * then the cell becomes occupied.
 * Otherwise, it becomes vacant.
 * 
 * 
 * (Note that because the prison is a row, the first and the last cells in the
 * row can't have two adjacent neighbors.)
 * 
 * We describe the current state of the prison in the following way: cells[i]
 * == 1 if the i-th cell is occupied, else cells[i] == 0.
 * 
 * Given the initial state of the prison, return the state of the prison after
 * N days (and N such changes described above.)
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: cells = [0,1,0,1,1,0,0,1], N = 7
 * Output: [0,0,1,1,0,0,0,0]
 * Explanation: 
 * The following table summarizes the state of the prison on each day:
 * Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
 * Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
 * Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
 * Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
 * Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
 * Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
 * Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
 * Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
 * Output: [0,0,1,1,1,1,1,0]
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * cells.length == 8
 * cells[i] is in {0, 1}
 * 1 <= N <= 10^9
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {

    // Time: O(2^(cells.length-2)*(cells.length-2)), O(2^n)
    // Space: O(n+2^n) ???
    // Since there are only 2 ^ 6 = 64 changes, if N is too large, there must be duplicates => use mod + hashmap to utilize
    public int[] prisonAfterNDays(int[] cells, int N) {
        // return BruteForce(cells, N);
        // Corner Cases
        if (cells.length == 0 || cells == null) {
            return new int[] {};
        }

        Map<String, Integer> map = new HashMap<>();

        while (N > 0) {
            map.put(Arrays.toString(cells), N);
            int[] tempRes = new int[8];
            tempRes[0] = tempRes[7] = 0;
            for (int curIndex = 1; curIndex < cells.length - 1; curIndex++) {
                if (cells[curIndex - 1] == cells[curIndex + 1]) {
                    tempRes[curIndex] = 1;
                } else {
                    tempRes[curIndex] = 0;
                }
            }
            N--;
            cells = tempRes;
            if (map.containsKey(Arrays.toString(cells))) {
                int diff = map.get(Arrays.toString(cells)) - N;
                N %= diff;
            }
        }
        return cells;
    }

    // Time: O(n^2)
    // Space: O(n)
    // Brute force traverse
    private int[] BruteForce(int[] cells, int N) {
        // Corner Cases
        if (cells.length == 0 || cells == null) {
        return new int[] {};
        }
        if (N == 0) {
            return cells;
        }
    
        while (N > 0) {
            int[] result = new int[8];
            result[0] = result[7] = 0;
            int curIndex = 1;
            while (curIndex + 1 < cells.length) {
            if (cells[curIndex - 1] == cells[curIndex + 1]) {
                result[curIndex] = 1;
            } else {
                result[curIndex] = 0;
            }
            curIndex++;
            }
            N--;
            cells = result;
        }
        return cells;
    }
}
// @lc code=end

