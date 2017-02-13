/* Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
You should preserve the original relative order of the nodes in each of the two partitions.

Example
Given 1->4->3->2->5->2->null and x = 3,
return 1->2->2->4->3->5->null.

* Definition for ListNode.
* public class ListNode {
*     int val;
*     ListNode next;
*     ListNode(int val) {
*         this.val = val;
*         this.next = null;
*     }
* } */

public class Solution {
    
    /* @param head: The first node of linked list.
     * @param x: an integer
     * @return: a ListNode */
    public ListNode partition(ListNode head, int x) {
        
        if (head == null) {
            return null;
        }
        
        // 小的编成一个list，大的编成一个list，最后再连在一起
        ListNode smallerDummyHead = new ListNode(-1);
        ListNode biggerAndEqualDummyHead = new ListNode(-1);
        ListNode curSmallerNode = smallerDummyHead;
        ListNode curBiggerAndEqualNode = biggerAndEqualDummyHead;
        
        while (head != null) {
            if (head.val < x) {
                curSmallerNode.next = head;
                curSmallerNode = head;
                head = head.next;
            } else { // >= x
                curBiggerAndEqualNode.next = head;
                curBiggerAndEqualNode = head;
                head = head.next;
            }
        }
        
        // concatenate the 2 lists into one list
        curSmallerNode.next = biggerAndEqualDummyHead.next;
        curBiggerAndEqualNode.next = null; // 别忘了这一步！！
        
        return smallerDummyHead.next;
    }
}
