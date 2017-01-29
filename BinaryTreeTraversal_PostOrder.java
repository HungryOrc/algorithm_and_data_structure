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
    
    // 方法1: Non Recursion (Recommended)，我的独创方式
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        
        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode> nodeStack = new Stack<>();
        
        if (root == null) {
            return result;
        }
        
        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            TreeNode curNode = nodeStack.pop();
            
            // 要么，这个node是leaf
            // 要么，这个node的左右子都已被纳入stack了
            if (curNode.left == null && curNode.right == null) {
                result.add(curNode.val);
            }
            else {
                // 先把中间的也就是自己放进stack
                nodeStack.push(curNode);
                
                // 再把右边的放进stack，即处理右子树
                if (curNode.right != null) {
                    nodeStack.push(curNode.right);
                }
                
               // 最后把左边的放进stack，即下一步要先处理左子树
                if (curNode.left != null) {
                    nodeStack.push(curNode.left);
                }
            
                // 本node的历史使命完成了。左右都剪掉，以利于将来被处理
                curNode.left = null;
                curNode.right = null;
            }
        }
        return result;
    }
    
    
    // 方法2: Recursion - Traverse
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        
        ArrayList<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }
    private void postorder(TreeNode curNode, ArrayList<Integer> result) {
        
        if (curNode == null) {
            return; // do nothing
        }
        
        postorder(curNode.left, result);
        postorder(curNode.right, result);
        result.add(curNode.val);
    }
   
    
    // 方法3: Divide & Conquer
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        
        ArrayList<Integer> result = new ArrayList<>();
        
        if (root == null) {
            return result;
        }
        
        ArrayList<Integer> leftVals = postorderTraversal(root.left);
        ArrayList<Integer> rightVals = postorderTraversal(root.right);
        
        result.addAll(leftVals);
        result.addAll(rightVals);
        result.add(root.val);
        
        return result;
    }
   
}
