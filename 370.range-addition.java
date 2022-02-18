import java.util.Arrays;

// https://leetcode.com/problems/range-addition/
class Solution {
    
  // Time: O(n+k)
  // Space: O(1)
  // https://leetcode.com/problems/range-addition/discuss/84217/Java-O(K-%2B-N)time-complexity-Solution
  public int[] getModifiedArray(int length, int[][] updates) {
      int[] result = new int[length];
      Arrays.fill(result, 0);
      
      // Corner Cases
      if (updates.length == 0) {
          return result;
      }
      
      for (int[] update : updates) {
          int startIdx = update[0];
          int endIdx = update[1];
          int inc = update[2];
          
          // Only add the inc to the start index
          result[startIdx] += inc;
          // Minus the inc value to the (endIdx + 1), so that in the final accumlation, sum would be end at (end + 1)
          if (endIdx < length - 1) {
              result[endIdx + 1] -= inc;
          }
      }
      
      int sum = 0;
      // Iterative accumlation
      for (int index = 0; index < length; index++) {
          sum += result[index];
          result[index] = sum;
      }
      
      return result;
  }
  
//     // Time: O(n^2)
//     // Space: O(1)
//     // Brute Force - Iteration
//     public int[] getModifiedArray(int length, int[][] updates) {
      
//         int[] result = new int[length];
//         Arrays.fill(result, 0);
      
//         // Corner Cases
//         if (updates.length == 0) {
//             return result;
//         }
      
//         for (int[] update : updates) {
//             int startIdx = update[0];
//             int endIdx = update[1];
//             int inc = update[2];
//             for (int index = startIdx; index <= endIdx; index++) {
//                 result[index] += inc;
//             }
//         }
      
//         return result;
//     }
}