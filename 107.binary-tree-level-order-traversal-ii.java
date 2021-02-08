import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;

import java.util.ArrayList;

/*
 * @lc app=leetcode id=107 lang=java
 *
 * [107] Binary Tree Level Order Traversal II
 *
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/
 *
 * algorithms
 * Easy (54.04%)
 * Likes:    1973
 * Dislikes: 235
 * Total Accepted:    404.3K
 * Total Submissions: 734.2K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Given a binary tree, return the bottom-up level order traversal of its
 * nodes' values. (ie, from left to right, level by level from leaf to root).
 * 
 * 
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 * 
 * 
 * 
 * return its bottom-up level order traversal as:
 * 
 * [
 * ⁠ [15,7],
 * ⁠ [9,20],
 * ⁠ [3]
 * ]
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // return BFSSolution(root);
        return DFSSolution(root);
    }

    // Time: O(n)
    // Space: O(n)
    // DFS Solution
    // To reverse the list, we could use: LinkedList.addFirst() or List.add(index, value)
    public List<List<Integer>> DFSSolution(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        dfs(root, res, 0);
        return res;
    }

    public void dfs(TreeNode root, List<List<Integer>> res, int level) {
        // Corner Cases
        // also the break condition of the recursion
        if (root == null) {
            return;
        }

        if (level == res.size()) {
            res.add(0, new LinkedList<Integer>());
        }

        // it is different from adding here and adding at the bottom
        // res.get(res.size() - level - 1).add(root.val);
        dfs(root.left, res, level + 1);
        dfs(root.right, res, level + 1);
        res.get(res.size() - level - 1).add(root.val);
    }

    // Time: O(n)
    // Space: O(n)
    // BFS solution
    // To reverse the list, we could use: LinkedList.addFirst() or List.add(index, value)
    public List<List<Integer>> BFSSolution(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        // Corner Cases
        if (root == null) {
            return res;
        }

        res = bfs(root);

        return res;
    }

    public List<List<Integer>> bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        // LinkedList<List<Integer>> resLinkedList = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();

            for (int index = 0; index < size; index++) {
                TreeNode curNode = queue.poll();
                level.add(curNode.val);

                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            // resLinkedList.addFirst(level);
            res.add(0, level);
        }
        // return resLinkedList;
        return res;
    }
}
// @lc code=end

