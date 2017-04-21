/* Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
For example, Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity? */


public class Solution 
{
    // 方法1：Bit Operation
    // Ref: https://leetcode.com/problems/missing-number/
    /* The basic idea is to use XOR operation. We all know that a^a = 0, b^a^a = b, 
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
    
    
    // 方法2：Sum 方法。我竟然没想到。。。。。。
    // Ref: https://leetcode.com/problems/missing-number/
    public int missingNumber(int[] nums) {
        int len = nums.length;
        int sum = (0+len)*(len+1)/2;
        for(int i=0; i<len; i++)
            sum-=nums[i];
        return sum;
    }
    
    
    // 方法3：用HashSet做。要过两次。第一次把所有数都放进去。第二次从0到n，看看哪个数不在set里contains
    
    
    // 方法4：将数组排序。然后缺失的数，必然是第一个index比element小一的数，它前面的所有数都
    // 一定是index==element的值。最后我们用二分法来找这个index
    // Ref: https://leetcode.com/problems/missing-number/
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int left = 0, right = nums.length, mid= (left + right)/2;
        while(left<right){
            mid = (left + right)/2;
            if(nums[mid]>mid) right = mid;
            else left = mid+1;
        }
        return left;
    }

}
