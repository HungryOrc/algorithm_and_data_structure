/* Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. 
The algorithm should run in linear time and in O(1) space. */


// 参考 Majority Element_One Half 那一题 ！！！

// 方法1：对消的方法
// 思路：https://gregable.com/2013/10/majority-vote-algorithm-find-majority.html
// Time: O(n), Space: O(1)
public class Solution {
    
    public List<Integer> majorityElement(int[] nums) {
        
    	if (nums == null || nums.length == 0)
    		return new ArrayList<Integer>();
    	List<Integer> result = new ArrayList<Integer>();
    	int number1 = nums[0], number2 = nums[0], count1 = 0, count2 = 0, len = nums.length;
    	for (int i = 0; i < len; i++) {
    		if (nums[i] == number1)
    			count1++;
    		else if (nums[i] == number2)
    			count2++;
    		else if (count1 == 0) {
    			number1 = nums[i];
    			count1 = 1;
    		} else if (count2 == 0) {
    			number2 = nums[i];
    			count2 = 1;
    		} else {
    			count1--;
    			count2--;
    		}
    	}
    	
    	count1 = 0;
    	count2 = 0;
    	for (int i = 0; i < len; i++) {
    		if (nums[i] == number1)
    			count1++;
    		else if (nums[i] == number2)
    			count2++;
    	}
    	if (count1 > len / 3)
    		result.add(number1);
    	if (count2 > len / 3)
    		result.add(number2);
    	return result;
    }
}


// 方法2：排序数组然后二分查找。也很巧妙！！！
// 先排序数组，然后考察数组里位于 1/3 和 2/3 的这两个位置的数。
// 首先，看从左到右1/3位置处的那个数，设为a，然后在整个数组里，用二分查找，寻找第一个a和最后一个a的位置，
// 如果她们相隔的距离 >= 数组长度/3，则a就是我们要找的数。
// 如果a不满足，就找2/3位置处的那个数，设为b，重复上面的做法，看b是否满足。如果b也不满足，则要找的数不存在。
// Time: O(n logn + logn)，后面那个 logn 是（四次）二分查找所需的时间


// 方法3：HashMap
// Time: O(n), Space: O(n)
