/*
Classical Binary Search.
Find any position of a target number in a sorted array. Return -1 if target does not exist.

Example:
Given [1, 2, 2, 4, 5, 5].
For target = 2, return 1 or 2.
For target = 5, return 4 or 5.
For target = 6, return -1. */

public class Solution {
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return an integer
     */
    
    // 运用九章二分模板
    public int findPosition(int[] nums, int target) {
        
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        
        if (nums[start] == target) {
            return start;
        } else if (nums[end] == target) {
            return end;
        } else // target does not exist in this array
            return -1;
    }
}
