/* Sort a linked list in O(n log n) time using constant space complexity.
Solve it by merge sort & quick sort separately.

Example
Given 1->3->2->null, sort it to 1->2->3->null.

/* Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * } */

public class Solution {
    /* @param head: The head of linked list.
     * @return: You should return the head of the sorted linked list,
            using constant space complexity. */
    
    // 方法1: Quick Sort for LinkedList
    // Recursion
    public ListNode sortList(ListNode head) {  
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode mid = findMedian(head); // O(n)
        
        // 分出来三个list：左list，中list，右list
        ListNode leftDummyHead = new ListNode(-1), leftTail = leftDummyHead;
        ListNode rightDummyHead = new ListNode(-1), rightTail = rightDummyHead;
        ListNode midDummyHead = new ListNode(-1), midTail = midDummyHead;
        
        ListNode curNode = head;
        while (curNode != null) {
            if (curNode.val < mid.val) {
                leftTail.next = curNode;
                leftTail = curNode;
            } else if (curNode.val > mid.val) {
                rightTail.next = curNode;
                rightTail = curNode;
            } else {
                midTail.next = curNode;
                midTail = curNode;
            }
            curNode = curNode.next;
        }
        
        // 别忘了！！把三个list的尾巴都切断！！！
        leftTail.next = null;
        rightTail.next = null;
        midTail.next = null;
        
        // Recursion
        ListNode leftHead = sortList(leftDummyHead.next);
        ListNode rightHead = sortList(rightDummyHead.next);
        
        return concatenate(leftHead, midDummyHead.next, rightHead);
    }
    private ListNode findMedian(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next  != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    private ListNode concatenate(ListNode leftHead, ListNode midHead, ListNode rightHead) {
        ListNode dummyHead = new ListNode(-1);
        ListNode tail = dummyHead;
        
        tail.next = leftHead; // 从左段开始
        tail = getTail(tail);
        tail.next = midHead; // 连上中段
        tail = getTail(tail); 
        tail.next = rightHead; // 连上右段
        tail = getTail(tail);
        
        return dummyHead.next;
    }
    private ListNode getTail(ListNode head) {
        if (head == null) {
            return null;
        }
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }
    
    
    
    
}

