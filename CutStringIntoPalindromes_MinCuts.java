/* Given a string, a partitioning of the string is a palindrome partitioning if every substring of the partition is a palindrome. 
Determine the fewest cuts needed for a palindrome partitioning of a given string.
Assumptions: The given string is not null

Examples:
“a | babbbab | bab | aba” is a palindrome partitioning of “ababbbabbababa”.
The minimum number of cuts needed is 3.    */


/* 方法1：DP


*/
public class Solution {

  public int minCuts(String input) {
    if (input == null || input.length() == 0) {
      return 0;
    }
    
    int n = input.length();
    int[] dp = new int[n];
    
    boolean[][] palinMatrix = fillInPalinMatrix(input);
    
    dp[0] = 0; // 1 char, no where to cut
    
    for (int i = 1; i < n; i++) {
      
      if (palinMatrix[0][i]) {
        dp[i] = 0;
        continue;
      }
      
      dp[i] = i; // i is natually the max possible value for dp[i]
      for (int j = 1; j <= i; j++) {
        
        if (palinMatrix[j][i]) {
          dp[i] = Math.min(dp[i], dp[j - 1] + 1);
        }
      }
    }
    return dp[n - 1];
  }
  
  private boolean[][] fillInPalinMatrix(String input) {
    char[] cArray = input.toCharArray();
    int n = cArray.length;
    boolean[][] palinMatrix = new boolean[n][n];
    
    for (int i = 0; i < n; i++) {
      palinMatrix[i][i] = true;
      
      int start = i - 1;
      int end = i + 1;
      
      while(start >= 0 && end < n) {
        if (cArray[start] == cArray[end] && palinMatrix[start + 1][end - 1]) {
          palinMatrix[start][end] = true;
        } else {
          palinMatrix[start][end] = false;
        }
        
        start --;
        end ++;
      }
    }
    
    for (int i = 0; i < n - 1; i++) {
      palinMatrix[i][i + 1] = (cArray[i] == cArray[i + 1]) ? true : false;
      
      int start = i - 1;
      int end = i + 2;
      
      while(start >= 0 && end < n) {
        if (cArray[start] == cArray[end] && palinMatrix[start + 1][end - 1]) {
          palinMatrix[start][end] = true;
        } else {
          palinMatrix[start][end] = false;
        }
        
        start --;
        end ++;
      }
    }
    
    return palinMatrix;
  }
}


// 方法2，比较原始一点，还是DP，检查 isPalindrome 用 char array 和 前后指针来做。无论如何不要用 substring 做！！！ 时间空间都 O(n) ！！！
// Time: O(n^3), 2 layer for-loops, and in each loop, we walk through the char array to check if it’s palindrome, 
// so another O(n) time
// Space: O(n), the DP array
public class Solution {
  
  public int minCuts(String input) {
    if (input == null || input.length() == 0) {
      return Integer.MIN_VALUE;
    }
    
    char[] cArray = input.toCharArray();
    int n = input.length();
    int[] dp = new int[n];
    dp[0] = 0; // 1 char, no where to cut
    
    for (int i = 1; i < n; i++) {
      
      if (isPalindrome(cArray, 0, i)) {
        dp[i] = 0;
        continue;
      }
      
      dp[i] = i; // i is natually the max possible value for dp[i]
      for (int j = 1; j <= i; j++) {
        
        if (isPalindrome(cArray, j, i)) {
          dp[i] = Math.min(dp[i], dp[j - 1] + 1);
        }
      }
    }
    return dp[n - 1];
  }
  
  private boolean isPalindrome(char[] cArray, int start, int end) {
    while (start < end) {
      if(cArray[start] != cArray[end]) {
        return false;
      }
      start ++;
      end --;
    }
    return true;
  }
}
