/* Reverse a singly linked list.

* Definition for singly-linked list:
* public class ListNode {
*     int val;
*     ListNode next;
*     ListNode(int x) { val = x; }
* } */
 
public class Solution {

    // 方法1：Iteration。根本不用 ArrayList 之类的东西来暂存！直接 in place 搞就行了！！
    public ListNode reverseList(ListNode head) {        
        if (head == null || head.next == null)
            return head;
        
        // 注意！！以下几步都是精华！！
        ListNode prevNode = null;
        while (head != null) {
            ListNode nextNode = head.next;
            head.next = prevNode;
            prevNode = head;
            head = nextNode;
        }
        return prevNode;
    }    


    // 方法2：Recursion without helper function
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        
        ListNode nextNode = head.next;
        ListNode newHead = reverseList(nextNode);   
     
        nextNode.next = head;
        head.next = null;
     
        return newHead;
    }
 
 
    // 方法3：Recursion with helper function。和上面的Iteration方法的思想是一致的
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        
        return reverse(head, null);
    }
 
    public ListNode reverse(ListNode curHead, ListNode prevNode) {
        if (curHead == null)
            return prevNode;
            
        ListNode next = curHead.next;
        curHead.next = prevNode;
        
        return reverse(next, curHead);
    }

}
