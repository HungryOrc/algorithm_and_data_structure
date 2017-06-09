/* Given a matrix that contains only 1s and 0s, find the largest cross which contains only 1s, 
with the same arm lengths and the four arms joining at the central point.
Return the arm length of the largest cross.
Assumptions:
The given matrix is not null, has size of N * M, N >= 0 and M >= 0.

Examples:
{ {0, 0, 0, 0},
  {1, 1, 1, 1},
  {0, 1, 1, 1},
  {1, 0, 1, 1} }
the largest cross of 1s has arm length 2.    */

/* 思路：这是一个 4个方向的 “Longest Consecutive Ones” 的问题 ！！！

方向1：从左到右，求出横向上，以各个cell为右端点的最长1序列的长度：
0 0 0 0
1 2 3 4
0 1 2 3
1 0 1 2

方向2：从右到左，求出横向上，以各个cell为左端点的最长1序列的长度：
0 0 0 0
4 3 2 1
0 3 2 1
1 0 2 1

方向3：从上到下，求出纵向上，以各个cell为底端点的最长1序列的长度：
0 0 0 0
1 1 1 1
0 2 2 2
1 0 3 3

方向4：从下到上，求出纵向上，以各个cell为顶端点的最长1序列的长度：
0 0 0 0
1 2 3 3
0 1 2 2
1 0 1 1

然后对于每一个cell，找出上面4个矩阵中，相应cell里的最小值，即得到
以这个cell为中心的最大的cross的边长（含中心点）   */

public class Solution {
  
  public int largest(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return 0;
    }
    
    int rows = matrix.length;
    int cols = matrix[0].length;
    
    int[][] leftToRight = new int[rows][cols];
    for (int row = 0; row < rows; row ++) {
      leftToRight[row][0] = matrix[row][0];
      for (int col = 1; col < cols; col ++) {
        if (matrix[row][col] == 0) {
          leftToRight[row][col] = 0;
        } else {
          leftToRight[row][col] = leftToRight[row][col - 1] + 1;
        }
      }
    }
    
    int[][] rightToLeft = new int[rows][cols];
    for (int row = 0; row < rows; row ++) {
      rightToLeft[row][cols - 1] = matrix[row][cols - 1];
      for (int col = cols - 2; col >= 0; col --) {
        if (matrix[row][col] == 0) {
          rightToLeft[row][col] = 0;
        } else {
          rightToLeft[row][col] = rightToLeft[row][col + 1] + 1;
        }
      }
    }
    
    int[][] upToDown = new int[rows][cols];
    for (int col = 0; col < cols; col ++) {
      upToDown[0][col] = matrix[0][col];
      for (int row = 1; row < rows; row ++) {
        if (matrix[row][col] == 0) {
          upToDown[row][col] = 0;
        } else {
          upToDown[row][col] = upToDown[row - 1][col] + 1;
        }
      }
    }
    
    int[][] downToUp = new int[rows][cols];
    for (int col = 0; col < cols; col ++) {
      downToUp[rows - 1][col] = matrix[rows - 1][col];
      for (int row = rows - 2; row >= 0; row --) {
        if (matrix[row][col] == 0) {
          downToUp[row][col] = 0;
        } else {
          downToUp[row][col] = downToUp[row + 1][col] + 1;
        }
      }
    }
    
    int longestCross = 0;
    for (int row = 0; row < rows; row ++) {
      for (int col = 0; col < cols; col ++) {
        if (matrix[row][col] != 0) {
          int curLongestCross = Math.min(
            Math.min(leftToRight[row][col], rightToLeft[row][col]),
            Math.min(upToDown[row][col], downToUp[row][col]));
          longestCross = Math.max(curLongestCross, longestCross);
        }
      }
    }
    return longestCross;
  } 
}
