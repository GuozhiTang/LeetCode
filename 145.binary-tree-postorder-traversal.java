import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
 * @lc app=leetcode id=145 lang=java
 *
 * [145] Binary Tree Postorder Traversal
 *
 * https://leetcode.com/problems/binary-tree-postorder-traversal/description/
 *
 * algorithms
 * Hard (52.20%)
 * Likes:    1346
 * Dislikes: 69
 * Total Accepted:    327.8K
 * Total Submissions: 627.8K
 * Testcase Example:  '[1,null,2,3]'
 *
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * 
 * Example:
 * 
 * 
 * Input: [1,null,2,3]
 * ⁠  1
 * ⁠   \
 * ⁠    2
 * ⁠   /
 * ⁠  3
 * 
 * Output: [3,2,1]
 * 
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

    private List<Integer> result = new ArrayList<>();

    // left -> right -> root
    public List<Integer> postorderTraversal(TreeNode root) {
        // return recursiveSolution(root);
        return iterativeSolution(root);
    }

    // Time: O(n)
    // Space: O(n)
    private List<Integer> iterativeSolution(TreeNode root) {
        // This is the MAIN IDEA with .addFirst()!!
        LinkedList<Integer> res = new LinkedList<>();

        // Corner Cases
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode curNode = stack.pop();

            if (curNode != null) {
                res.addFirst(curNode.val);
                stack.push(curNode.left);
                stack.push(curNode.right);
            }
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
        recursiveSolution(root.right);
        result.add(root.val);

        return result;
    }
}
// @lc code=end

