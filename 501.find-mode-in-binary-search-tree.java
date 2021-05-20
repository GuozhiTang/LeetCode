import java.util.*;

/*
 * @lc app=leetcode id=501 lang=java
 *
 * [501] Find Mode in Binary Search Tree
 *
 * https://leetcode.com/problems/find-mode-in-binary-search-tree/description/
 *
 * algorithms
 * Easy (42.68%)
 * Likes:    1375
 * Dislikes: 421
 * Total Accepted:    110.8K
 * Total Submissions: 251.3K
 * Testcase Example:  '[1,null,2,2]'
 *
 * Given the root of a binary search tree (BST) with duplicates, return all the
 * mode(s) (i.e., the most frequently occurred element) in it.
 * 
 * If the tree has more than one mode, return them in any order.
 * 
 * Assume a BST is defined as follows:
 * 
 * 
 * The left subtree of a node contains only nodes with keys less than or equal
 * to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or
 * equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [1,null,2,2]
 * Output: [2]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [0]
 * Output: [0]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [1, 10^4].
 * -10^5 <= Node.val <= 10^5
 * 
 * 
 * 
 * Follow up: Could you do that without using any extra space? (Assume that the
 * implicit stack space incurred due to recursion does not count).
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
    public int[] findMode(TreeNode root) {
        // return bruteForceSol(root);
        return oOneSol(root);
    }

    // Time: O(n)
    // Space: O(1)
    // Set two rounds of traverse:
    // 1st round to get the maximum count of "modes"
    // 2nd round to list all the "modes"
    // So there is no need to use "Map"
    private int[] oOneSol(TreeNode root) {
        inOrder(root);
        modes = new int[modeCount];
        modeCount = 0;
        curCount = 0;
        inOrder(root);
        return modes;
    }

    private int curVal;
    private int curCount = 0;
    private int maximumCount = 0;
    private int modeCount = 0;
    private int[] modes;
    private void handleValue(int val) {
        // check for the current value
        if (curVal != val) {
            curVal = val;
            curCount = 0;
        }
        // if curVal == val
        curCount++;

        if (curCount > maximumCount) {
            maximumCount = curCount;
            modeCount = 1;
        } else if (curCount == maximumCount) {
            // fill in the array in the second round traverse
            if (modes != null) {
                // Then where is the first mode in the array?
                // => The array will be filled in the second round of traverse.
                // => In the second round the modeCount starts from "0"
                modes[modeCount] = curVal;
            }
            modeCount++;
        }
    }

    private void inOrder(TreeNode node) {
        // Corner Cases
        if (node == null) {
            return;
        }

        // left -> mid -> right
        if (node.left != null) {
            inOrder(node.left);
        }
        handleValue(node.val);
        if (node.right != null) {
            inOrder(node.right);
        }
    }


    // Time: O(n)
    // Space: O(n)
    // Brute Froce Solution:
    // Use inorder traverse to traverse the tree
    // Use Map to store <node value, duplicate count>
    // Then compare with max one by one in the map
    private Map<Integer, Integer> map;
    private int maxCount;
    private int[] bruteForceSol(TreeNode root) {
        // Corner Cases
        if (root == null) {
            return new int[0];
        }

        map = new HashMap<>();
        inorderTraverse(root);

        List<Integer> list = new ArrayList<>();
        for (int key : map.keySet()) {
            // check for the maximums
            if (map.get(key) == maxCount) {
                list.add(key);
            }
        }

        int[] result = new int[list.size()];
        for (int index = 0; index < list.size(); index++) {
            result[index] = list.get(index);
        }

        return result;
    }

    private void inorderTraverse(TreeNode node) {
        // left
        if (node.left != null) {
            inorderTraverse(node.left);
        }

        // mid
        map.put(node.val, map.getOrDefault(node.val, 0) + 1);
        maxCount = Math.max(maxCount, map.get(node.val));

        // right
        if (node.right != null) {
            inorderTraverse(node.right);
        }
    }
}
// @lc code=end

