/* Insert a value in a sorted linked list.
Examples:
L = null, insert 1, return 1 -> null
L = 1 -> 3 -> 5 -> null, insert 2, return 1 -> 2 -> 3 -> 5 -> null
L = 1 -> 3 -> 5 -> null, insert 3, return 1 -> 3 -> 3 -> 5 -> null
L = 2 -> 3 -> null, insert 1, return 1 -> 2 -> 3 -> null */

/* class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * } */

public class Solution {

  public ListNode insert(ListNode head, int value) {
    
    ListNode dummyHead = new ListNode(-1);
    ListNode newNode = new ListNode(value);
    ListNode cur = dummyHead;
    
    dummyHead.next = head;
    
    while (cur.next != null && cur.next.value <= value) {
      cur = cur.next;
    }
    
    ListNode nextNode = cur.next;
    cur.next = newNode;
    newNode.next = nextNode;
    
    return dummyHead.next;
  }

}
