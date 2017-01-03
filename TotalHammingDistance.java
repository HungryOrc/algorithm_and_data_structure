/* The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
Now your job is to find the total Hamming distance between all pairs of the given numbers.

Example:
Input: 4, 14, 2. Output: 6
Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
showing the four bits relevant in this case). So the answer will be:
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.

Note:
Elements of the given array are in the range of 0 to 10^9
Length of the array will not exceed 10^4. */

public class Solution 
{
    // 笨办法。速度太慢
    public int totalHammingDistance(int[] nums) 
    {
        int n = nums.length;
        int xor = 0;
        int bitCount = 0;
        int sum = 0;
        
        for (int i = 0; i < n; i++)
        {
            for (int j = i+1; j < n; j++)
            {
                xor = nums[i] ^ nums[j];
                bitCount = countBit(xor);
                sum += bitCount;
            }
        }
        return sum;
    }
    private int countBit(int a)
    {
        int ones = 0;
        while (a > 0)
        {
            int thisDigit = a & 1;
            ones += thisDigit;
            a = a >>> 1;
        }
        return ones;
    }
    
}
