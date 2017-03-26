/* There is an integer matrix which has the following features:
The numbers in adjacent positions are different.
The matrix has n rows and m columns.
For all i < m, A[0][i] < A[1][i] && A[n - 2][i] > A[n - 1][i].
For all j < n, A[j][0] < A[j][1] && A[j][m - 2] > A[j][m - 1].

We define a position P is a peek if:
A[j][i] > A[j+1][i] && A[j][i] > A[j-1][i] && A[j][i] > A[j][i+1] && A[j][i] > A[j][i-1]
Find a peak element in this matrix. Return the index of the peak.

Notice: The matrix may contains multiple peeks, find any of them.

Challenge:
Solve it in O(n+m) time.
If you come up with an algorithm that you thought it is O(n log m) or O(m log n), 
can you prove it is actually O(n+m) or propose a similar but O(n+m) algorithm?

Example:
Given a matrix:
[
  [1 ,2 ,3 ,6 ,5],
  [16,41,23,22,6],
  [15,17,24,21,7],
  [14,18,19,20,10],
  [13,14,11,10,9]
]
return index of 41 (which is [1,1]) or index of 24 (which is [2,2]) */

class Solution {

    public List<Integer> findPeakII(int[][] A) {
        
        List<Integer> peakCoord = new ArrayList<Integer>();
        if (A == null || A.length == 0 || A[0].length == 0) {
            return peakCoord;
        }
        
        int startRow = 1;
        int endRow = A.length - 2;
        
        while (startRow <= endRow) {
            int midRow = startRow + (endRow - startRow) / 2;
            int maxCol = findMaxColInThisRow(A, midRow);
            
            if (A[midRow][maxCol] < A[midRow + 1][maxCol]) {
                startRow = midRow + 1;
            } else if (A[midRow][maxCol] < A[midRow - 1][maxCol]) {
                endRow = midRow - 1;
            } else { // ==
                peakCoord.add(midRow);
                peakCoord.add(maxCol);
                return peakCoord;
            }
        }
        return null; // actually we will never reach here
    }
    
    private int findMaxColInThisRow(int[][] A, int row) {
        int max = Integer.MIN_VALUE;
        int maxColIndex = -1;
        for (int i = 0; i < A[0].length; i++) {
            if (A[row][i] > max) {
                max = A[row][i];
                maxColIndex = i;
            }
        }
        return maxColIndex;
    }
}
