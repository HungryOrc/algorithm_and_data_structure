public class Solution {
     
    public int backPack(int capacity, List<Integer>[] sizes, List<Integer>[] values) {
        int n = sizes.length; // number of groups
        
        int[][] dp = new int[n][capacity + 1];
        
        // base case 1
        for (int i = 0; i < n; i++) {
            List<Integer> curSizeList = sizes[i];
            List<Integer> curValueList = values.get[i];
        
            for (int j = 0; j < curSizeList.size(); j++) {
                int curSize = curSizeList.get(j);
                int curValue = curValueList.get(j);
                
                dp[0][curSize] = curValue; ?????? need max here!!!!!!!!
            }
        }
        
        for (int sum = 1; sum <= capacity; sum++) {
            
            // 从第二个group（即i=1）开始
            for (int i = 1; i < n; i++) {
                List<Integer> curSizeList = sizes[i];
                List<Integer> curValueList = values.get[i];

                for (int j = 0; j < curSizeList.size(); j++) {
                    int curSize = curSizeList.get(j);
                    int curValue = curValueList.get(j);

                    if (sum - curSize >= 0) {
                        dp[i][sum] = Math.max(dp[i - 1][sum], dp[i - 1][sum - curSize] + curValue);
                    } else {
                        dp[i][sum] = Math.max(dp[i][sum], dp[i - 1][sum]); // must have max here! since we visit this sum for j times
!!!!!!!!
                    }
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
