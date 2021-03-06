import java.util.*;

/*
 * @lc app=leetcode id=508 lang=java
 *
 * [508] Most Frequent Subtree Sum
 *
 * https://leetcode.com/problems/most-frequent-subtree-sum/description/
 *
 * algorithms
 * Medium (58.30%)
 * Likes:    831
 * Dislikes: 142
 * Total Accepted:    82K
 * Total Submissions: 138.8K
 * Testcase Example:  '[5,2,-3]'
 *
 * 
 * Given the root of a tree, you are asked to find the most frequent subtree
 * sum. The subtree sum of a node is defined as the sum of all the node values
 * formed by the subtree rooted at that node (including the node itself). So
 * what is the most frequent subtree sum value? If there is a tie, return all
 * the values with the highest frequency in any order.
 * 
 * 
 * Examples 1
 * Input:
 * 
 * ⁠ 5
 * ⁠/  \
 * 2   -3
 * 
 * return [2, -3, 4], since all the values happen only once, return all of them
 * in any order.
 * 
 * 
 * Examples 2
 * Input:
 * 
 * ⁠ 5
 * ⁠/  \
 * 2   -5
 * 
 * return [2], since 2 happens twice, however -5 only occur once.
 * 
 * 
 * Note:
 * You may assume the sum of values in any subtree is in the range of 32-bit
 * signed integer.
 * 
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    Map<Integer, Integer> count = new HashMap<>();
    int maxCount = 0;

    // Time: O(n)
    // Space: O(n)
    // DFS solution
    // Reference: https://leetcode.com/problems/most-frequent-subtree-sum/discuss/98675/JavaC%2B%2BPython-DFS-Find-Subtree-Sum
    public int[] findFrequentTreeSum(TreeNode root) {
        // Corner Cases
        if (root == null) {
            return new int[] {};
        }

        dfs(root);

        List<Integer> res = new ArrayList<>();
        for (int sum : count.keySet()) {
            if (count.get(sum) == maxCount) {
                res.add(sum);
            }
        }

        // Convert ArrayList to Array int[]
        return res.stream().mapToInt(p->p).toArray();
    }

    private int dfs(TreeNode root) {
        // Break condition
        if (root == null) {
            return 0;
        }
        int sum = dfs(root.left) + dfs(root.right) + root.val;
        count.put(sum, count.getOrDefault(sum, 0) + 1);
        maxCount = Math.max(maxCount, count.get(sum));

        return sum;
    }
}
// @lc code=end

