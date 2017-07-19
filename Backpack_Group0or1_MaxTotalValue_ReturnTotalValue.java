/* “Group 0/1背包问题” 里的 Group 0/1 是指：每个group里的item最多取一次，最少取零次。一个group里可能有1个或多个items。
每个item有2个属性，size和value。
背包的size capacity有上限。求怎么填能获得最大的总value。背包不必被填满。 */

/* 思路：二维 DP
int dp[i][s] 的意思是：使用 index为0到i的 group 中的任意几个（可以一个也不选，也可以从中任选几个），
每个被选中的group里任选一个item，
正好组成总 size = s 的前提下，最大可能的总 value 是多少。
所以 int dp[][] = new int[number of groups][capacity of the backpack + 1]。

Base Cases:
Base Case 1: 对于第一个group，对于它里面的每个item，最多只有一个item可以被选用，最多只能被选用一次，所以：
    
    其他的 dp[0][s != sizes[i]] 都 = 0，因为不可能实现这些size，所以自然所能带来的总value也都是 0
Base Case 2: size和为0的情况，对于任何多个groups，都是可以的！！ 即什么都不放 ！！ 所以都置为 0，即不选任何group，自然value和为 0
    for (int i = 0; i < n; i++)，dp[i][0] = 0;
    
Induction Rule: 这一题的递推
dp[i][s] = max(dp[i - 1][s], dp[i - 1][s - sizes[i]] + values[i])
其中 dp[i - 1][s] 的意思是：i之前的那些items已经可以组成总和正好为 s 的组合了，item i 不参与的话，自然也还是和为s的组合；
dp[i - 1][s - sizes[i]] 的意思是：i之前的那些items组成了总和正好为 s - sizes[i] 的组合，那么 item i 参与进来后，自然正好就是和为s。

Induction Rule: 注意 ！！ 这一题的递推公式非常特别 ！！！




Time: O(n * m * capacity), 其中n是groups的个数，m是平均每个group里面的items的个数
Space: O(n * capacity)。可以优化为 O(capacity)，因为dp矩阵里，其实每一次loop只用一行就够了   */


public class Solution {
     
    public int backPack(int capacity, List<Integer>[] sizes, List<Integer>[] values) {
        int n = sizes.length; // number of groups
        
        int[][] dp = new int[n][capacity + 1];
        
        // base case 1
        for ()
        
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
