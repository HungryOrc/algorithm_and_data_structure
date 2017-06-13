/* Given a matrix that contains integers, find the submatrix with the largest sum.
Return the sum of the submatrix.
Assumptions:
The given matrix is not null and has size of M * N, where M >= 1 and N >= 1

Examples:
{ {1, -2, -1, 4},
  {1, -1,  1, 1},
  {0, -1, -1, 1},
  {0,  0,  1, 1} }
the largest submatrix sum is (-1) + 4 + 1 + 1 + (-1) + 1 + 1 + 1 = 7.
这一题用 DP 做，或者可以认为用 Prefix Sum of Row -> Prefix Sum of Matrix -> 拍扁的 Prefix Sum of Matrix 这样的心路历程来做  */


/* 方法1：brute force
认为矩阵的尺度是 n * n。选取sub matrix分为两步，第一步选左上角，一共有O(n^2)量级的选择；然后第二步选右下角，也有O(n^2)量级的选择。
所以一共有 O(n^4) 量级的sub matrix的个数。
对于每一个sub matrix，我们要把它里面的元素一个一个加起来，处理过程是 O(n^2) 的时间。
所以总共是 O(n^6) 量级的时间。  */


/* 方法2：用 Prefix Sum of Each Row 来提高速度
对于每一行，计算它们在每一个index上的prefix sum，其中prefix sum[i] 的意思是，从本行的第一个元素，到第 i 个元素(inclusive) 的总和。
把matrix的每一行都算完一共需要 O(n^2) 的时间，因为n行每行n个元素。
把这些计算结果记录在一个 DP matrix 里面，可以叫做 Prefix Sum of Each Row 这样一个matrix，作为helper matrix备查。
然后我们要计算每一个sub square的sum的时候，就把它对应的那些行的那些列加起来，因为有了这个helper matrix，我们其实只用加对应的行，
那么计算一个sub matrix sum的时间就是 O(n)。
按之前的讨论，一共有 O(n^4) 种sub matrix，所以总的计算时间是 O(n^5)。   */


/* 方法3：



*/

public class Solution {
  
  public int largest(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return Integer.MIN_VALUE;
    }
    
    int rows = matrix.length;
    int cols = matrix[0].length;
    
    int[][] prefixSumOnEachRow = new int[rows][cols];
    for (int i = 0; i < rows; i++) {
      prefixSumOnEachRow[i][0] = matrix[i][0];
      for (int j = 1; j < cols; j++) {
        prefixSumOnEachRow[i][j] = prefixSumOnEachRow[i][j - 1] + matrix[i][j];
      }
    }
    
    int[][] prefixSumOfEachSquare = new int[rows][cols];
    for (int j = 0; j < cols; j++) {
      prefixSumOfEachSquare[0][j] = prefixSumOnEachRow[0][j];
      for (int i = 1; i < rows; i++) {
        prefixSumOfEachSquare[i][j] = prefixSumOfEachSquare[i - 1][j] + prefixSumOnEachRow[i][j];
      }
    }
    
    int maxSquareSum = Integer.MIN_VALUE;
    
    for (int startRow = 0; startRow < rows; startRow++) {
      for (int endRow = startRow; endRow < rows; endRow++) {
        // 一开始搞一个min=0，意味着sub square从第一列开始的情况，这种情况下，要减去的prefix sub matrix不存在，
        // 就认为这样的 prefix sub matrix的sum为0，合情合理，也方便处理
        int minSquareSumBetweenTheseTwoRows = 0;
        
        for (int col = 0; col < cols; col++) {
          int curSquareSum = prefixSumOfEachSquare[endRow][col] - prefixSumOfEachSquare[startRow][col] 
            + prefixSumOnEachRow[startRow][col];
            
          maxSquareSum = Math.max(curSquareSum - minSquareSumBetweenTheseTwoRows, maxSquareSum);

          if (curSquareSum < minSquareSumBetweenTheseTwoRows) {
            minSquareSumBetweenTheseTwoRows = curSquareSum;
          }
        }
      }
    }
    return maxSquareSum;
  }
  
}
