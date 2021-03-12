import java.util.*;

/*
 * @lc app=leetcode id=230 lang=java
 *
 * [230] Kth Smallest Element in a BST
 *
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
 *
 * algorithms
 * Medium (60.99%)
 * Likes:    3595
 * Dislikes: 83
 * Total Accepted:    510K
 * Total Submissions: 813.3K
 * Testcase Example:  '[3,1,4,null,2]\n1'
 *
 * Given the root of a binary search tree, and an integer k, return the k^th
 * (1-indexed) smallest element in the tree.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is n.
 * 1 <= k <= n <= 10^4
 * 0 <= Node.val <= 10^4
 * 
 * 
 * 
 * Follow up: If the BST is modified often (i.e., we can do insert and delete
 * operations) and you need to find the kth smallest frequently, how would you
 * optimize?
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
    
    public int kthSmallest(TreeNode root, int k) {
        // return recursiveBinarySearchSol(root, k);
        // return dfsInOrderRecursiveSol(root, k);
        return dfsInOrderIterativeSol(root, k);
    }

    // Time: (n)
    // Space: O(n)
    // In-order DFS iterative solution
    // Reference: https://leetcode.com/problems/kth-smallest-element-in-a-bst/discuss/63660/3-ways-implemented-in-JAVA-(Python)%3A-Binary-Search-in-order-iterative-and-recursive
    private int dfsInOrderIterativeSol(TreeNode root, int k) {
        // Corner Case
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        int countNum = 0;

        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node); // just like the recursion
                node = node.left;
            } else {
                TreeNode curNode = stack.pop();
                countNum++;
                if (countNum == k) {
                    return curNode.val;
                }
                node = curNode.right;
            }
        }
        return 0;

        // // push nodes till the left end
        // while (root != null) {
        //     stack.push(root);
        //     root = root.left;
        // }

        // while (k != 0) {
        //     TreeNode curNode = stack.pop();
        //     k -= 1;
        //     if (k == 0) {
        //         return curNode.val;
        //     }

        //     TreeNode rightNode = curNode.right;
        //     // push the right node till the left end still!!!
        //     while(rightNode != null) {
        //         stack.push(rightNode);
        //         rightNode = rightNode.left;
        //     }
        // }

        // return 0; // never hit if k is valid and root is not null
    }

    // Time: O(n)
    // Space: O(h)
    // In-order recursive DFS
    // Reference: https://leetcode.com/problems/kth-smallest-element-in-a-bst/discuss/63660/3-ways-implemented-in-JAVA-(Python)%3A-Binary-Search-in-order-iterative-and-recursive
    private int result;
    private int count2;
    private int dfsInOrderRecursiveSol(TreeNode root, int k) {
        // Corner Case
        if (root == null) {
            return 0;
        }

        count2 = k;
        inorderDFS(root);
        return result;
    }

    // In-order DFS
    private void inorderDFS(TreeNode curNode) {
        if (curNode.left != null) {
            inorderDFS(curNode.left);
        }

        count2 -= 1;

        if (count2 == 0) {
            result = curNode.val;
            return;
        }

        if (curNode.right != null) {
            inorderDFS(curNode.right);
        }
    }

    // Time: O(n^2)
    // Space: O(h)
    // Divide the tree as left, root, right. Then compare each count with k to do recusion.
    // References: http://leetcode.jp/leetcode-230-kth-smallest-element-in-a-bst-%E8%A7%A3%E9%A2%98%E6%80%9D%E8%B7%AF%E5%88%86%E6%9E%90/
    // https://leetcode.com/problems/kth-smallest-element-in-a-bst/discuss/63660/3-ways-implemented-in-JAVA-(Python)%3A-Binary-Search-in-order-iterative-and-recursive
    private int recursiveBinarySearchSol(TreeNode root, int k) {
        // Corner Case
        if (root == null) {
            return 0;
        }

        if (root.left != null) { // 判断左子节点是否为空
            // 若左子节点不为空，计算左子树的节点数
            int leftCount = countNodes(root.left);
            if (leftCount >= k) { // 说明第k小的数一定在当前的左子树
                return recursiveBinarySearchSol(root.left, k);
            } else { // 说明第k小的数不再当前左子树，减去左子树的节点数量
                k -= leftCount;
            }
        }

        if (k == 1) { // 说明第k小的数就是当前根节点
            return root.val;
        } else { // 否则减去当前根节点的数量（1）
            k -= 1;
        }

        // 此时说明第k小的数一定在当前右子树
        return recursiveBinarySearchSol(root.right, k);
    }

    // Time: O(n)
    // Space: O(h)
    // Method to count the amount of nodes in a tree.
    private int countNodes(TreeNode root) {
        int count = 1;
        if (root.left != null) {
            count += countNodes(root.left);
        }
        if (root.right != null) {
            count += countNodes(root.right);
        }
        return count;
    }
}
// @lc code=end

