/* Write an efficient algorithm that searches for a value in an m x n matrix, return the occurrence of it.
This matrix has the following properties:
Integers in each row are sorted from left to right.
Integers in each column are sorted from up to bottom.
No duplicate integers in each row or column.

Requirement: O(m+n) time and O(1) extra space.

Example
Consider the following matrix:
[
  [1, 3, 5, 7],
  [2, 4, 7, 8],
  [3, 5, 9, 10]
]
Given target = 3, return 2. */

public class Solution {
    /**
     * @param matrix: A list of lists of integers
     * @param: A number you want to search in the matrix
     * @return: An integer indicate the occurrence of target in the given matrix
     */
     
     
    // 用二分法，也没多快
    public int searchMatrix(int[][] matrix, int target) {
        
        if (matrix == null || matrix.length == 0) {
            return 0;
        } else if (matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        
        int count = 0;
        
        int totalRows = matrix.length;
        int totalCols = matrix[0].length;
        
        int startCol = 0;
        int endCol = totalCols - 1;
        int lastFoundCol = totalCols;
        
        for (int i = 0; i < totalRows; i++) {
            
            int foundCol = -1;
            
            // 因为每一行以内不存在重复数，每一列以内不存在重复数
            // 但不同的行或者不同的列之间可能存在重复数
            // 所以我们在任何一行内找一个target的时候，要么一个，要么无
            // 如果在某一行找到了，则其下方的一行以找到的列的左边一列为最右列开始新一轮查找
            // 如果在某一行没找到，则可能是整行都太大，或者整行都太小，或者范围没问题但target就是没出现，
            // 那么其下方的一行依据这三种不同的情况分别处理
            while (startCol < endCol) {
                
                int midCol = startCol + (endCol - startCol) / 2;
                if (matrix[i][midCol] == target) {
                    foundCol = midCol;
                    break;
                } else if (matrix[i][midCol] > target) {
                    endCol = midCol - 1;
                } else {
                    startCol = midCol + 1;
                }
            }
            
            // if the above while loop didn't end in the middle process,
            // namely now startCol == endCol
            if (foundCol == -1) {
                if (matrix[i][startCol] == target) {
                    foundCol = startCol;
                } 
            }
            
            // if finally the target is found in this row i
            if (foundCol != -1) {
                lastFoundCol = foundCol;
                count ++;
                
                // if we found the target at the 1st column in this row
                // terminate everything and the count is done
                if (foundCol == 0) {
                    return count;
                // if we found the target at 2,3,4... column in this row
                } else { // foundCol > 0
                    endCol = foundCol - 1;
                    startCol = 0;
                }
            }
            // if finally the target is not found in this row i
            else {
                // if the whole row is bigger than the target
                // terminate everything and the count is done
                if (matrix[i][0] > target) {
                    return count;
                }
                // if the whole row is smaller than the target
                else if (matrix[i][totalCols - 1] < target) {
                    endCol = Math.min(lastFoundCol, totalCols) - 1;
                    startCol = 0;
                }
                // if the range of this row is ok
                else {
                    endCol = Math.min(lastFoundCol, totalCols) - 1;
                    startCol = 0;
                }
            }
        }
        return count;
    }
    
}

