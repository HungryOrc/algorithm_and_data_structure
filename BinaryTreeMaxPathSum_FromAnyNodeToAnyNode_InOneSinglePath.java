/* 这一题的关键在于，起始点和终止点，必须在一条单路径上。所谓的单路径，就是从整棵树的root到left的某一条路线上，
这样的路线可以左右拐弯，但不可以上下折返。
另外，起始点和终止点可以是同一个点。

Given a binary tree in which each node contains an integer number. 
Find the maximum possible subpath sum(both the starting and ending node of the subpath 
should be on the same path from root to one of the leaf nodes, and the subpath is allowed to contain only one node).
Assumptions: The root of given binary tree is not null

Examples
   -5
  /    \
2      11
     /    \
    6     14
           /
        -3
The maximum path sum is 11 + 14 = 25 */


// 我自己的方法
public class Solution {
  int maxPathSum;
  
  public int maxPathSum(TreeNode root) {
    if (root == null) {
      return Integer.MIN_VALUE;
    }
    
    maxPathSum = Integer.MIN_VALUE;
    
    findMaxPathSum(root, root.key, 0);
    return maxPathSum;
  }
  
  private void findMaxPathSum(TreeNode node, int curPrefixSum, int minPrefixSum) {

    maxPathSum = Math.max(maxPathSum, curPrefixSum - minPrefixSum);
    
    minPrefixSum = Math.min(curPrefixSum, minPrefixSum);
    
    if (node.left != null) {
      findMaxPathSum(node.left, curPrefixSum + node.left.key, minPrefixSum);
    }
    if (node.right != null) {
      findMaxPathSum(node.right, curPrefixSum + node.right.key, minPrefixSum);
    }
  }
}
