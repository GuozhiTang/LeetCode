import java.util.List;
import java.util.ArrayList;

class Solution {
    
  private List<Integer> results = new ArrayList<>();
  
  // Time: O(n)
  // Space: O(n)
  // Recursion
  // https://leetcode.com/problems/boundary-of-binary-tree/discuss/101280/Java(12ms)-left-boundary-left-leaves-right-leaves-right-boundary
  public List<Integer> boundaryOfBinaryTree(TreeNode root) {
      // Corner Cases
      if (root == null) {
          return results;
      }
      
      results.add(root.val);
      leftBoundary(root.left);
      leaves(root.left);
      leaves(root.right);
      rightBoundary(root.right);
      
      return results;
  }
  
  // Time: O(n)
  private void leftBoundary(TreeNode root) {
      // Corner Cases
      // Cannot be leaves
      if (root == null || (root.left == null && root.right == null)) {
          return;
      }
      results.add(root.val);
      if (root.left == null) {
          leftBoundary(root.right);
      } else {
          leftBoundary(root.left);
      }
  }
  
  // Time: O(n)
  private void leaves(TreeNode root) {
      // Corner Cases
      if (root == null) {
          return;
      }
      if (root.left == null && root.right == null) {
          results.add(root.val);
          return;
      }
      leaves(root.left);
      leaves(root.right);
  }
  
  // Time: O(n)
  private void rightBoundary(TreeNode root) {
      // Corner Cases
      // Cannot be leaves
      if (root == null || (root.left == null && root.right == null)) {
          return;
      }
      if (root.right == null) {
          rightBoundary(root.left);
      } else {
          rightBoundary(root.right);
      }
      results.add(root.val);
  }
}