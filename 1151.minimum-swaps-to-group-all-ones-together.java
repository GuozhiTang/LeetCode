// https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/
class Solution {
    
  // Time: O(n)
  // Space: O(1)
  // Sliding Window
  // https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/discuss/355506/JavaSliding-window-O(n)-with-detailed-explanation-very-easy-to-understand
  public int minSwaps(int[] data) {
      // Corner Cases
      if (data.length < 3) {
          return 0;
      }
      
      // O(n)
      // Count for all ones
      int numOfOnes = 0;
      for (int num : data) {
          if (num == 1) {
              numOfOnes++;
          }
      }
      if (numOfOnes == 0) {
          return 0;
      }
      
      // [start, end] is a sliding window
      // numOfZeros represents the number of swaps
      int start = 0, numOfZeros = 0, minSwap = data.length;
      for (int end = 0; end < data.length; end++) {
          if (data[end] == 0) {
              numOfZeros++;
          }
          
          // adjust the sliding window
          if (end - start + 1 > numOfOnes) {
              if (data[start] == 0) {
                  numOfZeros--;
              }
              start++;
          }
          
          if (end - start + 1 == numOfOnes) {
              minSwap = Math.min(minSwap, numOfZeros);
          }
      }
      
      return minSwap;
  }
}