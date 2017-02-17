/* Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
Supposed the linked list is 1 -> 2 -> 3 -> 4 (但list本身是不会给你的！) and you are ONLY given the node with value 3 (to be deleted), 
the linked list should become 1 -> 2 -> 4 after calling your function

Since we do not have access to the node before the one we want to delete, 
we cannot modify the next pointer of that node in any way. 
Instead, we have to replace the value of the node we want to delete with the value in the node after it, 
and then delete the node after it.   
Because we know that the node we want to delete is not the tail of the list, we can guarantee that this approach is possible. */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void deleteNode(ListNode nodeToDel) {
        // 注意！如果 nodeToBeDeleted.next 为空，即 nodeToBeDeleted 是最后一个node，
        // 则这一题没法做！虽然情理上这种情况也应该做，但在此题只给本node的前提下，没法做
        if (nodeToDel == null || nodeToDel.next == null) {
            return;
        }
        
        nodeToDel.val = nodeToDel.next.val;
        nodeToDel.next = nodeToDel.next.next;
    }
}

