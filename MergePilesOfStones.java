/* 这是一道传说中的二维DP题

We have a list of piles of stones, each pile of stones has a certain weight, represented by an array of integers. 
In each move, we can merge two adjacent piles into one larger pile, the cost is the sum of the weights of the two piles. 
We merge the piles of stones until we have only one pile left. Determine the minimum total cost.

Assumptions:
stones is not null and is length of at least 1

Examples:
{4, 3, 3, 4}, the minimum cost is 28
merge first 4 and first 3, cost 7
merge second 3 and second 4, cost 7
merge two 7s, cost 14
total cost = 7 + 7 + 14 = 28   */


/* 这一题思路的详解，见我总结的另一题：Cut Wood_Cost Related To Length

DP数组 M[i][j] 的意思是，从第i堆石头合并到第j堆石头，both i and j are inclusive，所需要的最小cost.
设第i堆石头的单独的cost是 A[i]。
设从A[i]到A[j]并包含它们两在内的总和为sum[i,j]，它可以用prefix sum很快地算出来。

(1) 一开始，我们有：M[0][1] = A[0] + A[1], M[1][2] = A[1] + A[2]... M[i][i+1] = A[i] + A[i+1]
(2) M[i][i+2] = min(M[i][i+1] + sum[i,i+2], 
                    M[i+1][i+2] + sum[i,i+2])
(3) M[i][i+3] = min(M[i][i+2] + sum[i,i+3], 
                    M[i][i+1] + M[i+2][i+3] + sum[i,i+3], 
                    M[i+1][i+3] + sum[i,i+3])
...
(k) M[i][i+k] = min(M[i][i+k-1] + sum[i,i+k], 
                    M[i][i+k-2] + M[i+k-1][i+k] + sum[i,i+k], 
                    M[i][i+k-3] + M[i+k-2][i+k] + sum[i,i+k],
                    ...
                    M[i+1][i+k] + sum[i,i+k])

我们最后要的答案是 M[0][n-1]，其中n是石头的堆数，就是一开始给的数组的长度    */
