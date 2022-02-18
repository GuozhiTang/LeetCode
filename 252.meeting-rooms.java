import java.util.Arrays;

// https://leetcode.com/problems/meeting-rooms/
class Solution {
    
  // TIme: O(nlogn)
  // Space: O(n)
  // Comparator + Iteration
  public boolean canAttendMeetings(int[][] intervals) {
      // Corner Cases
      if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
          return true;
      }
      
      // o(nlogn)
      Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
      // Arrays.sort(intervals, new Comparator<int[]>() {
      //     @Override
      //     public int compare(int[] a, int[] b) {
      //         return a[0] - b[0];
      //     }
      // });
      
      for (int index = 0; index < intervals.length - 1; index++) {
          if (intervals[index][1] > intervals[index + 1][0]) {
              return false;
          }
      }
      
      return true;
  }
}