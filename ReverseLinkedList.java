/* Reverse a singly linked list.

* Definition for singly-linked list:
* public class ListNode {
*     int val;
*     ListNode next;
*     ListNode(int x) { val = x; }
* } */
 
public class Solution {

    // 方法1：Iteration。根本不用 ArrayList 之类的东西来暂存！直接 in place 搞就行了！！
    // Time: O(n), because we walk through each node
    // Space: O(1), because we only introduce constant extra nodes to solve this problem
    public ListNode reverseList(ListNode head) {        
        if (head == null || head.next == null)
            return head;
        
        // 注意！！以下几步都是精华！！
        ListNode prevNode = null;
        while (head != null) {
            ListNode nextNode = head.next;
            head.next = prevNode;
            prevNode = head;
            head = nextNode;
        }
        return prevNode;
    }    


    // 方法2：Recursion without helper function
    // 注意 ！！它的理解也许比 iteration方法 更难 ！
    // Time: O(n), because we walk through each node
    // Space: O(n), because there are n layers of the call stack, and each call stack uses constant space
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        
        // 第一步：不管当前node，它以后的nodes会发生什么
        ListNode nextNode = head.next;
        ListNode newHead = reverseList(nextNode);   
     
        // 第二步：经过处理后的后部，与当前的前部，应该发生哪些关系？一个也不要漏！
        nextNode.next = head;
        
        /* 特别注意下面这句 ！！！
          如果是第一个head node，这么做就完事儿了。它也就该next -> null 
          但是对于原head以后的任何nodes，把它们的next设为null怎么行呢？！！窍门在于：
          reverseList这个函数在上面是不断嵌套自己的，
          在里面的一层，把某个node的next设为null以后；在紧邻着的外面的一层，就会再把它的next设为它之前的prev 
         
          从另外一个角度理解这个问题：对于head来说，sub problem是：
            1 -> 2 -> 3 -> 4 -> null
                \___sub problem ___/
              
          sub problem搞定以后，它的结果其实是这个样子：注意！节点2 在此时是指向null的 ！！！
            1 -> 2 <- 3 <- 4
                 |
                 V
                null                                                                       */
     
        head.next = null;
     
        return newHead;
    }
 
 
    // 方法3：Recursion with helper function。和上面的Iteration方法的思想是一致的
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        
        return reverse(head, null);
    }
 
    public ListNode reverse(ListNode curHead, ListNode prevNode) {
        if (curHead == null)
            return prevNode;
            
        ListNode next = curHead.next;
        curHead.next = prevNode;
        
        return reverse(next, curHead);
    }

}
