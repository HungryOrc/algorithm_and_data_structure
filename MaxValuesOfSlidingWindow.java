/* Given an integer array A and a sliding window of size K, find the maximum value of each window as it slides from left to right.

Assumptions:
The given array is not null and is not empty
K >= 1, K <= A.length

Examples:
A = {1, 2, 3, 2, 4, 2, 1}, K = 3, the windows are {{1,2,3}, {2,3,2}, {3,2,4}, {2,4,2}, {4,2,1}},
and the maximum values of each K-sized sliding window are [3, 3, 4, 4, 4]   */


/* 思路：两头读写，用 Deque 做
先把原来的int数组加工成同样长度的element数组，每个element包括原int的value，以及原int在int array里的index。
然后，对于element数组，从左到右，
每新来一个元素cur element，
1. 先看当前deque尾部的元素的value，是否比cur的value要小，如果是，则用poll last消灭这些元素，
因为有cur在，这些元素既比cur小，又在cur的前面，所以这些元素必然永远也翻不了身，不可能成为任何sliding window的max，所以poll掉它们
2. 然后把cur放在deque的尾部
3. 再看当前deque头部的元素的index，是否超过了sliding window的左界限，如果超过了，就用poll first来消灭掉，
它们超越了window的界限，自然不可能留下
4. 然后把当前deque的头部的元素的value放到result list里去
这一步必须在 当前cur的 index >= k - 1 的时候才能做！ 比如k=3，则到了元素index至少为2的时候，才能凑成长度为k=3的window！   */

class Element {
	int val;
	int index;
	public Element(int val, int index) {
		this.val = val;
		this.index = index;
	}
}

public class Solution {
	  
	  public List<Integer> maxWindows(int[] array, int k) {
	    List<Integer> result = new ArrayList<>();
	    int n = array.length;
	    
	    Element[] elements = new Element[n];
	    for (int i = 0; i < n; i++) {
	    	Element ele = new Element(array[i], i);
	    	elements[i] = ele;
	    }
	    
	    Deque<Element> deque = new LinkedList<>();
	    
	    for (int i = 0; i < n; i++) {
	    	
	    	Element cur = elements[i];
	    	while (!deque.isEmpty() && cur.val > deque.peekLast().val) {
	    		deque.pollLast();
	    	}
	    	deque.offerLast(cur);
	    	
	    	if (i >= k - 1) {
	    		while (!deque.isEmpty() && deque.peekFirst().index <= i - k) {
	    			deque.pollFirst();
	    		}
	    		result.add(deque.peekFirst().val);
	    	}
	    }
	    return result;
	  }
  }
