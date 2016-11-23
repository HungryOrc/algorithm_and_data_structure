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
    
    
}
