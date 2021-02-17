import java.util.*;

/*
 * @lc app=leetcode id=257 lang=java
 *
 * [257] Binary Tree Paths
 *
 * https://leetcode.com/problems/binary-tree-paths/description/
 *
 * algorithms
 * Easy (52.11%)
 * Likes:    2352
 * Dislikes: 124
 * Total Accepted:    374.7K
 * Total Submissions: 700.4K
 * Testcase Example:  '[1,2,3,null,5]'
 *
 * Given a binary tree, return all root-to-leaf paths.
 * 
 * Note: A leaf is a node with no children.
 * 
 * Example:
 * 
 * 
 * Input:
 * 
 * ⁠  1
 * ⁠/   \
 * 2     3
 * ⁠\
 * ⁠ 5
 * 
 * Output: ["1->2->5", "1->3"]
 * 
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
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
    public List<String> binaryTreePaths(TreeNode root) {
        // return DFSSolution(root);
        // return DFSStack(root);
        return BFSQueue(root);
    }

    // Time: O(n)
    // Space: O(n)
    // BFS Queue solution
    // References:
    // Updated based on (specified the `level` for loop): https://leetcode.com/problems/binary-tree-paths/discuss/68278/My-Java-solution-in-DFS-BFS-recursion
    private List<String> BFSQueue(TreeNode root) {
        List<String> res = new ArrayList<>();

        // Corner Cases
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<String> queueString = new LinkedList<>();
        queue.offer(root);
        queueString.offer("");

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int index = 0; index < size; index++) {
                TreeNode curNode = queue.poll();
                String curString = queueString.poll();
    
                if (curNode.left == null && curNode.right == null) {
                    res.add(curString + curNode.val);
                }
    
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                    queueString.offer(curString + curNode.val + "->");
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                    queueString.offer(curString + curNode.val + "->");
                }
            }
        }

        return res;
    }

    // Time: O(n)
    // Space: O(n)
    // DFS stack solution
    private List<String> DFSStack(TreeNode root) {
        List<String> res = new ArrayList<>();

        // Corner Cases
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        Stack<String> stackString = new Stack<>();
        stack.push(root);
        stackString.push("");

        while (!stack.isEmpty()) {
            TreeNode curNode = stack.pop();
            String curString = stackString.pop();

            if (curNode.left == null && curNode.right == null) {
                res.add(curString + curNode.val);
            }

            // Pre-order
            if (curNode.right != null) {
                stack.push(curNode.right);
                stackString.push(curString + curNode.val + "->");
            }
            if (curNode.left != null) {
                stack.push(curNode.left);
                stackString.push(curString + curNode.val + "->");
            }
        }

        return res;
    }

    // Time: O(n)
    // Space: O(h)
    // DFS Solution
    // References: https://blog.csdn.net/fuxuemingzhu/article/details/71249429
    private List<String> DFSSolution(TreeNode root) {
        List<String> res = new ArrayList<>();
        // Corner Cases
        if (root == null) {
            return res;
        }

        dfs(root, "", res);

        return res;
    }

    private void dfs(TreeNode root, String path, List<String> res) {
        if (root.left == null && root.right == null) {
            res.add(path + root.val); // After that, the path would return back to ""
        }

        if (root.left != null) {
            dfs(root.left, path + root.val + "->", res);
        }
        if (root.right != null) {
            dfs(root.right, path + root.val + "->", res);
        }
    }
}
// @lc code=end

