

int[][]
dp[i][j] means by using i items, the maximum size we can get with the j size constrains.
dp[i][j] = Max(dp[i-1][j], dp[i-1][j-A[i]] + A[i])， the second part has a prerequisit: j >= A[i]

我自己写写看！


只需要2行空间，我自己写写看！


从右往左？更快？我自己试试看
John老师说可以这样
