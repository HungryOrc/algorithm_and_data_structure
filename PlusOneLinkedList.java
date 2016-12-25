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
    // 一个很朴实但也很透彻的方法。我没想到。如果认真地把问题分解透彻了，是可以想出这个解法的
    // Ref: https://leetcode.com/problems/plus-one-linked-list/
    // Iterative Two-Pointers with dummy node Java O(n) time, O(1) space
    // i stands for the most significant digit that is going to be incremented if there exists a carry
    // dummy node can handle cases such as "9->9>-9" automatically
    public ListNode plusOne(ListNode head) 
    {
        ListNode dummy = new ListNode(0);
        // dummy，i，j 三个node一开始都是在head前面的
        dummy.next = head;
        ListNode i = dummy;
        ListNode j = dummy;
        
        // j最终将指向最后一个node，即最低级的一个数位
        // i最终将指向最低级的不为9的数位。如果最后一位不为9，则i就会是最后一位
        // 如果倒数n位都是9，则i将会是倒数第n+1位
        while (j.next != null) {
            j = j.next; 
            if (j.val != 9) {
                i = j;
            }
        }
        
        // 如果最后一位即j不为9，则把j+1，然后就完事了
        if (j.val != 9) {
            j.val++;
        // 如果最后一位j是9，则i一定得+1了，然后i到j之间的所有数（也必然都是9）都要变成0
        } else {
            i.val++;
            i = i.next;
            // 把i和j之间的所有位都变为0
            while (i != null) {
                i.val = 0;
                i = i.next;
            }
        }
        
        // dummy是head之前的那一位
        if (dummy.val == 0) {
            return dummy.next;
        }
        return dummy;
    }
    
    
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
        head.val = newVal % 10;
        return newVal / 10;
    }

}
