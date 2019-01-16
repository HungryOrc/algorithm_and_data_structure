/* Check if a given binary tree is completed. 
A complete binary tree is one in which every level of the binary tree is completely filled except possibly the last level. 
Furthermore, all nodes are as far left as possible.
What if the binary tree is null? Return true in this case.

Examples:

        5
      /    \
    3        8
  /   \
1      4
is a complete binary tree

        5
      /    \
    3        8
  /   \        \
1      4        11
is not a complete binary tree

* public class TreeNode {
*   public int key;
*   public TreeNode left;
*   public TreeNode right;
*   public TreeNode(int key) {
*     this.key = key;
*   }
* } */

public class Solution {

  public boolean isCompleted(TreeNode root) {
    if (root == null) {
      return true;
    }
 if any node has right, but left child is null, then return false
 
 if any node has less than 2 children, then if any node after him in the queue has any child, then return false
         for example in the above graph, the first tree, after 8, there are 1 and 4 in the queue, it is fine, as long as 1 and 4 dont have any children
}
