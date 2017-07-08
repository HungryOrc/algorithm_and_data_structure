/* Find the longest common substring of two given strings S and T.

Assumptions:
The two given strings are not null or empty.

Examples:
S = “abcde”, T = “cdf”, the longest common substring of S and T is “cd”.
S = "aaaaa", T = "bbb", return "".  */


/* 思路：设置一个二维的 DP 数组，dp[i][j] 的意思是，
在 string S 里，所有以 index = i 处的char结尾的substring，和 string T 里，所有以 index = j 处的char结尾的substring，
这两类substring之间存在的最长的 common substring 的长度。
注意 ！！！ 这两类substring的起始位置随意，但结束位置分别必须是 S里的i处 和 T里的j处 ！！！

那么，对于dp[i][j]，i = 0 或 j = 0 的情况下，如果 S[i] == T[j]，则 dp[i][j] = 1，否则为 0.
上面即第一行和第一列的情况。对于矩阵更内部的情况：

dp[i][j] = 0,                       if S[i] != T[j]
         = dp[i - 1][j - 1] + 1,    if S[i] == T[j]

最后取整个dp矩阵里的最大值为答案 max length。注意！不是取 dp[n - 1][m - 1]！    */

public class Solution {
  
  public String longestCommon(String s, String t) {
    if (s == null || t == null || s.length() == 0 || t.length() == 0) {
      return "";
    }
    
    char[] sArray = s.toCharArray();
    char[] tArray = t.toCharArray();
    int n = sArray.length;
    int m = tArray.length;
    
    int startIndexS = 0;
    int longestLength = 0;
    
    int[][] dp = new int[n][m];
    
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        
        if (sArray[i] == tArray[j]) {
          
          // update the dp matrix
          if (i == 0 || j == 0) {
            dp[i][j] = 1;
          } else {
            dp[i][j] = 1 + dp[i - 1][j - 1];
          }
          
          // update the max length and starting index in s
          if (dp[i][j] > longestLength) {
            longestLength = dp[i][j];
            startIndexS = i - longestLength + 1;
          }
        }
      }
    }
    
    /* 注意 ！ 如果最后不存在任何 common substring，这个算法也可以 handle ！！！
     因为，我们一开始设置了 startIndexS = 0，所以，如果没有 common substring，那么 max length 将为 0，
     那么下面这样取出来的 substring 将为 ""，正好符合题意 ！ */
    return s.substring(startIndexS, startIndexS + longestLength);
  }
}
