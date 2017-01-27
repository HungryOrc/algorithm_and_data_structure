/* Given a mountain sequence of n integers which increase firstly and then decrease, find the mountain top.

Example
Given nums = [1, 2, 4, 8, 6, 3] return 8
Given nums = [10, 9, 8, 7], return 10  */

public class Solution {
    /**
     * @param nums a mountain sequence which increase firstly and then decrease
     * @return then mountain top */
     
    public int mountainSequence(int[] nums) {
        
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        else if (nums[len - 1] >= nums[len - 2]) {
            return nums[len - 1];
        }
        
        // find the 1st element that his direct successor is smaller than him
        int start = 0, end = len - 2; // 止于倒数第二个！
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= nums[mid + 1]) {
                end = mid;
            } else { // <
                start = mid + 1;
            }
        }
        // now start == end
        return nums[start];
    }
}
