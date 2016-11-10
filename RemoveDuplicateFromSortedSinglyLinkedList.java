/* Given a sorted linked list, delete all duplicates such that each element appear only once.
For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.

 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    
    // Iteration
    public ListNode deleteDuplicates(ListNode head) {

        if (head == null)
            return null;
        ListNode originalHead = head;
        
        while (head.next != null)
        {
            if (head.next.val == head.val)
                head.next = head.next.next;
            
            // ！注意！这里要用一个 else！因为上面的情况，val相等时，head的next已经下移一位了！
            // 那么再次到while那里去做操作就正好，不应该再把head下移一位！！
            // 如果是下面的情况，即val不相等，才需要把head下移一位！！
            else
                head = head.next;
        }
        return originalHead;
    }
}
