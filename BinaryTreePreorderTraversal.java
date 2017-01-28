/* Given a binary tree, return the preorder traversal of its nodes' values. 
先root，再左子树，再右子树。

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * } */

// Ref: http://www.jiuzhang.com/solutions/binary-tree-preorder-traversal/
public class Solution 
{
    // 方法1: Non Recursion (Recommended)
    public ArrayList<Integer> preorderTraversal(TreeNode root) 
    {
        Stack<TreeNode> nodeStack = new Stack<>();
        ArrayList<Integer> preorder = new ArrayList<>();
        
        if (root == null) {
            return preorder;
        }
        
        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            TreeNode curNode = nodeStack.pop();
            preorder.add(curNode.val);
            
            // 注意！因为遍历的时候要先放左子，所以入栈的时候要先入右子！！！
            if (curNode.right != null) {
                nodeStack.push(curNode.right);
            }
            if (curNode.left != null) {
                nodeStack.push(curNode.left);
            }
        }
        return preorder;
    }
   
   
    // 方法2: Recursion - Traverse
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }
    private void preorder(TreeNode root, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }
       
        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right, result);
    }
   
   
    // 方法3: Divide & Conquer
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
       
        // Divide 
        ArrayList<Integer> leftVals = preorderTraversal(root.left);
        ArrayList<Integer> rightVals = preorderTraversal(root.right);
       
        // Conquer
        result.add(root.val);
        result.addAll(leftVals);
        result.addAll(rightVals);
        return result;
    }

}
