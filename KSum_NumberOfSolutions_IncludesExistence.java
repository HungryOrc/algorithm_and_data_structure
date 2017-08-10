/* 数组里没有重复数字。返回一共有多少种有效的解法。这一题当然地也就涵盖了返回“是否存在解法”的题目。

Given n distinct positive integers, integer k (k <= n) and a number target.
Find k numbers where sum is target. Calculate how many solutions there are?

Example
Given [1,2,3,4], k = 2, target = 5.
There are 2 solutions: [1,4] and [2,3]. So we return 2.   */


/* 思路：三维DP。和背包问题里的限定物品个数上限，限定总重量，求最高总价值那一题的想法类似。

int dp[数组长度 + 1][k + 1][target + 1]
其中 dp[i][j][sum] 的意思是，从数组里的前i个数里（这种做法下数组不用排序）（注意是前i个数，不是index<=i的数），
正好使用了j个数，总和正好为sum，满足这三个条件的解法一共有多少种。

边界条件：
dp[0到n][0][0] = 1，即在前i个数里，取0个数，总和为0，必然有且仅有一种方法。就是什么都不取。

递推关系：
情况一：如果 array[i - 1] <= sum，
    注意，第i个数的index是i-1，即第i个数的值是array[i-1]
    dp[i][j][sum] = dp[i - 1][j][sum] + dp[i - 1][j - 1][sum - array[i - 1]]
    等号右边加号左边是 没有使用第i个数，加号右边是 使用了第i个数。
情况二：如果 array[i - 1] > sum，
    dp[i][j][sum] = dp[i - 1][j][sum]    */
    
public class Solution {

    public int kSum(int array[], int k, int target) {
        if (array == null || array.length == 0) {
            return 0;
        }
        
        int n = array.length;
        
        int[][][] dp = new int[n + 1][k + 1][target + 1];
        
        for (int i = 0; i <= n; i++) {
            dp[i][0][0] = 1;
        }
        

        for (int i = 1; i <= n; i++) {
            // 第i个元素的index为 i-1
            int index = i - 1; 
            
            // 别忘了 用了几个元素 必须小于等于 前几个元素 ！
            for (int j = 1; j <= k && j <= i; j++) {
                
                for (int sum = 1; sum <= target; sum++) {
                        
                    if (array[index] <= sum) { // 别忘了这个判断 ！
                        dp[i][j][sum] = dp[i - 1][j][sum] + dp[i - 1][j - 1][sum - array[index]];
                    } else {
                        dp[i][j][sum] = dp[i - 1][j][sum];
                    }
                }
            }
        }
        
        return dp[n][k][target];
    }
}
