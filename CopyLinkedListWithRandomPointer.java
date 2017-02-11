/* A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
Return a deep copy of the list.

Challenge: Could you solve it with O(1) space?

/* Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * }; */

public class Solution {
    
    // 方法1，用HashMap。但是额外空间是n量级的，主要是mapping的这个关系占的空间
    // 答案所占用的空间，是必须的空间，不算是额外的空间
    // 这里的答案是一个deep copy出来的list，它是n空间的，但它一点也不算额外空间
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        
        HashMap<RandomListNode,RandomListNode> mappingFromOldToNewNodes = new HashMap<>();
        RandomListNode curOldNode = head;
        RandomListNode curNewNode = new RandomListNode(curOldNode.label);
        RandomListNode newHead = curNewNode;
        mappingFromOldToNewNodes.put(curOldNode, curNewNode);
        
        // 复制各个nodes，以及各个next关系
        while(curOldNode.next != null) {
            RandomListNode nextOldNode = curOldNode.next;
            RandomListNode nextNewNode = new RandomListNode(nextOldNode.label);
            curNewNode.next = nextNewNode;
            mappingFromOldToNewNodes.put(nextOldNode, nextNewNode);
            
            curOldNode = nextOldNode;
            curNewNode = nextNewNode;
        }
           
        // 复制各个random关系
        curOldNode = head;
        while(curOldNode != null) {
            RandomListNode curOldRandom = curOldNode.random;
            RandomListNode curNewRandom = mappingFromOldToNewNodes.get(curOldRandom);
            curNewNode = mappingFromOldToNewNodes.get(curOldNode);
            curNewNode.random = curNewRandom;
            
            curOldNode = curOldNode.next;
        }
        
        return newHead;
    }
}
