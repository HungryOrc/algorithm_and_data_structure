/* Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * } */

public class Solution
{
    // Iteration
    public ListNode removeElements(ListNode head, int val)
    {
        // 注意！可能一开头就是val，且可能一直都是val！
        ListNode beforeHead = new ListNode(0); 
        beforeHead.next = head;
        ListNode prev = beforeHead;
        ListNode curNode = head;
        
        while(curNode != null)
        {
            if (curNode.val != val)
            {
                prev = prev.next;
                curNode = curNode.next;
            }
            else // curNode.val == val
            {
                prev.next = curNode.next;
                curNode = curNode.next;
            }
        }
        // 很聪明的一招！
        return beforeHead.next;
    }
    
    
    // Recursion。Ref: https://discuss.leetcode.com/topic/12725/ac-java-solution
    // 非常巧妙。这个解法深刻体现了 Recursion 的本质
    public ListNode removeElements(ListNode head, int val)
    {
        if (head == null) return null;
        
        head.next = removeElements(head.next, int val);
        
        if (head.val == val)
            return head.next;
        else // head.val != val
            return head;
    }
    
}
