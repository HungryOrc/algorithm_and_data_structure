// 关于思路与解释，参考 Spiral Traversal of SQUARE Matrix

/* Generate an M * N 2D array in spiral order clock-wise starting from the top left corner, 
using the numbers of 1, 2, 3, …, M * N in increasing order.
Assumptions: M >= 0, N >= 0

Examples:
M = 3, N = 4, the generated matrix is
{ {1,  2,  3,  4}
  {10, 11, 12, 5},
  {9,  8,  7,  6} }    */
  
public class Solution {
  
  public int[][] spiralGenerate(int m, int n) { // m = rows, n = cols
    int[][] result = new int[m][n];
    
    if (m == 0 || n == 0) {
      return result;
    }
    
    spiralGenerate(result, m, n, 0, 1);
    return result;
  }
  
  private void spiralGenerate(int[][] result, int height, int width,
    int offset, int count) {
      
    if (height == offset * 2 || width == offset * 2) {
      return;
    }
    else if (height == offset * 2 + 1) {
      for (int col = offset; col < width - offset; col++) {
        result[offset][col] = count;
        count ++;
      }
      return;
    }
    else if (width == offset * 2 + 1) {
      for (int row = offset; row < height - offset; row++) {
        result[row][offset] = count;
        count ++;
      }
      return;
    }
    
    for (int col = offset; col < width - 1 - offset; col++) {
      result[offset][col] = count;
      count ++;
    }
    for (int row = offset; row < height - 1 - offset; row++) {
      result[row][width - 1 - offset] = count;
      count ++;
    }
    for (int col = width - 1 - offset; col > offset; col--) {
      result[height - 1 - offset][col] = count;
      count ++;
    }
    for (int row = height - 1 - offset; row > offset; row--) {
      result[row][offset] = count;
      count ++;
    }
    
    spiralGenerate(result, height, width, offset + 1, count);
  }
  
}
