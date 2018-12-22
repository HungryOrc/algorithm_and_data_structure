public int numOfSquares(int n) {
    if (n <= 0) {
        return 0;
    }
	
    int[] dp = new int[n + 1];
    for (int i = 1; i <= n; i++) {
	dp[i] = i;
        for (int i = 1; j * j <= i; j++) {    
            dp[i] = Math.min(dp[i], 1 + dp[i - j * j]);
	}
    }
    return dp[n];
}
