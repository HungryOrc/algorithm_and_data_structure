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
boolean dp[i][s] 的意思是：使用前i个items（注意不是index = i）中的任意个（0个到i个），能否正好组成总size为s。
注意数组里index为0的元素叫做 “第1个元素” 而非 “第0个元素”。
所以 boolean dp[][] = new boolean[number of items + 1][capacity of the backpack + 1]。

Base Case:
dp[0][0] = true，用前0个items，即不用任何items，是否可以组成总重量为0的组合？当然可以
dp[0][i = 1, 2, 3... capacity+1] = false

Induction Rule:
dp[i][s] = dp[i - 1][s] || dp[i - 1][s - sizes[i]]
其中 dp[i - 1][s] 的意思是：前i-1个items已经可以组成总和正好为 s 的组合了，第i个item不参与的话，自然也还是和为s的组合；
dp[i - 1][s - sizes[i]] 的意思是：前i-1个items组成了总和证号为 s - sizes[i] 的组合，那么第i个item参与进来后，自然正好就是和为s。

Return:
for (int sum = capacity; sum >= 1; sum--)，如果 dp[n][sum] == true，则返回 sum

Time: O(n * capacity), 其中n是items的个数
Space: O(n * capacity)。可以优化为 O(capacity)，因为dp矩阵里，其实每一次loop只用一行就够了   */

public class Solution {
     
    public int backPack(int capacity, int[] sizes) {
        if (sizes == null || sizes.length == 0 || capacity == 0) {
            return 0;
        }
         
        int n = sizes.length;
        
        boolean[][] canSumTo = new boolean[n + 1][capacity + 1];
        canSumTo[0][0] = true;
        
        // 这里的i是指第几个item ！！ 而非int[] sizes 里的index ！！
        for (int i = 1; i <= n; i++) {
            int curItemSize = sizes[i - 1]; // 所以这里不能忘了 -1 ！
            
            for (int sum = 0; sum <= capacity; sum++) {
                
                if (canSumTo[i - 1][sum]) {
                    canSumTo[i][sum] = true;
                } else if (sum - curItemSize >= 0 && sum - curItemSize <= capacity && // 别忘了检查越界 ！！！
                    canSumTo[i - 1][sum - curItemSize]) {
                    canSumTo[i][sum] = true;
                }
            }
        }
        
        // dp矩阵填完了，现在看到底最大可能的 total size 是多大
        for (int sum = capacity; sum >= 1; sum--) {
            if (canSumTo[n][sum]) {
                return sum;
            }
        }
        return 0; // actually we will never reach here
    }
}
