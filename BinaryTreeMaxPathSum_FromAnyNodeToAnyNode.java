/* Given a binary tree in which each node contains an integer number. 
Find the maximum possible sum from any node to any node (the start node and the end node can be the same). 
Assumptions: The root of the given binary tree is not null

Examples:
   -1
  /    \
2      11
     /    \
    6    -14
one example of paths could be -14 -> 11 -> -1 -> 2.
another example could be the node 11 itself.
The maximum path sum in the above binary tree is 6 + 11 + (-1) + 2 = 18    */

public class Solution {
  int maxPathSum;
  
  public int maxPathSum(TreeNode root) {
    if (root == null) {
      return Integer.MIN_VALUE;
    }
    
    maxPathSum = Integer.MIN_VALUE;
    dfs(root);
    return maxPathSum;
  }
  
  private int dfs(TreeNode node) {
    if (node == null) {
      return 0;
    }
    
    int maxSinglePathSum_Left = Math.max(0, dfs(node.left));
    int maxSinglePathSum_Right = Math.max(0, dfs(node.right));
    
    maxPathSum = Math.max(maxPathSum,
      node.key + maxSinglePathSum_Left + maxSinglePathSum_Right);
    
    return node.key + Math.max(maxSinglePathSum_Left, maxSinglePathSum_Right);
  } 
}
