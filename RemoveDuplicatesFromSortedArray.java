/* Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array nums = [1,1,2],
Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. 
It doesn't matter what you leave beyond the new length. */

public class Solution {

  // 记录位移量，每找到一个重复值，则位移量+1
	public int removeDuplicates(int[] nums) {

        if (nums == null)
            return 0;
        if (nums.length <= 1)
            return nums.length;
        
        int shiftSlots = 0;
        int i = 0;
        
        while (i < nums.length - shiftSlots)
        {
            nums[i] = nums[i + shiftSlots];
            while (i + shiftSlots + 1 < nums.length)
            {
                if (nums[i] == nums[i + shiftSlots + 1])
                    shiftSlots ++;
                else
                	break;
            }
            i++;
        }
        return nums.length - shiftSlots;
    }
    
    
    // 另一种思路：逐个查每个元素，遇到与前一个不同的，则计数+1，并在新的数组里填入这个不同的数
    	public int removeDuplicates(int[] nums)
	{
        int n = nums.length;
        
        if (n <= 1)
            return n;
            
        int index = 1;
        for (int i = 1; i < n; i++)
        {
            if (nums[i] != nums[i-1])
            {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }

    
}
