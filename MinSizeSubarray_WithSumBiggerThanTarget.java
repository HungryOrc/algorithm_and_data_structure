/* Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. 
If there isn't one, return -1 instead.
Runtime requirement: O(n).

Example
Given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal length under the problem constraint. */

// 我的方法。2个指针分别标示subarray的两端，都从左往右走
public class Solution {

    public int minimumSize(int[] nums, int s) {
        
        int minSize = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0, right = 0;
        
        while (right < nums.length) {
            
            sum += nums[right];
            
            while (sum >= s) {
                minSize = Math.min(minSize, right - left + 1);
                
                sum -= nums[left];
                left ++;
            } 
            right ++;
        }
        
        if (minSize == Integer.MAX_VALUE) {
            return -1;
        } else {
            return minSize;
        }
    }
}
