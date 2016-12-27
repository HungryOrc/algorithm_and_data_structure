/* Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, 
where a move is incrementing a selected element by 1 or decrementing a selected element by 1.
You may assume the array's length is at most 10,000.

Example:
Input: [1,2,3]
Output: 2
Explanation: Only two moves are needed (remember each move increments or decrements one element):
[1,2,3]  =>  [2,2,3]  =>  [2,2,2] */

public class Solution 
{
    // 我自己想的朴素的做法。看起来很长其实很直觉
    // 最后移动到的锚定数值，称为pivot，一定在本数组的最大值与最小值之间。
    // pivot的位置，最理想的是比它大的数的个数 = 比它小的数的个数。与它相等的数的个数不管
    // 那么此时无论pivot +1 还是 -1，总move的次数不变。这里应该是一个稳定极小值，即凹min
    // 且pivot可以不是本数组中现有的数。
    // 我们把数组排序。然后从中间那个数开始考量。把pivot的初始值设为它。
    // 如果比它大的数多于比它小的数，就把pivot +1，然后看总move次数减少了还是增大了。
    // 如果比它小的数多于比它大的数，就把pivot -1，然后看总move次数减少了还是增大了。
    // 总move次数减少或者不变都行，继续上面的操作。如果增大了，则停
    // 总move次数增大以前的那一个总move次数即我们要求的minimum次数
    public int minMoves2(int[] nums) 
    {
        if (nums==null || nums.length<=1)
            return 0;
            
        int n = nums.length;
        Arrays.sort(nums);
        
        int pivot = nums[n/2];
        int lowerIndex = n/2;
        int upperIndex = n/2;
        
        while (lowerIndex>=0 && nums[lowerIndex]==pivot)
            lowerIndex --;
        while (upperIndex<n && nums[upperIndex]==pivot)
            upperIndex ++;
        int biggerNums = n + 1 - upperIndex;
        int smallerNums = lowerIndex - 0 + 1;
        int equalNums = upperIndex - lowerIndex - 1;
        int diffNumBetweenBiggerAndSmaller = biggerNums - smallerNums;
        
        int totalMoves = 0;
        for (int ele : nums)
            totalMoves += Math.abs(ele - pivot);
        int oldTotalMoves = totalMoves;

        int oldPivot = pivot;
        while (totalMoves<=oldTotalMoves && lowerIndex>=0 && upperIndex<n)
        {
            oldTotalMoves = totalMoves;
            oldPivot = pivot;
            
            // the optimal pivot is found when diff number == 0
            if (diffNumBetweenBiggerAndSmaller == 0)
                break;
                
            // the number of elements that are bigger than the pivot 
            // is larger than the number of elements that are smaller than the pivot
            else if (diffNumBetweenBiggerAndSmaller > 0)
            {
                pivot ++;
                diffNumBetweenBiggerAndSmaller -= equalNums;
                smallerNums += equalNums;
                
                totalMoves += smallerNums;
                totalMoves -= biggerNums;
                
                equalNums = 0;
                while (upperIndex<n && nums[upperIndex]==pivot)
                {
                    equalNums ++;
                    diffNumBetweenBiggerAndSmaller --;
                    biggerNums ++;
                    upperIndex ++;
                }
            }
            
            // the number of elements that are smaller than the pivot 
            // is larger than the number of elements that are bigger than the pivot
            else // diffNumBetweenBiggerAndSmaller < 0
            {
                pivot --;
                diffNumBetweenBiggerAndSmaller += equalNums;
                biggerNums += equalNums;
                
                totalMoves -= smallerNums;
                totalMoves += biggerNums;
                
                equalNums = 0;
                while (lowerIndex>=0 && nums[lowerIndex]==pivot)
                {
                    equalNums ++;
                    diffNumBetweenBiggerAndSmaller ++;
                    smallerNums --;
                    lowerIndex --;
                }
            }
        }
        return oldTotalMoves;
    }
    
}


