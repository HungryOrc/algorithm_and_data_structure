/* Flatten a binary tree to a fake "linked list" in pre-order traversal.
Here we use the right pointer in TreeNode as the next pointer in ListNode.
Notice:
Don't forget to mark the left child of each node to null. Or you will get Time Limit Exceeded or Memory Limit Exceeded.

Challenge : Do it in-place without any extra memory.

Example:
              1
               \
     1          2
    / \          \
   2   5    =>    3
  / \   \          \
 3   4   6          4
                     \
                      5
                       \
                        6

* Definition of TreeNode:
* public class TreeNode {
*     public int val;
*     public TreeNode left, right;
*     public TreeNode(int val) {
*         this.val = val;
*         this.left = this.right = null;
*     }
* } */

public class Solution {
    /* @param root: a TreeNode, the root of the binary tree
     * @return: nothing */
    
    // 方法1: 普通做法，用 stack。只是要注意一下，要设置一个 prevNode 来处理整个流程
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        
        Stack<TreeNode> nodeStack = new Stack<>();
        TreeNode prevNode = new TreeNode(-1);
        
        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            TreeNode curNode = nodeStack.pop();
            
            if (curNode.right != null) {
                nodeStack.push(curNode.right);
            }
            if (curNode.left != null) {
                nodeStack.push(curNode.left);
            }
            
            // 注意以下三个关于prevNode的操作！
            prevNode.right = curNode;
            prevNode.left = null;
            
            prevNode = curNode;
        }
    }
  
    
    // 方法2: In-Place
  
  
  
  
  
}
