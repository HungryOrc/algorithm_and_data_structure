import java.util.*;

// sort an int array to ascending order
public class Solution {

	public static void sortArrayWithThreeStacks(int[] nums) {
		if (nums == null || nums.length <= 1) {
			return;
		}
		
		// stack 1
		Stack<Integer> unsortedStack = new Stack<>();
		for (int n : nums) {
			unsortedStack.push(n);
		}
		// stack 2
		Stack<Integer> sortedStack = new Stack<>();
		// stack 3
		Stack<Integer> tempStack = new Stack<>();
		
		while (sortedStack.size() < nums.length) {
			sortedStack.push(unsortedStack.pop()); // the 1st number in this comparing loop
			
			while (!unsortedStack.isEmpty()) {
				int curNum = unsortedStack.pop();
				if (curNum > sortedStack.peek()) {
					tempStack.push(sortedStack.pop());
					sortedStack.push(curNum);
				} else {
					tempStack.push(curNum);
				}
			}
			
			dump(tempStack, unsortedStack, tempStack.size());
		}
		
		for (int i = 0; i < nums.length; i++) {
			nums[i] = sortedStack.pop();
		}
	}

	private static void dump(Stack<Integer> fromStack, Stack<Integer> toStack, int dumpSize) {
		for (int i = 1; i <= dumpSize; i++) {
			toStack.push(fromStack.pop());
		}
	}
	
	public static void main(String[] args) {
		int[] nums = new int[]{1, 21, 0, -1, -9 , 10, 100, 0, 5, 48, -37};
		sortArrayWithThreeStacks(nums);
		for (int n : nums)
			System.out.print(n + " ");
	}
}
