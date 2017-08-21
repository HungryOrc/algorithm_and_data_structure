/* You are climbing a stair case. It takes n steps to reach to the top.
Each time you can either climb 1, 2 or 3 steps. In how many distinct ways can you climb to the top? 
注意！比如走上高度为3的台阶的话，先走1后走2，和先走2后走1，是两种不同的方法！！
那么走3一共有4种方法：
先走1再走1最后走1，先走1再走2，先走2再走1，一步走3   */

public class Solution {
    
    // DP
    // 这样的题当然也可以用recursion做，但是会慢无数倍
    public int climb(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 0) {
            // 就是一种方法：不走，所以 return 1
            // 这里return 1，并不光是耍嘴皮子，而是有切实意义的 ！！！
            // 比如要算n=3的时候，如果一开始在n=0的时候设为0，则n=3的时候dp[n-3]这一项就为0了，
            // 但是我们是可以从0到3直接走三步达到3的，这一种可能性必须存续下来，即dp[n-3]在n=3的时候必须是1，即dp[0]必须是1 ！！！
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        
        // dp数组的意思是，走到n这么高的台阶，一共能有多少种不同的走法
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }
}
