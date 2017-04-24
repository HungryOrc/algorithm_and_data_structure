/* 给一个linkedlist，已经按照绝对值从小到大排序，比如 1 => -2 => -3 => 5 => -10
要返回按照正常的从小到大排序的 linkedlist，比如 -10 => -3 => -2 => 1 => 5
Time: O(n)
Space: O(1) */

// 方法1：很巧妙！
// 先拿个 dummy head 连着头，然后一路走下去，发现是负的就插入到 dummyHead 和 dummyHead.next 之间就行了
public class Solution {
    
    public ListNode sortByActualValue(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
      
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
      
        ListNode curNode = dummyHead;
        while (curNode.next != null) {
            if (curNode.next.value < 0) {
                ListNode nextNode = curNode.next;
                
                curNode.next = curNode.next.next;
              
                nextNode.next = dummyHead.next;
                dummyHead.next = nextNode;
            }
        }
      
        return dummyHead.next;
    }
}


// 方法2：笨一点，不过很直观
// 将positive和negative的node分开到两个链表里面，
// 然后将negative链表翻转一下，最后将positive链表连到negative链表后面
