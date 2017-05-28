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


/* 方法2：DP。把绳子看成左右两大部分。每一部分都会被切 0次 到 若干次。最后的product是这两部分的product的product
这个思想是继承于上面的 recursion 思想的

1米长的rope：没法切，只能切0刀，即不切
    DP[1] = 0;
2米长的rope：只可能是中间切1刀，左边1米，右边1米
    DP[2] = max(1, DP[1]) * max(1, DP[1]) = 1 * 1 = 1
    每一个max里面，首先是这一部分一刀也不切的情况，然后是这一部分切至少一刀的情况下能得到的最大product，后者正是前一个sub-problem解决过的问题
3米长的rope：把它分成两部分，有2种分法，要么左1米右2米，要么左2米右1米
    DP[3]_case1 = max(1, DP[1]) * max(2, DP[2])
    DP[3]_case2 = max(2, DP[2]) * max(1, DP[1])
    易见其中的 DP[1] 和 DP[2] 都是前面的几个 sub-problem 解决过的
    然后可见，这2种分法，其实是一样的，左1右2和左2右1。易知，分段长度 >= 绳子长度的一半（最多再+1）的时候，就不必继续分了
    然后最后汇总：
    DP[3] = max(DP[3]_case1, DP[3]_case2) = max(2, 2) = 2
 4米长的rope：把她分成两部分，有3种分法：左1右3，左2右2，左3右1。然后按上面的分析，左3右1与左1右3其实是一样的，所以左3右1就不用再做了
    DP[4]_case1 = max(1, DP[1]) * max(3, DP[3])
    DP[4]_case2 = max(2, DP[2]) * max(2, DP[2])
    可见，这里面的 DP[1], DP[2] 和 DP[3] 也都是之前就出现过的sub-problems的答案
    汇总：
    DP[4] = max(DP[4]_case1, DP[4]_case2) = max(3, 4) = 4
 按这个分法，可以不断得到长度为任意正整数的绳子的最佳切分方式
 
 在这个过程中，还有一个很重要的观察：
 我们设置的DP数组 DP[i]，它的填充过程，在这一种方法下，是自然而然的从 i=0 到 i=1 到 i=2 到 i=3...
 所以我们不必考察这个数组里的任何元素之前是否被填充过，也不必把这个数组里的所有元素初始化为 0 或者 -1 之类的占位值，
 因为它的填充过程自然而然就不会出现要填的时候需要的组成部分还没有被赋值的情况 ！  */
    
