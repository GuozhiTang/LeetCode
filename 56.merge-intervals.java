import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=56 lang=java
 *
 * [56] Merge Intervals
 *
 * https://leetcode.com/problems/merge-intervals/description/
 *
 * algorithms
 * Medium (44.15%)
 * Likes:    12086
 * Dislikes: 486
 * Total Accepted:    1.3M
 * Total Submissions: 2.9M
 * Testcase Example:  '[[1,3],[2,6],[8,10],[15,18]]'
 *
 * Given an array of intervals where intervals[i] = [starti, endi], merge all
 * overlapping intervals, and return an array of the non-overlapping intervals
 * that cover all the intervals in the input.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into
 * [1,6].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {

    // Time: O(nlogn)
    // Space: O(n)
    // Comparator + Iteration
    // https://leetcode.com/problems/merge-intervals/discuss/21222/A-simple-Java-solution
    public int[][] merge(int[][] intervals) {
       // Corner Cases
       if (intervals.length == 0) {
           return new int[][] {};
       }

       // O(nlogn)
       Arrays.sort(intervals, (a,b) -> (a[0] - b[0]));

       List<int[]> result = new ArrayList<int[]>();
       int[] newInterval = intervals[0];

       // add `new` to the result first and update the new[1] based on if condition
       result.add(newInterval);
       for (int[] interval : intervals) {
           if (interval[0] <= newInterval[1]) {
               newInterval[1] = Math.max(newInterval[1], interval[1]);
           } else { // Once meet disjoint intervals, keep previous `new` and add new `new` to the result waiting for updates.
               newInterval = interval;
               result.add(newInterval);
           }
       }

       return result.toArray(new int[result.size()][]);
    }
}
// @lc code=end

