import java.util.Stack;

/*
 * @lc app=leetcode id=456 lang=java
 *
 * [456] 132 Pattern
 *
 * https://leetcode.com/problems/132-pattern/description/
 *
 * algorithms
 * Medium (28.59%)
 * Likes:    1138
 * Dislikes: 74
 * Total Accepted:    48.2K
 * Total Submissions: 167.3K
 * Testcase Example:  '[1,2,3,4]'
 *
 * 
 * Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a
 * subsequence ai, aj, ak such
 * that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n
 * numbers as input and checks whether there is a 132 pattern in the list.
 * 
 * Note: n will be less than 15,000.
 * 
 * Example 1:
 * 
 * Input: [1, 2, 3, 4]
 * 
 * Output: False
 * 
 * Explanation: There is no 132 pattern in the sequence.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [3, 1, 4, 2]
 * 
 * Output: True
 * 
 * Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
 * 
 * 
 * 
 * Example 3:
 * 
 * Input: [-1, 3, 2, 0]
 * 
 * Output: True
 * 
 * Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1,
 * 3, 0] and [-1, 2, 0].
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean find132pattern(int[] nums) {
        // boolean res1 = BruteForce(nums);
        // boolean res2 = Brute2(nums);
        boolean res3 = StackSol(nums);
        return res3;
    }

    // Time: O(n)
    // Space: O(n)
    // Stack
    private boolean StackSol(int[] nums) {
        // Corner Cases
        if (nums.length < 3 || nums == null) {
            return false;
        }

        Stack<Integer> stack = new Stack<>();
        int s3 = Integer.MIN_VALUE;

        for (int s1Index = nums.length - 1; s1Index >= 0; s1Index--) {
            if (nums[s1Index] < s3) {
                return true;
            } else { // nums[s1Index] >= s3
                // s3 could be the max in which smaller than s1Index
                while (!stack.isEmpty() && nums[s1Index] > stack.peek()) {
                    s3 = stack.pop();
                }
            }
            stack.push(nums[s1Index]);
        }
        return false;
    }

    // Time: O(n^2)
    // Space: O(1)
    // For Loop
    private boolean Brute2(int[] nums) {
        // Corner Cases
        if (nums.length < 3 || nums == null) {
            return false;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            int mid = Integer.MIN_VALUE;
            for (int k = i + 1; k < nums.length; k++) {
                if (mid < nums[k]) {
                    mid = nums[k];
                } else if (mid > nums[k] && mid > nums[i] && nums[i] < nums[k]) {
                    return true;
                }
            }
        }
        return false;
    }
    
    // Time: O(n^3)
    // Space: O(1)
    // For Loop
    private boolean BruteForce(int[] nums) {
        // Corner Cases
        if (nums.length < 3 || nums == null) {
            return false;
        }

        for (int i = 0; i < nums.length - 2; i++) {
          for (int j = i + 1; j < nums.length - 1; j++) {
            for (int k = j + 1; k < nums.length; k++) {
              if (nums[k] > nums[i] && nums[k] < nums[j]) {
                return true;
              }
            }
          }
        }
        return false;
    }
}
// @lc code=end

