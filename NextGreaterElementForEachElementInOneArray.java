/* Next Greater Element
Ref: http://www.geeksforgeeks.org/next-greater-element/

Given an array, print the Next Greater Element's index for every element. 
The Next greater Element for an element x is the first greater (>) element on the right side of x in array. 
Elements for which no greater element exist, consider next greater element as -1.

Examples:
a) For any array, rightmost element always has next greater element as -1.
b) For an array which is sorted in decreasing order, all elements have next greater element as -1.
c) For the input array [13, 7, 6, 12}, the next greater elements for each element are as follows.
  Element        NGE's index
   13      -->    -1
    7      -->     3
    6      -->     3
   12      -->    -1
   
   
方法：用Stack解。很巧妙 ！！！
Time Complexity: O(n)
Ref: http://www.geeksforgeeks.org/next-greater-element/

1) Push the first element to stack.
2) Pick rest of the elements one by one and follow following steps in loop.
  a) Mark the current element as next.
  b) If stack is not empty, then pop an element from stack and compare it with next.
  c) If next is greater than the popped element, then next is the next greater element for the popped element.
  d) Keep popping from the stack while the popped element is smaller than next, 
     so next becomes the next greater element for all such popped elements
  g) If next is smaller than the popped element, then push the popped element back.
3) After the loop in step 2 is over, pop all the elements from stack and print -1 as next element for them.

Prove: Time Complexity = O(n)
The worst case occurs when all elements are sorted in decreasing order. 
In this case, every element is processed at most 4 times:
a) Initially pushed to the stack.
b) Popped from the stack when next element is being processed.
c) Pushed back to the stack because next element is smaller.
d) Popped from the stack in step 3 of the above algorithm description. */

import java.util.*;

public class NextGreaterElement {

	public static int[] getIndexesOfTheNextGreaterElements(int[] nums) {
		int[] result = new int[nums.length];
		
		Stack<Integer> managedNums = new Stack<>();
		Stack<Integer> indexes = new Stack<>();
		
		int index = 0;
		managedNums.push(nums[index]);
		indexes.push(index);

		for (index = 1; index < nums.length; index++) {
			
			int nextNum = nums[index];
			
			while (!managedNums.isEmpty() && nextNum > managedNums.peek()) {
				managedNums.pop();
				int curIndex = indexes.pop();
				result[curIndex] = index;
			}
			
			// if the stack "managedNums" is now empty, or nextNum <= managedNums.peek(),
			// then we push nextNum into the stack "managedNums"
			managedNums.push(nextNum);
			indexes.push(index);
		}
		
		// 到了此时，stack "managedNums" 里一定还有元素。至少，数组里的最后一个数现在一定在这个stack里
		while (!managedNums.isEmpty()) {
			managedNums.pop();
			int curIndex = indexes.pop();
			result[curIndex] = -1;
		}
		
		return result;
	}
	
	public static void main(String[] args) {

		int[] nums = new int[]{10, 13, 1, 1, 8, 9, 20, 8, 0, 11, 16};
		int[] result = getIndexesOfTheNextGreaterElements(nums);
		
		for (int i : result) {
			System.out.print(i + " ");
		} // 1 6 4 4 5 6 -1 9 9 10 -1

	}

}
