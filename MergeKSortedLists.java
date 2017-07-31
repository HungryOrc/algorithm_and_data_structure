/* Merge K sorted lists into one big sorted list in ascending order.
Assumptions: ListOfLists is not null, and none of the lists is null.

* class ListNode {
*   public int value;
*   public ListNode next;
*
*   public ListNode(int value) {
*     this.value = value;
*   }
* } */


// 思路：参考我总结的另一题：Merge K Sorted Arrays

// helper class, this class is the kind of Nodes that will be offered into the PriorityQueue
class PQNode implements Comparable<PQNode> {
  public int value;
  public int list;
  
  public PQNode(int value, int list) {
    this.value = value;
    this.list = list;
  }
  
  // class PQNode 内置的比较函数，int compareTo(PQNode anotherPQNode)
  @Override
  public int compareTo(PQNode node) {
    if (this.value > node.value)
      return 1;
    else if (this.value < node.value)
      return -1;
    else
      return 0;
  }
}

public class Solution {
  
  public ListNode merge(List<ListNode> listOfLists) {
    if (listOfLists == null || listOfLists.size() == 0) {
      return null;
    }
    
    PriorityQueue<PQNode> pq = new PriorityQueue<>();
    
    // Step 1. 先把每个list里的打头的ListNode转化成PQNode, 放到PriorityQueue里面去
    for (int i = 0; i < listOfLists.size(); i++) {
      ListNode headListNodeInListI = listOfLists.get(i);
      pq.offer(new PQNode(headListNodeInListI.value, i));
      
      // 别忘了！！！ in each list, point the head pointer of the list to the next ListNode
      listOfLists.set(i, headListNodeInListI.next);
    }
    
    // Step 2. get the head ListNode for the final result list that we'll return for this program
    PQNode firstPQNode = pq.poll();
    ListNode headListNode = new ListNode(firstPQNode.value); // we'll eventually return this ListNode
    ListNode curListNode = headListNode;
    
    // 把head list node 后面的 next node 放到 priority queue 里面去
    if (listOfLists.get(firstPQNode.list) != null) {
      pq.offer(new PQNode(listOfLists.get(firstPQNode.list).value, firstPQNode.list));
      // 别忘了！！！ in each list, point the head pointer of the list to the next ListNode
      listOfLists.set(firstPQNode.list, listOfLists.get(firstPQNode.list).next);
    }
    
    // Step 3. 把k个lists都动起来
    while(!pq.isEmpty()) {
      PQNode curPQNode = pq.poll();
      
      curListNode.next = new ListNode(curPQNode.value);
      curListNode = curListNode.next;
      
      int curList = curPQNode.list;
      if (listOfLists.get(curList) != null) {
        pq.offer(new PQNode(listOfLists.get(curList).value, curList));
        // 别忘了！！！ in each list, point the head pointer of the list to the next ListNode
        listOfLists.set(curList, listOfLists.get(curList).next);
      }
    }
    
    return headListNode;
  }
}
