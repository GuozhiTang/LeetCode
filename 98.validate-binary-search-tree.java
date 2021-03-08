import java.util.*;

/*
 * @lc app=leetcode id=98 lang=java
 *
 * [98] Validate Binary Search Tree
 *
 * https://leetcode.com/problems/validate-binary-search-tree/description/
 *
 * algorithms
 * Medium (27.98%)
 * Likes:    5588
 * Dislikes: 660
 * Total Accepted:    922.4K
 * Total Submissions: 3.2M
 * Testcase Example:  '[2,1,3]'
 *
 * Given the root of a binary tree, determine if it is a valid binary search
 * tree (BST).
 * 
 * A valid BST is defined as follows:
 * 
 * 
 * The left subtree of a node contains only nodes with keys less than the
 * node's key.
 * The right subtree of a node contains only nodes with keys greater than the
 * node's key.
 * Both the left and right subtrees must also be binary search trees.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [2,1,3]
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is
 * 4.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [1, 10^4].
 * -2^31 <= Node.val <= 2^31 - 1
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
    // In-order traverse will get an ordered sequence.
    public boolean isValidBST(TreeNode root) {
        // return recursiveSolution(root);
        return iterativeSolution(root);
    }

    // Time: O(n)
    // Space: O(n)
    // in-order traverse => ordered sequence => to compare if (prev < cur)
    // Reference: https://leetcode.com/problems/validate-binary-search-tree/discuss/32112/Learn-one-iterative-inorder-traversal-apply-it-to-multiple-tree-questions-(Java-Solution)
    private boolean iterativeSolution(TreeNode root) {
        // Corner Cases
        if (root == null) {
            return true;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        TreeNode curNode = root; // just make it easier to understand

        while (!stack.isEmpty() || curNode != null) {
            // if there exists curNode, then push nodes to stack till the left end.
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }

            curNode = stack.pop();
            if (pre != null && curNode.val < pre.val) { // NOTE: "pre != null" not "curNode != null"
                return false;
            }
            pre = curNode;
            curNode = curNode.right;
        }

        return true;
    }

    private TreeNode prev;
    // Time: O(n)
    // Space: O(h) O(logn)
    // recursive solution using in order traverse.
    private boolean recursiveSolution(TreeNode root) {
        prev = null;
        return isValidInOrder(root);
    }

    private boolean isValidInOrder(TreeNode root) {
        // Break condition
        if (root == null) {
            return true;
        }

        // if left subtree is not a valid BST, return false
        if (!isValidInOrder(root.left)) {
            return false;
        }

        // Since it is an in-order traverse: left -> root -> right, the result is from small -> large
        // So, we can just compare the current value with the previous one (cur must > prev), or returns false.
        if (prev != null && root.val <= prev.val) {
            return false;
        }
        prev = root;

        // judge the right subtree
        return isValidInOrder(root.right);
    }
}
// @lc code=end

