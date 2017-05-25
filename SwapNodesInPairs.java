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
    
    
    // 方法2：Iteration
    // 注意这里是一种以 “一对nodes” 为一个考量单位的 iteration，和以往的 iteration 不太一样。详见下面的注释
    public ListNode reverseInPairs(ListNode head) {
        
        if (head == null || head.next == null)
            return head;
            
        ListNode originalNext = head.next;
        ListNode prevNode = new ListNode(0);
        
        while (head.next != null && head.next.next != null)
        {
            ListNode temp = head.next;
            head.next = temp.next;
            temp.next = head;
            
            head = head.next;
            
            prevNode.next = temp;
            prevNode = temp.next;
        }
        // 一对一对的结构不再完整以后，即List全部结束了，或者还剩一个单的node
        if (head.next == null) // List全部结束了
        {
            // do nothing
        }
        else if (head.next != null && head.next.next == null) // 还剩一个单的node
        {
            prevNode.next = head.next;
            head.next.next = head;
            head.next = null;
        }
        return originalNext;
    }
    
}
