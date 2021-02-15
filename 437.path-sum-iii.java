import java.util.HashMap;

/*
 * @lc app=leetcode id=437 lang=java
 *
 * [437] Path Sum III
 *
 * https://leetcode.com/problems/path-sum-iii/description/
 *
 * algorithms
 * Medium (47.44%)
 * Likes:    4767
 * Dislikes: 319
 * Total Accepted:    254.1K
 * Total Submissions: 528.6K
 * Testcase Example:  '[10,5,-3,3,2,null,11,3,-2,null,1]\n8'
 *
 * You are given a binary tree in which each node contains an integer value.
 * 
 * Find the number of paths that sum to a given value.
 * 
 * The path does not need to start or end at the root or a leaf, but it must go
 * downwards
 * (traveling only from parent nodes to child nodes).
 * 
 * The tree has no more than 1,000 nodes and the values are in the range
 * -1,000,000 to 1,000,000.
 * 
 * Example:
 * 
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * 
 * ⁠     10
 * ⁠    /  \
 * ⁠   5   -3
 * ⁠  / \    \
 * ⁠ 3   2   11
 * ⁠/ \   \
 * 3  -2   1
 * 
 * Return 3. The paths that sum to 8 are:
 * 
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
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

    public int pathSum(TreeNode root, int sum) {
        // return dfsSolution(root, sum);
        return hashSolution(root, sum);
    }

    // Time: O(n)
    // Space: O(h)
    // HashMap, like Two Sum
    // Reference: https://www.cnblogs.com/liujinhong/p/6444322.html
    // Need to be re-learnt for this problem
    private int hashSolution(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        return helper(root, 0, sum, preSum);
    }

    private int helper(TreeNode root, int curSum, int target, HashMap<Integer, Integer> preSum) {
        // Corner Cases
        if (root == null) {
            return 0;
        }

        curSum = curSum + root.val;
        int res = preSum.getOrDefault(curSum - target, 0);
        //把当前节点加入到路径中
        preSum.put(curSum, preSum.getOrDefault(curSum, 0) + 1);
        //处理该节点的左子树和右子树
        res += helper(root.left, curSum, target, preSum);
        res += helper(root.right, curSum, target, preSum);
        //回溯。把已经处理完毕的当前节点从路径中清除
        preSum.put(curSum, preSum.get(curSum) - 1);

        return res;
    }

    // Time: O(n^2)
    // Space: O(h), O(logn)
    // DFS + recursive node
    private int dfsSolution(TreeNode root, int sum) {
        // Corner Cases
        if (root == null) {
            return 0;
        }

        return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    // To find the amount of the path which equals to sum under this root TreeNode
    // Time: O(n)
    // References: https://blog.csdn.net/mine_song/article/details/69945582
    private int dfs(TreeNode root, int sum) {
        int res = 0;
        if (root == null) {
            return res;
        }
        if (root.val == sum) {
            res++;
        }
        res = res + dfs(root.left, sum - root.val);
        res = res + dfs(root.right, sum - root.val);

        return res;
    }
}
// @lc code=end

