/* Reverse a singly linked list.

 * Definition for singly-linked list:
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
public class Solution {

    // Iteration。根本不用 ArrayList 之类的东西来暂存！直接 in place 搞就行了！！
    public ListNode reverseList(ListNode head) {
        
        if (head == null)
            return null;
        if (head.next == null)
            return head;
        
        // 注意！！以下几步都是精华！！
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

        if (head == null)
            return null;
        else if (head.next == null)
            return head;
        
        return reverse(head, null);
    }
    public ListNode reverse (ListNode curHead, ListNode prevNode)
    {
        if (curHead == null)
            return prevNode;
            
        ListNode next = curHead.next;
        curHead.next = prevNode;
        
        return reverse(next, curHead);
    }

}
