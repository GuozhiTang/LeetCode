import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

/*
 * @lc app=leetcode id=863 lang=java
 *
 * [863] All Nodes Distance K in Binary Tree
 *
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/description/
 *
 * algorithms
 * Medium (60.41%)
 * Likes:    5960
 * Dislikes: 122
 * Total Accepted:    222.1K
 * Total Submissions: 364.6K
 * Testcase Example:  '[3,5,1,6,2,0,8,null,null,7,4]\n5\n2'
 *
 * Given the root of a binary tree, the value of a target node target, and an
 * integer k, return an array of the values of all nodes that have a distance k
 * from the target node.
 * 
 * You can return the answer in any order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
 * Output: [7,4,1]
 * Explanation: The nodes that are a distance 2 from the target node (with
 * value 5) have values 7, 4, and 1.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [1], target = 1, k = 3
 * Output: []
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [1, 500].
 * 0 <= Node.val <= 500
 * All the values Node.val are unique.
 * target is the value of one of the nodes in the tree.
 * 0 <= k <= 1000
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

    // node - distance to target
    private Map<TreeNode, Integer> map = new HashMap<>();

    // Time: O(n)
    // Space: O(n)
    // DFS + Tree Recursion
    // https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/discuss/143798/1ms-beat-100-simple-Java-dfs-with(without)-hashmap-including-explanation
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> resultList = new LinkedList<>();
        // Corner Cases
        if (root == null) {
            return resultList;
        }

        find(root, target);
        dfs(root, map.get(root), k, resultList);

        return resultList;
    }

    // traver the tree to get the distance from each node to target
    private int find(TreeNode root, TreeNode target) {
        // breack condition
        if (root == null) {
            return -1;
        }
        if (root == target) {
            map.put(root, 0);
            return 0;
        }

        int left = find(root.left, target);
        if (left >= 0) {
            map.put(root, left + 1);
            return left + 1;
        }

        int right = find(root.right, target);
        if (right >= 0) {
            map.put(root, right + 1);
            return right + 1;
        }

        return -1;
    }

    // directly dfs the tree to seach for all nodes with distance k
    private void dfs(TreeNode root, int length, int k, List<Integer> resultList) {
        // breack condition
        if (root == null) {
            return;
        }

        if (map.containsKey(root)) {
            length = map.get(root);
        }

        if (length == k) {
            resultList.add(root.val);
        }

        dfs(root.left, length + 1, k, resultList);
        dfs(root.right, length + 1, k, resultList);
    }
}
// @lc code=end

