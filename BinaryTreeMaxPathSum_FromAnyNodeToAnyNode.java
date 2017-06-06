/* Given a binary tree in which each node contains an integer number. 
Find the maximum possible sum from one leaf node to another leaf node. 
If there is no such path available, return Integer.MIN_VALUE(Java) / INT_MIN (C++).

Examples:
  -15
  /    \
2      11
     /    \
    6     14
The maximum leaft-to-leat path sum is 6 + 11 + 14 = 31.

注意！最后的答案，可能是一种很复杂的左拐右拐来回折的路线！！！ 左支路可能反复折很多次！右支路也可能反复折很多次！   */

// Time: O(n), n is the number of nodes in the tree, because we need to walk through every node in the tree
// Space: O(height of tree)，这是call stack的层数
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
    if (node.left == null && node.right == null) {
      return node.key;
    }
    else if (node.left == null) {
      return node.key + dfs(node.right);
    }
    else if (node.right == null) {
      return node.key + dfs(node.left);
    }
    
    int maxSinglePathSum_Left = dfs(node.left);
    int maxSinglePathSum_Right = dfs(node.right);
    
    maxPathSum = Math.max(maxPathSum, 
      node.key + maxSinglePathSum_Left + maxSinglePathSum_Right);
    
    return node.key + Math.max(maxSinglePathSum_Left, maxSinglePathSum_Right);
  } 
}
