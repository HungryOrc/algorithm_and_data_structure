/* Given a singly linked list, return a random node's value from the linked list. 
Each node must have the same probability of being chosen.

Follow up:
What if the linked list is extremely large and its length is unknown to you? 
Could you solve this efficiently without using extra space?
关于这个问题，见本文最后的解答

Example:
// Initiate a singly linked list [1,2,3].
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    Solution solution = new Solution(head);
// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
    solution.getRandom();

* Definition for singly-linked list.
* public class ListNode {
*     int val;
*     ListNode next;
*     ListNode(int x) { val = x; }
* } 
* Your Solution object will be instantiated and called as such:
*     Solution obj = new Solution(head);
*     int param = obj.getRandom(); */
 
 // 我的朴素解法。速度还挺快
 public class Solution 
{
    ListNode head;
    int listLength;
    
    // @param head: The linked list's head
    // Note that the head is guaranteed to be not null, so it contains at least one node
    public Solution(ListNode head) {
        this.head = head;        
        this.listLength = 1;
        ListNode curNode = head;
        while (curNode.next != null) {
            this.listLength ++;
            curNode = curNode.next;
        }
    }
    
    // Returns a random node's value
    public int getRandom()  {
        Random rand = new Random();
        // nextInt(max) method returns a pseudorandom, uniformly distributed int value 
        // between 0 (inclusive) and the specified max value (exclusive)
        int chosenIndex = rand.nextInt(listLength);

        ListNode targetNode = head;
        for (int i = 0; i < chosenIndex; i++)
            targetNode = targetNode.next;
        return targetNode.val;
    }
}

/* 本题的扩展问题的答案。很巧妙。我没想到
Ref: https://leetcode.com/problems/linked-list-random-node/

Brief explanation for Reservoir Sampling
Problem: Choose k entries from n numbers. Make sure each number is selected with the probability of k/n

Basic idea:
Choose 1, 2, 3, ..., k first and put them into the reservoir.
For k+1, pick it with a probability of k/(k+1), and randomly replace a number in the reservoir.
For k+i, pick it with a probability of k/(k+i), and randomly replace a number in the reservoir.
Repeat until k+i reaches n

Proof: gui na fa
For k+i, the probability that it is selected （and then will replace a number in the reservoir） is k/(k+i)
For a number in the reservoir before (let's say X), the probability that it keeps staying in the reservoir is
P(X was in the reservoir last time) × P(X is not replaced by k+i)
= P(X was in the reservoir last time) × (1 - P(k+i is selected and replaces X))
= k/(k+i-1) × （1 - k/(k+i) × 1/k）
= k/(k+i)
When k+i reaches n, the probability of each number staying in the reservoir is k/n

Example
Choose 3 numbers from [111, 222, 333, 444]. Make sure each number is selected with a probability of 3/4
First, choose [111, 222, 333] as the initial reservior
Then choose 444 with a probability of 3/4
For 111, it stays with a probability of
P(444 is not selected) + P(444 is selected but it replaces 222 or 333)
= 1/4 + 3/4*2/3
= 3/4
The same case with 222 and 333
Now all the numbers have the probability of 3/4 to be picked

This Problem <Linked List Random Node>
is the sp case where k=1 */
