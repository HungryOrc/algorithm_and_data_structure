/* Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
For example, Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity? */


public class Solution 
{
    // Bit Operation
    // Ref: https://leetcode.com/problems/missing-number/
    /* The basic idea is to use XOR operation. We all know that a^b^b =a, 
    which means two xor operations with the same number will eliminate the number and reveal the original number.
    In this solution, I apply XOR operation to both the index and value of the array.  
    so in a missing array, what left finally is the missing number. */
    public int missingNumber(int[] nums) 
    {
        int result = nums[0];
        for (int j = 1; j < nums.length; j++)
            result ^= nums[j];
        for (int i = 0; i <= nums.length; i++)
            result ^= i;
        return result;
    }
    
    
    // 我的一个方法。逻辑不是很简明。速度较慢
    public int missingNumber(int[] nums) 
    {
        int n = nums.length;
        // 多设一个标志位，专门为了数n。因为数组里的index不能到n，最多到n-1
        boolean nFound = false; 
        // 多设一个标志位，专门为了数0。因为取负数的做法对0无效
        int indexOfElementWithValueZero = -1; 
        
        for (int i = 0; i < n; i++)
        {
            int curABS = Math.abs(nums[i]);
            
            if (curABS == n)
                nFound = true;
            else
            {
                nums[curABS] *= (-1);
                
                if (curABS == 0)
                    indexOfElementWithValueZero = i;
            }
        }
    
        if (nFound == false)
            return n;
        else if (indexOfElementWithValueZero == -1) // didn't find zero
            return 0;
        else
        {
            for (int i = 0; i < n; i++)
            {
                if (nums[i] > 0)
                    return i;
            }
        }
        return indexOfElementWithValueZero;
    }
    
}
