/* Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

For example, given the following matrix:
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4. */

/* 思路：DP
/ Ref: https://discuss.leetcode.com/topic/15328/easy-dp-solution-in-c-with-detailed-explanations-8ms-o-n-2-time-and-o-n-space

*/

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
