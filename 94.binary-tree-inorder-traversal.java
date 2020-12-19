import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * @lc app=leetcode id=94 lang=java
 *
 * [94] Binary Tree Inorder Traversal
 *
 * https://leetcode.com/problems/binary-tree-inorder-traversal/description/
 *
 * algorithms
 * Medium (60.63%)
 * Likes:    2660
 * Dislikes: 116
 * Total Accepted:    664.7K
 * Total Submissions: 1.1M
 * Testcase Example:  '[1,null,2,3]'
 *
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * 
 * Example:
 * 
 * 
 * Input: [1,null,2,3]
 * ⁠  1
 * ⁠   \
 * ⁠    2
 * ⁠   /
 * ⁠  3
 * 
 * Output: [1,3,2]
 * 
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 * 
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    // must be outside of the method for recursive
    private List<Integer> result = new ArrayList<>();

    // left -> root -> right
    public List<Integer> inorderTraversal(TreeNode root) {
        // return recursiveSolution(root);
        return iterativeSolution(root);
    }

    // Time: O(n)
    // Space: O(n)
    private List<Integer> iterativeSolution(TreeNode root) {

        List<Integer> res = new ArrayList<>();

        // Corner Cases
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;

        while (!stack.isEmpty() || curNode != null) {
            // if there is a curNode, then push items to stack till the left end.
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }

            curNode = stack.pop();
            res.add(curNode.val);
            curNode = curNode.right;
        }

        return res;
    }

    // Time: O(n)
    // Space: O(n)
    private List<Integer> recursiveSolution(TreeNode root) {
        // Corner Cases
        if (root == null) {
            return result;
        }

        recursiveSolution(root.left);
        result.add(root.val);
        recursiveSolution(root.right);

        return result;
    }
}
// @lc code=end

