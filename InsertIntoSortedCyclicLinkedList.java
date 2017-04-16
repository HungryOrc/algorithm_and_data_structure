/* Given a node from a cyclic linked list which has been sorted, 
write a function to insert a value into the list such that it remains a cyclic sorted list. 
The given node can be any single node in the list. Return the inserted new node.

Example
Given 3->5->1, insert a value 4. Return: 3->4->5->1(->3)
Given 3->4->5->1, insert a value 4. Return: 3->4->4->5->1(->3)
Given 2->2->2, insert a value 3. Return: 2->2->2->3(->2)

/* Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * } */

// 这一题的关键在于，insert以后，还要保持list是sorted ！！
// 我自己的方法
public class Solution {

    public ListNode insert(ListNode node, int x) {
        
        ListNode toBeInserted = new ListNode(x);

        // 如果原本一个node也没有，那么新来的这个加进来以后，还必须自己loop到自己！！
        if (node == null) {
            toBeInserted.next = toBeInserted;
            return toBeInserted;
        }
        
        // 如果之前就只有一个node，那么这个node必须是自己next到自己的 ！！
        if (node.next == node) {
            node.next = toBeInserted;
            toBeInserted.next = node;
            return toBeInserted;
        }
        
        // 如果之前有大于等于两个node
        ListNode curNode = node;
        while (!(curNode.val <= x && curNode.next.val > x) &&
               // 特别注意！！！
               // 下面这个判断条件，是特别为了对付这种情况：2->2->2这个list要加个3
               // curNode.next != node 表示转一圈又回来了 ！！！
               curNode.next != node) {
            curNode = curNode.next;
        }
        ListNode nextNode = curNode.next;
        curNode.next = toBeInserted;
        toBeInserted.next = nextNode;
        
        return toBeInserted;
    }
    
}
