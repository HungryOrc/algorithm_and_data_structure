/* Given a non-empty array of integers, return the third maximum number in this array. 
If it does not exist, return the maximum number. The time complexity must be in O(n).

Example 1: Input: [3, 2, 1], Output: 1
Explanation: The third maximum is 1.
Example 2: Input: [1, 2], Output: 2
Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
Example 3: Input: [2, 2, 3, 1], Output: 1
Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum. */

// 看起来简单，其实要考虑的特殊情况不少！下面注释里列了
public class Solution 
{
    public int thirdMax(int[] nums) 
    {
        if (nums == null || nums.length == 0)
            return Integer.MIN_VALUE;
        
        int max = nums[0];
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;
        
        int secondShiftedTimes = 0;
        int thirdShiftedTimes = 0;
        
        for (int i = 1; i < nums.length; i++)
        {
            int n = nums[i];
            
            if (n > max)
            {
                third = second;
                second = max;
                max = n;
                
                secondShiftedTimes ++;
                // 注意！！！要先判断这里是不是刚完成了second数的第一次被移动！！！
                // 如果上面刚刚是second数的第一次被移动，则third数此时不应该跟着移动！！！
                if (secondShiftedTimes > 1)
                    thirdShiftedTimes ++;
            }
            else if (n < max && n > second)
            {
                third = second;
                second = n;
                
                secondShiftedTimes ++;
                if (secondShiftedTimes > 1)
                    thirdShiftedTimes ++;
            }
            // this is especially for the case like:
            // [2, -2147483648, 1]
            // in which -2147483648 is exactly Integer.MIN_VALUE
            // and
            // in the following if criterions, "max > second" is for the case like"
            // [-2147483648, -2147483648, -2147483648, 1, 1]
            else if (n == second && secondShiftedTimes == 0 && max > second)
            {
                // second在此处初次被移动（且在此情况下只可能是 n = -2137483648）
                // 此时third是不跟着移动的
                secondShiftedTimes ++;
            }
            else if (n < second && n > third)
            {
                third = n;
                
                thirdShiftedTimes ++;
            }
            // in the following if criterions, "second > third" is for the case like"
            // [-2147483648, -2147483648, -2147483648, 1, 1]
            else if (n == third && thirdShiftedTimes == 0 && second > third)
            {
                thirdShiftedTimes ++;       
            }
        }
        if (thirdShiftedTimes == 0)
            return max;
        else
            return third;
    }
}

