import java.util.*;

/*
 * @lc app=leetcode id=113 lang=java
 *
 * [113] Path Sum II
 *
 * https://leetcode.com/problems/path-sum-ii/description/
 *
 * algorithms
 * Medium (47.48%)
 * Likes:    2509
 * Dislikes: 86
 * Total Accepted:    391.6K
 * Total Submissions: 800K
 * Testcase Example:  '[5,4,8,11,null,13,4,7,2,null,null,5,1]\n22'
 *
 * Given the root of a binary tree and an integer targetSum, return all
 * root-to-leaf paths where each path's sum equals targetSum.
 * 
 * A leaf is a node with no children.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: [[5,4,11,2],[5,8,4,5]]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [1,2,3], targetSum = 5
 * Output: []
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: root = [1,2], targetSum = 0
 * Output: []
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
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
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        // Corner Cases
        if (root == null) {
            return res;
        }

        List<Integer> path = new ArrayList<>();

        dfs(root, res, path, targetSum);

        return res;
    }

    // Time: O(n)
    // Space: O(n)
    // DFS + Backtracking
    // References: https://blog.csdn.net/katrina95/article/details/79466778
    private void dfs(TreeNode root, List<List<Integer>> res, List<Integer> path, int targetSum) {
        if (root.left == null && root.right == null && root.val == targetSum) {
            path.add(root.val);
            res.add(new ArrayList<>(path));
            path.remove(path.size() - 1); // why remove only the last parameter here???
            // may cause the scenario:
            // Expected Answer: [[5,4,11,2],[5,8,4,5]]
            // Output: [[5,4,11,2],[5,4,8,4,5]]
            return;
        }

        path.add(root.val);
        if (root.left != null) {
            dfs(root.left, res, path, targetSum - root.val);
        }
        if (root.right != null) {
            dfs(root.right, res, path, targetSum - root.val);
        }
        path.remove(path.size() - 1); // back-tracking: if a path does not satisfy, then remove the parameters in this path list.
    }
}
// @lc code=end

