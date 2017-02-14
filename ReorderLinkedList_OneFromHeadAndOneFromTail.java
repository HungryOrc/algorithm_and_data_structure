/* Given a singly linked list L: L0 → L1 → … → Ln-1 → Ln
reorder it to: L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
Challenge: Can you do this in-place without altering the nodes' values?

Example
Given 1->2->3->4->null, reorder it to 1->4->2->3->null.

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
     * @return: void */
    // 我自己的方法
    public void reorderList(ListNode head) {  
        if (head == null || head.next == null || head.next.next == null) {
            return; // do nothing
        }
        
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        
        ListNode fast = dummyHead; // 2 steps each time
        ListNode slow = dummyHead; // 1 step each time
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        ListNode headOfSecondHalf = slow.next;
        ListNode curNode = headOfSecondHalf;
        
        // 别忘了这一步！！！
        // 要把前半部分的最后一个node的next设为null！！！即给前半部分断尾！！！
        slow.next = null;
        
        ListNode prevNode = null;
        // reverse the 2nd half
        while (curNode != null) {
            ListNode nextNode = curNode.next;
            curNode.next = prevNode;
            prevNode = curNode;
            curNode = nextNode;
        }
        ListNode headOfSecondHalfAfterReverse = prevNode;
        
        ListNode firstHalfPointer = head;
        ListNode secondHalfPointer = headOfSecondHalfAfterReverse;
        // 如果原list有偶数个node，则最后一个node属于原后半部分
        // 如果原list有奇数个node，则最后一个node属于原前半部分
        // 所以，后半部分的node被用光，会早于或同时于前半部分的node被用光
        while (firstHalfPointer.next != null) {
            ListNode firstHalfNext = firstHalfPointer.next;
            ListNode secondHalfNext = secondHalfPointer.next;
            firstHalfPointer.next = secondHalfPointer;
            secondHalfPointer.next = firstHalfNext;
            
            firstHalfPointer = firstHalfNext;
            secondHalfPointer = secondHalfNext;
        }
        // 到了这里，就是前半部分还剩最后一个node了
        // 此时，
        // 如果后半部分也还剩最后一个node，就连上它
        if (secondHalfPointer != null) {
            firstHalfPointer.next = secondHalfPointer;
            secondHalfPointer.next = null;
        }
        // 如果后半部分已经用光，则任务完毕，连上null
        else if (secondHalfPointer == null) {
            firstHalfPointer.next = null;
        }
    }
}


