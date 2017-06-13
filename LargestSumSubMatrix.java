/* Given a matrix that contains integers, find the submatrix with the largest sum.
Return the sum of the submatrix.
Assumptions:
The given matrix is not null and has size of M * N, where M >= 1 and N >= 1

Examples:
{ {1, -2, -1, 4},
  {1, -1,  1, 1},
  {0, -1, -1, 1},
  {0,  0,  1, 1} }
the largest submatrix sum is (-1) + 4 + 1 + 1 + (-1) + 1 + 1 + 1 = 7. */



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
