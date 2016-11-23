/* Given a binary tree, determine if it is height-balanced.
For this problem, a height-balanced binary tree is defined as a binary tree in which 
the depth of the two subtrees of every node never differ by more than 1.

 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * } */
 
// Ref: https://discuss.leetcode.com/topic/7798/the-bottom-up-o-n-solution-would-be-better
public class Solution {
    
    // Top Down Approach
    /* checks whether the tree is balanced strictly according to the definition of balanced binary tree: 
     the difference between the heights of the two sub trees are not bigger than 1, 
     and both the left sub tree and right sub tree are also balanced.
     For the current node root, calling depth() for its left and right children actually has to access all of its children, 
     thus the complexity is O(N). We do this for each node in the tree, 
     so the overall complexity of isBalanced will be O(N^2).
    */
    public boolean isBalanced(TreeNode root) {
        
        if (root == null)
            return true;
        
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        
        return (Math.abs(leftDepth - rightDepth) <= 1 && isBalanced(root.left) && isBalanced(root.right));
    }
    private int depth (TreeNode root)
    {
        if (root == null)
            return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }   
    
    
    // Bottom Up Approach
        /* This method is based on DFS Recursion. 
     Instead of calling depth() explicitly for EACH child node, we return the height of the current node in DFS recursion. 
     When the sub tree of the current node (inclusive) is balanced, the function dfsHeight() returns a non-negative value as the height.
     Otherwise -1 is returned. 
     According to the leftHeight and rightHeight of the two children, 
     the parent node could check if the sub tree is balanced, and decides its return value.
    
     In this bottom up approach, EACH node in the tree only need to be accessed ONCE ! ! ! ! !
     Since any -1 in any level of the tree will result in ALL the ABOVE levels in that path to be -1 ! ! ! ! !
     Thus the time complexity is O(N), much better than the Top Down Solution.
    */
    public boolean isBalanced(TreeNode root)
    {
        return dfsHeight(root) != -1;
    }
    private int dfsHeight(TreeNode root)
    {
        if (root == null)
            return 0;
        
        int leftHeight = dfsHeight(root.left);
        if (leftHeight == -1)
            return -1;
        
        int rightHeight = dfsHeight(root.right);
        if (rightHeight == -1)
            return -1;
        
        if (Math.abs(leftHeight - rightHeight) > 1)
            return -1;
        
        return Math.max(leftHeight, rightHeight) + 1;
    }    
    
}
