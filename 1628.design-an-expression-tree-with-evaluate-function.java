import java.util.Stack;

/**
 * This is the interface for the expression tree Node.
 * You should not remove it, and you can define some classes to implement it.
 */

// https://leetcode.com/problems/design-an-expression-tree-with-evaluate-function/
// DFS + BST
// https://leetcode.com/problems/design-an-expression-tree-with-evaluate-function/discuss/933995/Java-Stack-%2B-OOP
abstract class Node {
  public abstract int evaluate();
  // define your fields here
};

class TreeNode extends Node {
  String val;
  TreeNode left;
  TreeNode right;
  
  // constructor
  TreeNode(String val) {
      this.val = val;
  }
  
  // abstract class could not have method body which should be specified here
  // Based on the input with corresponding tree architecture, the traverse method could be DFS
  public int evaluate() {
      return dfs(this);
  }
  
  // DFS traverse of a BST
  private int dfs(TreeNode root) {
      // break condition
      if (root.left == null && root.right == null) {
          return Integer.valueOf(root.val);
      }
      
      int left = dfs(root.left);
      int right = dfs(root.right);
      
      int result = 0;
      String checkOperators = root.val;
      if (checkOperators.equals("+")) {
          result = left + right;
      } else if (checkOperators.equals("-")) {
          result = left - right;
      } else if (checkOperators.equals("*")) {
          result = left * right;
      } else if (checkOperators.equals("/")) {
          result = left / right;
      }
      
      return result;
  }
}


/**
* This is the TreeBuilder class.
* You can treat it as the driver code that takes the postinfix input 
* and returns the expression tree represnting it as a Node.
*/

class TreeBuilder {
  String operators = "+-*/";
  Stack<TreeNode> stack = new Stack<>();
  
  Node buildTree(String[] postfix) {
      for (String str : postfix) {
          if (operators.contains(str)) {
              TreeNode curNode = new TreeNode(str);
              curNode.right = stack.pop();
              curNode.left = stack.pop();
              stack.push(curNode);
          } else {
              stack.push(new TreeNode(str));
          }
      }
      return stack.pop();
  }
};


/**
* Your TreeBuilder object will be instantiated and called as such:
* TreeBuilder obj = new TreeBuilder();
* Node expTree = obj.buildTree(postfix);
* int ans = expTree.evaluate();
*/