/* 这一类题也叫 “Naive背包问题”，或 “0/1背包问题”，因为每个item最多取一次。
每个item有自己的size（或者weight）。背包有最大的容量（对于size或weight）。求最少用多少个items，可以正好填满这个背包。*/

/* 思路：二维 DP
int dp[i][s] 的意思是：使用数组里index为0到i的items中的任意几个（可以一个也不选，也可以从中任选几个），
最少用多少个items，能正好组成总 size = s。
所以 int dp[][] = new int[number of items][capacity of the backpack + 1]。

Base Cases:
Base Case 1: 对于数组里的第一个item，
    if(sizes[0] <= capacity)，canSumTo[0][sizes[0]] = 1
    其他的 canSumTo[0][s != sizes[0]] 都 = 正无穷大，用这种方式来表示根本不可能完成
Base Case 2: size和为0的情况，对于任何多个items，都是可以的！！ 即什么都不放 ！！ 所以都置为 0，即不放任何item
    for (int i = 0; i < n; i++)，canSumTo[i][0] = 0;
    
Induction Rule:
dp[i][s] = min(dp[i - 1][s], dp[i - 1][s - sizes[i]] + 1)
其中 dp[i - 1][s] 的意思是：i之前的那些items已经可以组成总和正好为 s 的组合了，item i 不参与的话，自然也还是和为s的组合；
dp[i - 1][s - sizes[i]] 的意思是：i之前的那些items组成了总和正好为 s - sizes[i] 的组合，那么 item i 参与进来后，自然正好就是和为s。

Return: dp[n - 1][capacity of the backpack]

Time: O(n * capacity), 其中n是items的个数
Space: O(n * capacity)。可以优化为 O(capacity)，因为dp矩阵里，其实每一次loop只用一行就够了   */



两个特殊情况 ！！！

一个是 max + 1 = -1，一个是 size - curSize < 0 ！！！




public class Solution {
     
    public int backPack(int capacity, int[] sizes) {
        int n = sizes.length;
        
        int[][] canSumTo = new int[n][capacity + 1];
        
        // base case 1
        if (sizes[0] <= capacity) {
            canSumTo[0][sizes[0]] = 1;
        }
        // base case 2
        for (int i = 0; i < n; i++) {
            canSumTo[i][0] = 1;
        }
        
        // 从第二个item（即i=1）开始
        for (int i = 1; i < n; i++) {
            int curItemSize = sizes[i];
            
            for (int sum = 1; sum <= capacity; sum++) {
                
                if (sum - curItemSize >= 0) { // 别忘了检查越界 ！！！
                    canSumTo[i][sum] = canSumTo[i - 1][sum] + canSumTo[i - 1][sum - curItemSize];
                } else {
                    canSumTo[i][sum] = canSumTo[i - 1][sum]; // 这种情况下就不加后面那项了 ！！！
                }
            }
        }
        
        return dp[n - 1][capacity];
    }
}
