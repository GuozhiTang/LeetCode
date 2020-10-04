import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=692 lang=java
 *
 * [692] Top K Frequent Words
 *
 * https://leetcode.com/problems/top-k-frequent-words/description/
 *
 * algorithms
 * Medium (49.10%)
 * Likes:    1369
 * Dislikes: 116
 * Total Accepted:    128.8K
 * Total Submissions: 259.7K
 * Testcase Example:  '["i", "love", "leetcode", "i", "love", "coding"]\n2'
 *
 * Given a non-empty list of words, return the k most frequent elements.
 * Your answer should be sorted by frequency from highest to lowest. If two
 * words have the same frequency, then the word with the lower alphabetical
 * order comes first.
 * 
 * Example 1:
 * 
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 * ⁠   Note that "i" comes before "love" due to a lower alphabetical order.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is",
 * "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent
 * words,
 * ⁠   with the number of occurrence being 4, 3, 2 and 1 respectively.
 * 
 * 
 * 
 * Note:
 * 
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Input words contain only lowercase letters.
 * 
 * 
 * 
 * Follow up:
 * 
 * Try to solve it in O(n log k) time and O(n) extra space.
 * 
 * 
 */

// @lc code=start
class Solution {
    
    // Time: O(nlogk)
    // Space: O(n)
    // PriorityQueue + HashMap
    public List<String> topKFrequent(String[] words, int k) {
        // Corner Cases
        if (words.length == 0 || words == null) {
            return new LinkedList<>();
        }

        // use hashmap to store the word => frequency
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        // O(logK)
        // use priorityqueue to store all the maps - minHeap
        PriorityQueue<Map.Entry<String, Integer>> pqueue = new PriorityQueue<>(new customizedCom());

        // O(nlogk)
        // traverse and store the top K words
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (pqueue.size() < k) {
                pqueue.offer(entry);
            } else {
                if (canBeInserted(pqueue, entry)) {
                    pqueue.poll();
                    pqueue.offer(entry);
                }
            }
        }

        // return as a list
        // pqueue.poll() -> value smallest, letter largest
        LinkedList<String> result = new LinkedList<>();
        while (!pqueue.isEmpty()) {
            result.addFirst(pqueue.poll().getKey());
        }
        return result;
    }

    private class customizedCom implements Comparator<Map.Entry<String, Integer>>{
        @Override
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            // if two words have same frequency, then sort the word string from large -> small
            if (o1.getValue() == o2.getValue()) {
                return o2.getKey().compareTo(o1.getKey());
            }
            // else sort the frequency from small -> large
            return o1.getValue() - o2.getValue();
        }
    }

    private boolean canBeInserted(PriorityQueue<Map.Entry<String, Integer>> pqueue, Map.Entry<String, Integer> entry) {
        if ((entry.getValue() > pqueue.peek().getValue()) || (entry.getValue() == pqueue.peek().getValue() && entry.getKey().compareTo(pqueue.peek().getKey()) < 0)) {
            return true;
        }
        return false;
    }
}
// @lc code=end

