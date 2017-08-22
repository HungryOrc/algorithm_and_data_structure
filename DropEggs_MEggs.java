/* 理解这题可以首先看看 DropEggs_双蛋 以及 三蛋 那两题。

There is a building of n floors. If an egg drops from the k th floor or above, it will break. 
If it's dropped from any floor below, it will not break.
You're given m eggs, Find k while minimize the number of drops for the worst case. 
Return the number of drops in the worst case.

Example:
Given m = 2, n = 100 return 14
Given m = 2, n = 36 return 8 */

public class Solution {
    
    // DP。非常巧妙 ！！！
    // m eggs, n floors
    public int dropEggs2(int m, int n) {
        
        // dp[i][j] 的意思是 用i个eggs，测试j层楼梯，最坏的情况下，最少需要摔多少次egg
        int[][] dp = new int[m + 1][n + 1];

        // 一共只有 0层 和 1层 的情况
        for (int i = 1; i <= m; i++) {
            dp[i][0] = 0;
            dp[i][1] = 1;
        }
     
        // 只有一个蛋的情况
        for (int j = 1; j <= n; j++) {
            dp[1][j] = j;
        }

        for (int i = 2; i <= m; i++) {
            for (int j = 2; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                
                for (int k = 1; k <= j; k++) {
                    
                    // 精华在此 ！！！
                    // 使用第i个egg，在第k层扔了一次，然后有两种后果：
                    // 第一种后果，蛋碎了，那么就需要在 1到k-1 层之间找，即一共是k-1层，但是蛋只剩下 i-1个了，
                    // 所以这一项是 dp[i - 1][k - 1]；
                    // 第二种后果，蛋没碎，那么就需要在 k+1层到j层之间找，即一共是j-k层，这时蛋还有i个的，因为没碎嘛，
                    // 所以这一项是 dp[i][j - k]。
                    
                    // 特别注意 ！！！
                    // 用 i个蛋判断 1到100层，和用 i个但判断 101到200层，所需要的次数是  相  同  的 ！！！
                    
                    dp[i][j] = Math.min(dp[i][j], 1 + Math.max(dp[i - 1][k - 1], dp[i][j - k]));
                }
            }
        }
        return dp[m][n];
    }
}
