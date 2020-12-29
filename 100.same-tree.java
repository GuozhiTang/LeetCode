import java.util.Stack;

/*
 * @lc app=leetcode id=100 lang=java
 *
 * [100] Same Tree
 *
 * https://leetcode.com/problems/same-tree/description/
 *
 * algorithms
 * Easy (53.63%)
 * Likes:    2770
 * Dislikes: 76
 * Total Accepted:    653K
 * Total Submissions: 1.2M
 * Testcase Example:  '[1,2,3]\n[1,2,3]'
 *
 * Given the roots of two binary trees p and q, write a function to check if
 * they are the same or not.
 * 
 * Two binary trees are considered the same if they are structurally identical,
 * and the nodes have the same value.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: p = [1,2], q = [1,null,2]
 * Output: false
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: p = [1,2,1], q = [1,1,2]
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in both trees is in the range [0, 100].
 * -10^4 <= Node.val <= 10^4
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

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // return recursiveSolution(p, q);
        return iterativeSolution(p, q);
    }

    // Time: O(n)
    // Space: O(n)
    // Pre-order tree traverse: root -> left -> right
    private boolean iterativeSolution(TreeNode p, TreeNode q) {
        // Corner Cases
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(p);
        stack2.push(q);

        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            TreeNode curNode1 = stack1.pop();
            TreeNode curNode2 = stack2.pop();

            // Not completely sure why here is this if condition
            if (curNode1 == null && curNode2 == null) {
                continue;
            }

            if (curNode1 == null || curNode2 == null || curNode1.val != curNode2.val) {
                return false;
            }

            stack1.push(curNode1.right);
            stack2.push(curNode2.right);
            stack1.push(curNode1.left);
            stack2.push(curNode2.left);
        }

        return true;
    }

    // Time: O(n)
    // Space: O(h)
    private boolean recursiveSolution(TreeNode p, TreeNode q) {
        // Corner Cases
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }

        return recursiveSolution(p.left, q.left) && recursiveSolution(p.right, q.right);
    }
}
// @lc code=end

