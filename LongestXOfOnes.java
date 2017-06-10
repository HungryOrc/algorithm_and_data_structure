/* Given a matrix that contains only 1s and 0s, find the largest X shape which contains only 1s, 
with the same arm lengths and the four arms joining at the central point.
Return the arm length of the largest X shape.
Assumptions:
The given matrix is not null, has size of N * M, N >= 0 and M >= 0.

Examples:
{ {0, 0, 0, 0},
  {1, 1, 1, 1},
  {0, 1, 1, 1},
  {1, 0, 1, 1} }
the largest X of 1s has arm length 2. */

// 思路：详细解释，见我归纳的 Longest Cross of Ones 那一题

public class Solution {
  
  public int largest(int[][] matrix) {
    
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return 0;
    }
    
    int rows = matrix.length;
    int cols = matrix[0].length;
    
    int[][] bottomLeftToTopRight = new int[rows][cols]; // DP Matrix 1
    
    for (int startRow = 0; startRow < rows; startRow ++) {
      bottomLeftToTopRight[startRow][0] = matrix[startRow][0];
      int row = startRow;
      for (int col = 1; col < cols; col ++) {
        row --;
        if (row < 0) { // index out of bound
          break;
        }
        // fill in the cell to the top right direction
        if (matrix[row][col] == 0) {
          bottomLeftToTopRight[row][col] = 0;
        } else {
          bottomLeftToTopRight[row][col] = bottomLeftToTopRight[row + 1][col - 1] + 1; 
        }
      }
    }
    for (int startCol = 1; startCol < cols; startCol ++) {
      bottomLeftToTopRight[rows - 1][startCol] = matrix[rows - 1][startCol];
      int col = startCol;
      for (int row = rows - 2; row >= 0; row --) {
        col ++;
        if (col == cols) { // index out of bound
          break;
        }
        // fill in the cell to the top right direction
        if (matrix[row][col] == 0) {
          bottomLeftToTopRight[row][col] = 0;
        } else {
          bottomLeftToTopRight[row][col] = bottomLeftToTopRight[row + 1][col - 1] + 1; 
        }
      }
    }
    
    int[][] topRightToBottomLeft = new int[rows][cols]; // DP Matrix 2
    
    for (int startRow = 0; startRow < rows; startRow ++) {
      topRightToBottomLeft[startRow][cols - 1] = matrix[startRow][cols - 1];
      int row = startRow;
      for (int col = cols - 2; col >= 0; col --) {
        row ++;
        if (row == rows) { // index out of bound
          break;
        }
        // fill in the cell to the bottom left direction
        if (matrix[row][col] == 0) {
          topRightToBottomLeft[row][col] = 0;
        } else {
          topRightToBottomLeft[row][col] = topRightToBottomLeft[row - 1][col + 1] + 1; 
        }
      }
    }
    for (int startCol = cols - 2; startCol >= 0; startCol --) {
      topRightToBottomLeft[0][startCol] = matrix[0][startCol];
      int col = startCol;
      for (int row = 1; row < rows; row ++) {
        col --;
        if (col < 0) { // index out of bound
          break;
        }
        // fill in the cell to the bottom left direction
        if (matrix[row][col] == 0) {
          topRightToBottomLeft[row][col] = 0;
        } else {
          topRightToBottomLeft[row][col] = topRightToBottomLeft[row - 1][col + 1] + 1; 
        }
      }
    }
    
    int[][] topLeftToBottomRight = new int[rows][cols]; // DP Matrix 3
    
    for (int startRow = 0; startRow < rows; startRow ++) {
      topLeftToBottomRight[startRow][0] = matrix[startRow][0];
      int row = startRow;
      for (int col = 1; col < cols; col ++) {
        row ++;
        if (row == rows) { // index out of bound
          break;
        }
        // fill in the cell to the bottom right direction
        if (matrix[row][col] == 0) {
          topLeftToBottomRight[row][col] = 0;
        } else {
          topLeftToBottomRight[row][col] = topLeftToBottomRight[row - 1][col - 1] + 1; 
        }
      }
    }
    for (int startCol = 1; startCol < cols; startCol ++) {
      topLeftToBottomRight[0][startCol] = matrix[0][startCol];
      int col = startCol;
      for (int row = 1; row < rows; row ++) {
        col ++;
        if (col == cols) { // index out of bound
          break;
        }
        // fill in the cell to the bottom right direction
        if (matrix[row][col] == 0) {
          topLeftToBottomRight[row][col] = 0;
        } else {
          topLeftToBottomRight[row][col] = topLeftToBottomRight[row - 1][col - 1] + 1; 
        }
      }
    }
    
    int[][] bottomRightToTopLeft = new int[rows][cols]; // DP Matrix 4
    
    for (int startRow = rows - 1; startRow >= 0; startRow --) {
      bottomRightToTopLeft[startRow][cols - 1] = matrix[startRow][cols - 1];
      int row = startRow;
      for (int col = cols - 2; col >= 0; col --) {
        row --;
        if (row < 0) { // index out of bound
          break;
        }
        // fill in the cell to the top left direction
        if (matrix[row][col] == 0) {
          bottomRightToTopLeft[row][col] = 0;
        } else {
          bottomRightToTopLeft[row][col] = bottomRightToTopLeft[row + 1][col + 1] + 1; 
        }
      }
    }
    for (int startCol = cols - 2; startCol >= 0; startCol --) {
      bottomRightToTopLeft[rows - 1][startCol] = matrix[rows - 1][startCol];
      int col = startCol;
      for (int row = rows - 2; row >= 0; row --) {
        col --;
        if (col < 0) { // index out of bound
          break;
        }
        // fill in the cell to the top left direction
        if (matrix[row][col] == 0) {
          bottomRightToTopLeft[row][col] = 0;
        } else {
          bottomRightToTopLeft[row][col] = bottomRightToTopLeft[row + 1][col + 1] + 1; 
        }
      }
    }
    
    int longestLeg = 0;
    int[][] combined = new int[rows][cols]; // Combine all the 4 DP Matrixes
    for (int row = 0; row < rows; row ++) {
      for (int col = 0; col < cols; col ++) {
        if (matrix[row][col] == 1) {
          combined[row][col] = Math.min(
            Math.min(topLeftToBottomRight[row][col], bottomRightToTopLeft[row][col]),
            Math.min(topRightToBottomLeft[row][col], bottomLeftToTopRight[row][col]));
          longestLeg = Math.max(longestLeg, combined[row][col]);
        }
      }
    }
    return longestLeg;
  }
  
}
