/* Design a data structure that supports the following methods:
(1) insert(int n): add integer n to a stream of integers.
(2) getFirstUniqueInteger(): return the first unique integer in the stream if found, else return -1.

Example:
for input sequence:
insert(2) // 2
insert(2) // -1
insert(3) // 3
insert(4) // 3
insert(3) // 4 */

/* 思路：
建立一个双链表，从前到后按时间存出现过的数。自定义一个 class ListNode，来装 integer值，prev，next，以及是否重复了的boolean值
用一个 HashMap 来装：<出现的integer值，此integer被放在的node> */

class DLNode { // Doubly-Linked List Node
    public int value;
    public DLNode next, prev;
    public boolean duplicated; // 默认是 false
  
    public DLNode(int value) {
        this.value = value;
    }
}

public class FirstUnique {
    public HashMap<Integer, DLNode> records;
    public DLNode dummyHead, dummyTail; // 这两个变量就是代表了我们要的 双链表

    public FirstUnique() {
        records = new HashMap<>();
        
        // 构造双链表
        dummyHead = new DLNode(0);
        dummyTail = new DLNode(0);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    // DLL: Doubly Linked List
    private void addToTail_DLL(DLNode node) {
        node.next = dummyTail;
        node.prev = dummyTail.prev;
      
        dummyTail.prev.next = node;
        dummyTail.prev = node;
    }
    
    private void delete_DLL(DLNode node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    public void insert(int n) {
        if (records.containsKey(n)) { // 如果这个int之前在stream里出现过
            DLNode node = records.get(n);
            // 如果这个数之前只出现过一次，那么我们就要从双链表里删除这个int所对应的node，
            // 然后将这个node标记为已经重复过
            // 如果这个int再出现第三次，第四次……我们就什么也不用做了，因为它已经在双链表里被删除了
            if (node.duplicated == false) {
                this.delete_DLL(node); // 把这个node从双链表里删除。但不要把这个node从hashmap里删除！
                DLNode.duplicated = true;
            }
        } else { // 如果这个int是第一次在stream里出现
            DLNode node = new DLNode(n);
            records.put(int, node);
            this.addToTail_DLL(node);
        }
    }
  
    public int getFirstUniqueInteger() {
        if (dummyHead.next = dummyTail) { // 当前的双链表是空的。要么是一开始空的情况。要么是没有任何不重复的数了所以双链表是之前被删空了
            return -1;
        }
        return dummyHead.next.value;
    }
}
