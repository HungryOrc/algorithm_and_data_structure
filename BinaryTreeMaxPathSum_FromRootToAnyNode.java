/* Given a binary tree, find the maximum path sum from root.
The path may end at any node in the tree and contain at least one node in it.

Example
Given the below binary tree:

  1
 / \
2   3
return 4. (1->3)

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

    // 方法1：Recursion - Divide and Conquer
    public int maxPathSum2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int maxPathSumOfLeftSubtree = maxPathSum2(root.left);
        int maxPathSumOfRightSubtree = maxPathSum2(root.right);
        
        int maxPathSumOfSubtrees = Math.max(maxPathSumOfLeftSubtree, maxPathSumOfRightSubtree);
        if (maxPathSumOfSubtrees > 0) {
            return root.val + maxPathSumOfSubtrees;
        } else { // 如果 <= 0，那么还不如不加下面的子树了，就只有root.val自己反而更大
            return root.val;
        }
    }
    
    
    
    
    
    
    
    
    
}
