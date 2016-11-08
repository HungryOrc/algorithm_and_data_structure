/* Reverse a singly linked list.

 * Definition for singly-linked list:
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
public class Solution {

    // Iteration。根本不用 ArrayList　之类的东西来暂存！直接 in place 搞就行了！！
    public ListNode reverseList(ListNode head) {
        
        // 这两种特殊输入，别忘了
        if (head == null)
            return null;
        if (head.next == null)
            return head;
        
        ListNode prevNode = null;
        while (head != null)
        {
            ListNode nextNode = head.next;
            head.next = prevNode;
            prevNode = head;
            head = nextNode;
        }
        return prevNode;
    }    


    // Recursion
    public ListNode reverseList(ListNode head) {
        
        // 这两种特殊输入，别忘了
        if (head == null)
            return null;
        else if (head.next == null)
            return head;
        
        return reverse(head, null);
    }
    
    public ListNode reverse (ListNode curHead, ListNode prevNode)
    {
        // 结束条件，别忘了
        if (curHead == null)
            return prevNode;
            
        ListNode nextToCurHead = curHead.next;
        curHead.next = prevNode;
        
        return reverse(nextToCurHead, curHead);
    }

}
