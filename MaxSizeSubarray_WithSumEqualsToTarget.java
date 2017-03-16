/* Given an array nums and a target value k, find the maximum length of a subarray that sums to k. 
If there isn't one, return 0 instead.
Note: The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

Example 1:
Given nums = [1, -1, 5, -2, 3], k = 3,
return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
Example 2:
Given nums = [-2, -1, 2, 1], k = 1,
return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

Follow Up: Can you do it in O(n) time?  */

public class Solution 
{
    // 用 HashMap 做，很巧妙的方法 ！！
    // Ref: https://discuss.leetcode.com/topic/33259/o-n-super-clean-9-line-java-solution-with-hashmap
    public int maxSubArrayLen(int[] nums, int k) 
    {
        HashMap<Integer,Integer> prefixSumsMap = new HashMap<>();
        int len = nums.length;
        int curPrefixSum = 0;
        int maxLength = 0;
        
        for (int i = 0; i < len; i++)
        {
            curPrefixSum += nums[i];
            
            // check the current prefix sum
            if (curPrefixSum == k) 
                maxLength = i + 1;
            // check the complement of the current prefix sum
            else if (prefixSumsMap.containsKey(curPrefixSum - k))
            {
                // the index of the complement of the current prefix sum
                // is the index of the prefix sum that we need to remove to meet the sum k
                int complementIndex = prefixSumsMap.get(curPrefixSum - k);
                int thisSubArrayLength = i - complementIndex;
                
                maxLength = Math.max(thisSubArrayLength, maxLength);
            }
            
            // no matter what above, we need to record this prefix sum into the map,
            // if it is not recorded yet
            if (!prefixSumsMap.containsKey(curPrefixSum))
                // record the first index i that this prefix sum occurrs
                // this prefix sum might occurr later for more times,
                // but we only care about its first occurrence, since we need the max length of sub array
                prefixSumsMap.put(curPrefixSum, i);
        }
        return maxLength;
    }
}
