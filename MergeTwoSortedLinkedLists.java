/* Merge two sorted linked lists and return it as a new list. 
The new list should be made by splicing together the nodes of the first two lists.

 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * } */

public class Solution {
    
    // 方法1：Recursion
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        
        ListNode head = null;
        if (l1.val <= l2.val) {
            head = l1;
            l1.next = mergeTwoLists(l1.next, l2);
        } else { // l1.val > l2.val
            head = l2;
            l2.next = mergeTwoLists(l2.next, l1);
        }
        return head;
    }
    
    // 方法2：Iteration
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode curNode = dummyHead;
        
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                curNode.next = l2;
                curNode = l2;
                l2 = l2.next;
            } else { // <=
                curNode.next = l1;
                curNode = l1;
                l1 = l1.next;
            }
        }
        
        if (l1 == null) {
            curNode.next = l2;
        } else if (l2 == null) {
            curNode.next = l1;
        }
        
        return dummyHead.next;
    }
}
