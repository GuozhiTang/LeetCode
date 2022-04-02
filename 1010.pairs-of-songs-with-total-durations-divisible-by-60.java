import java.util.HashMap;

/*
 * @lc app=leetcode id=1010 lang=java
 *
 * [1010] Pairs of Songs With Total Durations Divisible by 60
 *
 * https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/description/
 *
 * algorithms
 * Medium (53.88%)
 * Likes:    3040
 * Dislikes: 117
 * Total Accepted:    187.1K
 * Total Submissions: 348.3K
 * Testcase Example:  '[30,20,150,100,40]'
 *
 * You are given a list of songs where the i^th song has a duration of time[i]
 * seconds.
 * 
 * Return the number of pairs of songs for which their total duration in
 * seconds is divisible by 60. Formally, we want the number of indices i, j
 * such that i < j with (time[i] + time[j]) % 60 == 0.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: time = [30,20,150,100,40]
 * Output: 3
 * Explanation: Three pairs have a total duration divisible by 60:
 * (time[0] = 30, time[2] = 150): total duration 180
 * (time[1] = 20, time[3] = 100): total duration 120
 * (time[1] = 20, time[4] = 40): total duration 60
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: time = [60,60,60]
 * Output: 3
 * Explanation: All three pairs have a total duration of 120, which is
 * divisible by 60.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= time.length <= 6 * 10^4
 * 1 <= time[i] <= 500
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        // int result = bruteForceSolution(time);
        int result = hashMapSolution(time);
        return result;
    }

    // Time: O(n)
    // Space: O(n)
    // HashMap + Remainders
    // https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/discuss/256738/JavaC%2B%2BPython-Two-Sum-with-K-60
    private int hashMapSolution(int[] time) {
        // Corner Cases
        if (time == null || time.length == 0) {
            return 0;
        }

        // remainder - count
        HashMap<Integer, Integer> map = new HashMap<>();
        int result = 0;

        // num1, num2
        // if (num1 + num2) % 60 == 0
        // then (num1 % 60) + (num2 % 60) = 60
        for (int t : time) {
            int remainder1 = t % 60;
            if (map.containsKey((60 - remainder1) % 60)) {
                result += map.get((60 - remainder1) % 60);
            }
            map.put(remainder1, map.getOrDefault(remainder1, 0) + 1);
        }

        return result;
    }

    // Time: O(n^2)
    // Space: O(1)
    // Two loops
    private int bruteForceSolution(int[] time) {
        // Corner Cases
        if (time == null || time.length == 0) {
            return 0;
        }

        int count = 0;
        for (int firstIndex = 0; firstIndex < time.length - 1; firstIndex++) {
            for (int secondIndex = firstIndex + 1; secondIndex < time.length; secondIndex++) {
                if ((time[firstIndex] + time[secondIndex]) % 60 == 0) {
                    count++;
                }
            }
        }

        return count;
    }
}
// @lc code=end

