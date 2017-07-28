/* Given a string, a partitioning of the string is a palindrome partitioning if every substring of the partition is a palindrome. 
Determine the fewest cuts needed for a palindrome partitioning of a given string.
Assumptions: The given string is not null

Examples:
“a | babbbab | bab | aba” is a palindrome partitioning of “ababbbabbababa”.
The minimum number of cuts needed is 3.    */


/* 方法1：DP
DP 数组 dp[i] 表示：对于input string，它的substring [0, i]（两头都inclusive），
最少需要多少刀能切成 palindrome partitions。
dp[0] 一定是 0，因为dp[0]表示只有一个char的substring，即第一个char。这时无处可切，必然是0刀，
且就一个char必然为palindrome，所以也不用切。所以还是必然是0刀。
然后我们不断地向右边延长，逐步考察 dp[1]（即2个char）、dp[2]（即3个char）、dp[3] ... dp[input.length() - 1]。

对于 dp[i]，
我们首先看，字符串 [0, i] 是否本身就是一个 palindrome，如果是，就把它填为 0，然后下面都不管了，去到下一个，dp[i + 1]。
如果 [0, i] 不是palindrome，那么 dp[i] 的值最多为 i，
因为 dp[i] 指的是长度为 i+1 的substring，把它切成一个一个char的话，一定每一段都是palindrome，这样一共需要 i 刀。先记录下这个默认的最大值。
然后，搁置i的这个最大值，我们要求dp[i]的最小值，
我们把 [0, i] 切成两部分来看（所以中间这里就要至少切一刀了）：
右半边，[j, i]，其中 1 <= j <= i，我们要看 [j, i] 这一段是不是palindrome（见后面的详述）。
这里 j 最小也得是1，不能是0，因为必须得给左半段留下至少 1 的长度，即1个char。
左半边，[0, j - 1]，要利用之前填写 dp 数组的现成结果，直接调用 dp[j - 1]。
然后综合起来，
dp[i] 先填入最大的默认值 i，
然后对于每一个可能的 j 值，如果[j, i]是palindrome，则 dp[i] = min(dp[i], dp[j - 1] + 1)；
如果[j, i]不是palindrome，则不做任何事，看下一个j    

时间：O(n^2)
其实是 O(n^2) + O(n^2)，前一个是主函数的双层for-loop；后一个是填写boolean matrix的过程
空间：O(n^2)
其实是 O(n) + O(n^2)，前一个是主函数的 DP数组；后一个是helper function的boolean matrix，它其实也是一个 DP matrix   */

public class Solution {

  public int minCuts(String input) {
    if (input == null || input.length() == 0) {
      return Integer.MIN_VALUE;
    }
    
    // 先把准备工作做好，研究好input string的所有可能的substring，分别有哪些是palindrome
    boolean[][] palinMatrix = fillInPalinMatrix(input); 
    
    int n = input.length();
    int[] dp = new int[n];
    dp[0] = 0; 
    
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
  
  /* helper function, fill in the n*n boolean matrix, in which the cell matrix[i][j] represents if
  input string 的第 i 个 char 到第 j 个 char （both inclusive）所组成的substring是不是palindrome
  这相当于把一个一维问题化成了二维问题 ！！！ 但其实是简化了，而不是复杂化了 ！！！
  
  对于任何 substring[i, j]，看它是不是palindrome，就看以下2个条件 ！！！
  首先看i和j这两个char是否相等，
  然后看substring[i-1, j+1]是否是palin。
  然后这么往中间缩下去追溯的base case最后有2种，要么缩成一个char，要么缩成相邻的2个char。下面代码分别作了处理。
  
  为了不必记录这个 boolean matrix 里面哪些元素是填过的，哪些是没填的，我很聪明地分别从两种base case开始填，从中间往两边扩 ！！！ */
  
  private boolean[][] fillInPalinMatrix(String input) {
    char[] cArray = input.toCharArray();
    int n = cArray.length;
    boolean[][] palinMatrix = new boolean[n][n];

    // base case 1: palindrome的核心是一个char，即它拓展出来的每个substring[i, j]都有奇数个字符
    for (int i = 0; i < n; i++) {
      palinMatrix[i][i] = true;

      int start = i - 1;
      int end = i + 1;

      while(start >= 0 && end < n) {
        if (cArray[start] == cArray[end] && palinMatrix[start + 1][end - 1]) {
          palinMatrix[start][end] = true;
          start --;
          end ++;
        } else {
          break;
        }
      }
    }

    // base case 2: palindrome的核心是一对相邻的chars，即它们拓展出来的每个substring[i, j]都有偶数个字符
    for (int i = 0; i < n - 1; i++) {
      palinMatrix[i][i + 1] = (cArray[i] == cArray[i + 1]) ? true : false;

      if (palinMatrix[i][i + 1] == false) {
          continue;
      }

      int start = i - 1;
      int end = i + 2;

      while(start >= 0 && end < n) {
        if (cArray[start] == cArray[end] && palinMatrix[start + 1][end - 1]) {
          palinMatrix[start][end] = true;
          start --;
          end ++;
        } else {
          break;
        }
      }
    }

    return palinMatrix;
  }
}


// 方法2，比较原始一点，还是DP。有上面的方法，这个方法其实不用再看了。只是列在这里，备查，知道最朴素的做法在哪里。

// 检查 isPalindrome 用 char array 和 前后指针来做。无论如何不要用 substring 做！！！ 时间空间都 O(n) ！！！
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
