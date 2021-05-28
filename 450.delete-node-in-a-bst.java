/*
 * @lc app=leetcode id=450 lang=java
 *
 * [450] Delete Node in a BST
 *
 * https://leetcode.com/problems/delete-node-in-a-bst/description/
 *
 * algorithms
 * Medium (44.47%)
 * Likes:    3020
 * Dislikes: 111
 * Total Accepted:    173.4K
 * Total Submissions: 378.6K
 * Testcase Example:  '[5,3,6,2,4,null,7]\n3'
 *
 * Given a root node reference of a BST and a key, delete the node with the
 * given key in the BST. Return the root node reference (possibly updated) of
 * the BST.
 * 
 * Basically, the deletion can be divided into two stages:
 * 
 * 
 * Search for a node to remove.
 * If the node is found, delete the node.
 * 
 * 
 * Follow up: Can you solve it with time complexity O(height of tree)?
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [5,3,6,2,4,null,7], key = 3
 * Output: [5,4,6,2,null,null,7]
 * Explanation: Given key to delete is 3. So we find the node with value 3 and
 * delete it.
 * One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
 * Please notice that another valid answer is [5,2,6,null,4,null,7] and it's
 * also accepted.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [5,3,6,2,4,null,7], key = 0
 * Output: [5,3,6,2,4,null,7]
 * Explanation: The tree does not contain a node with value = 0.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: root = [], key = 0
 * Output: []
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [0, 10^4].
 * -10^5 <= Node.val <= 10^5
 * Each node has a unique value.
 * root is a valid binary search tree.
 * -10^5 <= key <= 10^5
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
    // Space: O(h)
    // Reference: https://blog.csdn.net/MebiuW/article/details/54583367
    // Reference: https://leetcode.com/problems/delete-node-in-a-bst/discuss/93296/Recursive-Easy-to-Understand-Java-Solution
    public TreeNode deleteNode(TreeNode root, int key) {
        // Corner Cases
        if (root == null) {
            return null;
        }

        if (root.val == key) {
            // if root.left is null
            if (root.left == null) {
                return root.right;
            }
            // if root.right is null
            if (root.right == null) {
                return root.left;
            }

            // root.val = findReplacement(root, root.left, true);
            TreeNode minNode = findMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, root.val);
        } else {
            // if current node is not key
            // if current node > key => key should be in left subtree
            // if current node < key => key should be in right subtree
            if (root.val > key) {
                root.left = deleteNode(root.left, key);
            } else {
                root.right = deleteNode(root.right, key);
            }
        }

        return root;
    }

    // right subtree => find the minimum node
    private TreeNode findMin(TreeNode node) {
        // traverse the left subtree
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // if current node == key, and has both left and right subtree.
    // right subtree => find the minimum node
    // left subtree => find the maximum node
    private int findReplacement(TreeNode parent, TreeNode node, boolean isLeft) {
        if (node.right == null) {
            if (isLeft) {
                parent.left = node.left;
            } else {
                parent.right = node.left;
            }
            return node.val;
        }

        return findReplacement(node, node.right, false);
    } 
}
// @lc code=end

