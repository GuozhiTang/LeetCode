import java.util.Arrays;
import java.util.PriorityQueue;

// https://leetcode.com/problems/meeting-rooms-ii/
class Solution {
    
  // Time: O(nlogn)
  // Space: O(n)
  // PriorityQueue + Comparator
  // https://leetcode.com/problems/meeting-rooms-ii/discuss/67857/AC-Java-solution-using-min-heap
  public int minMeetingRooms(int[][] intervals) {
     // Corner Cases
      if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
          return 0;
      }
      
      // O(nlogn)
      Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
      
      // use PriorityQueue to store the intervals to get the minimum end time
      PriorityQueue<int[]> meetingRooms = new PriorityQueue<int[]>((a, b) -> (a[1] - b[1]));
      
      meetingRooms.offer(intervals[0]);
      
      for (int index = 1; index < intervals.length; index++) {
          int[] preInterval = meetingRooms.poll();
          int[] curInterval = intervals[index];
          
          if (preInterval[1] <= curInterval[0]) { // no need for a new room, expand the end
              preInterval[1] = curInterval[1];
          } else { // need a new room
              meetingRooms.offer(curInterval);
          }
          
          meetingRooms.offer(preInterval);
      }
      
      return meetingRooms.size();
  }
}