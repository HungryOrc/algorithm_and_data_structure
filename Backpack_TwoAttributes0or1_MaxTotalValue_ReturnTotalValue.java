
public class Solution {     
    public int backPack(int maxSize, int maxWeight, int[] sizes, int[] weights, int[] values) {
        // corner cases ！！！！！！！！
        
        int n = sizes.length;
        int[][][] dp = new int[n][maxSize + 1][maxWeight + 1];
        
        // base case
        if(sizes[0] <= maxSize && weights[0] <= maxWeight) {
            dp[0][sizes[0]][weights[0]] = values[0];
        }
        
        // 从第二个item（即i=1）开始
        for (int i = 1; i < n; i++) {
            int curSize = sizes[i];
            int curWeight = weights[i];
            
            for (int sumS = 1; sumS <= maxSize; sumS ++) {
                for (int sumW = 1; sumW <= maxWeight; sumW ++) {
                
                    if (sumS - curSize >= 0 && sumW - curWeight >= 0) { // 别忘了检查越界！
                        dp[i][sumS][sumW] = Math.max(dp[i - 1][sumS][sumW], 
                                                     dp[i - 1][sumS - curSize][sumW - curWeight] + values[i]);
                    } else {
                        dp[i][sumS][sumW] = dp[i - 1][sumS][sumW]; // 这种情况下就不加后面那项了！
                    }
                }
            }
        }
        
        int maxTotalValue = 0;
        for (int sumS = 1; sumS <= maxSize; sumS ++) {
            for (int sumW = 1; sumW <= maxWeight; sumW ++) {
                maxTotalValue = Math.max(maxTotalValue, dp[n - 1][sumS][sumW]);
            }
        }
        return maxTotalValue;
    }
}
