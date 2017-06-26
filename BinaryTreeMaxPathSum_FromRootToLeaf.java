public class Solution {
  int maxSum;
  
  public int maxPathSum(TreeNode root) {
    maxSum = Integer.MIN_VALUE;
    
    findMaxPathSum(root, 0);
    return maxSum;
  }
  
  private void findMaxPathSum(TreeNode node, int pathSum) {
    if (node == null) {
      return;
    }
    
    int curPathSum = pathSum + node.key;
    if (node.left == null && node.right == null) {
      maxSum = Math.max(maxSum, curPathSum);
      return;
    }
    
    findMaxPathSum(node.left, curPathSum);
    findMaxPathSum(node.right, curPathSum);
  }
}
