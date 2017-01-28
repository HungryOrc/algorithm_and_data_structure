/* Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree [1,null,2,3],
   1
    \
     2
    /
   3
return [1,3,2].

 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * } */

public class Solution 
{ 
    // 方法1: Non Recursion (Recommended)，我的独创方式
    public List<Integer> inorderTraversal(TreeNode root) 
    {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode> nodeStack = new Stack<>();
        
        if (root == null)
            return result;
        
        nodeStack.push(root);
        while (!nodeStack.isEmpty())
        {
            TreeNode curNode = nodeStack.pop();
           
            // if this is a leaf
            if (curNode.left==null && curNode.right==null)
                result.add(curNode.val);
            else
            {
                // 先把右边的放进stack，即最后处理右子树
                if (curNode.right != null)
                    nodeStack.push(curNode.right);
                    
                // 把中间的也就是自己放进stack
                nodeStack.push(curNode);
                
                // 最后把左边的放进stack，即下一步要先处理左子树
                if (curNode.left != null)
                    nodeStack.push(curNode.left);
                
                // 本node的历史使命完成了。左右都剪掉，以利于将来被处理
                curNode.left = null;
                curNode.right = null;
            }
        }
        return result;
    }
 
    
    // 方法2: Recursion - Traverse
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }
    private void inorder(TreeNode root, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }
       
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }
   
    
    // 方法3: Divide & Conquer
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
       
        // Divide 
        ArrayList<Integer> leftVals = inorderTraversal(root.left);
        ArrayList<Integer> rightVals = inorderTraversal(root.right);
       
        // Conquer
        result.addAll(leftVals);
        result.add(root.val);
        result.addAll(rightVals);
        return result;
    }
 
}
