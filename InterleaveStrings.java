/* Interleave 的意思是 “交错”

Given three strings A, B and C. Determine if C can be created by merging A and B in a way that maintains 
the relative order of the characters in A and B.

Assumptions:
none of A, B, C is null.
C might be empty, if so,return true.
A or B might be empty, if so, continue the program, don't immediately return anything, for example "","abc" and "abc" will
be a "true".

Examples:
C = "abcde", A = "acd", B = "be", return true
C = "abcde", A = "adc", B = "be", return false  */


/* 思路：DP，这一题里动态规划矩阵的设计方式很巧妙 ！！！
搞一个二维的boolean矩阵，其中 dp[i][j] 的意思是：
运用 a里的第1个到第i个char，以及 b里的第1个到第j个char，任意交错搭配，是否有可能组成 c里的第1个到第(i+j)个char。  */

public class Solution {
  
  public boolean canMerge(String a, String b, String c) {
    if (c == null || c.length() == 0) {
        return true;
    }
    
    int aLen = a.length();
    int bLen = b.length();
    int cLen = c.length();
    
    if (aLen + bLen != cLen) {
      return false;
    }
    
    boolean[][] mergable = new boolean[aLen + 1][bLen + 1];
    
    // this is the only corner case that we need to set !!!
    mergable[0][0] = true;
    
    for (int i = 0; i <= aLen; i++) {
      for (int j = 0; j <= bLen; j++) {
        
        // 2 possible ways of matching the first i+j characters in c
        // 如果a里的第i个char 和 c里的第i+j个char 相同
        if (i > 0 && c.charAt(i + j - 1) == a.charAt(i - 1)) {
          if (mergable[i - 1][j]) {
            mergable[i][j] = true;
            continue;
          }
        }
        // 如果b里的第j个char 和 c里的第i+j个char 相同
        if (j > 0 && c.charAt(i + j - 1) == b.charAt(j - 1)) {
          if (mergable[i][j - 1]) {
            mergable[i][j] = true;
          }
        }
      }
    }
    
    return mergable[aLen][bLen];
  }
}
