/* Given an integer array with all positive numbers and no duplicates, 
find the number of possible combinations that add up to a positive integer target.

Example:
nums = [1, 2, 3]
target = 4
The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
Note that different sequences are counted as different combinations.
Therefore the output is 7

Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers? */

// Ref: https://discuss.leetcode.com/topic/52302/1ms-java-dp-solution-with-detailed-explanation

public class Solution 
{
    // Recursion
    public int combinationSum4(int[] nums, int target) 
    {
        // 数组里全是正数，所以target不可能一开始就是0
        // 如果target是0，只可能是运算到后面时，剩余的target sum降到0了
        // 此时也就是一个valid组合被找到了
        if (target == 0)
            return 1;
        
        int result = 0;
        for (int n : nums)
        {
            if (n <= target)
                result += combinationSum4(nums, target-n);
        }
        return result;
    }
    
    
    

}
