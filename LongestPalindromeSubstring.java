/* Given a string S, find the longest palindromic substring in S.

Assumptions:
There exists one unique longest palindromic substring.    
The input S is not null.

Examples:
Input: "abbc", Output: "bb"
Input: "abcbcbd", Output: "bcbcb"   */


/* 方法1：二维DP
The time complexity can be reduced by storing results of subproblems. 
We maintain a boolean table[n][n] that is filled in bottom up manner. 
The value of table[i][j] is true, if the substring is palindrome, otherwise false. 
To calculate table[i][j], we first check the value of table[i+1][j-1], if the value is true and str[i] is same as str[j], 
then we make table[i][j] true. Otherwise false.
时间：O(n^2)，空间：O(n^2)   */

public class Solution {
  
  public String longestPalindrome(String s) {
    if (s == null || s.length() == 0) {
      return "";
    } else if (s.length() == 1) {
      return s;
    }
    
    int n = s.length();
    int maxLen = 1;
    int start = 0, end = 0;
    boolean[][] isPalin = new boolean[n][n];
    
    for (int i = 0; i < n; i++) {
      isPalin[i][i] = true;
    }
    for (int i = 0; i < n - 1; i++) {
      isPalin[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
      if (maxLen == 1 && isPalin[i][i + 1] == true) {
        maxLen = 2;
        start = i;
        end = i + 1;
      }
    }
    
    for (int len = 3; len <= n; len++) {
      for (int i = 0; i <= n - len; i++) {
        int j = i + len - 1;
        
        if (s.charAt(i) == s.charAt(j) && isPalin[i + 1][j - 1]) {
          isPalin[i][j] = true;
          
          if (len > maxLen) {
            maxLen = len;
            start = i;
            end = j;
          }
        }
      }
    }
    
    return s.substring(start, end + 1);
  }
}


// 方法2：从中间开始，往两边找。先找中间是1个点的，再找中间是2个点的
// 每次往两边扩，左-1，右+1，看还是不是palindrome。如果不是，这个中心点就不必再继续下去了。看下一个中心点
// 这样做法看起来暴力，但时间也是 O(n^2)，和DP方法一样。其实没有任何重复查找，所以不必记忆化任何中间信息 ！！！

