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
以下是我的分析和我的方法
只要彻底分析透彻了，落实代码的时候小心一点细致一点，就一定能水到渠成

DP数组 M[i][j] 的意思是，从第i堆石头合并到第j堆石头，both i and j are inclusive，所需要的最小cost.
设第i堆石头的单独的cost是 A[i]。
设从A[i]到A[j]并包含它们两在内的总和为sum[i,j]，它可以用prefix sum很快地算出来。

(1) 一开始，我们有：M[0][1] = A[0] + A[1], M[1][2] = A[1] + A[2]... M[i][i+1] = A[i] + A[i+1]
(2) M[i][i+2] = min(M[i][i+1] + sum[i,i+2], 
                    M[i+1][i+2] + sum[i,i+2])
(3) M[i][i+3] = min(M[i][i+2] + sum[i,i+3],     --------------------> "头部"情况
                    M[i][i+1] + M[i+2][i+3] + sum[i,i+3], 
                    M[i+1][i+3] + sum[i,i+3])   --------------------> "尾部"情况
    要特别注意“一头一尾”的情况 ！！！
    “一个石头 + 一堆石头”，以及“一堆石头 + 一个石头” 这就叫一头一尾，这样的情况的处理，等于：
    M[那一堆石头] + sum[那一堆石头+那一个石头]；
    这与下面这些情况：
    “一堆石头 + 另一堆石头” 是不同的 ！！！ 这样的情况的处理，等于：
    M[一堆石头] + M[另一堆石头] + sum[一堆石头+另一堆石头]
(4) ...
...
...
(k) M[i][i+k] = min(M[i][i+k-1] + sum[i,i+k], 
                    M[i][i+k-2] + M[i+k-1][i+k] + sum[i,i+k], 
                    M[i][i+k-3] + M[i+k-2][i+k] + sum[i,i+k],
                    ...
                    M[i+1][i+k] + sum[i,i+k])

我们最后要的答案是 M[0][n-1]，其中n是石头的堆数，就是一开始给的数组的长度

时间：O(n^2 * n) = O(n^3)
     最后这个n，意思就是，每确定一个M[i][j]，最多需要O(n)对数来计算确定，最终取min得M[i][j]
空间：O(n^2)    */


// 我的代码实现
public class Solution {
  
  public int minCost(int[] stones) {
    int n = stones.length;
    
    int[] prefixSums = new int[n];
    prefixSums[0] = stones[0];
    for (int i = 1; i < n; i++) {
      prefixSums[i] = prefixSums[i - 1] + stones[i];
    }
    
    // DP array
    int[][] M = new int[n][n];
    
    // base cases
    for (int i = 0; i < n - 1; i++) {
      M[i][i + 1] = stones[i] + stones[i + 1];
    }
    
    // 特别注意！！！ 这一题双层for-loop的循环顺序 ！！！
    // 根据上面的解法分析，这一题的双层循环，
    // 必须先循环合并的石头共有多少堆，即2堆、3堆、4堆……
    // 然后再循环合并的石头的起始堆是哪一堆，即 i = 0, 1, 2, 3...
    //
    // 如果按照一般的思路，先循环 i，再循环 i + delta，那就傻比了 ！！！ 哇哈哈 ！！！
    for (int delta = 2; delta < n; delta++) {
      
      for (int i = 0; i < n - 2; i++) {
        
        if (i + delta < n) {
          
          M[i][i + delta] = Integer.MAX_VALUE;
          int curSum = prefixSums[i + delta] - prefixSums[i] + stones[i];
          
          // (1) the cases in both ends, for example:
          // the case M[i][i+2] + sum[i,i+3] and case M[i+1][i+3] + sum[i,i+3]
          // for M[i][i+3];
          // the case M[i][i+delta-1] + sum[i,i+delta] and case M[i+1][i+delta] + sum[i,i+delta])
          // for M[i][i+delta]
          M[i][i + delta] = Math.min(M[i][i + delta - 1] + curSum, M[i][i + delta]);
          M[i][i + delta] = Math.min(M[i + 1][i + delta] + curSum, M[i][i + delta]);
          
          // (2) the case in the middle, for example:
          // the case M[i][i+1] + M[i+2][i+3] + sum[i,i+3]
          // for M[i][i+3];
          // the case M[i][i+delta-2] + M[i+delta-1][i+k] + sum[i,i+delta], 
          //      and M[i][i+delta-3] + M[i+delta-2][i+delta] + sum[i,i+delta],
          //      and ...
          // for M[i][i+delta]
          for (int k = 1; k < delta - 1; k++) {
            M[i][i + delta] = Math.min(M[i][i + delta],
              M[i][i + k] + M[i + k + 1][i + delta] + curSum);
          }
        }
      }
    }
    
    return M[0][n - 1];
  }
}
