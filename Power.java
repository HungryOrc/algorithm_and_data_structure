/* 不要小看这一题！要做到最优解，时间空间都是logn，是有一些地方要小心的 ！！！

Evaluate a to the power of b, assuming both a and b are integers and b is non-negative. 
Corner Cases:
What if the result is overflowed? We can assume the result will not be overflowed when we solve this problem on this online judge.

Examples:
power(2, 0) = 1
power(2, 3) = 8
power(0, 10) = 0
power(-2, 5) = -32 */

// Time: O(logb)
// Space: O(logb)

public class Solution {
  
  public long power(int a, int b) {
    if (a == 0 || a == 1) {
      return a;
    }
    
    if(b == 0) {
      return 1;
    }
    
    // 一步将 T(n) 变成 T(n/2) ！！！看似弱智，其实未必能想到的 ！！！
    // 幂 b 的演变过程：1000 -> 500 -> 250 -> 125 -> 62 -> 31 -> 15 -> 7 -> 3 -> 1 -> 0
    long half = power(a, b / 2);
    
    if (b % 2 == 0) {
      return half * half;
    } else {
      return half * half * a;
    }
  }
  
}
