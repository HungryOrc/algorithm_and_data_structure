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

Note: You may assume k is always valid, 1 ≤ k ≤ n^2.

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
public class Solution 
{
    public int kthSmallest(int[][] matrix, int k) 
    {
        int matrixDimen = matrix.length;
        int lowValue = matrix[0][0]; 
        int highValue = matrix[matrixDimen-1][matrixDimen-1] + 1; // [lowValue, highValue)
        
        while(lowValue < highValue) 
        {
            int mid = lowValue + (highValue - lowValue) / 2;
           
            // number of elements in the matrix that are smaller than mid
            // 对每一个mid值，算一下，在matrix里，有多少个数小于或者等于这个mid值
            int count = 0; 
           
            int j = matrixDimen - 1;
            // 从上到下，逐行
            for(int i = 0; i < matrixDimen; i++) 
            {
                // 从右到左，逐列
                while(j >= 0 && matrix[i][j] > mid) 
                    j--;
                count += (j + 1);
            }
            
            // 对于当前的mid值来说，count算出来了。现在把count和k做对比
            if(count < k) 
                lowValue = mid + 1;
            else 
                highValue = mid;
        }
        // 最后return highValue或者lowValue都是一样的
        // 因为按照此处二分法的实施方式，它们两最后一定相等，不会交叉
        return highValue;
    }
}
