// Given a random generator random5(), the return value of random5() is 0 - 4 with equal probability. 
// Use only random5() to implement random7().

/* 思路：先用Random 5 搞两次，生成等概率的 Random 25，再从 Random 25 里选取 Random 7
后面这一步还是有一个trick的。如果从25里选取前7个，后面的18个都放弃，则数据利用率低，等待率高
更巧妙的办法是：25里的后面4个放弃掉，前面的21个对7取余数，返回这个余数 ！！！
这样的话，数据利用率高达 21/25，等待率降低到 4/25     */

public class Solution {
  
  public int random7() {
    int rand = Integer.MAX_VALUE; 
    
    // make sure we finally achieve: 0 <= rand <= 20, so it'll be 21 possible values in all
    do {
      rand = 5 * (int)(Math.random() * 5) + (int)(Math.random() * 5); // [0, 24)
    }
    while (rand > 20);
    
    return rand % 7;
  }
  
}
