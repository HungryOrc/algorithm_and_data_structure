/* Given a n x n matrix where each of the rows and columns are sorted in ascending order, 
find the kth smallest element in the matrix.
Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:
matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,
return 13.

Note: You may assume k is always valid, 1 ≤ k ≤ n2.

注意：

1. 并不一定沿着每一条对角线，从右上方到左下方越来越大。反例如下：
1  2  3
2  5  6
2  7  9

2. 并不一定靠右的对角线上的任何数都比靠左的对角线上的任何数要大。反例如下：
1  3  5
6  7  12
11 14 14
上面第一排最右边的5，就比第二排第一个的6要小。 */

// 用Binary Search做。我没有想到。很厉害
// Ref: https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;//[lo, hi)
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0,  j = matrix[0].length - 1;
            for(int i = 0; i < matrix.length; i++) {
                while(j >= 0 && matrix[i][j] > mid) j--;
                count += (j + 1);
            }
            if(count < k) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}
