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
    int n = matrix.length;
    
    spiralTraversal(matrix, n, 0, 0, result);
    return result;
  }
  
  private void spiralTraversal(int[][] matrix, int width,
    int rowOffset, int colOffset, List<Integer> result) {
      
      if (width == rowOffset * 2) {
        return;
      }
      if (width == rowOffset * 2 + 1) {
        result.add(matrix[rowOffset][colOffset]);
        return;
      }
      
      for (int col = colOffset; col < width - 1 - colOffset; col++) {
        result.add(matrix[rowOffset][col]);
      }
      for (int row = rowOffset; row < width - 1 - rowOffset; row++) {
        result.add(matrix[row][width - 1 - colOffset]);
      }
      for (int col = width - 1 - colOffset; col > colOffset ; col--) {
        result.add(matrix[width - 1 - rowOffset][col]);
      }
      for (int row = width - 1 - rowOffset; row > rowOffset; row--) {
        result.add(matrix[row][colOffset]);
      }
      
      spiralTraversal(matrix, width, rowOffset + 1, colOffset + 1, result);
    }
  
}
