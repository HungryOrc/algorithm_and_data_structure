/* Find the longest common substring of two given strings.

Assumptions:
The two given strings are not null

Examples:
S = “abcde”, T = “cdf”, the longest common substring of S and T is “cd”.
S = "aaaaa", T = "bbb", return "".  */


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
