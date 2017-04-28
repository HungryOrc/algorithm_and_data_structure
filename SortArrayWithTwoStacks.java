import java.util.*;

// sort an int array to ascending order
public class Solution {

	public static void sortArrayWithTwoStacks(int[] nums) {
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
		// helper parameter
		int sortedSize = 0;
		
		while (sortedSize < nums.length) {
			int curMax = Integer.MIN_VALUE;
			
			while (!unsortedStack.isEmpty()) {
				int curNum = unsortedStack.pop();
				if (curNum > curMax) {
					curMax = curNum;
				}
				sortedStack.push(curNum);
			}
			
      // 注意！数组里可能有重复元素！所以在一轮loop里，等于当前loop的最大值的数可能超过一个！
      // 那么，第一个等于 当前max 的数，就不push回 unsorted stack 了 ！！！
      // 而后面那些等于 当前max 的数们，还得被push回 unsorted stack 里 ！！！
			boolean curMaxPopped = false;
			while (sortedStack.size() > sortedSize) {
				int curNum = sortedStack.pop();
	
				if (curNum != curMax) {
					unsortedStack.push(curNum);
				}
				else if (curNum == curMax && curMaxPopped == false) {
					curMaxPopped = true;
				}
				else if (curMaxPopped == true) {
					unsortedStack.push(curNum);
				}
			}
			
			sortedStack.push(curMax); // 别忘了这个！这一轮的max还要push回sorted stack里去 ！！！
			sortedSize ++;
		}
		
		for (int i = 0; i < nums.length; i++) {
			nums[i] = sortedStack.pop();
		}
	}
  
}
