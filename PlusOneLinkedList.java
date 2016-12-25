/* Given a non-negative number represented as a singly linked list of digits, plus one to the number.
The digits are stored such that the most significant digit is at the head of the list.

Example:
Input: 1->2->3
Output: 1->2->4

Definition for singly-linked list.
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
} */

public class Solution 
{
    // 很巧妙的方法！！Recursion
    // Ref: https://leetcode.com/problems/plus-one-linked-list/
    public ListNode plusOne(ListNode head) 
    {
        if(getCarry(head) == 0)
            return head;
        else{
            // 现有head再进一位的话，新的最终的head必然只能是值为1
            ListNode newHead = new ListNode(1);
            newHead.next = head;
            return newHead;
        }
    }
    // 这个函数的返回值，是node head向它的前一位进贡的carry（进位）值
    public int getCarry(ListNode head)
    {
        // 当前node等于null的唯一情况，是当前node即List的最后一个node的next node
        // 这样的话，这个null node向last node of the List的进位必然只能是1
        // 因为按题意最后一位要+1
        if(head == null) 
            return 1;
        
        // 本node上要接受来自next node进贡上来的carry
        int carry = getCarry(head.next);
        
        // 一旦next node进攻上来的carry是0，则本node及本node之前的所有nodes
        // 向再往前的node进贡的carry都一定只能是0
        if(carry == 0) 
            return 0;
        
        // 继续上面的。如果本node要接受的carry不是0，那么只能是1
        // 所以本node上的val就要+1，且本node向再前一个node的进位即carry为 (val+1)/10
        int newVal = head.val + 1;
        head.val = val%10;
        return val/10;
    }
}
