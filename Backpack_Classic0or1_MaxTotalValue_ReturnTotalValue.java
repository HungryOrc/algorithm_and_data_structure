public class Solution {
     
    public int backPack(int capacity, int[] sizes, int[] values) {
        
        // 这里还应该考虑两个数组长度不同的情况！！！！！写了代码以后留一句注释在这里！！！
        
        
        
        int n = sizes.length;
        
        int[][] dp = new int[n][capacity + 1];
        
        // base case 1
        if (sizes[0] <= capacity) {
            dp[0][sizes[0]] = value[0];
        }
        
        // base case 2 ---- 这个其实可以不写，因为默认都是 0. 写了只是更能解释清楚思路
        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }
        
        // 从第二个item（即i=1）开始
        for (int i = 1; i < n; i++) {
            int curItemSize = sizes[i];
            
            for (int sum = 1; sum <= capacity; sum++) {
                dp[i][sum] = dp[i - 1][sum];
                
                if (sum - curItemSize >= 0) {
                    dp[i][sum] = Math.max(dp[i][sum], dp[i - 1][sum - curItemSize] + values[i]);
                }
            }
        }
        
        int maxTotalValue = 0;
        for (int sum = 1; sum <= capacity; sum++) {
            maxTotalValue = Math.max(maxTotalValue, dp[n - 1][sum]);
        }
        return maxTotalValue;
    }
}


只用一行dp数组，不用矩阵，
然后从右往左填：

int[] dp = new int[m + 1]
int n = nums.length;

for (int i = 0; i < size; i++) {
    for (int j = m; j >= 0; j--) {
        if (j >= nums[i - 1]) {
            dp[j] = Math.max(dp[j], dp[j - nums[i]] + V[i]);
        }
    }
}
return dp[m]

nums是重量，V数组是values
