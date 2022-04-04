import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=1481 lang=java
 *
 * [1481] Least Number of Unique Integers after K Removals
 *
 * https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/description/
 *
 * algorithms
 * Medium (59.51%)
 * Likes:    858
 * Dislikes: 64
 * Total Accepted:    68.3K
 * Total Submissions: 113.5K
 * Testcase Example:  '[5,5,4]\n1'
 *
 * Given an array of integers arr and an integer k. Find the least number of
 * unique integers after removing exactly k elements.
 * 
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [5,5,4], k = 1
 * Output: 1
 * Explanation: Remove the single 4, only 5 is left.
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [4,3,1,1,3,3,2], k = 3
 * Output: 2
 * Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3
 * will be left.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 10^9
 * 0 <= k <= arr.length
 * 
 */

// @lc code=start
class Solution {

    // Time: O(nlogn)
    // Space: O(n)
    // PriorityQueue + HashMap
    // https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/discuss/686335/JavaPython-3-Greedy-Alg.%3A-3-methods-from-O(nlogn)-to-O(n)-w-brief-explanation-and-analysis.
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
       // Corner Cases
       if (arr == null || arr.length == 0) {
           return -1;
       }

       // number - count
       Map<Integer, Integer> map = new HashMap<>();
       for (int num : arr) {
           map.put(num, map.getOrDefault(num, 0) + 1);
       }

       // O(nlogn)
       // small -> large
       PriorityQueue<Integer> pq = new PriorityQueue<>(map.values());

       while (k > 0) {
           // what stored in the pq are the counts of each element
           k -= pq.poll();
       }

       return (k < 0) ? pq.size() + 1 : pq.size();
    }
}
// @lc code=end

