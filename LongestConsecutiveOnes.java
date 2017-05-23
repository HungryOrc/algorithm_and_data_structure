/* Given an array containing only 0s and 1s, find the length of the longest subarray of consecutive 1s.
Assumptions: The given array is not null

Examples:
{0, 1, 0, 1, 1, 1, 0}, the longest consecutive 1s is 3. */


// 方法1：DP
public class Solution {
  
  public int longest(int[] array) {
    if (array == null || array.length == 0) {
      return 0;
    }
    
    int[] dp = new int[array.length];
    
    dp[0] = array[0];
    int longestConsecOnes = dp[0];
    
    for (int i = 1; i < array.length; i++) {
      if (array[i] == 0) {
        dp[i] = 0;
      } else if (array[i - 1] == 0) {
        dp[i] = array[i];
      } else {
        dp[i] = dp[i - 1] + 1;
      }
      
      longestConsecOnes = Math.max(dp[i], longestConsecOnes);
    }
    return longestConsecOnes;
  }
}


// 方法2：two pointers
public class Solution {
  
  public int longest(int[] array) {
    int slow = 0, fast = 0;
    int longestConsecOnes = 0;
    
    while (fast < array.length) {
      
      while (fast < array.length && array[fast] == 0) {
        fast ++;
      }
      slow = fast;
      
      if (fast < array.length) {
        while (fast < array.length && array[fast] == 1) {
          fast ++;
        }
      }
      
      longestConsecOnes = Math.max(fast - slow, longestConsecOnes);
    }
    
    return longestConsecOnes;
  }
}
