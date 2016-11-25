// Given an integer n, return the number of trailing zeroes in n!.
// Note: Your solution should be in logarithmic time complexity.

// Ref: https://discuss.leetcode.com/topic/6516/my-one-line-solutions-in-3-languages
// 很聪明的办法！Time: O(log(n))
/* Because all trailing 0 is from factors 5 * 2. 
But sometimes one number may have several 5 factors, for example, 25 have two 5 factors, 125 have three 5 factors.
In the n! operation, factors 2 is always ample. So we just count how many 5 factors in all number from 1 to n.
*/
public class Solution {
    
    public int trailingZeroes(int n) {
        
        if (n == 0) // 特殊处理，别忘了
            return 0; 
        return (n / 5) + trailingZeroes(n / 5); // 很聪明的递归！！
    }
    
    // 把上面的思路用 Iteration 处理
    public int trailingZeroes(int n) {
        
        if (n == 0)
            return 0; // 特殊处理！
        
        int result = 0;
        
        // 在当前的n里，最高有5的几次方
        // 注意：比如200里最高的是5的3次方125，200里只能最多有1个125；
        // 而100里最高25，100里能有3个25！分别是25,50和75
        int log5OfN = (int) (Math.log10(n) / Math.log10(5));
        // curPower就是上面说的，200里的那个125
        int curPower = (int) Math.pow(5, log5OfN);
        // 这个就是上面说的，200里最多出现几个125
        int occurrencesOfCurPower = n / curPower;
        
        // 每个125带来3个5
        // 每个25带来2个5
        // 每个5带来1个5
        // 我们从高次到低次，逐层计算125、25、5出现的次数
        // 关键在于！！125只算一次，25也只算一次，因为每个更低阶的5的次方的计数都会把它们算上
        for (int i = log5OfN; i >= 1; i--)
        {
            result += occurrencesOfCurPower;

            // 为更低一阶的5的次方的计算做准备
            curPower /= 5;
            occurrencesOfCurPower = n / curPower;
        }
        return result;
    }
}
