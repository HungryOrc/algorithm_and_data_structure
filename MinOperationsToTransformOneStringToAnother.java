/* Given two strings of alphanumeric characters, determine the minimum number of Replace, Delete, and Insert operations 
needed to transform one string into the other.
Assumptions: Both strings are not null

Examples:
string one: “sigh”, string two : “asith”
the edit distance between one and two is 2 (one insert “a” at front then replace “g” with “t”). */


/* 方法1：Recursion
要通过4种操作，把string one 变成 string two。我们可以从string one 的第一个char开始，逐步往右走。
每一步都试验所有的4种操作，最后要提交的是4种操作即4种分支里面最短的一种

时间：O(4^(2n))，4 是因为有4种操作即4个分支，2n 是因为 ？？？？？？？？？？？？？  */
public class Solution {
  
  public int editDistance(String one, String two) {
    
    // base cases
    if (one.isEmpty()) {
      return two.length();
    }
    else if (two.isEmpty()) {
      return one.length();
    }
    
    // 4 Scenarios:
    
    // S1. one.charAt(0) == two.charAt(0), so we need to do nothing
    int doNothing = Integer.MAX_VALUE; // we must make it positive-infinite, in case S1 did not happen
    if (one.charAt(0) == two.charAt(0)) {
      doNothing = editDistance(one.substring(1), two.substring(1));
    }
    
    // S2. if we do a Replace at the head of word1, to make one.charAt(0) == two.charAt(0)
    int replace = 1 + editDistance(one.substring(1), two.substring(1));
    
    // S3. if we do a Deleted at the head of word1
    int delete = 1 + editDistance(one.substring(1), two);
    
    // S4. if we do an Insert at the head of word1, to make one.charAt(0) == two.charAt(0)
    int insert = 1 + editDistance(one, two.substring(1));
    
    // return the best choice of this step among the 4 choices
    return Math.min(Math.min(doNothing, replace), Math.min(delete, insert));
  } 
}


/* 方法2：DP
建立一个二维的dp数组，dp[i][j] 的意思是 把word1的前i个字符 转化成 word2的前j个字符 所需的最少的操作次数。
注意，这里i是前i个字符，所以i=0的时候，指word1里的第0个字符，即没有字符，而不是第1个字符，这样做是为了边界条件容易做。
这样的话，我们很容易填充这个二维数组的边界：
二维数组的第1行即i=0的那一行，dp[0][j] = j，因为从0个字符到j个字符，必然最小需要j次insert。
二维数组的第1列即j=0的那一列，dp[i][0] = i，因为从0个字符到i个字符，必然最小需要i次insert。
上面的这些包括了最左上角的那一格，即 dp[0][0] = 0.

然后其他内部的格子，就需要依据边界条件以及 induction rules 来填充了。填充方向是每一行从左向右，然后逐行向下。
此时可见，dp matrix里的每一个cell的值，取决于它的左上角，正上方，左方三个“前导”cells的值 ！

induction rules 一共分为4种场景，类似于上面的recursion方法里的规则。
场景1. 如果one.charAt(i-1)==two.charAt(j-1)，则可以采用do nothing，则：dp[i][j] = dp[i-1][j-1]
   如果不满足上面这个字符相等的条件，则不能采用do nothing的处理，这里就什么都不做
场景2. 采用 delete，把word1里的第i个char删去，则：dp[i][j] = 1 + dp[i-1][j]
场景3. 采用insert，在word1的第i个char的右边添加一个与word2的第j个char相同的char，则：dp[i][j] = 1 + dp[i][j-1]
场景4. 采用replace，把word1的第i个char替换成word2的第j个char，则：dp[i][j] = 1 + dp[i-1][j-1]
每一步，都看这4个场景里，那一个发展下去的支路的总消耗最小，就采用哪个支路

时间：O(m*n)   */

public class Solution {
  
  public int editDistance(String one, String two) {
    
    int[][] distance = new int[one.length() + 1][two.length() + 1];
    
    for (int i = 0; i <= one.length(); i++) {
      for (int j = 0; j <= two.length(); j++) {
        
        if (i == 0) {
          distance[i][j] = j;
        } else if (j == 0) {
          distance[i][j] = i;
        }
        
        else if (one.charAt(i - 1) == two.charAt(j - 1)) {
          distance[i][j] = distance[i - 1][j - 1];
        } else {
          distance[i][j] = Math.min(Math.min(
            distance[i - 1][j] + 1,
            distance[i][j - 1] + 1),
            distance[i - 1][j - 1] + 1);
        }
      }
    }
    return distance[one.length()][two.length()];
  } 
}
