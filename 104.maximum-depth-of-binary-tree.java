import java.util.Queue;
import java.util.LinkedList;

/*
 * @lc app=leetcode id=104 lang=java
 *
 * [104] Maximum Depth of Binary Tree
 *
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
 *
 * algorithms
 * Easy (66.55%)
 * Likes:    3360
 * Dislikes: 87
 * Total Accepted:    1M
 * Total Submissions: 1.5M
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Given the root of a binary tree, return its maximum depth.
 * 
 * A binary tree's maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [1,null,2]
 * Output: 2
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: root = []
 * Output: 0
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: root = [0]
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [0, 10^4].
 * -100 <= Node.val <= 100
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
    public int maxDepth(TreeNode root) {
        // return recursiveSolution(root);
        return iterativeSolution(root);
    }

    // Time: O(n)
    // Space: O(n)
    // BFS
    private int iterativeSolution(TreeNode root) {
        // Corner Cases
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            while (queueSize > 0) {
                TreeNode curNode = queue.poll();
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
                queueSize--;
            }
            depth++;
        }
        return depth;
    }

    // Time: O(n)
    // Space: O(h)
    // if the node is null, then take it as zero, if not, each recurse would let the current max depth + 1
    private int recursiveSolution(TreeNode root) {
        // Corner Cases
        if (root == null) {
            return 0;
        }

        return (1 + Math.max(recursiveSolution(root.left), recursiveSolution(root.right)));
    }
}
// @lc code=end

