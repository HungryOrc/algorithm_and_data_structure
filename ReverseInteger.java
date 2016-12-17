/* Reverse digits of an integer.
Example1: x = 123, return 321
Example2: x = -123, return -321

Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, 
then the reverse of 1000000003 overflows. How should you handle such cases?
Assume that your function returns 0 when the reversed integer overflows. */

public class Solution 
{
    public int reverse(int x) 
    {
        // 考虑到x可能是负数
        boolean isNegative = false;
        if (x < 0)
            isNegative = true;
        
        x = Math.abs(x);
        
        // 考虑到倒序的过程中可能溢出 int 的范围
        long result = 0;
        while (x > 0)
        {
            int curDigit = x % 10;
            x = x / 10;
            
            result *= 10;
            result += curDigit;
        }
        
        if (isNegative == true)
            result *= -1;
        // 按题意要求，溢出则返回0
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
            return 0;
        return (int)result;
    }
}
