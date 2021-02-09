import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

/*
 * @lc app=leetcode id=872 lang=java
 *
 * [872] Leaf-Similar Trees
 *
 * https://leetcode.com/problems/leaf-similar-trees/description/
 *
 * algorithms
 * Easy (64.53%)
 * Likes:    1035
 * Dislikes: 44
 * Total Accepted:    114.9K
 * Total Submissions: 178K
 * Testcase Example:  '[3,5,1,6,2,9,8,null,null,7,4]\n[3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]'
 *
 * Consider all the leaves of a binary tree, from left to right order, the
 * values of those leaves form a leaf value sequence.
 * 
 * 
 * 
 * For example, in the given tree above, the leaf value sequence is (6, 7, 4,
 * 9, 8).
 * 
 * Two binary trees are considered leaf-similar if their leaf value sequence is
 * the same.
 * 
 * Return true if and only if the two given trees with head nodes root1 and
 * root2 are leaf-similar.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 =
 * [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root1 = [1], root2 = [1]
 * Output: true
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: root1 = [1], root2 = [2]
 * Output: false
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: root1 = [1,2], root2 = [2,2]
 * Output: true
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: root1 = [1,2,3], root2 = [1,3,2]
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in each tree will be in the range [1, 200].
 * Both of the given trees will have values in the range [0, 200].
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
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        // return DFSSolution(root1, root2);
        return IterativeSolution(root1, root2);
    }

    // I could refer further to the solution in LeetCode Discussion:
    // https://leetcode.com/problems/leaf-similar-trees/discuss/152329/C%2B%2BJavaPython-O(H)-Space

    // Time: O(m+n)
    // Space: O(m+n)
    // Iterative solution
    // References: http://www.zhaojun.im/leetcode-872/
    public boolean IterativeSolution(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        getLeaves(root1, list1);
        getLeaves(root2, list2);

        return list1.equals(list2);
    }

    public void getLeaves(TreeNode root, List<Integer> list) {
        // Corner Cases
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            TreeNode curNode = stack.pop();

            if (curNode.left != null) {
                stack.push(curNode.left);
            }
            if (curNode.right != null) {
                stack.push(curNode.right);
            }

            if (curNode.left == null && curNode.right == null) {
                list.add(curNode.val);
            }
        }
    }

    // Time: O(m+n)
    // Space: O(m+n)
    // DFS with two recursions
    // References: http://www.zhaojun.im/leetcode-872/
    public boolean DFSSolution(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        dfs(root1, list1);
        dfs(root2, list2);
        return list1.equals(list2);
    }

    public void dfs(TreeNode root, List<Integer> list) {
        // Corner Cases
        // Also the break condition of the recursion
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            list.add(root.val);
        }
        
        dfs(root.left, list);
        dfs(root.right, list);
    }
}
// @lc code=end

