public class Solution {
    public int backPack_UnknownQuestionNumber(int capacity, int m, int[] sizes, int[] values) {
        int n = sizes.length;
        
        int[][][] dp = new int[n][m + 1][capacity + 1];
        
        // base case：只取1个item的情况
        // 只取index=0的item
        if (sizes[0] <= capacity) { // 别忘了size不可超过capacity ！
            dp[0][1][sizes[0]] = values[0];
        }
        // 在index=0到i的items里取一个item
        for (int i = 1; i < n; i++) {
            if (sizes[i] <= capacity) { // 别忘了size不可超过capacity ！
                dp[i][1][sizes[i]] = Math.max(values[i], dp[i - 1][1][sizes[i]]);
            }
        }
        
        // 从第二个item（即i=1）开始
        for (int i = 1; i < n; i++) {
            int curSize = sizes[i];
            int curValue = values[i];
            
            // 从取2个items开始
            // 注意 ！ 别忘了，取几个items，不能超过现在能用的items有几个 ！！！
            // 现在能用的items是index=0到i的items，即有 i+1 个items可用，那么j就必须 <= i+1 ！！！
            for (int j = 2; j <= i + 1 && j <= m; j++) {
            
                for (int sum = 1; sum <= capacity; sum++) {
                
                    if (sum - curSize >= 0) { // 别忘了检查越界 ！！！
                        dp[i][j][sum] = Math.max(dp[i - 1][j][sum], 
                                                 dp[i - 1][j - 1][sum - curSize] + curValue);
                    } else {
                        dp[i][j][sum] = dp[i - 1][j][sum]; // 这种情况下就不加后面那项了 ！！！
                    }
                }
            }
        }
        
        int maxTotalValue = 0;
        for (int sum = 1; sum <= capacity; sum++) {
            for (int j = 1; j <= m; j ++) {
                maxTotalValue = Math.max(maxTotalValue, dp[n - 1][j][sum]);
            }
        }
        return maxTotalValue;
    }
}
