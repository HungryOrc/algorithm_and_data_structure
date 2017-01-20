/* For a given sorted array (ascending order) and a target number, find the first index of this number in O(log n) time complexity.
If the target number does not exist in the array, return -1.

Example
If the array is [1, 2, 3, 3, 4, 5, 10], for given target 3, return 2. */

public class Solution {
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return an integer
     */
    public int findFirstPosition(int[] nums, int target) {
        
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int len = nums.length;
        int start = 0, end = len - 1;
        
        while (start < end) {
            int mid = start + (end - start) / 2;
            
            if (nums[mid] == target) {
                if (mid == 0) {
                    return 0;
                }
                else {
                    if (nums[mid - 1] == target) {
                        end = mid - 1;
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
        
        // 此时 start == end
        if (nums[start] == target) {
            return start;
        }
        else {
            return -1;
        }
    }
}
