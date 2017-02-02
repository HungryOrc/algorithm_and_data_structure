/* Given a binary tree and a sum, 
注意！这里只是一般的Binary Tree！左右子节点和父节点之间没有任何大小的关系！
determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * } */
 
public class Solution {
    
    // 方法1: Recursion
    public boolean hasPathSum(TreeNode root, int sum) {
        
        if (root == null)
            return false;
        
        // 到当前节点为止，正好加和为sum。而且当前节点正好是一个leaf
        if (root.val == sum && root.left == null && root.right == null)
            return true;
        else
        {
            sum -= root.val;
            return (hasPathSum(root.left, sum) || hasPathSum(root.right, sum));
        }
    }
    
    
    // 方法2: Iteration, by Stack, DFS
    // 如果用Queue做BFS，也是大同小异
    public boolean hasPathSum(TreeNode root, int sum) {
        
        if (root == null)
            return false;
        
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);
        Stack<Integer> sumStack = new Stack<>();
        sumStack.push(0);
        
        while(!nodeStack.isEmpty())
        {
            TreeNode curNode = nodeStack.pop();
            int curSum = sumStack.pop();
            curSum += curNode.val;
            
            if (curSum == sum && curNode.left == null && curNode.right == null)
                return true;
            
            if (curNode.left != null)
            {
                nodeStack.push(curNode.left);
                sumStack.push(curSum);
            }
            if (curNode.right != null)
            {
                nodeStack.push(curNode.right);
                sumStack.push(curSum);
            }
        }
        return false;
    }
    
    
}
