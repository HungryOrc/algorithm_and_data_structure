/* Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. 
Return the maximum product you can get.
For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
Note: You may assume that n is not less than 2 and not larger than 58.

// 很牛的解法解释。用了导数：
// Ref: https://discuss.leetcode.com/topic/43055/why-factor-2-or-3-the-math-behind-this-problem
// 当 n > 4 时，尽量取3为一份，能实现总乘积的最大化
// O(n)

Why factor 3 or 2? The math behind this problem:

For convenience, say n is sufficiently large and can be broken into any smaller real positive numbers. 
We now try to calculate which real number generates the largest product.
Assume we break n into (n / x) x's, then the product will be xn/x, and we want to maximize it.

Taking its derivative gives us n * xn/x-2 * (1 - ln(x)).
The derivative is positive when 0 < x < e, and equal to 0 when x = e, then becomes negative when x > e,
which indicates that the product increases as x increases, then reaches its maximum when x = e, then starts dropping.

This reveals the fact that if n is sufficiently large and we are allowed to break n into real numbers,
the best idea is to break it into nearly all e's.
On the other hand, if n is sufficiently large and we can only break n into integers, we should choose integers that are closer to e.
The only potential candidates are 2 and 3 since 2 < e < 3, but we will generally prefer 3 to 2. Why?

Of course, one can prove it based on the formula above, but there is a more natural way shown as follows.
6 = 2 + 2 + 2 = 3 + 3. But 2 * 2 * 2 < 3 * 3.
Therefore, if there are three 2's in the decomposition, we can replace them by two 3's to gain a larger product.

All the analysis above assumes n is significantly large. When n is <= 4, it may contain flaws.
For instance, when n = 4, we have 2 * 2 > 3 * 1.   */

public class Solution 
{
    public int integerBreak(int n) 
    {
        if (n==2) return 1;
        else if (n==3) return 2;
        else if (n==4) return 4;
        
        int maxProduct = 1;
        while (n > 4)
        {
            n -= 3;
            maxProduct *= 3;
        }
        maxProduct *= n; // 最后别忘了乘以n的残留值
        
        return maxProduct;
    }
}
