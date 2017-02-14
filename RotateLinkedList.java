/* Given a list, rotate the list to the right by k places, where k is non-negative.

Example: Given 1->2->3->4->5 and k = 2, return 4->5->1->2->3.

/* Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * } */

public class Solution {
    
    /* @param head: the List
     * @param k: rotate to the right k places
     * @return: the list after rotation */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        
        int len = 0;
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode curNode = dummyHead;
        while (curNode.next != null) {
            curNode = curNode.next;
            len ++;
        }
        ListNode tail = curNode;
        
        // 有可能多转过去好几圈！
        k = k % len;
        if (k == 0) {
            return head;
        }
        
        // 找翻转后的新tail和新head
        curNode = dummyHead;
        for (int i = 1; i <= len - k; i++) {
            curNode = curNode.next;
        }
        ListNode newTail = curNode;
        ListNode newHead = curNode.next;
        
        // 这一步千万别忘了！！！
        newTail.next = null;
        tail.next = head;
        
        return newHead;
    }
}
