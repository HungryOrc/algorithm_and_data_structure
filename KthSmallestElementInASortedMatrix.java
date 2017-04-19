/* Given a n x m matrix where each of the rows and columns are sorted in ascending order, 
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


// 方法2：用Binary Search做。我没有想到。很厉害
// Ref: https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
public class Solution 
{
    public int kthSmallest(int[][] matrix, int k) 
    {
        int rows = matrix.length;
        int cols = matrix[0].length;
       
        int lowValue = matrix[0][0];
        int highValue = matrix[rows - 1][cols - 1];
        
        while(lowValue < highValue) 
        {
            int mid = lowValue + (highValue - lowValue) / 2;

            // 对每一个mid值，算一下，在matrix里，有多少个数小于或者等于这个mid值
            int count = 0; 
           
            int j = cols - 1;
            // 从上到下，逐行，去掉这一行里 > mid 的数
            for(int i = 0; i < rows; i++) {
                while(j >= 0 && matrix[i][j] > mid) {
                    j--;
                }
                count += (j + 1);
            }
            /* 上面这段也可以换做：从左到右，逐列，去掉这一列里 > mid 的数
            int i = rows - 1;
            for(int j = 0; j < cols; j++) {
                while(i >= 0 && matrix[i][j] > mid) {
                    i--;
                }
                count += (i + 1);
            } */
            
            // 对于当前的mid值来说，count算出来了。现在把count和k做对比
            if (count < k) 
                lowValue = mid + 1;
            else if (count >= k)
                highValue = mid;
            /* 注意！！！上面这段 if else 水特别深 ！！！
            >= k 时，不要 mid-1，只要mid ！！！
            == k 时，不要马上 return mid，而是非要等到最后 high 和 low重合再return！！！这样的目的是
            找到实际存在于matrix里的第k个数！！！！！而非某个大于matrix里的第k个数而小于matrix里的第k+1个数 的数 ！！！！！ */
        }
        // 最后return highValue或者lowValue都是一样的
        // 因为按照此处二分法的实施方式，它们两最后一定相等
        return highValue;
    }
}
