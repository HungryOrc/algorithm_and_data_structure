// 关于思路与解释，参考 Spiral Traversal of SQUARE Matrix

/* Generate an N * N 2D array in spiral order clock-wise starting from the top left corner, 
using the numbers of 1, 2, 3, …, N * N in increasing order.
Assumptions: N >= 0

Examples:
N = 3, the generated matrix is
{ {1,  2,  3}
  {8,  9,  4},
  {7,  6,  5} }   */

public class Solution {
  
  public int[][] spiralGenerate(int n) { // n = diameter
    int[][] result = new int[n][n];
    
    if (n == 0) {
      return result;
    }
    
    spiralGenerate(result, n, 0, 1);
    return result;
  }
  
  private void spiralGenerate(int[][] result, int diameter,
    int offset, int count) {
      
    if (diameter == offset * 2) {
      return;
    }
    else if (diameter == offset * 2 + 1) {
      result[offset][offset] = count;
      return;
    }
    
    for (int col = offset; col < diameter - 1 - offset; col++) {
      result[offset][col] = count;
      count ++;
    }
    for (int row = offset; row < diameter - 1 - offset; row++) {
      result[row][diameter - 1 - offset] = count;
      count ++;
    }
    for (int col = diameter - 1 - offset; col > offset; col--) {
      result[diameter - 1 - offset][col] = count;
      count ++;
    }
    for (int row = diameter - 1 - offset; row > offset; row--) {
      result[row][offset] = count;
      count ++;
    }
    
    spiralGenerate(result, diameter, offset + 1, count);
  }
  
}
