/* Given a rope with positive integer-length n, how to cut the rope into m integer-length parts with length p[0], p[1], ...,p[m-1], 
in order to get the maximal product of p[0]*p[1]* ... *p[m-1]? 
m is determined by you (m可以是任意正整数) and must be greater than 0 (at least one cut must be made). 
Return the max product you can have.

Assumptions: 
(1) 绳子的长度 n >= 2
(2) 切成m段，m是任意正整数

Examples:
n = 12, the max product is 3 * 3 * 3 * 3 = 81(cut the rope into 4 pieces with length of each is 3).   */


// 方法1：Recursion。很慢。但思想很重要。是接下来的DP等做法的思想基础
// 时间：高达 O(n^n) ！！！
public class Solution {
  
  public int maxProduct(int n) {
    if (n <= 1) {
      return 0;
    }
    
    int maxProduct = 0;
    
    for (int i = 1; i < n; i++) { // i: the length of the left-most section
      
      // n - i: 
      // keep the remaining (rightwards) part of the rope as a whole, namely un-cut
      // getMaxProduct(n - i): 
      // cut the remaining part once, or twice, or more...
      // this is also a sub-problem of the whole problem
      int maxProductOfTheRemainingRope = Math.max(n - i, maxProduct(n - i));
      
      maxProduct = Math.max(maxProduct, i * maxProductOfTheRemainingRope);
    }
   
    return maxProduct; 
  }
}
