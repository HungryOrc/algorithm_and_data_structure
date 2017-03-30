/* Inversion Count for an array indicates – how far (or close) the array is from being sorted.
If array is already sorted then inversion count is 0. If array is sorted in reverse order that inversion count is the maximum.
Formally speaking, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j.
For example,
The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3). */

/* 思路：
Ref: https://discuss.leetcode.com/topic/218/count-inversion
1. 找到每一个数后面有几个数比它小
   这一步直接参考我的git里的 Count of Smaller Numbers after Self in an Array 这一题 ！！！
   更详细的思路说明和解释，看那一题的注释
2. 把这些个数都累加起来，就是我们要求的整个数组的 inversion 值 */

public class Solution {
    
    public int countInversion(int[] nums) {
        
        int[] numOfSmallerNumbersAfterSelf = countSmaller(nums);
        
        int inversions = 0;
        for (int n : numOfSmallerNumbersAfterSelf) {
            inversions += n;
        }
        return inversions;
    }  
    
    // 从这里往下，就都是 Count of Smaller Numbers after Self in an Array 那一题的代码了 ！！！
    
    private int[] countSmaller(int[] nums) {
        
        Integer[] result = new Integer[nums.length];
        List<Integer> sortedAL = new ArrayList<Integer>();
        
        // 从后往前！！！一个关键
        for (int i = nums.length - 1; i >= 0; i--) {
            int curNum = nums[i];
            
            int numOfSmallerNumAfterCurNumber = getThe1stNumThatIsBiggerOrEqualToTargetInAL(curNum, sortedAL);
            
            sortedAL.add(numOfSmallerNumAfterCurNumber, curNum);
            result[i] = numOfSmallerNumAfterCurNumber;
        }
        return result;
    }
    
    // Binary Search
    private int getThe1stNumThatIsBiggerOrEqualToTargetInAL(int target, List<Integer> sortedAL) {
        if (sortedAL.size() == 0) {
            return 0;
        }
        
        int left = 0;     
        int right = sortedAL.size() - 1;
        
        // 特别注意 ！！！
        // 这里要特别处理一下后面的二分查找所无法解决的情况：就是整个查找区域里根本就没有我们要的案例
        // 这一题我们要找第一个大于等于target的数，但也许整个sorted list里所有的数都小于target，
        // 如果是这样的话，如果还按照下面的二分的方式走，会return整个list的最末尾一位的index，
        // 但我们真正要的是插到list的最末尾一位的后面，而不是最末尾一位上，
        // 所以不得已啊，只能写一下下面这个 special case
        if (sortedAL.get(right) < target) {
            return right + 1;
        }
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            int num = sortedAL.get(mid);
            
            if (num >= target) {
                right = mid;
            } else { // <
                left = mid + 1;
            }
        }
        if (sortedAL.get(left) >= target) {
            return left;
        } else {
            return right;
        }
    }
}
