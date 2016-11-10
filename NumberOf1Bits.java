/* Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).
For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.
*/

// you need to treat the input int n as an unsigned value
public class Solution {
    
    // n & 1 然后 >>> 1
    // ？疑问？我写 (n & 1) == 1 这样的语句会报错，不知为何？？？
    // ？？？
    public int hammingWeight(int n) {
            
        int numOfOnes = 0;
        while (n != 0)
        {
            numOfOnes += (n & 1);
            n = n >>> 1;
        }
        return numOfOnes;
    }
    
    // n - 1 然后 并 n，这样操作几次n到0，则意味着n含有几个1
    public int hammingWeight(int n) {
            
        int numOfOnes = 0;
        while (n != 0)
        {
            n = (n-1) & n;
            numOfOnes++;
        }
        return numOfOnes;
    }
    
}
