/*
 * @lc app=leetcode id=110 lang=java
 *
 * [110] Balanced Binary Tree
 *
 * https://leetcode.com/problems/balanced-binary-tree/description/
 *
 * algorithms
 * Easy (43.79%)
 * Likes:    3135
 * Dislikes: 207
 * Total Accepted:    535K
 * Total Submissions: 1.2M
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Given a binary tree, determine if it is height-balanced.
 * 
 * For this problem, a height-balanced binary tree is defined as:
 * 
 * 
 * a binary tree in which the left and right subtrees of every node differ in
 * height by no more than 1.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: root = []
 * Output: true
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [0, 5000].
 * -10^4 <= Node.val <= 10^4
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
 *         this.val = val;∂
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    // References:
    // (1) https://blog.csdn.net/zxzxzx0119/article/details/81108640
    // (2) http://www.noteanddata.com/leetcode-110-Balanced-Binary-Tree-java-go-solution-note.html
    public boolean isBalanced(TreeNode root) {
        // // Corner Cases
        // if (root == null) {
        //     return true;
        // }

        // int leftHeight = height(root.left);
        // int rightHeight = height(root.right);

        // if (Math.abs(leftHeight - rightHeight) > 1) {
        //     return false;
        // }

        // return isBalanced(root.left) && isBalanced(root.right);
        /*****************************/
        return helper(root)[1] == 1;
    }

    // Time: O(nlogn)
    // Returns the height of that node.
    public int height (TreeNode node) {
        // Corner Cases
        if (node == null) {
            // Not totally understand why it returns 0 here in the following recursion...
            // :( Maybe today my head does not work...
            return 0;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Time: O(n)
    // Returns an array with two info: [height, balanced]
    public int[] helper(TreeNode node) {
        if (node == null) {
            return new int[] {0, 1};
        }

        int[] left = helper(node.left);
        int[] right = helper(node.right);

        // if left/right node is not balanced or their difference is more than 1 => not balanced
        if (left[1] == 0 || right[1] == 0 || Math.abs(left[0] - right[0]) > 1) {
            return new int[] {0, 0};
        }

        return new int[] {Math.max(left[0], right[0]) + 1, 1};
    }
}
// @lc code=end

