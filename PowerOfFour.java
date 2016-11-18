/* Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
Example:
Given num = 16, return true. Given num = 5, return false.
Follow up: Could you solve it without loops/recursion? */

// 两种方法，都能解决任意数的power问题：
// Ref: https://discuss.leetcode.com/topic/66887/two-methods-to-find-if-a-number-is-a-power-of-any-number-n
public class Solution {

    /* Convert to base n and check the pattern "10*" appears in that base representation
    eg: 4 base 4 = 10
        16 base 4 = 100
        3 base 3 = 10 
        9 base 3 = 100
    */
    public boolean isPowerOfFour(int num) {
        
        /* Integer.toString(int i, int radix)
         Returns a string representation of the first argument in the "radix" (即base number, 基数) specified by the second argument.
         
         Java Regex (正则表达式)
         这里的 "10*" 里的 * 表示：把紧贴着*的前一个字符再重复0次或者1次或者多次
        */
        Return(Integer.toString(num,4).matches("10*"));
    }
    
    
    /* 将num与4对于同一个底数，比如10，来求log，前者的log是后者的整数倍的话意味着前者是后者的n次方 */
    public boolean isPowerOfFour(int num)
    {
        return ((Math.log10(num) / Math.log10(4)) % 1 == 0);
    }
    
}

