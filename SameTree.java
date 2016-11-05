// Given two binary trees, write a function to check if they are equal or not.
// Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
public class Solution {
    
    // Recursion。自己做的
    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null)
            return true;
        else if ((p == null && q != null) || (p != null) && (q == null))
            return false;
        else if (p.val != q.val)
            return false;
        else
            return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }
    
    
    // Iteration, DFS。自己做的
    public boolean isSameTree(TreeNode p, TreeNode q) {
        
        Stack<TreeNode> nodeStack_P = new Stack<>();
        Stack<TreeNode> nodeStack_Q = new Stack<>();
        nodeStack_P.push(p);
        nodeStack_Q.push(q);
        
        while (!nodeStack_P.isEmpty() && !nodeStack_Q.isEmpty())
        {
            TreeNode curNodeInP = nodeStack_P.pop();
            TreeNode curNodeInQ = nodeStack_Q.pop();
            
            if (curNodeInP == null && curNodeInQ == null)
                continue;
            else if ((curNodeInP == null && curNodeInQ != null) || (curNodeInP != null) && (curNodeInQ == null))
                return false;
            else if (curNodeInP.val != curNodeInQ.val)
                return false;
            else
            {
                nodeStack_P.push(curNodeInP.left);
                nodeStack_Q.push(curNodeInQ.left);
                nodeStack_P.push(curNodeInP.right);
                nodeStack_Q.push(curNodeInQ.right);
            }
        }
        
        if (nodeStack_P.isEmpty() && nodeStack_Q.isEmpty())
            return true;
        else 
            return false;
    }
    
 
 
 
}
