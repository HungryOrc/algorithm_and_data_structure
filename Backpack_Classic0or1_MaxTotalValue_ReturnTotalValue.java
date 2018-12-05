/* 这一类题也叫 “Classic 0/1背包问题”，因为每个item最多取一次，
所谓Classic的意思是每个item有不止一个属性，比如既有size又有value。
背包有最大的容量（对于size或weight）。求怎么填能获得最大的总value。背包不必被填满。 */

/* 思路：二维 DP
int dp[i][s] 的意思是：使用数组里index为0到i的items中的任意几个（可以一个也不选，也可以从中任选几个），
正好组成总 size = s 的前提下，最大可能的总 value 是多少。
所以 int dp[][] = new int[number of items][capacity of the backpack + 1]。

Base Cases:
Base Case 1: 对于数组里的第一个item，
    if(sizes[0] <= capacity)，dp[0][sizes[0]] = values[0]
    其他的 dp[0][s != sizes[0]] 都 = 0，因为不可能实现这些size，所以自然所能带来的总value也都是 0
Base Case 2: size和为0的情况，对于任何多个items，都是可以的！！ 即什么都不放 ！！ 所以都置为 0，即不放任何item，自然value和为 0
    for (int i = 0; i < n; i++)，dp[i][0] = 0;
    
Induction Rule:
dp[i][s] = max(dp[i - 1][s], dp[i - 1][s - sizes[i]] + values[i])
其中 dp[i - 1][s] 的意思是：i之前的那些items已经可以组成总和正好为 s 的组合了，item i 不参与的话，自然也还是和为s的组合；
dp[i - 1][s - sizes[i]] 的意思是：i之前的那些items组成了总和正好为 s - sizes[i] 的组合，那么 item i 参与进来后，自然正好就是和为s。

Return:
注意，这里不是返回 dp[n - 1][capacity] ！！ 
因为dp矩阵的第二维的意义是“正好”总size为某某值 ！！ 而获得总value最大时的总size未必是capacity ！！！
    for (int sum = 1; sum <= capacity; sum++) {
        maxTotalValue = Math.max(maxTotalValue, dp[n - 1][sum]);
    }
    return maxTotalValue;
        
Time: O(n * capacity), 其中n是items的个数
Space: O(n * capacity)。可以优化为 O(capacity)，因为dp矩阵里，其实每一次loop只用一行就够了   */


public class Solution {
     
    public int backPack(int capacity, int[] sizes, int[] values) {
        
        // 这里还应该考虑两个数组长度不同的情况！！！！！
        
        
        
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
