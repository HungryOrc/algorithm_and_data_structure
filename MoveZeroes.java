/* Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
Note:
1. You must do this in-place without making a copy of the array.
2. Minimize the total number of operations.
*/

// 我自己想出来的方法！
// 逐位看，是0则+1需要步进的步数，不是0则步进那么多步
// Time: O(n). Space: O(1).
public class Solution {
    
    public void moveZeroes(int[] nums) {
        
        if (nums == null || nums.length == 0) 
            return;     
        
        int forwardSteps = 0;
        for (int i = 0; i < nums.length; i++)
        {
            if (nums[i] == 0)
                forwardSteps ++;
            else
                nums[i - forwardSteps] = nums[i];
        }
        
        for (int i = nums.length-forwardSteps; i < nums.length; i ++)
            nums[i] = 0;
    }
}
