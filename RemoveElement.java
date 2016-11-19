/* Given an array and a value, remove all instances of that value in place and return the new length.
Do not allocate extra space for another array, you must do this in place with constant memory.
The order of elements can be changed. It doesn't matter what you leave beyond the new length.
Example:
Given input array nums = [3,2,2,3], val = 3
Your function should return length = 2, with the first two elements of nums being 2. */

public class Solution {
    
    // 不改变其他元素的排列顺序的方法：把所有元素依次往前挪，往前挪的位数累加
    public int removeElement(int[] nums, int val) {
        
        int shiftSlots = 0;
        for (int i = 0; i < nums.length - shiftSlots; i++)
        {
            // 首先要挪一下！！！
            nums[i] = nums[i+shiftSlots];
            
            // 注意1：如果挪过来的数又等于val！！则要位移量+1，然后再挪一次！！
            // 注意2：这种情况可能连续出现！！即第二次挪过来的数还是等于val、第三次挪过来的数还是等于val……
            while (nums[i] == val)
            {
                shiftSlots++;
                
                // 注意3：必须是在没有越出Array长度的条件下，才挪！
                // 否则就break循环，结束整个处理过程
                if (i+shiftSlots < nums.length)
                    nums[i] = nums[i+shiftSlots];
                else
                    break;
            }
        }
        return nums.length - shiftSlots; // 最终的位移量，等于val出现过的次数
    }
    
    
    // 改变其他元素的排列顺序的方法：头尾两个光标，前者向后，后者向前。从Array尾部取数往前填
    // 如果Array尾部的元素也等于val，则计数+1并向前跳一位
    public int removeElement(int[] nums, int val) {
        
        int removeCounts = 0;
        for (int i = 0, j = nums.length-1; i <= j; i++)
        {
            if (nums[i] == val)
            {
                removeCounts++;
                
                while (nums[j] == val && j > i)
                {
                    removeCounts++;
                    j--;
                }
                if (j > i) // 且默认有 nums[j] != val
                {
                    nums[i] = nums[j];
                    j--;
                }
            }
        }
        return nums.length - removeCounts; // 最终的位移量，等于val出现过的次数
    }
    
}
