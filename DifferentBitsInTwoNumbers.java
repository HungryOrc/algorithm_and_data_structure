/* Determine the number of bits that are different for two given integers.

Examples: 5(“0101”) and 8(“1000”) has 3 different bits */

// 特别注意！a 或者 b 有可能是 负数 ！！！
public class Solution {
  
  public int diffBits(int a, int b) {
    
    int c = a ^ b; // 如果a与b一正一负，则c的最左一位必为 1 ！！！

    int count = 0;
    
    while (c != 0) { // 这里要用 != 0，而非 > 0 ！ 因为c一开始可能就是 < 0 的（在a与b一正一负的情况下）！！！
      count += (c & 1); // 看c当前的个位是否为 1
      c = c >>> 1; // 这里用带符号的 >>>，而非不带符号的 >>。如果是后者，则如果c为负数，c>>的过程中c的最左位永远是1 ！！！
    }
    return count;
  }
  
}
