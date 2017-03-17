/* Given an array of integers, find a contiguous subarray which has the largest sum.
The subarray should contain at least one number.
Challenge: Can you do it in time complexity O(n)?

Example
Given the array [−2,2,−3,4,−1,2,1,−5,3], the contiguous subarray [4,−1,2,1] has the largest sum = 6. */

public class Solution {

    // 方法1：Prefix-Sum
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int maxSum = Integer.MIN_VALUE;
        int curPrefixSum = 0;
        int minPrefixSum = 0;
        
        for (int n : nums) {
            curPrefixSum += n;
            maxSum = Math.max(maxSum, curPrefixSum - minPrefixSum);
            minPrefixSum = Math.min(minPrefixSum, curPrefixSum);
        }
        return maxSum;
    }

    
    // 方法2：Greedy。如果不大于零，就清零！！！
    // Ref: http://www.jiuzhang.com/solutions/maximum-subarray/
    public int maxSubArray(int[] A) {
        if (A == null || A.length == 0){
            return 0;
        }
        
        int max = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            max = Math.max(max, sum);
            sum = Math.max(sum, 0);
        }
        return max;
    }
}
