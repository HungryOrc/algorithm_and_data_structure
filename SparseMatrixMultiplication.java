/* Given two sparse matrices A and B, return the result of AB.
You may assume that A's column number is equal to B's row number.

Example:
A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]
B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]
     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
*/

public class Solution 
{
    // 笨办法。速度非常慢
    public int[][] multiply(int[][] A, int[][] B) 
    {
        int totalRow = A.length;
        int totalCol = B[0].length;
        int interDimension = B.length;
        int[][] result = new int[totalRow][totalCol];
        
        for (int i = 0; i < totalRow; i++)
        {
            for (int j = 0; j < totalCol; j++)
            {
                for (int k = 0; k < interDimension; k++)
                {
                    if (A[i][k] != 0 && B[k][j] != 0)
                        result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }



}
