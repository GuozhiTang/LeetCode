import java.util.PriorityQueue;

// https://leetcode.com/problems/minimum-cost-to-connect-sticks/
class Solution {
    
  // Time: O(nlogn)
  // Space: O(n)
  // PriorityQueue
  public int connectSticks(int[] sticks) {
      // Corner Cases
      if (sticks.length == 1) {
          return 0;
      }
      
      PriorityQueue<Integer> pqueue = new PriorityQueue<>();
      
      // O(nlogn)
      for (int stick : sticks) {
          pqueue.offer(stick);
      }
      
      int stickCost = 0, totalCost = 0;
      // O(nlogn)
      while (pqueue.size() > 1) {
          stickCost = pqueue.poll() + pqueue.poll();
          totalCost += stickCost;
          pqueue.offer(stickCost);
      }
      
      return totalCost;
  }
}