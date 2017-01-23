/* Suppose a sorted array is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
Find the minimum element.

Notice: 
You may assume the array is not null and its length is bigger than zero.
数组中可以有重复的元素。下面的算法可以处理有重复的情况。

Example:
Given [4, 5, 6, 7, 0, 1, 2] return 0. */

public class Solution {
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array */
    
    // Ref: http://www.jiuzhang.com/solutions/find-minimum-in-rotated-sorted-array/
    public int findMin(int[] nums) {
        
        /* 如果选用 <= the last number in the rotated sorted array 作为标准，则能找到真正的答案。
         如果选用 <= the first number 作为标准，则会得到 the first number 自己，错误。
         如果选用 < the first number 作为标准，看似解决了上述问题，但其实还是不行。
         因为如果array没有被rotate，即first number即最小，则会得到无解。*/
        int bar = nums[nums.length - 1];
        
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            
            // 判断标准：与bar相比！
            if (nums[mid] <= bar) {
                end = mid;
            } 
            else { // if (nums[mid] > bar) 
                start = mid + 1;
            }
        }
        // now start == end
        return nums[start];
    }
}
