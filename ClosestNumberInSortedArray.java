/* Given a target number and an integer array A sorted in ascending order, 
find the index i in A such that A[i] is closest to the given target.
Return -1 if there is no element in the array.
O(logn) time complexity.
Notice: There can be duplicate elements in the array, and we can return any of the indices with same value.

Example
Given [1, 2, 3] and target = 2, return 1.
Given [1, 4, 6] and target = 3, return 1.
Given [1, 4, 6] and target = 5, return 1 or 2.
Given [1, 3, 3, 4] and target = 2, return 0 or 1 or 2. */

public class Solution {
    /**
     * @param nums: an integer array sorted in ascending order
     * @param target: an integer
     * @return an integer */
    
    // 九章二分模板，正好前后预留两个，最接近的一定存在于这两个之一
    public int closestNumber(int[] nums, int target) {
        
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                end = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else { // ==
                return mid; // 都相等了，不可能更接近了，所以return这个index
            }
        }
        
        if (Math.abs(target - nums[start]) <= Math.abs(nums[end] - target)) {
            return start;
        } else {
            return end;
        }
    }
}
