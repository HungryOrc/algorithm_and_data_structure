/* Given a linked list, determine if it has a cycle in it.
Follow up: Can you solve it without using extra space?

 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * } */

// 用快慢2个指针，slowPointer一次走一步，fastPointer一次走两步
// Ref: https://leetcode.com/articles/linked-list-cycle/
// 如果不循环，则fast一定会撞到null，且一定不会撞到slow
// 如果循环，则fast一定不会撞到null，且迟早一定会撞到slow。如果我们设fast一次走超过两步比如n步，也一定会撞到slow，只是撞到slow的时间会延长或缩短
// Time: O(n); Space: O(1)，因为就2个node的space
public class Solution {
    
    public boolean hasCycle(ListNode head) {
        
        if (head == null || head.next == null)
            return false;
        
        // 快的从一开始就先多走一步！后面的处理会因此简化点
        ListNode fastPointer = head.next;
        ListNode slowPointer = head;
        
        while(fastPointer != slowPointer)
        {
            if (fastPointer.next == null || fastPointer.next.next == null)
                return false;
            else
            {
                fastPointer = fastPointer.next.next;
                slowPointer = slowPointer.next;
            }
        }
        return true; // this is when fast == slow
    }
}
