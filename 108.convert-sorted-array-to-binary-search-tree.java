import java.util.*;

/*
 * @lc app=leetcode id=108 lang=java
 *
 * [108] Convert Sorted Array to Binary Search Tree
 *
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
 *
 * algorithms
 * Easy (58.75%)
 * Likes:    3607
 * Dislikes: 267
 * Total Accepted:    511.2K
 * Total Submissions: 842.5K
 * Testcase Example:  '[-10,-3,0,5,9]'
 *
 * Given an integer array nums where the elements are sorted in ascending
 * order, convert it to a height-balanced binary search tree.
 * 
 * A height-balanced binary tree is a binary tree in which the depth of the two
 * subtrees of every node never differs by more than one.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: [0,-10,5,null,-3,null,9] is also accepted:
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,3]
 * Output: [3,1]
 * Explanation: [1,3] and [3,1] are both a height-balanced BSTs.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums is sorted in a strictly increasing order.
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
    public TreeNode sortedArrayToBST(int[] nums) {
        // Corner Cases
        if (nums == null || nums.length == 0) {
            return null;
        }

        // return recursiveDFS(nums, 0, nums.length - 1);
        return iterativeSol(nums);
    }

    // Time: O(n)
    // Space: O(n)
    // Iteratively build a tree using pre-order traverse and stack.
    // Reference: https://blog.csdn.net/happyaaaaaaaaaaa/article/details/51496939
    private TreeNode iterativeSol(int[] nums) {
        // 一个栈用于存放区间的左，右边界指针 - left, right
        Stack<Integer> stack = new Stack<>();
        // 一个栈存放当前自取减对应子树的根节点
        Stack<TreeNode> tree = new Stack<>();

        stack.add(nums.length - 1);
        stack.add(0);

        TreeNode root = new TreeNode(0);
        tree.add(root);

        while (!stack.isEmpty()) { // while the left and right interval is not empty.
            int left = stack.pop();
            int right = stack.pop();
            int mid = left + (right - left) / 2;

            TreeNode curNode = tree.pop();
            curNode.val = nums[mid];

            int low = left, high = mid - 1;
            if (low <= high) {
                curNode.left = new TreeNode(0);
                tree.add(curNode.left);
                stack.push(high);
                stack.push(low);
            }

            low = mid + 1;
            high = right;
            if (low <= high) {
                curNode.right = new TreeNode(0);
                tree.add(curNode.right);
                stack.push(high);
                stack.push(low);
            }
        }

        return root;
    }

    // Time: O(n)
    // Space: O(h)
    // Recursively build a tree using DFS/pre-order.
    private TreeNode recursiveDFS(int[] nums, int left, int right) {
        // Break condition
        if (left > right) {
            return null;
        }

        int mid = (right - left) / 2 + left;
        TreeNode root = new TreeNode(nums[mid]); // here the value is nums[mid], not mid
        root.left = recursiveDFS(nums, left, mid - 1);
        root.right = recursiveDFS(nums, mid + 1, right);

        return root;
    }
}
// @lc code=end

