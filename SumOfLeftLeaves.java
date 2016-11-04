/* Find the sum of all left leaves in a given binary tree.
Example:
    3
   / \
  9  20
    /  \
   15   7
There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.

Definition for a binary tree node.
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}   */
 
public class Solution {
    
    // Recursion。自己想出来的，纪念一下
    public int sumOfLeftLeaves(TreeNode root) {
        
        if (root == null)
            return 0;
            
        int sumOfLeft = 0;
        
        if (root.left != null && root.left.left == null && root.left.right == null)
            sumOfLeft = root.left.val;
        
        sumOfLeft += sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
        
        return sumOfLeft;
    }
    
    
    // Iterative DFS。自己想出来的，纪念一下
    public int sumOfLeftLeaves(TreeNode root) {
        
        if (root == null)
            return 0;
            
        int sumOfLeft = 0;
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);
        
        while(!nodeStack.isEmpty())
        {
            TreeNode curNode = nodeStack.pop();
            
            if (curNode.left != null && curNode.left.left == null && curNode.left.right == null)
                sumOfLeft += curNode.left.val;
            else if (curNode.left != null)
                nodeStack.push(curNode.left);
                
            if (curNode.right != null)
                nodeStack.push(curNode.right);
        }
        return sumOfLeft;
    }
    
    
    // Iterative BFS。自己想出来的，纪念一下
    public int sumOfLeftLeaves(TreeNode root) {
        
        if (root == null)
            return 0;
            
        int sumOfLeft = 0;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        
        while(!nodeQueue.isEmpty())
        {
            int numOfRemainingNodesInThisLevel = nodeQueue.size();
            
            while (numOfRemainingNodesInThisLevel > 0)
            {
                TreeNode curNode = nodeQueue.poll();
                
                if (curNode.left != null && curNode.left.left == null && curNode.left.right == null)
                    sumOfLeft += curNode.left.val;
                else if (curNode.left != null)
                    nodeQueue.offer(curNode.left);
                    
                if (curNode.right != null)
                    nodeQueue.offer(curNode.right);
                    
                numOfRemainingNodesInThisLevel --;
            }
        }
        return sumOfLeft;
    }
    
}
