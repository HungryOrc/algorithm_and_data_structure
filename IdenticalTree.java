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
    
    // 方法1，Recursion。自己做的
    public boolean isIdentical(TreeNode one, TreeNode two) {
      if (one == null && two == null) {
        return true;
      } else if (one == null || two == null) {
        return false;
      }

      if (one.key != two.key) {
        return false;
      }

      return (isIdentical(one.left, two.left)) && (isIdentical(one.right, two.right));
    }
    
    
    // 方法2，Iteration DFS by 2 Stacks。自己做的
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
    
 
    // Iteration BFS by 2 Queues。自己做的
    public boolean isSameTree(TreeNode p, TreeNode q) {
        
        Queue<TreeNode> nodeQueue_P = new LinkedList<>();
        Queue<TreeNode> nodeQueue_Q = new LinkedList<>();
        nodeQueue_P.offer(p);
        nodeQueue_Q.offer(q);
        
        while (!nodeQueue_P.isEmpty() && !nodeQueue_Q.isEmpty())
        {
            TreeNode curNodeInP = nodeQueue_P.poll();
            TreeNode curNodeInQ = nodeQueue_Q.poll();
            
            if (curNodeInP == null && curNodeInQ == null)
                continue;
            else if ((curNodeInP == null && curNodeInQ != null) || (curNodeInP != null) && (curNodeInQ == null))
                return false;
            else if (curNodeInP.val != curNodeInQ.val)
                return false;
            else
            {
                nodeQueue_P.offer(curNodeInP.left);
                nodeQueue_Q.offer(curNodeInQ.left);
                nodeQueue_P.offer(curNodeInP.right);
                nodeQueue_Q.offer(curNodeInQ.right);
            }
        }
        
        if (nodeQueue_P.isEmpty() && nodeQueue_Q.isEmpty())
            return true;
        else 
            return false;
    } 
 
}
