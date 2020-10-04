import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * @lc app=leetcode id=449 lang=java
 *
 * [449] Serialize and Deserialize BST
 *
 * https://leetcode.com/problems/serialize-and-deserialize-bst/description/
 *
 * algorithms
 * Medium (50.26%)
 * Likes:    1084
 * Dislikes: 63
 * Total Accepted:    90.6K
 * Total Submissions: 178.5K
 * Testcase Example:  '[2,1,3]'
 *
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in
 * the same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary search tree. There
 * is no restriction on how your serialization/deserialization algorithm should
 * work. You just need to ensure that a binary search tree can be serialized to
 * a string and this string can be deserialized to the original tree
 * structure.
 * 
 * The encoded string should be as compact as possible.
 * 
 * Note: Do not use class member/global/static variables to store states. Your
 * serialize and deserialize algorithms should be stateless.
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
 class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // Corner Cases
        if (root == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        // buildString(root, sb);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            sb.append(node.val).append(",");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return sb.toString();
    }

    private void buildString(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val).append(",");
        buildString(root.left, sb);
        buildString(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // Corner Cases
        if (data.equals("null")) {
            return null;
        }

        Queue<Integer> queue = new LinkedList<>();
        String[] nodeArray = data.split(",");
        for (String nodeStr : nodeArray) {
            queue.offer(Integer.parseInt(nodeStr));
        }
        return getNode(queue);
    }

    private TreeNode getNode(Queue<Integer> queue) {
        // Corner Cases
        if (queue.isEmpty()) {
            return null;
        }
        Queue<Integer> smallerQueue = new LinkedList<>();
        TreeNode root = new TreeNode(queue.poll());
        while (!queue.isEmpty() && queue.peek() < root.val) {
            smallerQueue.offer(queue.poll());
        }
        root.left = getNode(smallerQueue);
        root.right = getNode(queue);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
// @lc code=end

