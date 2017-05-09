/* Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

For example, given the following matrix:
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4. */

/* 思路：DP
Ref: https://discuss.leetcode.com/topic/15328/easy-dp-solution-in-c-with-detailed-explanations-8ms-o-n-2-time-and-o-n-space

设置一个DP矩阵，尺寸与原矩阵相同。这个DP矩阵里的cell：DP[i][j]，表示以原矩阵里的cell：matrix[i][j]为右下角的正方形的最大可能边长。
(1) 易知，对于第一行来说，DP[i][j] = matrix[i][j]（要么0要么1）。对于第一列来说，也是如此。这样就完成了DP的边界条件的输入。
(2) 对于从第二行第二列开始的坐标为[i][j]的cell来说，
    如果matrix[i][j] == 0，那么以它为右下角的正方形的最大可能边长一定也是零。
    如果matrix[i][j] == 1，那么有戏。画一下分析图就可知，DP[i][j] = Math.min(DP[i-1][j-1], DP[i][j-1], DP[i-1][j]) + 1，
    即这些cell的DP值是由它的 左边、上方、以及左上方 这3个cell的DP值所决定的！   */

// 方法1：上面思路的实现
// Time: O(n*m), Space: O(n*m)
public class Solution {
    
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] maxSide = new int[rows][cols];
        int maxSideOverall = 0;
        
        for (int col = 0; col < cols; col++) {
            if(matrix[0][col] == '1') {
                maxSide[0][col] = 1;
                maxSideOverall = 1;
            }
        }
        for (int row = 0; row < rows; row++) {
            if(matrix[row][0] == '1') {
                maxSide[row][0] = 1;
                maxSideOverall = 1;
            }
        }
        
        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                if (matrix[row][col] == '1') {
                    maxSide[row][col] = Math.min(maxSide[row - 1][col - 1], 
                        Math.min(maxSide[row - 1][col], maxSide[row][col - 1])) + 1;
                    maxSideOverall = Math.max(maxSideOverall, maxSide[row][col]);
                }
            }
        }
        
        return maxSideOverall * maxSideOverall;
    }
}


/* 方法2：对上面方法的空间优化。
我们可以看到，其实计算每个cell的最大边长，只需要这个cell的左边一个，左上一个，上边一个，这三个值。
所以我们其实只用维护一个一维数组，长度是原矩阵的列数即可。
这时候要注意每一行的第一个以及最后一个cell的处理，会有一点需要特别处理的地方。
这样做之后，空间消耗从 O(rows*cols) 降低到 O(cols)。时间消耗理论上不变，但实测表明比上面的方法也要好了一点 */
public class Solution {
    
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] maxSide = new int[cols];
        int maxSideOverall = 0;
        
        for (int col = 0; col < cols; col++) { // 输入第一行作为DP的边界条件
            if(matrix[0][col] == '1') {
                maxSide[col] = 1;
                maxSideOverall = 1;
            }
        }
        
        for (int row = 1; row < rows; row++) { // 从第二行开始做DP
            // 定义好四个辅助变量！
            int maxSide_Left = 0;
            int maxSide_TopLeft = 0;
            int maxSide_Top = maxSide[0];
            int maxSide_Cur = 0;
            
            if (matrix[row][0] == '1') { // 特殊处理一下每一行的第一列
                maxSide_Cur = 1;
                maxSideOverall = Math.max(1, maxSideOverall);
            }
            
            for (int col = 1; col < cols; col++) { // 每一行的第二列到最后一列，进行DP
                // 更新四个辅助变量的值！
                maxSide_Left = maxSide_Cur;
                maxSide_TopLeft = maxSide_Top;
                maxSide_Top = maxSide[col];
                maxSide_Cur = 0;
                
                if (matrix[row][col] == '1') {
                    maxSide_Cur = Math.min(maxSide_Left, Math.min(maxSide_TopLeft, maxSide_Top)) + 1;
                    maxSideOverall = Math.max(maxSideOverall, maxSide_Cur);
                }
                
                maxSide[col - 1] = maxSide_Left; // 特别注意！！！到这里才能开始更新maxSide数组里的值 ！！！
                // 这个时候是本cell即将移动到右边一个cell的前夕！！！
            }
            
            // 对每一行的最后一列的特殊处理：循环到最右边一列的时候，记得把maxSide数组里的最后一个数也填上！
            maxSide[cols - 1] = maxSide_Cur;
        }
        
        return maxSideOverall * maxSideOverall;
    }
}
