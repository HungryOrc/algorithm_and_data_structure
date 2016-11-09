/* Given an integer, write a function to determine if it is a power of three.
Follow up: Could you do it without using any loop / recursion?   */

public class Solution {
    
    // 自然想法：不断地除以 3，最后得 1 则为true，否则为false
    public boolean isPowerOfThree(int n) {
        
        if (n <= 0)
            return false;
        
        while (n % 3 == 0)
            n = n / 3;
            
        if (n != 1)
            return false;
        else
            return true;
    }
    
    
    // Ref: https://leetcode.com/articles/power-of-three/
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
