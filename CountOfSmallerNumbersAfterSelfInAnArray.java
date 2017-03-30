/* You are given an integer array nums and you have to return a new counts array. 
The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example: Given nums = [5, 2, 6, 1]
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0]. */

// 很巧妙的方法！
// 从尾部开始做 + ArrayList + 二分查找
// Ref: https://discuss.leetcode.com/topic/31173/my-simple-ac-java-binary-search-code
/* Traverse from the BACK to the beginning of the array, maintain an sorted ArrayList of numbers that had been visited. 
Use findIndex() to find the first element in the sorted ArrayList which is larger or equal to target number. 
For example, [5,2,3,6,1], when we reach 2, we have a sorted array[1,3,6], 
findIndex() returns 1, which is the index where 2 should be inserted and is ALSO the number smaller than 2. 
Then we insert 2 into the sorted array to form [1,2,3,6]. 
Then keep doing this till the start of the input int[] array */

public class Solution {
    
    public List<Integer> countSmaller(int[] nums) {
        
        Integer[] result = new Integer[nums.length];
        List<Integer> sortedAL = new ArrayList<Integer>();
        
        // 从后往前！！！一个关键
        for (int i = nums.length - 1; i >= 0; i--) {
            int curNum = nums[i];
            
            int numOfSmallerNumsAfterSelf = getThe1stNumThatIsBiggerOrEqualToTargetInAL(curNum, sortedAL);
            
            sortedAL.add(numOfSmallerNumsAfterSelf, curNum);
            result[i] = numOfSmallerNumsAfterSelf;
        }
        return Arrays.asList(result); // 注意这个直接从数组转到ArrayList的函数！
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
