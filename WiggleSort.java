/* Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4]. */

public class Solution 
{
    // 我的朴素方法。速度较慢
    // 将数组从小到大排序。把最小的一半放在奇数位置，即slot 0,2,4,6...；把最大的一半放在偶数位置，即slot 1,3,5,7...
    public void wiggleSort(int[] nums) 
    {   
        int n = nums.length;
        int[] temp = new int[n];
        for (int i = 0; i < n; i++)
            temp[i] = nums[i];
        
        Arrays.sort(temp);
        
        for (int i = 0, odd = 0; odd < n; i++, odd+=2)
            nums[odd] = temp[i];
        
        int i = 0;
        if (n % 2 == 0)
            i = n/2;
        else
            i = (n+1) / 2;
        for (int even = 1; even < n; i++, even+=2)
            nums[even] = temp[i];
    }   
    
    
    
    
    
}
