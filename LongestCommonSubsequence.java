/* Find the length of longest common subsequence of two given strings.

Assumptions:
The two given strings are not null

Examples:
S = “abcde”, T = “cbabdfe”, the longest common subsequence of s and t is {‘a’, ‘b’, ‘d’, ‘e’}, the length is 4.
Input: "aaaaa","abbaba", return 3. */


/* 思路：注意与 Longest Common Substring 的区别：DP数组的含义 ！！ 以及处理逻辑 ！！

设置一个二维的 DP 数组，dp[i][j] 的意思是，
在 string S 里，所有 结尾char的 index <= i 的substring，
和 string T 里，所有 结尾char的 index <= j 的substring，
这两类substring之间存在的最长的 common substring 的长度。
注意 ！！！ 这两类substring的起始位置随意。结束位置也未定，但不能超过 S里的i 和 T里的j ！！！

那么，对于dp[i][j]，i = 0 或 j = 0 的情况下，如果 S[i] == T[j]，则 dp[i][j] = 1，否则为 0.
上面即第一行和第一列的情况。对于矩阵更内部的情况：

dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]),     if S[i] != T[j]
         = dp[i - 1][j - 1] + 1,                     if S[i] == T[j]
         
最后取整个dp矩阵里的最大值为 max length。注意！不是取 dp[n - 1][m - 1]！   
时间：O(nm)   */

public class Solution {
  
  public int longest(String s, String t) {
    if (s == null || t == null || s.length() == 0 || t.length() == 0) {
      return 0;
    }
    
    char[] sArray = s.toCharArray();
    char[] tArray = t.toCharArray();
    int n = sArray.length;
    int m = tArray.length;
    
    int longestLength = 0;
    int[][] dp = new int[n][m];
    
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        
        if (sArray[i] == tArray[j]) {
          
          if (i == 0 || j == 0) {
            dp[i][j] = 1;
          } else {
            dp[i][j] = dp[i - 1][j - 1] + 1;
            longestLength = Math.max(dp[i][j], longestLength);
          }
        }
        
        else if (i != 0 && j != 0){
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    
    return longestLength;
  }
}
