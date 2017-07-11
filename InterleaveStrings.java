/* Interleave 的意思是 “交错”，即 a里取0到n个char，然后b里取0到m个char，然后照此反复。

Given three strings A, B and C. Determine if C can be created by merging A and B in a way that maintains 
the relative order of the characters in A and B.

Assumptions:
none of A, B, C is null

Examples:
C = "abcde", A = "acd", B = "be", return true
C = "abcde", A = "adc", B = "be", return false  */


/* 思路：DP，这一题里动态规划矩阵的设计方式很巧妙 ！！！
搞一个二维的boolean矩阵，其中 dp[i][j] 的意思是：
运用 a里的第1个到第i个char，以及 b里的第1个到第j个char，任意交错搭配，是否有可能组成 c里的第1个到第(i+j)个char。  */

