
public class Solution {
     
    public int backPack(int capacity, int[] sizes) {
        int n = sizes.length;
        
        int[][] dp = new int[n][capacity + 1];
        
        // base case 1
        for (int i = 0; i < n; i++) {
            dp[0][i] = Integer.MAX_VALUE;
        }
        if (sizes[0] <= capacity) {
            dp[0][sizes[0]] = 1;
        }
        
        // base case 2
        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }
        
        // 从第二个item（即i=1）开始
        for (int i = 1; i < n; i++) {
            int curItemSize = sizes[i];
            
            for (int sum = 1; sum <= capacity; sum++) {
                
                if (sum - curItemSize >= 0 && // 别忘了检查越界 ！！！
                    dp[i - 1][sum - curItemSize] != Integer.MAX_VALUE) { // 如果等于正无限，就别再 +1 了 ！！！
                    dp[i][sum] = Math.min(dp[i - 1][sum], dp[i - 1][sum - curItemSize] + 1);
                } else {
                    dp[i][sum] = dp[i - 1][sum]; // 这种情况下就不加后面那项了 ！！！
                }
            }
        }
        
        return dp[n - 1][capacity];
    }
}
