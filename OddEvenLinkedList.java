/* Given a singly linked list, group all odd nodes together followed by the even nodes. 
Please note here we are talking about the node number and not the value in the nodes.
You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example:
Given 1->2->3->4->5->NULL,
return 1->3->5->2->4->NULL.

Note:
The relative order inside both the even and odd groups should remain as it was in the input. 
The first node is considered odd, the second node even and so on ... */

 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * } */

public class Solution 
{
    // 我的淳朴方法
    public ListNode oddEvenList(ListNode head) 
    {
        if (head == null)
            return null;
            
        ListNode firstOdd = head;
        ListNode prevOdd = new ListNode(-1);
        ListNode curOdd = head;
        
        ListNode firstEven = head.next;
        ListNode curEven = head.next;

        while(curOdd != null || curEven != null)
        {
            if (curOdd != null)
            {
                if (curOdd.next != null)
                    curOdd.next = curOdd.next.next;
                    
                prevOdd = curOdd;
                
                // 如果curOdd.next==null，则curOdd就变为null
                // /如果是上面的那种情况，curOdd.next!=null，则curOdd就成为下一个odd node
                curOdd = curOdd.next;
            }
            if (curEven != null)
            {
                if (curEven.next != null)
                    curEven.next = curEven.next.next;
                    
                curEven = curEven.next;
            }
        }
        prevOdd.next = firstEven; // 此时curOdd已经是null了
        return firstOdd;
    }
    
    
    // Ref: https://discuss.leetcode.com/topic/34292/simple-o-n-time-o-1-space-java-solution
    // 别人的改进了的方法
    public ListNode oddEvenList(ListNode head) {
        if (head != null) {

            ListNode odd = head, even = head.next, evenHead = even; 

            while (even != null && even.next != null) {
                odd.next = odd.next.next; 
                even.next = even.next.next; 
                odd = odd.next;
                even = even.next;
            }
            odd.next = evenHead; 
        }
        return head;
    }
    
}
