/* Count the number of prime numbers less than a non-negative number, n.
A prime number is a whole number greater than 1, 
whose only two whole-number factors are 1 and itself. 
The first few prime numbers are 2, 3, 5, 7, 11, 13, 17, 19, 23, and 29.
*/

public class Solution 
{
    //Ref: https://leetcode.com/problems/count-primes/
    public class Solution {
        public int countPrimes(int n) {
            boolean[] notPrime = new boolean[n];
            int count = 0;
            for (int i = 2; i < n; i++) {
                if (notPrime[i] == false) {
                    count++;
                    for (int j = 2; i*j < n; j++) {
                        notPrime[i*j] = true;
                    }
                }
            }
            return count;
        }
    }


    // 我的更直观的算法。但要慢不少
    public int countPrimes(int n) 
    {
        if(n <= 1)
            return 0;
        
        int[] oneToN = new int[n-1]; // 题意要求是小于n的质数
        // 把每个元素都填上1到n这些数。之后看到谁不是质数，就把那一个对应元素置为0
        for (int i = 0; i < n-1; i++)
            oneToN[i] = i+1;
            
        // 注意！质数要大于1！即1不算是质数！所以把第一个元素置为0
        oneToN[0] = 0;
        
        for (int j = 2; j <= n/2; j++)
        {
            // 第一个j是质数，要留下，不能置为0
            // 比如第一个2是质数，然后后面的4,6,8,10...都不是质数，从4开始都置为0
            int curNum = j*2;
            while (curNum <= n-1)
            {
                oneToN[curNum-1] = 0;
                curNum += j;            
            }
        }
        
        int result = 0;
        for (int num : oneToN)
        {
            if (num != 0)
                result ++;
        }
        return result;
    }

}
