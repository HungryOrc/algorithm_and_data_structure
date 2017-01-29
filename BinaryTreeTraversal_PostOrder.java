/* Given a binary tree, return the postorder traversal of its nodes' values.

Example
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1]. */

/* Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * } */

public class Solution {
    /* @param root: The root of binary tree.
     * @return: Postorder in ArrayList which contains node values. */
    
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        
        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode> nodeStack = new Stack<>();
        
        if (root == null) {
            return result;
        }
        
        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            TreeNode curNode = nodeStack.pop();
            
            if (curNode.left == null && curNode.right == null) {
                result.add(curNode.val);
            }
            else {
                
                nodeStack.push(curNode);
                
                if (curNode.right != null) {
                    nodeStack.push(curNode.right);
                }
                
                if (curNode.left != null) {
                    nodeStack.push(curNode.left);
                }
            
                curNode.left = null;
                curNode.right = null;
            }
        }
        return result;
        
    }
}
