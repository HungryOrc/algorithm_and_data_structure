

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
