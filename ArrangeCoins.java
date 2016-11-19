/* You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
Given n, find the total number of full staircase rows that can be formed.
n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1: n = 5
The coins can form the following rows:
¤
¤ ¤
¤ ¤
Because the 3rd row is incomplete, we return 2.

Example 2: n = 8
The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤
Because the 4th row is incomplete, we return 3.
*/

public class Solution {
    
    // 利用等差数列求和公式：1+2+3+...+m = m*(m+1)/2
    // Time: O(1), beat 90% answers
    public int arrangeCoins(long n) {
        
        int maxSqrtRoot = (int)(Math.sqrt(n * 2));
        
        // 注意！这里做乘法要暂时把两个乘数换成long，因为这样才能保证结果是long，才能保证不会丢位数
        // 否则两个乘数都是int，则结果一定只能是int，就很有可能丢位数，因为题意中说n最大会到32bit
        if ((long)maxSqrtRoot * (long)(maxSqrtRoot + 1) <= n * 2)
            return maxSqrtRoot;
        else
            return maxSqrtRoot - 1;
        
    }
}
