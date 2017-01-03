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
    // 我的朴素 Iteration 方法
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
            if (curNode.left==null && curNode.right==null)
                result.add(curNode.val);
            else
            {
                // 先把右边的放进stack，即最后处理右子树
                if (curNode.right != null)
                    nodeStack.push(curNode.right);
                    
                // 把中间的也就是自己放进stack
                nodeStack.push(curNode);
                
                // 最后把右边的放进stack，即下一步要先处理左子树
                if (curNode.left != null)
                    nodeStack.push(curNode.left);
                
                // 本node的历史使命完成了。左右都剪掉，以利于将来被处理
                curNode.left = null;
                curNode.right = null;
            }
        }
        return result;
    }
 
    
    // 我的朴素 Recursion 方法。运算速度比 Iteration 快
    public List<Integer> inorderTraversal(TreeNode root) 
    {
        ArrayList<Integer> result = new ArrayList<>();
        
        if (root == null)
            return result;
        else
            inorder(root, result);
            
        return result;
    }
    private static void inorder(TreeNode curNode, ArrayList<Integer> al)
    {
        if (curNode == null)
            return;
        else
        {
            inorder(curNode.left, al);
            al.add(curNode.val);
            inorder(curNode.right, al);
        }
    }    
 
}
