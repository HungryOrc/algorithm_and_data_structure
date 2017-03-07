/* Ref: https://discuss.leetcode.com/topic/28256/ternary-expression-to-binary-tree
Convert a ternary expression to a binary tree.
Assume that all these ternary expression are valid, 
connamely they won't cause any error or conflict when building the binary trees accordingly.

Examples: 
a?b:c to
  a
 /  \
b   c

a?b?c:d:e to
     a
    / \
   b   e
  / \
 c   d

a?b:c?d:e
  a
 / \
b   c
   / \
  d   e             
  
a?b:c?d:e:f 这样的话怎么办？？？ f 怎么处置？？？ 这一题网上没有原题，无法获取澄清  */
 
// 尝试方法，未必对
class TreeNode {
    char val;
    TreeNode left, right;
    public TreeNode(char val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class Solution {
    
    public TreeNode ternaryExpressionToBinaryTree(char[] expression) {
        if (expression == null || expression.length == 0) {
            return null;
        }
      
        Stack<TreeNode> treeNodeStack = new Stack<>();
        TreeNode root = new TreeNode(expression[0]);
        treeNodeStack.push(root);
      
        int i = 1;
        while (i < expression.length) {
            TreeNode newNode = new TreeNode(expression[i + 1]);
          
            if (expression[i] == '?') {
                treeNodeStack.peek().left = newNode;
                treeNodeStack.push(newNode);
            }
          
            if (expression[i] == ':') {
                treeNodeStack.pop();
                
                while (!treeNodeStack.peek().right != null) {
                    treeNodeStack.pop();
                }
                treeNodeStack.peek().right = newNode;
              
                treeNodeStack.push(newNode);
            }
            i += 2;
        }
      
        return root;
    }
}
