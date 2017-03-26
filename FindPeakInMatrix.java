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

// 方法1：不断地缩小row的范围
// Ref: http://www.jiuzhang.com/solutions/find-peak-element-ii/
// 对行进行binary search。在矩阵的正中间那一行，找到这一行的max cell即max列，
// 在这一列上，如果本cell比上一行的cell小，则查找peak的范围缩小到end row = 上一行，
// 如果本cell比下一行小，则查找peak的范围缩小到 start row = 下一行，
// 如果本cell同时比上一行及下一行都小，则上面两种操作任择其一都可，
// 如果本cell同时比上一行及下一行都大，则本cell就是一个peak了，返回本cell即可
// O(n*logn)
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
            } else { // >下一行 且 >上一行
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


// 方法2：交替地：缩小row，缩小col，缩小row，缩小col……
// 很巧妙！！！
// O(m + n)
// Ref: http://www.jiuzhang.com/solutions/find-peak-element-ii/
class Solution {

    public List<Integer> findPeakII(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        return find(1, n - 2, 1, m - 2, A, true);
    }
  
    private List<Integer> find(int x1, int x2, int y1, int y2, int[][] A, boolean flag) {
      
        if (flag) {
            int mid = x1 + (x2 - x1) / 2;
            int index = y1;
            for (int i = y1; i <= y2; ++i)
                if (A[mid][i] > A[mid][index])
                    index = i;
                    
            if (A[mid - 1][index] > A[mid][index])
                return find(x1, mid - 1, y1, y2, A, !flag); // 逆置flag
            else if (A[mid + 1][index] > A[mid][index])
                return find(mid + 1, x2, y1, y2, A, !flag); // 逆置flag
            else
                return new ArrayList<Integer>(Arrays.asList(mid, index));
          
        } else {
            int mid = y1 + (y2 - y1) / 2;
            int index = x1;
            for (int i = x1; i <= x2; ++i)
                if (A[i][mid] > A[index][mid])
                    index = i;
                    
            if (A[index][mid - 1] > A[index][mid])
                return find(x1, x2, y1, mid - 1, A, !flag); // 逆置flag
            else if (A[index][mid + 1] > A[index][mid])
                return find(x1, x2, mid + 1, y2, A, !flag); // 逆置flag
            else
                return new ArrayList<Integer>(Arrays.asList(index, mid));
        }
    }
}
