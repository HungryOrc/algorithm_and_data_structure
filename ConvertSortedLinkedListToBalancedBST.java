/* Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

Example:
               2
1->2->3  =>   / \
             1   3
             
/* Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }

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
    
    /* @param head: The first node of linked list.
     * @return: a tree node */
    public TreeNode sortedListToBST(ListNode head) {  
        TreeNode root = findMid_Separate_ConnectChildren(head);
        return root;
    }
    private TreeNode findMid_Separate_ConnectChildren(ListNode curListNode) {
        if (curListNode == null) { // list里一个node也没有
            return null;
        } else if (curListNode.next == null) { // list里只有一个node
            return new TreeNode(curListNode.val);
        } else if (curListNode.next.next == null) { // list里只有两个node
            TreeNode root = new TreeNode(curListNode.val);
            TreeNode rightChild = new TreeNode(curListNode.next.val); // 因为next更大
            root.right = rightChild;
            return root;
        } // 余下的情况，就是list里有至少三个node了
        
        // 1，找到list的中点
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = curListNode;
        ListNode slow = curListNode, fast = curListNode;
        ListNode prevSlow = dummyHead; // 这个node是为了之后的左段的截尾工作
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            
            prevSlow = prevSlow.next;
        }
        ListNode mid = slow;
        
        // 2，把list分割成三部分：中间一个点，左一段，右一段
        ListNode leftListHead = curListNode;
        ListNode rightListHead = mid.next;
        // 千万别忘了！！！这三段都要分别截尾！！！
        // 不是光设了头就完了！还要截尾！！！
        // 但最后一段本来就有null做结尾。所以我们要手动截尾的是左段和中段这两段
        prevSlow.next = null;
        mid.next = null;
        
        // 3，设立当前子树的root，然后把此root的左右子树连上来
        TreeNode curSubtreeRoot = new TreeNode(mid.val);
        curSubtreeRoot.left = findMid_Separate_ConnectChildren(leftListHead);
        curSubtreeRoot.right = findMid_Separate_ConnectChildren(rightListHead);
        
        return curSubtreeRoot;
    }
    
}
