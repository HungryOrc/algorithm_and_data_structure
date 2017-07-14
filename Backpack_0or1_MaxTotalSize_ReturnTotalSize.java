/* 这一类题也叫 “Naive背包问题”，或 “0/1背包问题”，因为每个item最多取一次。
每个item有自己的size（或者weight）。背包有最大的容量（对于size或weight）。求最大可能的总装载量（对于size或weight）。

题目出处：http://www.lintcode.com/en/problem/backpack/
Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?
You function should return the max size we can fill in the given backpack.
Notice: You can not divide any item into small pieces.

Example:
If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], 
so that the max size we can fill this backpack is 10. If the backpack size is 12. 
we can select [2, 3, 7] so that we can fulfill the backpack. */


/* 思路：二维 DP
boolean dp[i][s] 的意思是：使用数组里index为0到i的items中的任意几个（可以一个也不选，也可以从中任选几个），能否正好组成总size为s。
所以 boolean dp[][] = new boolean[number of items][capacity of the backpack + 1]。

Base Cases:
Base Case 1: 对于数组里的第一个item，if(sizes[0] <= capacity)，canSumTo[0][sizes[0]] = true;
Base Case 2: size和为0的情况，对于任何多个items，都是可以的！！ 即什么都不放 ！！
    for (int i = 0; i < n; i++)，canSumTo[i][0] = true;

Induction Rule:
dp[i][s] = dp[i - 1][s] || dp[i - 1][s - sizes[i]]
其中 dp[i - 1][s] 的意思是：i之前的那些items已经可以组成总和正好为 s 的组合了，item i 不参与的话，自然也还是和为s的组合；
dp[i - 1][s - sizes[i]] 的意思是：i之前的那些items组成了总和证号为 s - sizes[i] 的组合，那么 item i 参与进来后，自然正好就是和为s。

Return:
for (int sum = capacity; sum >= 1; sum--)，如果 dp[n - 1][sum] == true，则返回 sum

Time: O(n * capacity), 其中n是items的个数
Space: O(n * capacity)。可以优化为 O(capacity)，因为dp矩阵里，其实每一次loop只用一行就够了   */

public class Solution {
     
    public int backPack(int capacity, int[] sizes) {
        int n = sizes.length;
        
        boolean[][] canSumTo = new boolean[n][capacity + 1];
        
        // base case 1
        if (sizes[0] <= capacity)
            canSumTo[0][sizes[0]] = true;
        // base case 2
        for (int i = 0; i < n; i++) {
            canSumTo[i][0] = true;
        }
        
        // 从第二个item（即i=1）开始
        for (int i = 1; i < n; i++) {
            int curItemSize = sizes[i];
            
            for (int sum = 1; sum <= capacity; sum++) {
                
                if (canSumTo[i - 1][sum]) {
                    canSumTo[i][sum] = true;
                } else if (sum - curItemSize >= 0 && sum - curItemSize <= capacity && // 别忘了检查越界 ！！！
                    canSumTo[i - 1][sum - curItemSize]) {
                    canSumTo[i][sum] = true;
                }
            }
        }
        
        for (int sum = capacity; sum >= 1; sum--) {
            if (canSumTo[n - 1][sum]) {
                return sum;
            }
        }
        return 0; // actually we will never reach here
    }
}
