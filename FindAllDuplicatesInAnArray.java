/* Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
Find all the elements that appear twice in this array.
Could you do it without extra space and in O(n) runtime?

Example:
Input: [4,3,2,7,8,2,3,1]
Output: [2,3]

我的思路：在数组里从左到右逐个slot看。如果当前slot里的element的(绝对)值为4，则将数组的第4个element的值乘以-1；
如果后面又出现一个element的绝对值为4，则再去将第4个element乘以-1的时候，就会发现这个地方的数已经 <0 了。
所以4是之前就出现过了的。
特别注意！！数组里的各个元素的值的范围是 [1, n]，但index的范围是 [0, n-1]
所以像上面叙述的例子，找到一个element的绝对值是4，那么要把 index = 4-1 = 3 的那个元素置为负数  */

public class Solution 
{
    public List<Integer> findDuplicates(int[] nums) 
    {
        int n = nums.length;
        ArrayList<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < n; i++)
        {
            int absValueInCurSlot = Math.abs(nums[i]);
            
            int valueInTheSlotWithIndex_EqualToTheABSValueInCurSlot 
                = nums[absValueInCurSlot - 1];
            if (valueInTheSlotWithIndex_EqualToTheABSValueInCurSlot > 0)
                nums[absValueInCurSlot - 1] *= (-1);
            else // < 0. 等于0是不可能的，因为nums里的所有数都介于[1, n]
                result.add(absValueInCurSlot);
        }
        return result;
    }
}
