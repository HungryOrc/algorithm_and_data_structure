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
    // Ref: https://leetcode.com/problems/sparse-matrix-multiplication/
    // A sparse matrix can be represented as a sequence of rows, 
    // each of which is a sequence of (column-number, value) pairs of the nonzero values in the row.
    // So let's create a non-zero array for A, and do multiplication on B.
    public int[][] multiply(int[][] A, int[][] B) 
    {
       int totalRow = A.length;
       int interDimension = A[0].length; 
       int totalCol = B[0].length;
       int[][] result = new int[totalRow][totalCol];

       // create a non-zero array for A
       ArrayList[] nonZeroInA = new ArrayList[totalRow];
       for(int i = 0; i < totalRow; i++) 
       {
          ArrayList<Integer[]> nonZeroInRowI = new ArrayList<Integer[]>();
          for(int j = 0; j < interDimension; j++) 
          {
              if(A[i][j] != 0)
              {
                  // 注意！这里要定义为 Integer的数组，而不能是 int的数组！
                  // 否则后面这个小数组无法被加到 ArrayList 里面去
                  Integer[] colAndValue = new Integer[2]; 
                  colAndValue[0] = j; 
                  colAndValue[1] = A[i][j];
                  
                  nonZeroInRowI.add(colAndValue);
              }
          }
          nonZeroInA[i] = nonZeroInRowI;
       }
      
       // do multiplication on B
       for(int i = 0; i < totalRow; i++) 
       {
          ArrayList<Integer[]> nonZeroInRowI = nonZeroInA[i];
          for(int k = 0; k < nonZeroInRowI.size(); k++) 
          {
              int colA = nonZeroInRowI.get(k)[0];
              int valA = nonZeroInRowI.get(k)[1];
              
              for(int j = 0; j < totalCol; j ++) 
              {
                  int valB = B[colA][j];
                  
                  if (valB != 0)
                      result[i][j] += valA * valB;
              }
           }
        }
        return result;   
    }
  
  
    // 根据上面的思路，用两个HashMap分别来存储 A的各个列里 和 B的各个行里 不为0的元素
    // 思路更严整了。但运行速度似乎更慢。也许是 HashMap 的问题
    public static int[][] multiply(int[][] A, int[][] B) 
    {
       int totalRow = A.length;
       int interDimension = A[0].length; 
       int totalCol = B[0].length;
       int[][] product = new int[totalRow][totalCol];

       // create an Array of HashMaps for the Non-Zero elements in Matrix A
       // each HashMap represents a row in A
       HashMap<Integer,Integer>[] nonZeroInA = new HashMap[totalRow];
       for(int i = 0; i < totalRow; i++) 
       {
          HashMap<Integer,Integer> nonZeroInRowIOfA = new HashMap<>();
          for(int j = 0; j < interDimension; j++) 
          {
              if(A[i][j] != 0)
            	  nonZeroInRowIOfA.put(j, A[i][j]);
          }
          nonZeroInA[i] = nonZeroInRowIOfA;
       }
      
       // create an Array of HashMaps for the Non-Zero elements in Matrix B
       // each HashMap represents a column in B
       HashMap<Integer,Integer>[] nonZeroInB = new HashMap[totalCol];
       for(int i = 0; i < totalCol; i++) 
       {
          HashMap<Integer,Integer> nonZeroInColIOfB = new HashMap<>();
          for(int j = 0; j < interDimension; j++) 
          {
              if(B[j][i] != 0)
            	  nonZeroInColIOfB.put(j, B[j][i]);
          }
          nonZeroInB[i] = nonZeroInColIOfB;
       }
       
       // for each row in A
       for (int i = 0; i < totalRow; i++) 
       {
          HashMap<Integer,Integer> nonZeroInRowIOfA = nonZeroInA[i];
          
          // for each col in B
          for (int j = 0; j < totalCol; j++)
          {
        	  HashMap<Integer,Integer> nonZeroInColJOfB = nonZeroInB[j];
        	  
        	  // for a given column (for example column x) in row i of A, 
        	  // check if the row x in B is zero. If not zero, then do the multiplication
	          for(int colInA : nonZeroInRowIOfA.keySet()) 
	          { 
	        	  if (nonZeroInColJOfB.containsKey(colInA))
	              {
	        		  int elementInA = nonZeroInRowIOfA.get(colInA);
	                  int elementInB = nonZeroInColJOfB.get(colInA);
	               
	                  product[i][j] += elementInA * elementInB;
	              }
	           }
           }
        }
        return product;   
    }
  
}
