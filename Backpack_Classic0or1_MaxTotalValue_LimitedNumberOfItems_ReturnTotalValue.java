/* 所谓 “Classic 0/1背包问题”，0/1 是指每个item最多取一次，Classic的意思是每个item有不止一个属性，比如既有size又有value。
背包有最大的容量（对于size）。然后这一题的特殊之处在于，限制了最多只能选取 m 个items。
求怎么填能获得最大的总value。背包不必被填满。 */

/* 思路：三维 DP
int dp[i][j][s] 的意思是：使用 index为0到i 的 items 中的 j 个（总共最多能选用 m 个items），
正好组成总 size = s 的前提下，最大可能的总 value 是多少。
所以 int dp[][][] = new int[number of items][m + 1][capacity of the backpack + 1]。

Base Cases: 对于只选取了1个item的情况（设一共有n个items）：
    for (int i = 0; i < n; i++) {
        找到从index=0到i的所有items之中，最高value的那个，比如说是item j：
        if (size[j] < capacity)
            dp[i][1][sizes[j]] = values[j];
    }
    
Induction Rule:



Return:
注意，这里不是返回 dp[n - 1][m][capacity] ！！ 
因为获得总value最大时，总items的个数未必是 m ！！！ 总size也未必是 capacity ！！！
    for (all the possible number of items, and all the possible total size) {
        maxTotalValue = Math.max(maxTotalValue, dp[n - 1][numuber of items][total size]);
    }
    return maxTotalValue;
        
Time: O(n * m * capacity), 其中n是items的个数，m是最多可以有几个items
Space: O(n * m * capacity)。可以优化为 O(m * capacity)   */


public class Solution {
     
    public int backPack(int capacity, int[] sizes, int[] values) {
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
                
                if (sum - curItemSize >= 0) { // 别忘了检查越界 ！！！
                    dp[i][sum] = Math.max(dp[i - 1][sum], dp[i - 1][sum - curItemSize] + values[i]);
                } else {
                    dp[i][sum] = dp[i - 1][sum]; // 这种情况下就不加后面那项了 ！！！
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
