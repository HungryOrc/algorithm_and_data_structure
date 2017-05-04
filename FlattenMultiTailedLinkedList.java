/* Given a linked list with both next and down pointers, flatten the list into a single linked list.
For example, given:

A---B---C---D---E---F---NULL
        |
        G---H---I---J---NULL
            |
            K---L---NULL

should become A-B-C-G-H-K-L-I-J-D-E-F-NULL

class ListNode {
    char val;
    ListNode next, down;
    public ListNode(char val) {
        this.val = val;
    }
} */

// 用 DFS 的方法做
public class Solution {

    public ListNode flattenMultiTail(ListNode root) {
        if (root == null || (root.next == null && root.down == null)) {
            return root;
        }
        
        Stack<ListNode> postponedBranches = new Stack<>();
        postponedBranches.push(root);
                
        ListNode curNode = root;
        ListNode prevNode = new ListNode(' ');
                
        while (!postponedBranches.isEmpty()) {
            curNode = postponedBranches.pop();
            prevNode.next = curNode;        
                    
            while (curNode != null) {
                if (curNode.down != null) {
                    postponedBranches.push(curNode.next); // 关键！
                    prevNode = curNode;
                    curNode = curNode.down;
                } else {
                    prevNode = curNode;
                    curNode = curNode.next;
                }
            }
        }
        return root;
    }
}
