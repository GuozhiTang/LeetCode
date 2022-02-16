import java.util.Stack;

/*
 * @lc app=leetcode id=907 lang=java
 *
 * [907] Sum of Subarray Minimums
 *
 * https://leetcode.com/problems/sum-of-subarray-minimums/description/
 *
 * algorithms
 * Medium (33.40%)
 * Likes:    3101
 * Dislikes: 194
 * Total Accepted:    64.9K
 * Total Submissions: 192.9K
 * Testcase Example:  '[3,1,2,4]'
 *
 * Given an array of integers arr, find the sum of min(b), where b ranges over
 * every (contiguous) subarray of arr. Since the answer may be large, return
 * the answer modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [3,1,2,4]
 * Output: 17
 * Explanation: 
 * Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4],
 * [3,1,2,4]. 
 * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
 * Sum is 17.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [11,81,94,43,3]
 * Output: 444
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 3 * 10^4
 * 1 <= arr[i] <= 3 * 10^4
 * 
 * 
 */

// @lc code=start
class Solution {

    // Time: O(n)
    // Space: O(n)
    // Monotonous Increase Stack
    // https://leetcode.com/problems/sum-of-subarray-minimums/discuss/178876/stack-solution-with-very-detailed-explanation-step-by-step
    public int sumSubarrayMins(int[] arr) {
        // Corner Cases
        if (arr == null || arr.length == 0) {
            return 0;
        }

        long result = 0, mod = (int)1e9 + 7;
        int length = arr.length;
        // left[] is for the distance of previous less element
        // right[] is for the distance of next less element
        int[] left = new int[length];
        int[] right = new int[length];
        Stack<int[]> PLEStack = new Stack<>();
        Stack<int[]> NLEStack = new Stack<>();

        for (int index = 0; index < length; index++) {
            // e.g.: [3, 7, 8, 4]
            // For Previous Less Element
            // Use >= to deal with duplication
            while (!PLEStack.isEmpty() && PLEStack.peek()[0] >= arr[index]) {
                PLEStack.pop();
            }
            // (index - (-1)) => (index + 1)
            left[index] = PLEStack.isEmpty() ? (index + 1) : (index - PLEStack.peek()[1]);
            PLEStack.push(new int[] {arr[index], index});
        }

        // For Next Less Element
        for (int index = length - 1; index >= 0; index--) {
            while (!NLEStack.isEmpty() && NLEStack.peek()[0] > arr[index]) {
                NLEStack.pop();
            }
            // (length - 1 - index + 1) => (length - index)
            right[index] = NLEStack.isEmpty() ? (length - index) : (NLEStack.peek()[1] - index);
            NLEStack.push(new int[] {arr[index], index});
        }

        // Get the final sum result
        for (int index = 0; index < length; index++) {
            // System.out.println("arr[" + index + "]: " + arr[index] + ", left[" + index + "]: " + left[index] + ", right[" + index + "]: " + right[index]);
            result = (result + (long)arr[index] * left[index] * right[index]) % mod;
        }

        return (int)result;
    }
}
// @lc code=end

