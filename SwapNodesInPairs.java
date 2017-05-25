/* Given a linked list, every two adjacent nodes and return its head.
For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.
Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed. 

 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
public class Solution {
    
    // 方法1：Recursion
    public ListNode reverseInPairs(ListNode head) {
    
     if (head == null || head.next == null) {
       return head;
     }

     ListNode originalNext = head.next;

     ListNode newHeadAfterNext = reverseInPairs(head.next.next);

     originalNext.next = head;
     head.next = newHeadAfterNext;

     return originalNext;
   }
    
    
    // 方法2：Iteration。Laioffer。非常经典的做法 ！！！ 要记下来 ！！！
    public ListNode reverseInPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        
        while (cur.next != null && cur.next.next != null) {
            ListNode nodeAfterNext = cur.next.next;
         
            cur.next.next = nodeAfterNext.next;
            nodeAfterNext.next = cur.next;
            cur.next = nodeAfterNext;
         
            cur = cur.next.next;
        }
        return dummy.next;
    }   
}
