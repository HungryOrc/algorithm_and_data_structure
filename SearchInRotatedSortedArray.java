/* Suppose a sorted array is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
You are given a target value to search. If found in the array return its index, otherwise return -1.
You may assume no duplicate exists in the array.
Runtime requirement: O(logN) time.

Example
For [4, 5, 1, 2, 3] and target=1, return 2.
For [4, 5, 1, 2, 3] and target=0, return -1. */

public class Solution {
    /** 
     *@param nums: an integer rotated sorted array
     *@param target :  an integer to be searched
     *return : an integer */
    
    // 两次二分的做法
    public int search(int[] nums, int target) {
        
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int start = 0, end = nums.length - 1;
        
        // 如果数组真的被翻转过
        if (nums[0] > nums[nums.length - 1])
        {
            // find the start of the right half array
            int rightHalfEndValue = nums[nums.length - 1];
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] > rightHalfEndValue) {
                    start = mid + 1;
                } else { // if (mid <= rightHalfEndValue)
                    end = mid;
                }
            }
            // now start == end
            int rightHalfBeginValue = nums[start];

            // locate the half in which the target is in
            if (target < rightHalfEndValue) {
                // start 不变，此时start就等于right half的起始index
                end = nums.length - 1;
            } else if (target > rightHalfEndValue) {
                start = 0;
                end -= 1;
            } else { // ==
                return nums[nums.length - 1];
            }
        }
    
        // find the index of the target
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else { // ==
                return mid;
            }
        }
        // now start == end
        if (nums[start] == target) {
            return start;
        } else {
            return -1;
        }
    }
    
    
    // 一次二分的做法
    
    
    
    
}
