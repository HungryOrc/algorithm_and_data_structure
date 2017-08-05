/* Matrix里面的值都是整数。要求的这个path是可以转弯的，但是每一步只能走上下左右四个直的方向，不能斜着走。
path上的每一点的值都必须 > 前一点的值。
返回这个最长path的长度。

思路：记忆化的 DFS
做一个int矩阵 M，它的长宽和题目给的Matrix相同。M[i][j] 的意思是 从题目给的Matrix的[i][j]元素开始，能得到的 
longest ascending path 的长度。  */


public class Solution {

    public int longestAscendingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return -1;
        }
      
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] M = new int[n][m];
        int maxLength = 0;
      
        // 对原矩阵里的每一个点，都要从它出发，搞一次dfs
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maxLength = Math.max(maxLength, dfs(matrix, M, i, j));
            }
        }
      
        return maxLength;
    }
  
    private int dfs(int[][] matrix, int[][] M, int x, int y) {
        
        // 特别注意这一句 ！！！
        // M里的元素，一旦被确定以后，就是定了 ！！！
        // 而且这个过程会巨量地减枝 ！！！ 比如从[0][0]开始，走dfs，能确定一大批 M[i][j] 的值 ！！！
        if (M[x][y] > 0) {
            return M[x][y];
        }
      
        // 从[x][y]出发往下走的四个方向
        // 精妙之处在于，把接下来一步的dfs放到max compare的里面去了 ！！！ 就是说做max compare的时候一开始还不知道比较对象到底是多大 ！！！
        if (x + 1 < n && matrix[x + 1][y] > matrix[x][y]) {
            M[x][y] = Math.max(M[x][y], dfs(matrix, M, x + 1, y) + 1);
        }
        if (x - 1 >= 0 && matrix[x - 1][y] > matrix[x][y]) {
            M[x][y] = Math.max(M[x][y], dfs(matrix, M, x - 1, y) + 1);
        }
        if (y + 1 < m && matrix[x][y + 1] > matrix[x][y]) {
            M[x][y] = Math.max(M[x][y], dfs(matrix, M, x, y + 1) + 1);
        }
        if (y - 1 >= 0 && matrix[x][y - 1] > matrix[x][y]) {
            M[x][y] = Math.max(M[x][y], dfs(matrix, M, x, y - 1) + 1);
        }
      
        return M[x][y];
    }
}
