/* Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
Find all the elements of [1, n] inclusive that do not appear in this array.
Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:
Input: [4,3,2,7,8,2,3,1]
Output: [5,6] 

// 非常巧妙的方法！
//Ref: https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
The basic idea is that we iterate through the input array and mark elements as negative using nums[nums[i] -1] = -nums[nums[i]-1].
In this way all the numbers that we have seen will be marked as negative. 
In the second iteration, if a value is not marked as negative, it implies we have never seen that index before, 
so just add it to the return list.
即：逐个看数组nums里的数的绝对值，如果看到数x存在在这个数组里，或者更确切地说，x是数组里的某个元素的绝对值，
则把数组nums的第x-1位置为负数。最后考察整数1-n，在数组nums里的第0到第n-1位，哪一位不是负数，则这一位+1的数不存在于原数组nums中 */

public class Solution 
{
    public List<Integer> findDisappearedNumbers(int[] nums) 
    {
        int n = nums.length;
        for (int i = 0; i < n; i++)
        {
            int absValueInThisSlot = Math.abs(nums[i]);
            if (nums[absValueInThisSlot-1] > 0)
                nums[absValueInThisSlot-1] *= -1;
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        for (int j = 0; j < n; j++)
        {
            if (nums[j] > 0)
                result.add(j+1);
        }
        
        return result;
    }
}
