/* Count the number of prime numbers less than a non-negative number n.
A prime number is a whole number greater than 1, 
whose only two whole-number factors are 1 and itself. 
The first few prime numbers are 2, 3, 5, 7, 11, 13, 17, 19, 23, and 29.
*/

public class Solution 
{
    //Ref: https://leetcode.com/problems/count-primes/
    public int countPrimes(int n) 
    {
        // 数组长度为n，index为0到n-1。这个算法是直接用index i 表示数字 i
        // 同时弃置不用i=0那一位。所以这个方法会表示的数字范围是 1到n-1，正好是题意要的范围
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) 
        {
            if (notPrime[i] == false) // 当前数是质数
            {
                count++;
                // 注意！！我们只把质数的整数倍置为非质数！！
                // 非质数的整数倍不用管！！因为它们含在质数的整数倍里了！！
                for (int j = 2; i*j < n; j++) {
                    notPrime[i*j] = true;
                }
            }
        }
        return count;
    }
}
