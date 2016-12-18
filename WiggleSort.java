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
    
    
    // 方法二：Sort 之后，邻近的2个swap。可保无虞！
    // Ref: https://leetcode.com/articles/wiggle-sort/
    public void wiggleSort(int[] nums)
    {
        Arrays.sort(nums);
        // 注意！从第二个元素开始swap！即第一个swap是在 i=1 和 i=2 之间！
        for (int i = 1; i < nums.length - 1; i += 2)
            swap(nums, i, i + 1);
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    
    // 方法三：其实可以一次过。奇数位上的数如果大于后一个，则swap；偶数位上的数如果小于后一个，则swap
    // 关键的迷雾是：
    // 如果偶数位上的数因为小于后一个而swap，并不会影响这个偶数位之前的数要比此偶数位上的数小的要求！！！
    // Ref: https://leetcode.com/articles/wiggle-sort/
    public void wiggleSort(int[] nums) 
    {
        for (int i = 0; i < nums.length - 1; i++) {
            if (((i % 2 == 0) && nums[i] > nums[i + 1])
                    || ((i % 2 == 1) && nums[i] < nums[i + 1])) {
                swap(nums, i, i + 1);
            }
        }
    }
    
}
