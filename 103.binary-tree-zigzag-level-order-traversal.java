import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

/*
 * @lc app=leetcode id=103 lang=java
 *
 * [103] Binary Tree Zigzag Level Order Traversal
 *
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
 *
 * algorithms
 * Medium (45.12%)
 * Likes:    1649
 * Dislikes: 85
 * Total Accepted:    315.6K
 * Total Submissions: 692.1K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Given a binary tree, return the zigzag level order traversal of its nodes'
 * values. (ie, from left to right, then right to left for the next level and
 * alternate between).
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
 * return its zigzag level order traversal as:
 * 
 * [
 * ⁠ [3],
 * ⁠ [20,9],
 * ⁠ [15,7]
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
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    // Time: O(n)
    // Space: O(n)
    // BFS + LinkedList
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        // Corner Cases
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        return bfs(result, queue);
    }

    private List<List<Integer>> bfs(List<List<Integer>> result, Queue<TreeNode> queue) {
        boolean reverse = false;

        while (!queue.isEmpty()) {
            int curSize = queue.size();

            // list to store the nodes of each layer
            LinkedList<Integer> list = new LinkedList<>();

            // layer by layer
            for (int index = 0; index < curSize; index++) {
                TreeNode curNode = queue.poll();

                if (!reverse) {
                    list.add(curNode.val);
                } else {
                    list.addFirst(curNode.val);
                }

                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }

            result.add(list);
            reverse = !reverse;
        }

        return result;
    }

    // =================================================================

    // // Time: O(n)
    // // Space: O(n)
    // // BFS
    // public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    //     List<List<Integer>> result = new LinkedList<>();
    //     // Corner Cases
    //     if (root == null) {
    //         return result;
    //     }

    //     Queue<TreeNode> queue = new LinkedList<>();
    //     queue.offer(root);
    //     boolean needReverse = false;

    //     while (!queue.isEmpty()) {
    //         int queueSize = queue.size();

    //         LinkedList<Integer> resLayer = new LinkedList<>();
    //         for (int index = 0; index < queueSize; index++) {
    //             TreeNode curNode = queue.poll();

    //             if (needReverse) {
    //                 resLayer.addFirst(curNode.val);
    //             } else {
    //                 resLayer.add(curNode.val);
    //             }

    //             if (curNode.left != null) {
    //                 queue.offer(curNode.left);
    //             }
    //             if (curNode.right != null) {
    //                 queue.offer(curNode.right);
    //             }
    //         }
    //         needReverse = !needReverse;
    //         result.add(resLayer);
    //     }
    //     return result;
    // }
}
// @lc code=end

