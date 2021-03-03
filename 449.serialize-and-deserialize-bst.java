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

    // Time: O(n)
    // Space: O(n)
    // Encodes a tree to a single string.
    // The same idea as No.297. This one is a special case.
    // Pre-order traverse to convert the tree nodes to a string.
    // Reference: http://www.noteanddata.com/leetcode-297-Serialize-and-Deserialize-Binary-Tree-java-solution-note.html
    public String serialize(TreeNode root) {
        // Corner Cases
        if (root == null) {
            return "#";
        }

        StringBuilder nodeString = new StringBuilder();
        nodeString.append(root.val)
            .append(",").append(serialize(root.left))
            .append(",").append(serialize(root.right));
        
        return nodeString.toString();
    }

    // Time: O(n)
    // Space: O(n)
    // Decodes your encoded data to tree.
    // The same idea as No.297. This one is a special case.
    // Use queue + recursion to realize this process.
    public TreeNode deserialize(String data) {
        // Corner Cases
        if (data.length() == 0) {
            return null;
        }

        String[] nodeArray = data.split(",");

        Queue<String> queue = new LinkedList<>();
        for (String node : nodeArray) {
            queue.offer(node);
        }

        return buildTree(queue);
    }

    private TreeNode buildTree(Queue<String> queue) {
        String curNode = queue.poll();

        // Break Condition
        if (curNode.equals("#")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(curNode));

        root.left = buildTree(queue);
        root.right = buildTree(queue);

        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
// @lc code=end

