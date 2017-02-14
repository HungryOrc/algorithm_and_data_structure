/* Write a program to find the node at which the intersection of two singly linked lists begins.

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
    
    // 方法1: 用HashSet做，时间 O(n)，空间 O(n)。空间方面不满足 O(1) 的要求
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
        return headA; // return headB 也一样
    }
    
}
