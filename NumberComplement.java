/* Given a positive integer, output its complement number. 
The complement strategy is to flip the bits of its binary representation.
Note:
1. The given integer is guaranteed to fit within the range of a 32-bit signed integer.
2. You could assume no leading zero bit in the integer’s binary representation.

Example 1:
Input: 5
Output: 2
Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.

Example 2:
Input: 1
Output: 0
Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.

很巧妙的方法。用了内置函数 Integer.highestOneBit(num)
Ref: https://discuss.leetcode.com/topic/74642/java-1-line-bit-manipulation-solution

According to the problem, the result is:
1. The flipped version of the original input but
2. Only flip N bits within the range from LEFTMOST bit of 1 to RIGHTMOST.
For example input = 5 (the binary representation is 101), the LEFTMOST bit of 1 is the third one from RIGHTMOST (100, N = 3). 
Then we need to flip 3 bits from RIGHTMOST and the answer is 010

To achieve above algorithm, we need to do 3 steps:
1. Create a bit mask which has N bits of 1 from RIGHTMOST. In above example, the mask is 111. 
And we can use the decent Java built-in function Integer.highestOneBit to get the LEFTMOST bit of 1, 
left shift one, and then minus one. 
Please remember this wonderful trick to create bit masks with N ones at RIGHTMOST, you will be able to use it later.
2. Bit-wise Complement the whole input number, which means "~"
3. Bit AND (&) numbers in step 1 and 2.   */

public class Solution 
{
    public int findComplement(int num) 
    {
        // Please remember this wonderful trick to create bit masks with N ones at RIGHTMOST
        // Use Integer.highestOneBit to get the LEFTMOST bit of 1, left shift one, and then minus one
        int mask = (Integer.highestOneBit(num) << 1) - 1;
        
        // ~ is bitwise compliment，补
        // Binary Ones Complement Operator is unary and has the effect of 'flipping' bits
        // ex: ~00111100 will give -61 which is 11000011 in 2's complement form due to a signed binary number
        num = ~num;
        
        return num & mask;
    }

}
