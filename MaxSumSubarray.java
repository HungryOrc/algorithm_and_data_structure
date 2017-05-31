/* Given an array of integers, find a contiguous subarray which has the largest sum.
The subarray should contain at least one number.
Challenge: Can you do it in time complexity O(n)?

Example
Given the array [−2,2,−3,4,−1,2,1,−5,3], the contiguous subarray [4,−1,2,1] has the largest sum = 6. */

public class Solution {

    // 方法1：DP
    // dp数组里 dp[i] 的意思是，结尾在第i个元素的所有subarray里，sum最大的值
    public int largestSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int maxSubarraySum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] >= 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }

            maxSubarraySum = Math.max(dp[i], maxSubarraySum);
        }
        return maxSubarraySum;
    }

    
    // 方法2：Prefix-Sum
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
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

    
    // 方法3：Greedy。如果不大于零，就清零！！！ 这个思路其实和DP方法的思路是完全一样的 ！
    // Ref: http://www.jiuzhang.com/solutions/maximum-subarray/
    public int maxSubArray(int[] A) {
        if (A == null || A.length == 0){
            return Integer.MIN_VALUE;
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
