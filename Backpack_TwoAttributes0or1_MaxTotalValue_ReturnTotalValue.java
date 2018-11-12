/* “Two Attributes 0/1背包问题” 里的 Two Attributes 0/1 是指：
每个item有2个受限制的属性，比如size和weight。然后每个item还有一个value属性。
每个item可以被取用 0次 或 1次。
背包的size capacity有上限，同时weight capacity也有上限。求怎么填能获得最大的总value。背包不必被填满。 */

/* 思路：三维 DP
int dp[i][s][w] 的意思是：的意思是：使用数组里index为0到i的items中的任意几个（可以一个也不选，也可以从中任选几个），
正好组成 总size = s 且 总weight = w 的前提下，最大可能的总 value 是多少。
所以 int dp[][][] = new int[number of items][max size of backpack + 1][max weight of backpack + 1]。

Base Cases:
对于数组里的第一个item，
if(sizes[0] <= maxSize && weights[0] <= maxWeight)，dp[0][sizes[0]][weights[0]] = values[0]
其他的 dp[0][s != sizes[0]][w != weights[0]] 都 = 0，因为不可能实现这些size或者weight，所以自然所能带来的总value也都是 0
    
Induction Rule:
dp[i][s][w] = max(dp[i - 1][s][w], dp[i - 1][s - sizes[i]][w - weights[i]] + values[i])

Return:
注意，这里不是返回 dp[n - 1][maxSize][maxWeight] ！！ 
因为dp矩阵的第二维的意义是“正好”总size为某某值 ！！ 而获得总value最大时的总size未必是上限处 ！！！
    for (all the s and w) {
        maxTotalValue = Math.max(maxTotalValue, dp[n - 1][s][w]);
    }
    return maxTotalValue;
        
Time: O(n * maxSize * maxWeight), 其中n是items的个数
Space: O(n * maxSize * maxWeight)。可以优化为 O(maxSize * maxWeight)，因为dp矩阵里，其实每一次loop只用一行就够了   */


public class Solution {
     
    public int backPack(int maxSize, int maxWeight, int[] sizes, int[] weights, int[] values) {
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
