
                
                if (sum - curItemSize >= 0 && // 别忘了检查越界 ！！！
                    dp[i - 1][sum - curItemSize] != Integer.MAX_VALUE) { // 如果等于正无限，就别再 +1 了 ！！！
                    dp[i][sum] = Math.min(dp[i - 1][sum], dp[i - 1][sum - curItemSize] + 1);
                } else {
                    dp[i][sum] = dp[i - 1][sum]; // 这种情况下就不加后面那项了 ！！！
                }
            }
        }
        
        return dp[n - 1][capacity];
    }
}
