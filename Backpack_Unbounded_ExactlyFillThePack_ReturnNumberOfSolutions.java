    // 这里分两种情况，
    // 加号左边 dp[i - 1][sum] 表示，sum里面将没有array[i]的任何参与，
    // 加号右边 dp[i][sum - curValue] 表示，sum里面将存在array[i]的一次或者多次参与，
 
    // dp[i][sum - curValue] 是包含了 dp[i - 1][sum - curValue] 的 ！！！ 所以下面的式子不要再加 dp[i - 1][sum - curValue] ！！！
    // 因为：
    // dp[i - 1][sum - curValue] 表示 array[i] 已经被使用0次，然后将会被使用且仅使用一次 ！！！
    // dp[i][sum - curValue] 表示 array[i] 已经被使用 0次，1次，或多次了，然后将再被使用一次 ！！！
    
    dp[i][sum] = dp[i - 1][sum] + dp[i][sum - curValue];
}

// 方法2：
public class Solution {
    
    public static long makeChange(int[] coins, int money) {
        if (coins == null || coins.length == 0 || money < 0) {
            return 0L;
        }
        
        int n = coins.length;
        long[][] dp = new long[n][money + 1];
        
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1L;
        }
        
        if (coins[0] <= money) {
            int sum = coins[0];
            while (sum <= money) {
                dp[0][sum] = 1L;
                sum += coins[0];
            }
        }
        
        for (int i = 1; i < n; i++) {
            for (int sum = 1; sum <= money; sum++) {
                int curValue = coins[i];
                
                if (curValue > sum) {
                    dp[i][sum] = dp[i - 1][sum];
                } else {
                    dp[i][sum] = dp[i - 1][sum] + dp[i][sum - curValue];
                }
            }
        }
        
        return dp[n - 1][money];
    }
}
