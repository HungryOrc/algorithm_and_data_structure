/* Given a singly linked list, determine if it is a palindrome.
Follow up: Could you do it in O(n) time and O(1) space?

* Definition for singly-linked list.
* public class ListNode {
*     int val;
*     ListNode next;
*     ListNode(int x) { val = x; }
* } */

// 这一题也蕴含了“O(n)时间，O(1)空间，反转一个单向LinkedList”的问题
public class Solution {
    
    // Ref: https://discuss.leetcode.com/topic/18675/easy-understand-java-solution-o-1-space-cost/20
    // 快慢两个指针，都从head出发，快的一次走两步，慢的一次走一步
    // 注意：当fast走到末尾的null时，slow最终也到达“后一半”list的起始点
    public boolean isPalindrome(ListNode head) {        
        ListNode fast = head;
        ListNode slow = head;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 如果整个list有奇数个，则slow还要再向后进一位，才是后一半list的起始点
        if (fast != null)
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
