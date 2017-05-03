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
