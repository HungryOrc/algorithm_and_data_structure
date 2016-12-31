/* You are given two linked lists representing two non-negative numbers. 
The most significant digit comes first and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
Example:
Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7

 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * } */
 
public class Solution 
{
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) 
    {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        
        while (l1 != null)
        {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null)
        {
            s2.push(l2.val);
            l2 = l2.next;
        }
        
        int carry = 0;
        ListNode curNode = new ListNode(0);
        while (!s1.isEmpty() || !s2.isEmpty())
        {
            int curDigitSum = 0;
            if (!s1.isEmpty() && !s2.isEmpty())
                curDigitSum = s1.pop() + s2.pop() + carry;
            else if (s1.isEmpty())
                curDigitSum = s2.pop() + carry;
            else // s2.isEmpty()
                curDigitSum = s1.pop() + carry;
            
            curNode.val = curDigitSum % 10;
            carry = curDigitSum / 10;
            
            // 这一步特别重要！head一定要在这里就赋值为carry！不要等下一个循环了！
            // 因为也许没有下一个循环了！比如5+5=10，就没有下一个循环了！这个情况下
            // 必须就预先赋值head为1，否则一切都结束了
            ListNode head = new ListNode(carry);
            head.next = curNode;
            curNode = head;
        }
        
        if (curNode.val == 1)
            return curNode;
        else // curNode.val == 0
            return curNode.next;
    }
}
