/*
 * @lc app=leetcode id=687 lang=java
 *
 * [687] Longest Univalue Path
 *
 * https://leetcode.com/problems/longest-univalue-path/description/
 *
 * algorithms
 * Easy (36.41%)
 * Likes:    2188
 * Dislikes: 547
 * Total Accepted:    110K
 * Total Submissions: 294.8K
 * Testcase Example:  '[5,4,5,1,1,5]'
 *
 * Given the root of a binary tree, return the length of the longest path,
 * where each node in the path has the same value. This path may or may not
 * pass through the root.
 * 
 * The length of the path between two nodes is represented by the number of
 * edges between them.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [5,4,5,1,1,5]
 * Output: 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [1,4,5,4,4,5]
 * Output: 2
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [0, 10^4].
 * -1000 <= Node.val <= 1000
 * The depth of the tree will not exceed 1000.
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

    private int maxPath = 0;

    // Time: O(n)
    // Space: O(h)
    // Divide and Conquer
    // Reference: https://leetcode.com/problems/longest-univalue-path/discuss/108175/java-solution-with-global-variable
    // Can refer to No.124 (hard), No.543 (easy) as well
    public int longestUnivaluePath(TreeNode root) {
        // Corner Cases
        if (root == null) {
            return 0;
        }

        getMaxPath(root, root.val);

        return maxPath;
    }

    private int getMaxPath(TreeNode root, int rootVal) {
        // Break condition
        if (root == null) {
            return 0;
        }

        // Divide
        int left = getMaxPath(root.left, root.val);
        int right = getMaxPath(root.right, root.val);

        // Conquer
        maxPath = Math.max(maxPath, left + right);

        if (rootVal == root.val) {
            return Math.max(left, right) + 1;
        }
        return 0;
    }
}
// @lc code=end

