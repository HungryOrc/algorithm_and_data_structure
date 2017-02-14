/* Given a linked list and two values v1 and v2. Swap the two nodes in the linked list with values v1 and v2. 
It's guaranteed there is no duplicate values in the linked list. 
If v1 or v2 does not exist in the given linked list, do nothing.
Notice: You should swap the two nodes with values v1 and v2. Do not directly swap the values of the two nodes.

Example
Given 1->2->3->4->null and v1 = 2, v2 = 4.
Return 1->4->3->2->null.

/* Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * } */

public class Solution {
    
    /* @param head a ListNode
     * @oaram v1 an integer
     * @param v2 an integer
     * @return a new head of singly-linked list */
    public ListNode swapNodes(ListNode head, int v1, int v2) {
        if (head == null) {
            return null;
        }
        
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode curNode = dummyHead;
        
        ListNode node1 = null, node2 = null;
        ListNode preNode1 = dummyHead, preNode2 = dummyHead;
        
        while(curNode.next != null) {
            if (curNode.next.val == v1) {
                node1 = curNode.next;
                preNode1 = curNode;
            }
            if (curNode.next.val == v2) {
                node2 = curNode.next;
                preNode2 = curNode;
            }
            curNode = curNode.next;
        }
        
        if (node1 == null || node2 == null) {
            return head;
        } else if (node1.next == node2) {
            ListNode postNode2 = node2.next;    
            preNode1.next = node2;
            node2.next = node1;
            node1.next = postNode2;
        } else if (node2.next == node1) {
            ListNode postNode1 = node1.next;
            preNode2.next = node1;
            node1.next = node2;
            node2.next = postNode1;
        } else {
            ListNode postNode1 = node1.next;
            ListNode postNode2 = node2.next;
            preNode1.next = node2;
            preNode2.next = node1;
            node1.next = postNode2;
            node2.next = postNode1;
        }
        return dummyHead.next;
    }
}
