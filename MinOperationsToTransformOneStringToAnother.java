/* Given two strings of alphanumeric characters, determine the minimum number of Replace, Delete, and Insert operations 
needed to transform one string into the other.
Assumptions: Both strings are not null

Examples:
string one: “sigh”, string two : “asith”
the edit distance between one and two is 2 (one insert “a” at front then replace “g” with “t”). */


/* 方法1：Recursion
要通过4种操作，把string one 变成 string two。我们可以从string one 的第一个char开始，逐步往右走。
每一步都试验所有的4种操作，最后要提交的是4种操作即4种分支里面最短的一种 */

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
