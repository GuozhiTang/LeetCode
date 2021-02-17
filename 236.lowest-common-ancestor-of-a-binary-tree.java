import java.util.*;

/*
 * @lc app=leetcode id=236 lang=java
 *
 * [236] Lowest Common Ancestor of a Binary Tree
 *
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 *
 * algorithms
 * Medium (42.32%)
 * Likes:    3098
 * Dislikes: 164
 * Total Accepted:    410.6K
 * Total Submissions: 950.3K
 * Testcase Example:  '[3,5,1,6,2,0,8,null,null,7,4]\n5\n1'
 *
 * Given a binary tree, find the lowest common ancestor (LCA) of two given
 * nodes in the tree.
 * 
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor
 * is defined between two nodes p and q as the lowest node in T that has both p
 * and q as descendants (where we allow a node to be a descendant of itself).”
 * 
 * Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant
 * of itself according to the LCA definition.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * All of the nodes' values will be unique.
 * p and q are different and both values will exist in the binary tree.
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

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dfsRecursion(root, p, q);
        // return bfsHashMap(root, p, q);
    }

    // Time: Worst Case: O(n)
    // Space: O(h)
    // Need to diffenciate this to the tree triming/Pruning/Deleting
    // References: https://blog.csdn.net/happyaaaaaaaaaaa/article/details/51635658
    private TreeNode dfsRecursion(TreeNode root, TreeNode p, TreeNode q) {
        // Corner Cases
        // Break condition:
        // if reaches p or q => no need to continue recusion due to `Ancestor`.
        // if reaches null => there is no p or q in this branch.
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root; // it would be under root path.
        }
        if (left != null) {
            return left; // means that it finds q or p in left path.
        }
        return right;
    }

    // Time: O(n)
    // Space: O(n)
    // Use HashMap to store <node, fater node> and use HashSet to compare the common father nodes
    // References: https://blog.csdn.net/happyaaaaaaaaaaa/article/details/51635658
    private TreeNode bfsHashMap(TreeNode root, TreeNode p, TreeNode q) {
        // Corner Cases
        if (root == null) {
            return null;
        }

        // HashMap<node, father node>
        HashMap<TreeNode, TreeNode> parent = new HashMap<TreeNode, TreeNode>();
        parent.put(root, null);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // find the fater node of p and q
        // break the while loop only if parent contains both q and p
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            int size = queue.size();
            for (int index = 0; index < size; index++) {
                TreeNode curNode = queue.poll();
                if (curNode != null) {
                    parent.put(root.left, root);
                    parent.put(root.right, root);
                    if (curNode.left != null) {
                        queue.offer(curNode.left);
                    }
                    if (curNode.right != null) {
                        queue.offer(curNode.right);
                    }
                }
            }
        }

        HashSet<TreeNode> set = new HashSet<>();
        // put all p's fater nodes to the set
        while (p != null) {
            set.add(p);
            p = parent.get(p);
        }

        // Seach when set does not contain q's this father node
        // which mean returns when find q as well in the set
        while (!set.contains(q)) {
            q = parent.get(q);
        }

        return q; // return this common fater node
    }
}
// @lc code=end

