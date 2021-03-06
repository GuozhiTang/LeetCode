/*
 * @lc app=leetcode id=124 lang=java
 *
 * [124] Binary Tree Maximum Path Sum
 *
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
 *
 * algorithms
 * Hard (34.68%)
 * Likes:    5241
 * Dislikes: 383
 * Total Accepted:    476.1K
 * Total Submissions: 1.3M
 * Testcase Example:  '[1,2,3]'
 *
 * A path in a binary tree is a sequence of nodes where each pair of adjacent
 * nodes in the sequence has an edge connecting them. A node can only appear in
 * the sequence at most once. Note that the path does not need to pass through
 * the root.
 * 
 * The path sum of a path is the sum of the node's values in the path.
 * 
 * Given the root of a binary tree, return the maximum path sum of any path.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 =
 * 6.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 +
 * 7 = 42.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [1, 3 * 10^4].
 * -1000 <= Node.val <= 1000
 * 
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
    private int maxSum = Integer.MIN_VALUE;

    // Time: O(n)
    // Space: O(h)
    // Reference: https://blog.csdn.net/mine_song/article/details/69951308
    public int maxPathSum(TreeNode root) {
        // Corner Cases
        if (root == null) {
            return 0;
        }

        maxCore(root);

        return maxSum;
    }

	// 该函数返回是左右的最大路径和，而非左+右+root的最大值
	// 使用curValue，来标记左+右+root
    private int maxCore(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
		// 求以root为根的当前子树的最大路径和
		// 如果左右子树都是负数，
		// 那么就最大路径就是当前结点值（无论正负）
        int curVal = root.val;
        int leftMax = maxCore(root.left);
        int rightMax = maxCore(root.right);

        if (leftMax > 0) {
            curVal += leftMax;
        }
        if (rightMax > 0) {
            curVal += rightMax;
        }

        maxSum = Math.max(maxSum, curVal);

		// 返回以当前root为根的子树的最大路径和
		// 左右有可能都为负数，所以需要参与比较大小
        int curNodeMax = Math.max(root.val, Math.max(root.val + leftMax, root.val + rightMax));
        return curNodeMax;
    }
}
// @lc code=end

