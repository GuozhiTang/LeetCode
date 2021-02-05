/*
 * @lc app=leetcode id=111 lang=java
 *
 * [111] Minimum Depth of Binary Tree
 *
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/description/
 *
 * algorithms
 * Easy (37.64%)
 * Likes:    2118
 * Dislikes: 791
 * Total Accepted:    518.4K
 * Total Submissions: 1.3M
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Given a binary tree, find its minimum depth.
 * 
 * The minimum depth is the number of nodes along the shortest path from the
 * root node down to the nearest leaf node.
 * 
 * Note: A leaf is a node with no children.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [2,null,3,null,4,null,5,null,6]
 * Output: 5
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [0, 10^5].
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

    // Time: O(n)
    // Space: O(n)
    // Recursion solution
    // Reference: http://www.noteanddata.com/leetcode-111-Minimum-Depth-of-Binary-Tree-java-go-solution-note.html
    // public int minDepth(TreeNode root) {
    //     // Corner Cases
    //     if (root == null) {
    //         return 0;
    //     }

    //     // root node has no children.
    //     if (root.left == null && root.right == null) {
    //         return 1;
    //     }

    //     // root node has no left child, but still has right child.
    //     if (root.left == null) {
    //         return minDepth(root.right) + 1;
    //     }

    //     // root node has no right child, but still has left child.
    //     if (root.right == null) {
    //         return minDepth(root.left) + 1;
    //     }

    //     return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    // }

    // Time: O(n)
    // Space: O(n)
    // DFS solution
    // Reference: https://blog.csdn.net/MebiuW/article/details/52627077
    int min;
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        min = Integer.MAX_VALUE;
        dfs(0, root);
        return min;
    }

    // search one route till the end, and get the min depth of this route
    public void dfs(int depth, TreeNode node) {
        // Corner cases
        // if the current depth is larger than the min depth exists already, then skip the current route directly
        if (depth >= min || node == null) {
            return;
        }

        depth++;
        if (node.left == null && node.right == null) {
            min = Math.min(depth, min);
        }

        dfs(depth, node.left);
        dfs(depth, node.right);
    }
}
// @lc code=end

