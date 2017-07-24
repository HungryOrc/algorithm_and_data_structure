/* Given a singly linked list, determine if it is a palindrome.
Follow up: Could you do it in O(n) time and O(1) space?

* Definition for singly-linked list.
* public class ListNode {
*     int val;
*     ListNode next;
*     ListNode(int x) { val = x; }
* } */

public class Solution {
    
    // Ref: https://discuss.leetcode.com/topic/18675/easy-understand-java-solution-o-1-space-cost/20
    // 快慢两个指针，都从head出发，快的一次走两步，慢的一次走一步
    // 当fast走到末尾的null时，slow最终也到达“后一半”list的起始点
    // 然后将后半段list反转！最后看前半段和反转后的后半段是否逐个元素都相等！
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        
        ListNode fast = head;
        ListNode slow = head;
        
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        // slow还要再向后进一位，才是后一半list的起始点
        slow = slow.next;
            
        // 从slow开始，将整个后一半list颠倒过来
        // 最终slow是颠倒过来以后的后半list的起点，即最开始的整个list的最后一个node
        slow = reverse(slow);
        
        // 比较前半部分，和颠倒后的后半部分
        while (slow != null && head.val == slow.val) {
            head = head.next;
            slow = slow.next;
        }
        return (slow == null);
    }
    
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode next = null;
        
        while (head != null) {
            next = head.next;
            
            // 精华！下面这步，
            // 在第一次执行时，即把最初的head即最终的最后一个node的next设置为null了
            // 在后面的执行中，即把当前head的next指向为原来在它前面的那一个，即实现颠倒
            head.next = prev; 
            
            prev = head;
            head = next;
        }
        // 全部反转完以后，按上面的while条件，head将指向null，则head的next即翻转后的首node
        return prev;
    }   
}
