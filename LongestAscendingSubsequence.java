/* Given an array A[0]...A[n-1] of integers, find out the length of the longest ascending subsequence.

Assumptions:
A is not null

Examples:
Input: A = {5, 2, 6, 3, 4, 7, 5}; Output: 4.
Because [2, 3, 4, 5] is the longest ascending subsequence.  */


/* 思路：DP
这一题和 Longest Ascending Subarray 最大的区别是，后者需要所有元素连续在一起。
DP数组里的元素 dp[i] 的意思是，原数组里，结束点的index <= i 的所有subarray中，存在的最长的 ascending subarray 的长度。

步骤：
dp[0] = 1，然后，每个dp[i]的默认值都是1，
然后，对于每一个 j 属于 [0, i-1]，如果 array[j] < array[i]，就更新 dp[i] = Math.max(dp[j] + 1, dp[i])，
注意！这里不是 dp[i] = dp[j] + 1！
最后，更新 maxLength = Math.max(dp[i], maxLength)。最终的 maxLength 就是答案。   */

public class Solution {
  
  public int longest(int[] array) {
    if (array == null || array.length == 0) {
      return 0;
    }
    
    int n = array.length;
    int maxLength = 1;
    
    int[] dp = new int[n];
    dp[0] = 1; // 注意！别忘了这个
    
    for (int i = 1; i < n; i++) {
      dp[i] = 1; // 注意！default value of each element is 1! Not 0!
      
      for (int j = i - 1; j >= 0; j--) {
        if (array[j] < array[i]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
          maxLength = Math.max(dp[i], maxLength);
        }
      }
    }
    
    return maxLength;
  }
}
