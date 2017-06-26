/* Convert a binary tree to doubly linked list with in-order traversal.

Example: Given a binary search tree:
    4
   / \
  2   5
 / \
1   3
return 1<->2<->3<->4<->5

* Definition of TreeNode:
* public class TreeNode {
*     public int val;
*     public TreeNode left, right;
*     public TreeNode(int val) {
*         this.val = val;
*         this.left = this.right = null;
*     }
* }

* Definition for Doubly-ListNode:
* public class DoublyListNode {
*     int val;
*     DoublyListNode next, prev;
*     DoublyListNode(int val) {
*         this.val = val;
*         this.next = this.prev = null;
*     }
* } */


// 方法1：我改进的九章和Laioffer的一个方法，构思很巧妙，不太容易想到，用的 Recursion
public class Solution {
    /* @param root: The root of tree
     * @return: the head of doubly list node */
    
    static DoublyListNode headOfTheWholeList = null;
    static DoublyListNode prevListNode = null;
    
    public DoublyListNode bstToDoublyList(TreeNode root) {  
        if (root == null) {
            return null;
        }
        
        DoublyListNode curListNode = new DoublyListNode(root.val);
        
        // convert the left subtree of the current TreeNode
        bstToDoublyList(root.left);
        
        if (prevListNode == null) {
            headOfTheWholeList = curListNode;
        }
        else {
            curListNode.prev = prevListNode;
            prevListNode.next = curListNode;
        }
        
        prevListNode = curListNode;
        
        // convert the right subtree of the current TreeNode
        bstToDoublyList(root.right);
        
        return headOfTheWholeList;
    }
}


// 方法2：我自己的方法，上场可以考虑用这个方法，熟门熟路
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
