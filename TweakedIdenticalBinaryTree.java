/* Determine whether two given binary trees are identical assuming any number of ‘tweak’s are allowed. 
A tweak is defined as a swap of the children of one node in the tree.

Examples:
        5
      /    \
    3        8
  /   \
1      4
and:
        5
      /    \
    8        3
           /   \
          1     4
these 2 binary trees are tweaked identical. */

/* public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * } */
 
// laioffer的方法
public class Solution {
  
  public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
    if (one == null && two == null) {
      return true;
    }
    if (one == null || two == null) {
      return false;
    }
    
    if (one.key != two.key) {
      return false;
    }
    
    return ((isTweakedIdentical(one.left, two.right) && isTweakedIdentical(one.right, two.left)) ||
      (isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right)));
  }
  
}
