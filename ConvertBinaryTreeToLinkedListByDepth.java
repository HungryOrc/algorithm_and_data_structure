/* Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth 
(e.g., if you have a tree with depth D, you'll have D linked lists).

Example:
Given binary tree:
    1
   / \
  2   3
 /
4
return:
[
  1->null,
  2->3->null,
  4->null
]

/* Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }

 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * } */

public class Solution {
    /* @param root the root of binary tree
     * @return a lists of linked list */
    
    public List<ListNode> binaryTreeToLists(TreeNode root) {
        List<ListNode> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        treeNodeQueue.offer(root);
        
        while (!treeNodeQueue.isEmpty()) {
            int curLevelSize = treeNodeQueue.size();
            ListNode prevListNode = null;
            ListNode curListNode = null;
            
            for (int i = 0; i < curLevelSize; i++) {
                TreeNode curTreeNode = treeNodeQueue.poll();
                curListNode = new ListNode(curTreeNode.val);

                if (i == 0) { // 如果当前的 list node 是本level的第一个node
                    result.add(curListNode); // result 里只含有每一层的第一个node
                } else {
                    prevListNode.next = curListNode;
                }

                prevListNode = curListNode;
                
                if (curTreeNode.left != null) {
                    treeNodeQueue.offer(curTreeNode.left);
                }
                if (curTreeNode.right != null) {
                    treeNodeQueue.offer(curTreeNode.right);
                }
            }
            curListNode.next = null; // 每一层的末尾，按照题意，要加一个null节点作为结尾
        }
        return result;
    }
}
