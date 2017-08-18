/* 这一类题叫“Unbounded”的意思是，每个item可以被取用 0次 到 无限次。
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
    
Induction Rule: 这里归纳出来两种递归规则，即两种方法

方法1：          
for (int j = 0; j <= size / sizes[i]; j++) {
    dp[i][size] += dp[i - 1][size - j * sizes[i]];
}

方法2：
if (curValue > sum) {
    dp[i][sum] = dp[i - 1][sum];
} else {
    // 这里分两种情况，
    // 加号左边 dp[i - 1][sum] 表示，sum里面将没有array[i]的任何参与，
    // 加号右边 dp[i][sum - curValue] 表示，sum里面将存在array[i]的一次或者多次参与，
    // 特别注意 ！！！ 
    // dp[i][sum - curValue] 是包含了 dp[i - 1][sum - curValue] 的 ！！！ 所以下面的式子不要再加 dp[i - 1][sum - curValue] ！！！
    // 因为：
    // dp[i - 1][sum - curValue] 表示 array[i] 已经被使用0次，然后将会被使用且仅使用一次 ！！！
    // dp[i][sum - curValue] 表示 array[i] 已经被使用 0次，1次，或多次了，然后将再被使用一次 ！！！
    
    dp[i][sum] = dp[i - 1][sum] + dp[i][sum - curValue];
}

Return: dp[n - 1][capacity]
        
Time: O(n * capacity), 其中n是items的个数
Space: O(capacity)   */


// 方法1：
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


// 方法2：
public class Solution {
    
    public static long makeChange(int[] coins, int money) {
        if (coins == null || coins.length == 0 || money < 0) {
            return 0L;
        }
        
        int n = coins.length;
        long[][] dp = new long[n][money + 1];
        
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1L;
        }
        
        if (coins[0] <= money) {
            int sum = coins[0];
            while (sum <= money) {
                dp[0][sum] = 1L;
                sum += coins[0];
            }
        }
        
        for (int i = 1; i < n; i++) {
            for (int sum = 1; sum <= money; sum++) {
                int curValue = coins[i];
                
                if (curValue > sum) {
                    dp[i][sum] = dp[i - 1][sum];
                } else {
                    dp[i][sum] = dp[i - 1][sum] + dp[i][sum - curValue];
                }
            }
        }
        
        return dp[n - 1][money];
    }
}
