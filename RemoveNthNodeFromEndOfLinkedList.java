/* Given a linked list, remove the nth node from the end of list and return its head.

For example, Given linked list: 1->2->3->4->5, and n = 2.
After removing the second node from the end, the linked list becomes 1->2->3->5.

Note:
Given n will always be valid.
Try to do this in one pass.

 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * } */

// 题意保证 n <= list的长度！！不然要检验的话挺费劲的，我试过，得分别考虑n=1,2,以及>2的情况
public class Solution
{
    // 很巧妙的方法。用fast和slow两个index！！
    // Ref: https://discuss.leetcode.com/topic/7031/simple-java-solution-in-one-pass
    /* A one pass solution can be done using pointers. Move one pointer fast --> n+1 places forward, 
     to maintain a gap of n between the two pointers and then move both at the same speed. 
     Finally, when the fast pointer reaches the end, the slow pointer will be n+1 places behind, 
     just the right spot for it to be able to skip the next node.
    */
    public ListNode removeNthFromEnd(ListNode head, int n)
    {
        if (head == null)
            return null;
            
        // head有可能是那个要被抹掉的node！！所以切不可把slow赋值为head！！
        ListNode slow = new ListNode(0);
        slow.next = head;
        
        ListNode fast = head;
        
        // fast往前挪 n 步。这样之后slow和fast同步挪的时候，slow的后一个就一直是要去掉的那个node
        for (int i = 1; i <= n; i++)
            fast = fast.next;
        
        // fast和slow同步往后挪
        while(fast != null)
        {
            fast = fast.next;
            slow = slow.next;
        }
        
        // 末了
        // 如果slow的next还是head，即slow压根没动过，即n正好等于list的长度
        // 则相当于要把head去掉
        if (slow.next == head)
            return head.next;
        // 否则，即n小于list的长度。那么就要去掉slow的next node。然后返回最初的原head
        // 这种情况下被remove的一定不是原head
        else
        {
            // 到现在，slow的下一个node就是要被去掉的node了
            slow.next = slow.next.next;
            return head;
        }
    }
}

