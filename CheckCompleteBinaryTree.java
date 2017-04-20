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
    
    Queue<TreeNode> nodeQueue = new LinkedList<>();
    nodeQueue.offer(root);
    
    boolean childrenEnded = false;
    
    while (!nodeQueue.isEmpty()) {
      TreeNode curNode = nodeQueue.poll();
      
      if (childrenEnded && 
          (curNode.left != null || curNode.right != null)) {
        return false;
      }
      
      if (curNode.left == null || curNode.right == null) {
        childrenEnded = true;
      }
      
      if (curNode.left == null && curNode.right != null) {
        return false;
      }
      
      if (curNode.left != null) {
        nodeQueue.offer(curNode.left);
      }
      if (curNode.right != null) {
        nodeQueue.offer(curNode.right);
      }
    }
    
    return true;
  }
  
}
