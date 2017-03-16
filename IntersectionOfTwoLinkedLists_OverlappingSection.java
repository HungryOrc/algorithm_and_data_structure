/* Write a program to find the node at which the intersection of two singly linked lists begins.
注意！！这两个list，在后面有一段重合的段！从交点开始，一直到结尾，都是在一起的！！
而且也不可能相交之后重新分开！因为一个点（那个交点）不可能有两个next node！！

For example, the following two linked lists:
A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.

Notes:
If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory. 

 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * } */
 
public class Solution {
    
    // 方法1: 用HashSet做，时间 O(n)，空间 O(n)
    // 注意！！！HashSet 是可以存 Reference 变量并比较 Reference 变量的！！！不是只能存 值变量！！！
    public ListNode getIntersectionNode(ListNode headA, ListNode headB)
    {
        HashSet<ListNode> nodeSetA = new HashSet<>();
        
        // 将 ListA 填注到HashSet里去
        while (headA != null) {
            nodeSetA.add(headA);
            headA = headA.next;
        }
        // 然后逐个比对另一个List里的nodes，看是否在HashSet里
        while (headB != null) {
            if (nodeSetA.contains(headB))
                return headB;
            headB = headB.next;
        }
        return null;
    }
    
    
    // 方法2: 两个指针，到尾之后再折回来！很巧妙！
    // 时间O(n)，空间O(1)，后者是重点！！
    // Ref: https://leetcode.com/articles/intersection-two-linked-lists/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB)
    {
        if (headA == null || headB == null)
            return null;
        
        ListNode originalHeadA = headA;
        ListNode originalHeadB = headB;
        boolean headAShifted = false;
        boolean headBShifted = false;
        
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
            
            // 两个pointer可能同时到达尾端，也可能不同时。但这不要紧：
          
            // 当 pointer A 到了 listA 的尾端
            if (headA == null) {
                // 如果pointer A 还没有被转到listB的头部过，就把它转到listB的头部
                if (headAShifted == false) {
                    headA = originalHeadB;
                    headAShifted = true;
                }
                // if headA had been shifted once, then it become null again means
                // there is no intersection between the 2 lists
                else
                    return null;
            }
          
            // 当 Pointer B 到了 listB 的尾端
            if (headB == null) {
                if (headBShifted == false) {
                    headB = originalHeadA;
                    headBShifted = true;
                }
                // if headB had been shifted once, then it become null again means 
                // there is no intersection between the 2 lists
                else
                    return null;
            }
        }
      
        // 结束了这个while loop，没有触发 return null，此时有 headA == headB，
        // 其实就是 Pointer A == Pointer B，意味着找到了交点
        // 注意！！因为两个list的后半段重合在一起，所以两个pointer交汇的点一定也就是两个list的交点！！
        // 这个东西不是总必然的！！如果两个list的后半段并不重合，比如在交点处再分开，两个pointer的交汇点就未必是两个list的交点了！
        return headA; // return headB 也一样
    }
    
     
    // 方法3: 九章的方法。其数学上的有效性我自己还没证明
    // 先找到一个list的终点（其实也就是两个list共同的终点了），然后接到另一个list的起点去，这样构成一个部分self loop的list
    // 然后用快慢两个pointer，差速走一次，再同速走一次，就找到了交汇点
    // http://www.jiuzhang.com/solutions/intersection-of-two-linked-lists/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        
        // get the tail of list A.
        ListNode node = headA;
        while (node.next != null) {
            node = node.next;
        }
        node.next = headB;
        ListNode result = listCycleII(headA);
        node.next = null;
        return result;
    }
    private ListNode listCycleII(ListNode head) {
        ListNode slow = head, fast = head.next;
        
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return null;
            }
            
            slow = slow.next;
            fast = fast.next.next;
        }
        
        slow = head;
        fast = fast.next;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
    }
  
}
