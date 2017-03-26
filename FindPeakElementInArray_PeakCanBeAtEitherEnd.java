/* A peak element is an element that is greater than its neighbors.
Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2. */

// 二分法
public class Solution {
    
    public int findPeakElement(int[] nums) {      
        // 处理nums长度太短的情况
        if (nums.length == 1) {
            return 0;
        } else if (nums.length == 2) {
            return nums[0] > nums[1] ? 0 : 1;
        } 
        // 数组长度大于2，然后数组里的第一个或者最后一个元素是peak的情况
        else {
            if (nums[0] > nums[1]) {
                return 0;
            } else if (nums[nums.length - 1] > nums[nums.length - 2]) {
                return nums.length - 1;
            }
        }
        
        // 处理其他的普通情况
        int start = 1, end = nums.length - 1;
        // 九章模板
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] < nums[mid - 1]) {
                end = mid;
            } else { // nums[mid] > nums[mid - 1]
                start = mid;
            }
        }
        if(nums[start] < nums[end]) {
            return end;
        } else { 
            return start;
        }
    }
}
