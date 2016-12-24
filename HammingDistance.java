/* The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
Given two integers x and y, calculate the Hamming distance.
Note: 0 ≤ x, y < 231.

Example:
Input: x = 1, y = 4
Output: 2
Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑
The above arrows point to positions where the corresponding bits are different. */

public class Solution 
{
    public int hammingDistance(int x, int y) 
    {
        int result = 0;
        
        int z = x ^ y; // 先异或一下，不同的位都成了1
        while(z != 0)
        {
            if ((z & 1) == 1) // &要加个括号！！否则执行顺序会出错！！
                result ++;
            z >>>= 1; // shift with +/- sign
        }
        return result;
    }
}
