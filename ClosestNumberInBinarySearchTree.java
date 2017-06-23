/* In a binary search tree, find the node containing the closest number to the given target number.
Assumptions: 
The given root is not null.
There are no duplicate keys in the binary search tree.

Examples:
    5
  /    \
2      11
     /    \
    6     14
closest number to 4 is 5
closest number to 10 is 11
closest number to 6 is 6      */


/* 思路：
Maintain a slution node.
Start with the root node.
case 1: if root == null, do nothing
case 2: if root.value == target, return root
case 3: if root.value < target, update the solution node if necessary, and then walk rightwards
case 4: if root.value > target, update the solution node if necessary, and then walk leftwards     */

public class Solution {
  TreeNode result;
  
  public int closest(TreeNode root, int target) {
    if (root == null) {
      return Integer.MAX_VALUE;
    }
    
    result = root;
    findClosest(root, target);
    return result.key;
  }
  
  private void findClosest(TreeNode root, int target) {
    if (root == null) {
      return;
    }
    
    if (root.key == target) {
      result = root;
      return;
    } else if (root.key > target) {
      if (Math.abs(root.key - target) < Math.abs(result.key - target)) {
        result = root;
      }
      findClosest(root.left, target);
    } else { // root.key < target
      if (Math.abs(root.key - target) < Math.abs(result.key - target)) {
        result = root;
      }
      findClosest(root.right, target);
    }
  }
}
