// Power of 3 和 Power of 2 是一样的 ！！！

/* Given an integer, write a function to determine if it is a power of three.
Follow up: Could you do it without using any loop / recursion?   */

public class Solution {
    
    // 方法1：用 Recursion。不要一个一个3地除下去！！ 那样太慢 ！！
    public boolean isPowerOfThree(int n) {
        if (n == 3 || n == 1) {
            return true;
        } else if (n == 0) {
            return false;
        }
        
        return (n % 3 == 0) && (isPowerOfThree(n / 3));
    }
    
    
    // 方法2：用 Iteration，思路与上面的recursion是一样的
    public boolean isPowerOfTwo(int n) {
        if (n < 1) {
            return false;
        }
        for (int i = n; i > 1; i /= 2) {
            if (i % 2 != 0) {
                return false;
            }
        }
        return true;
    }
    
    
    // 方法3：如果是要求 power of 2，那么正好用 2进制数的性质，2的幂次方一定是只有一个1，其它位数都是0。
    // 那么我们可以用 x 与 x - 1 之间求AND来判断，因为，比如：
    //     x = 0 0 1 0 0 0 0 0 
    // x - 1 = 0 0 0 1 1 1 1 1
    // 可见只要 x 是2的power，则上面两个数之间取AND一定得到 0 ！！！
    public boolean isPowerOfTwo(int x) {
        return (x & (x - 1) == 0) && (x != 0);
    }
    
    
    // 方法4，Ref: https://leetcode.com/articles/power-of-three/
    /* let n = 3^i, then we have:
     i = log_3(n) => i = log_10(n) / log_10(3)
     n is a power of 3 if and only if i is an integer. 
     In Java, we check if a number is an integer by taking the decimal part (using % 1) and checking if it is 0. 
    */
    public boolean isPowerOfThree (int n)
    {
       double i = Math.log10(n) / Math.log10(3);
       return (i % 1 == 0);
    }
     
}
