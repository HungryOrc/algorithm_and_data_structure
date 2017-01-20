/* Find the last position of a target number in a sorted array. Return -1 if target does not exist.

Example:
Given [1, 2, 2, 4, 5, 5].
For target = 2, return 2.
For target = 5, return 5.
For target = 6, return -1. */

public class Solution {
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return an integer
     */
    public int lastPosition(int[] nums, int target) {
        
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int len = nums.length;
        int start = 0, end = len - 1;
        
        while (start < end) {
            int mid = start + (end - start) / 2;
            
            if (nums[mid] == target) {
                if (mid == len - 1) {
                    return mid;
                }
                else {
                    if (nums[mid + 1] == target) {
                        start = mid + 1;
                    }
                    else {
                        return mid;
                    }
                }
            }
            else if (nums[mid] < target) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }
        
        // 此时 start == end 了
        if (nums[start] == target) {
            return start;
        }
        else {
            return -1;
        }
    }
}
