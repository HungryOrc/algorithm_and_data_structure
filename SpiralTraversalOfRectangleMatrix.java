// 关于思路与解释，参考 Spiral Traversal of SQUARE Matrix

/* Traverse an M * N 2D array in spiral order clock-wise starting from the top left corner. 
Return the list of traversal sequence.
Assumptions: The 2D array is not null and has size of M * N where M, N >= 0

Examples:
{ {1,  2,  3,  4},
  {5,  6,  7,  8},
  {9, 10, 11, 12} }
the traversal sequence is [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]   */

public class Solution {
  
  public List<Integer> spiral(int[][] matrix) {
    List<Integer> result = new ArrayList<>();
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return result;
    }
    
    int height = matrix.length;
    int width = matrix[0].length;
    
    spiralTraversal(matrix, height, width, 0, result);
    return result;
  }
  
  private void spiralTraversal(int[][] matrix, int height, int width,
    int offset, List<Integer> result) {
      
    if (height == offset * 2 || width == offset * 2) {
      return;
    }
    else if (height == offset * 2 + 1) {
      for (int col = offset; col < width - offset; col++) {
        result.add(matrix[offset][col]);
      }
      return;
    }
    else if (width == offset * 2 + 1) {
      for (int row = offset; row < height - offset; row++) {
        result.add(matrix[row][offset]);
      }
      return;
    }
    
    for (int col = offset; col < width - 1 - offset; col++) {
      result.add(matrix[offset][col]);
    }
    for (int row = offset; row < height - 1 - offset; row++) {
      result.add(matrix[row][width - 1 - offset]);
    }
    for (int col = width - 1 - offset; col > offset; col--) {
      result.add(matrix[height - 1 - offset][col]);
    }
    for (int row = height - 1 - offset; row > offset; row--) {
      result.add(matrix[row][offset]);
    }
    
    spiralTraversal(matrix, height, width, offset + 1, result);
  }
  
}
