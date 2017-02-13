/* Reverse a linked list from position m to n.
Given m, n satisfy the following condition: 1 ≤ m ≤ n ≤ length of list.
Challenge:
Reverse it in-place and in one-pass

Example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4, 
return 1->4->3->2->5->NULL.

/* Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * } */

public class Solution {
    
    /* @param ListNode head is the head of the linked list 
     * @oaram m and n
     * @return: The head of the reversed ListNode */
    public ListNode reverseBetween(ListNode head, int m , int n) {
        if (head == null || m < 1 || n < 1 || m >= n) {
            return head;
        }
        
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        
        ListNode curNode = dummyHead;
        // 把curNode移动到 node m 的前一位
        for (int i = 1; i < m; i++) {
            if (curNode.next == null) {
                return null;
            }
            curNode = curNode.next;
        }
        
        // 标记node m之前的最后一个node，为之后备用
        // 注意！这么做也可以兼顾 m = 1 的情况！！
        ListNode preMNode = curNode;
        // 标记 node m
        ListNode mNode = curNode.next;
        
        // 要开始反转 node m 到 node n 了
        ListNode prevNode = new ListNode(-1);
        curNode = curNode.next;
        for (int i = m; i <= n; i++) {
            if (curNode == null) {
                return null;
            }
            
            ListNode nextNode = curNode.next;
            curNode.next = prevNode;
            prevNode = curNode;
            curNode = nextNode;
        }
        
        // 标记 node n
        ListNode nNode = prevNode;
        // 标记 node n 之后的第一个node
        ListNode postNNode = curNode;
        
        // 前方不翻转部分的最后一个node的next，等于翻转部分翻转后的第一个node
        preMNode.next = nNode;
        // 翻转部分翻转后的最后一个node的next，等于后方不翻转部分的第一个node
        mNode.next = postNNode;
        
        return dummyHead.next;
    }
}
