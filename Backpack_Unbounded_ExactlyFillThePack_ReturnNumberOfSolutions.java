/* 这一类题叫“Unbounded”的意思是，每个item可以被取用 0次 到 无限次。
每个item有不止一个属性，比如既有size又有value。
背包有最大的容量（对于size或weight）。求有多少种正好填满背包的方法。 */

/* 思路：二维 DP
int dp[i][s] 的意思是：使用数组里index为0到i的items中的任意几个（可以一个也不选，也可以从中任选几个），
正好组成总 size = s 的前提下，一共有多少种可能的方法。
所以 int dp[][] = new int[number of items][capacity of the backpack + 1]。

Base Cases:
Base Case 1: 对于数组里的第一个item，
    if(sizes[0] * i <= capacity)，dp[0][sizes[0] * i] = 1，即1种方法
    其中 i = 1, 2, 3...
    其他的 dp[0][s != sizes[0]] 都 = 0，因为不可能实现这些size，所以是0种方法
Base Case 2: size和为0的情况，对于任何多个items，都是可以的！！ 即什么都不放 ！！ 所以都置为1，即1种方法
    for (int i = 0; i < n; i++)，dp[i][0] = 1;
    
Induction Rule: 注意！这一题的递推公式比较特别 ！！          
for (int j = 0; j <= size / sizes[i]; j++) {
    dp[i][size] += dp[i - 1][size - j * sizes[i]];
}
             
Return: dp[n - 1][capacity]
        
Time: O(n * capacity), 其中n是items的个数
Space: O(n * capacity)。可以优化为 O(capacity)，因为dp矩阵里，其实每一次loop只用一行就够了   */


public class Solution {

    public int backPackIV(int[] sizes, int capacity) {
        if (sizes == null || sizes.length == 0 || capacity <= 0) {
            return 0;        
        }
        
        int n = sizes.length;
        int[][] dp = new int[n][capacity + 1];
        
        // base case 1
        for (int i = 1; i <= capacity / sizes[0]; i++) {
            dp[0][sizes[0] * i] = 1;
        }
        
        // base case 2，这个很重要！别忘了！这里都是 1 ！！ 不是 0 ！！
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        
        for (int i = 1; i < n; i++) {
            for (int size = 1; size <= capacity; size++) {
                int curSize = sizes[i];
                
                for (int j = 0; j <= size / curSize; j++) {
                    dp[i][size] += dp[i - 1][size - j * curSize];
                }
            }
        }
        
        return dp[n - 1][capacity];
    }
}
