/* Given a string, a partitioning of the string is a palindrome partitioning if every substring of the partition is a palindrome. 
Determine the fewest cuts needed for a palindrome partitioning of a given string.
Assumptions: The given string is not null

Examples:
“a | babbbab | bab | aba” is a palindrome partitioning of “ababbbabbababa”.
The minimum number of cuts needed is 3.    */


// 方法2，比较原始一点，还是DP，检查 isPalindrome 用 char array 和 前后指针来做。无论如何不要用 substring 做！！！ 时间空间都 O(n) ！！！
public class Solution {
  
  public int minCuts(String input) {
    if (input == null || input.length() == 0) {
      return 0;
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
