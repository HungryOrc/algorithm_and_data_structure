/* 这一类题叫“Unbounded”的意思是，每个item可以被取用 0次 到 无限次。
背包有最大的容量（对于size或weight）。背包里的items的size都各不相同。
求有多少种正好填满背包的方法，注意，这一题的特别之处在于，每一种组合，改变内部的items的顺序之后，算是不同的组合！！！
Example:
Given nums = [1, 2, 4], target = 4
The possible combination ways are:
[1, 1, 1, 1]
[1, 1, 2]
[1, 2, 1]
[2, 1, 1]
[2, 2]
[4]
return 6     */

/* 思路：一维 DP
int dp[s] 的意思是：使用数组里的所有items，每一个都可以被选用 0次 到 无限次，
正好组成总 size = s 的前提下，而且items的排列顺序不同就算不同的方法，一共有多少种可能的方法。
所以 int dp[] = new int[capacity of the backpack + 1]。

Base Cases:
    dp[0] = 1，即任何item都不用，总size = 0，就一种方法
    
Induction Rule: 注意 ！！ 这一题的递推公式非常特别 ！！！          
for (int sum = 1; sum <= capacity; sum++) {
    for (int i = 0; i < n; i++) {
        if (sizes[i] <= sum) {            
            dp[sum] += dp[sum - sizes[i]];
        }
    }
}
             
Return: dp[capacity]
        
Time: O(n * capacity), 其中n是items的个数
Space: O(n * capacity)。可以优化为 O(capacity)，因为dp矩阵里，其实每一次loop只用一行就够了   */


public class Solution {

    public int backPackVI(int[] sizes, int capacity) {
        int n = sizes.length;
        
        int[] dp = new int[capacity + 1];
        dp[0] = 1;
        
        for (int sum = 1; sum <= capacity; sum++) {
            for (int i = 0; i < n; i++) {
                
                if (sizes[i] <= sum) {            
                    dp[sum] += dp[sum - sizes[i]];
                }
            }
        }
        return dp[capacity];
    }
}
