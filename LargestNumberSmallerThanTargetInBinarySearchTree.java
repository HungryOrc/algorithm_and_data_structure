/* In a binary search tree, find the node containing the largest number smaller than the given target number.
If there is no such number, return INT_MIN.
Assumptions:
The given root is not null.
There are no duplicate keys in the binary search tree.

Examples
    5
  /    \
2      11
     /    \
    6     14
largest number smaller than 1 is Integer.MIN_VALUE(Java) or INT_MIN(c++)
largest number smaller than 10 is 6
largest number smaller than 6 is 5    */

public class Solution {

  int smallerClosest;
  
  public int largestSmaller(TreeNode root, int target) {
    if (root == null) {
      return Integer.MIN_VALUE;
    }
    
    smallerClosest = Integer.MIN_VALUE;
    find(root, target);
    return smallerClosest;
  }
  
  private void find(TreeNode node, int target) {
    if (node == null) {
      return;
    }
    
    if (node.key >= target) { // 相等也不行！ 只要小于的！！
      find(node.left, target);
    } else { // node.key < target
      smallerClosest = (Math.abs(smallerClosest - target) > Math.abs(node.key - target)) 
        ? node.key : smallerClosest;
      find(node.right, target);
    }
  }
}
