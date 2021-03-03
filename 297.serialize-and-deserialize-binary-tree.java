import java.util.*;

/*
 * @lc app=leetcode id=297 lang=java
 *
 * [297] Serialize and Deserialize Binary Tree
 *
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/
 *
 * algorithms
 * Hard (48.15%)
 * Likes:    4015
 * Dislikes: 186
 * Total Accepted:    417.3K
 * Total Submissions: 837.5K
 * Testcase Example:  '[1,2,3,null,null,4,5]'
 *
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in
 * the same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary tree. There is no
 * restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and
 * this string can be deserialized to the original tree structure.
 * 
 * Clarification: The input/output format is the same as how LeetCode
 * serializes a binary tree. You do not necessarily need to follow this format,
 * so please be creative and come up with different approaches yourself.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = []
 * Output: []
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: root = [1]
 * Output: [1]
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: root = [1,2]
 * Output: [1,2]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [0, 10^4].
 * -1000 <= Node.val <= 1000
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
public class Codec {

    // Time: O(n)
    // Space: O(n)
    // Encodes a tree to a single string.
    // Pre-order traverse to convert the tree nodes to a string.
    // Reference: http://www.noteanddata.com/leetcode-297-Serialize-and-Deserialize-Binary-Tree-java-solution-note.html
    public String serialize(TreeNode root) {
        // Corner Cases
        if (root == null) {
            return "#"; // use "#" to represent the null node
        }

        StringBuilder output = new StringBuilder();
        output.append(root.val)
            .append(",").append(serialize(root.left))
            .append(",").append(serialize(root.right));

        return output.toString();
    }

    // Time: O(n)
    // Space: O(n)
    // Decodes your encoded data to tree.
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

        return deserializeQueue(queue);
    }

    private TreeNode deserializeQueue(Queue<String> queue) {
        String curNode = queue.poll();
        // Break condition
        if (curNode.equals("#")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(curNode));

        root.left = deserializeQueue(queue);
        root.right = deserializeQueue(queue);

        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
// @lc code=end

