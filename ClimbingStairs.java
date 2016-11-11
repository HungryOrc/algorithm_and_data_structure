/* You are climbing a stair case. It takes n steps to reach to the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top? */

public class Solution {
    
    public int climbStairs(int n) {
    
        int maxNumOfTwo = n / 2;
        int numOfWays = 0;
        
        // i 表示：当前的走法有几个2
        for (int i = 0; i <= maxNumOfTwo; i++)
        {
            // 这个数表示：当前的走法一共走几步（每一步是1或2）
            int curNumOfClimbs = i + (n - i*2);
            numOfWays += combination_MOutOfN(i, curNumOfClimbs);
        }
        return numOfWays;
    }
    
    private int combination_MOutOfN(int m, int n)
    {
        // 后面的除法可能出现小数
        double numOfCombinations = 1;
        
        if (m > 0)
        {
            // 乘和除交替进行， 以确保 numOfCombinations 不会超出int的范围
            // 如果不这么做，甚至long的范围都会超出，因为几个两位数连乘的结果极大
            // 用double是因为，最后的结果一定是int，但在轮番乘除的过程中的每一个中间结果，很可能出现小数
            // 每一个中间结果都是int的可能性有，但不大
            for (int i = 1; i <= m; i++)
            {
                numOfCombinations *= (n + 1 - i);
                numOfCombinations /= i;
            }
        }
        return (int)numOfCombinations;
    }
    
}
