/* Convert a binary search tree to doubly linked list with in-order traversal.

Example: Given a binary search tree:
    4
   / \
  2   5
 / \
1   3
return 1<->2<->3<->4<->5

/* Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 
 * Definition for Doubly-ListNode.
 * public class DoublyListNode {
 *     int val;
 *     DoublyListNode next, prev;
 *     DoublyListNode(int val) {
 *         this.val = val;
 *         this.next = this.prev = null;
 *     }
 * } */
public class Solution {
    /* @param root: The root of tree
     * @return: the head of doubly list node */

    // Iteration, by Stack
    public DoublyListNode bstToDoublyList(TreeNode root) {  
        if (root == null) {
            return null;
        }
        
        Stack<TreeNode> treeNodeStack = new Stack<>();
        treeNodeStack.push(root);
        ArrayList<DoublyListNode> allNodes_InOrder = new ArrayList<>();
        DoublyListNode headDoublyNode = null;
        
        while (!treeNodeStack.isEmpty()) {
            TreeNode curTreeNode = treeNodeStack.pop();
            
            // 当前node是leaf；
            // 或者当前node的左右children都被放入stack了，即当前node之前已被处理过了。
            // 那么当前node就可以被放入list了
            if (curTreeNode.left == null && curTreeNode.right == null) {
                DoublyListNode curDoublyNode = new DoublyListNode(curTreeNode.val);
                
                if (allNodes_InOrder.size() == 0) { // 当前是第一个node
                    headDoublyNode = curDoublyNode;
                } else {
                    // 取list里的前一个list node
                    DoublyListNode prevDoublyNode = allNodes_InOrder.get(allNodes_InOrder.size() - 1);
                    curDoublyNode.prev = prevDoublyNode;
                    prevDoublyNode.next = curDoublyNode;
                }
                
                allNodes_InOrder.add(curDoublyNode);
                continue;
            }
            
            if (curTreeNode.right != null) {
                treeNodeStack.push(curTreeNode.right);
            }
            treeNodeStack.push(curTreeNode);
            if (curTreeNode.left != null) {
                treeNodeStack.push(curTreeNode.left);
            }
            
            curTreeNode.left = null;
            curTreeNode.right = null;
        }
        
        return headDoublyNode;
    }
}
