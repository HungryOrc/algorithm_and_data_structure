/* Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
You may not alter the values in the nodes, only nodes itself may be changed.
Only constant memory is allowed.

Example
Given this linked list: 1->2->3->4->5
For k = 2, you should return: 2->1->4->3->5
For k = 3, you should return: 3->2->1->4->5

* Definition for singly-linked list.
* public class ListNode {
*     int val;
*     ListNode next;
*     ListNode(int x) { val = x; }
* } */

public class Solution {
    
    // 我自己的方法！
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        
        ListNode prevNode = null;
        ListNode nextNode = null;
        
        ListNode endOfPrevLoop = null;
        ListNode beginOfThisLoop = null;
        
        ListNode finalHead = null;
        boolean finalHeadLocated = false;
        
        while (stillHasKNodesToGo(head, k)) {
            
            for (int i = 1; i <= k; i++) {
                if (i == 1) {
                    beginOfThisLoop = head;
                }
                nextNode = head.next;
                head.next = prevNode;
                prevNode = head;
                head = nextNode;
            }
            
            // get the node that we need to return as the result
            // this is a one-time issue in the whole program
            if (finalHeadLocated == false) {
                finalHead = prevNode;
                finalHeadLocated = true;
            }
            
            // connect the end of the previous loop to the new head of this loop
            if (endOfPrevLoop != null) {
                endOfPrevLoop.next = prevNode;
            }
            // update the end of the previous loop
            endOfPrevLoop = beginOfThisLoop;
            
            // reset the prevNode to be null, to be ready for the next k-node loop
            // 特别注意这一步！
            // 如果不做，那么 prevNode 还是指向当前loop的反转后的头部，这样是不能继续下一个loop的！
            prevNode = null;
        }
        
        // if there are still nodes left to be managed, 
        // but their number is less than k
        if (head != null) {
            endOfPrevLoop.next = head;
        }
        
        return finalHead;
    }
    
    private boolean stillHasKNodesToGo(ListNode curHead, int k) {
        if (curHead == null) {
            return false;
        }
        for (int i = 1; i <= k - 1; i++) {
            if (curHead.next == null) {
                return false;
            }
            curHead = curHead.next;
        }
        return true;
    }
}
