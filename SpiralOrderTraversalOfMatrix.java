/* Traverse an N * N 2D array in spiral order clock-wise starting from the top left corner. 
Return the list of traversal sequence.
Assumptions: The 2D array is not null and has size of N * N where N >= 0

Examples:
{ {1, 2, 3, 4},
  {5, 6, 7, 8},
  {9, 10, 11, 12},
  {13, 14, 15, 16} }
The traversal sequence is [1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10].   */

/* 思路：
比如上面那个矩阵，我们一层一层，从外往里剥。
然后对于每一层，我们是上->右->下->左 这样4个边的顺序剥。
然后每一边，我们只剥剩一个单元格，就转向。具体来说，比如最外面一层，我们的4个边分别这么剥：1,2,3 -> 4,8,12 -> 16,15,14 -> 13,9,5   */

public class Solution {
  
  public List<Integer> spiral(int[][] matrix) {
    List<Integer> result = new ArrayList<>();
    int diameter = matrix.length;
    
    spiralTraversal(matrix, diameter, 0, result);
    return result;
  }
  
  private void spiralTraversal(int[][] matrix, int diameter,
    int offset, List<Integer> result) {
      
      if (diameter == offset * 2) {
        return;
      }
      if (diameter == offset * 2 + 1) {
        result.add(matrix[offset][offset]);
        return;
      }
      
      for (int col = offset; col < diameter - 1 - offset; col++) {
        result.add(matrix[offset][col]);
      }
      for (int row = offset; row < diameter - 1 - offset; row++) {
        result.add(matrix[row][diameter - 1 - offset]);
      }
      for (int col = diameter - 1 - offset; col > offset; col--) {
        result.add(matrix[diameter - 1 - offset][col]);
      }
      for (int row = diameter - 1 - offset; row > offset; row--) {
        result.add(matrix[row][offset]);
      }
      
      spiralTraversal(matrix, diameter, offset + 1, result);
    }
  
}
