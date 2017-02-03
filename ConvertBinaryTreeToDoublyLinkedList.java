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
        
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);
        ArrayList<DoublyListNode> allNodes_InOrder = new ArrayList<>();
        DoublyListNode rootDoublyNode = null;
        
        while (!nodeStack.isEmpty()) {
            TreeNode curNode = nodeStack.pop();
            
            // 当前node是leaf；
            // 或者当前node的左右children都被放入stack了，即当前node之前已被处理过了。
            // 那么当前node就可以被放入list了
            if (curNode.left == null && curNode.right == null) {
                DoublyListNode curDoublyNode = new DoublyListNode(curNode.val);
                
                if (allNodes_InOrder.size() == 0) { // 当前是第一个node
                    rootDoublyNode = curDoublyNode;
                } else { // allNodes_InOrder.size() > 0
                    DoublyListNode prevDoublyNode = allNodes_InOrder.get(allNodes_InOrder.size() - 1);
                    curDoublyNode.prev = prevDoublyNode;
                    prevDoublyNode.next = curDoublyNode;
                }
                
                allNodes_InOrder.add(curDoublyNode);
                continue;
            }
            
            if (curNode.right != null) {
                nodeStack.push(curNode.right);
            }
            nodeStack.push(curNode);
            if (curNode.left != null) {
                nodeStack.push(curNode.left);
            }
            
            curNode.left = null;
            curNode.right = null;
        }
        
        return rootDoublyNode;
    }
}
