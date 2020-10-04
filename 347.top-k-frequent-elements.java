import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=347 lang=java
 *
 * [347] Top K Frequent Elements
 *
 * https://leetcode.com/problems/top-k-frequent-elements/description/
 *
 * algorithms
 * Medium (58.77%)
 * Likes:    2489
 * Dislikes: 174
 * Total Accepted:    328.8K
 * Total Submissions: 555.1K
 * Testcase Example:  '[1,1,1,2,2,3]\n2'
 *
 * Given a non-empty array of integers, return the k most frequent elements.
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1], k = 1
 * Output: [1]
 * 
 * 
 * Note: 
 * 
 * 
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is
 * the array's size.
 * 
 * 
 */

// @lc code=start
class Solution {
    // Time: O(nlogk)
    // Space: O(n)
    // HashMap + PriorityQueue
    public List<Integer> topKFrequent(int[] nums, int k) {
        // Corner Cases
        if (nums.length == 0 || nums == null) {
            return new LinkedList<>();
        }

        // O(n)
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // O(logk) - minHeap
        // minHeap to get the minimum value everytime
        PriorityQueue<Map.Entry<Integer, Integer>> pqueue = new PriorityQueue<>(new customizedCom());

        // O(nlogk)
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (pqueue.size() < k) {
                pqueue.offer(entry);
            } else if (entry.getValue() > pqueue.peek().getValue()) {
                pqueue.poll();
                pqueue.offer(entry);
            }
        }

        LinkedList<Integer> result = new LinkedList<>();
        while (!pqueue.isEmpty()) {
            result.addFirst(pqueue.poll().getKey());
        }
        return result;
    }

    private class customizedCom implements Comparator<Map.Entry<Integer, Integer>> {
        @Override
        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
            return o1.getValue() - o2.getValue();
        }
    }
}
// @lc code=end

