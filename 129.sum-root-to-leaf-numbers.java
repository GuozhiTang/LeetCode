import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

/*
 * @lc app=leetcode id=129 lang=java
 *
 * [129] Sum Root to Leaf Numbers
 *
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/description/
 *
 * algorithms
 * Medium (49.66%)
 * Likes:    2064
 * Dislikes: 53
 * Total Accepted:    315.8K
 * Total Submissions: 621.6K
 * Testcase Example:  '[1,2,3]'
 *
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path
 * could represent a number.
 * 
 * An example is the root-to-leaf path 1->2->3 which represents the number
 * 123.
 * 
 * Find the total sum of all root-to-leaf numbers.
 * 
 * Note: A leaf is a node with no children.
 * 
 * Example:
 * 
 * 
 * Input: [1,2,3]
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   3
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 * 
 * Example 2:
 * 
 * 
 * Input: [4,9,0,5,1]
 * ⁠   4
 * ⁠  / \
 * ⁠ 9   0
 * / \
 * 5   1
 * Output: 1026
 * Explanation:
 * The root-to-leaf path 4->9->5 represents the number 495.
 * The root-to-leaf path 4->9->1 represents the number 491.
 * The root-to-leaf path 4->0 represents the number 40.
 * Therefore, sum = 495 + 491 + 40 = 1026.
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
    public int sumNumbers(TreeNode root) {
        // return dfsSolution(root);
        // return dfsStack(root);
        return bfsQueue(root);
    }

    // Time: O(n)
    // Space: O(n)
    // BFS Solution
    // References: https://blog.csdn.net/happyaaaaaaaaaaa/article/details/51586542
    private int bfsQueue(TreeNode root) {
        // Corner Cases
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> queueSum = new LinkedList<>();
        queue.offer(root);
        queueSum.offer(0);

        int res = 0;

        return bfs(queue, queueSum, res);
    }

    private int bfs(Queue<TreeNode> queue, Queue<Integer> queueSum, int res) {
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int index = 0; index < size; index++) {
                TreeNode curNode = queue.poll();
                int curSum = queueSum.poll() * 10 + curNode.val;

                if (curNode.left == null && curNode.right == null) {
                    res = res + curSum;
                }

                if (curNode.left != null) {
                    queue.add(curNode.left);
                    queueSum.add(curSum);
                }
                if (curNode.right != null) {
                    queue.add(curNode.right);
                    queueSum.add(curSum);
                }
            }
        }
        
        return res;
    }

    // Time: O(n)
    // Space: O(n)
    // Use stack to imitate DFS
    // References: https://blog.csdn.net/happyaaaaaaaaaaa/article/details/51586542
    private int dfsStack(TreeNode root) {
        // Corner Cases
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> stackSum = new Stack<>();
        stack.add(root);
        stackSum.add(0);

        int res = 0;
        while (!stack.isEmpty()) {
            root = stack.pop();
            int curSum = stackSum.pop();
            curSum = curSum * 10 + root.val;
            if (root.left == null && root.right == null) {
                res = res + curSum;
            }

            if (root.left != null) {
                stack.add(root.left);
                stackSum.add(curSum);
            }
            if (root.right != null) {
                stack.add(root.right);
                stackSum.add(curSum);
            }
        }

        return res;
    }

    // Time: O(n)
    // Space: O(h)
    // DFS Solution
    // References: https://blog.csdn.net/happyaaaaaaaaaaa/article/details/51586542
    private int dfsSolution(TreeNode root) {
        // Corner Cases
        if (root == null) {
            return 0;
        }

        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int curSum) {
        // Break condition
        if (root == null) {
            return 0;
        }

        curSum = curSum * 10 + root.val;

        if (root.left == null && root.right == null) {
            return curSum;
        }

        return dfs(root.left, curSum) + dfs(root.right, curSum);
    }
}
// @lc code=end

