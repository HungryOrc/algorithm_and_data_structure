



// 方法2：。我没有想到。很巧妙 ！！！
// 

public class Solution {
   
    public int kthSmallest(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;
       
        int lowValue = matrix[0][0];
        int highValue = matrix[rows - 1][cols - 1];
        
        while(lowValue < highValue) {
            int mid = lowValue + (highValue - lowValue) / 2;

            // 对每一个mid值，算一下，在matrix里，有多少个数小于或者等于这个mid值
            int count = 0; 
           
            // 从上到下，逐行，找这一行里 > mid 的数有多少个
            int j = cols - 1;
            for(int i = 0; i < rows; i++) {
                while(j >= 0 && matrix[i][j] > mid) {
                    j--;
                }
                count += (j + 1);
            }
            /* 上面这段也可以换做：从左到右，逐列，找这一列里 > mid 的数有多少个
            int i = rows - 1;
            for(int j = 0; j < cols; j++) {
                while(i >= 0 && matrix[i][j] > mid) {
                    i--;
                }
                count += (i + 1);
            } */
            
            // 对于当前的mid值来说，count算出来了。现在把count和k做对比
            if (count < k) {
                lowValue = mid + 1;
            } else { // count >= k
                highValue = mid;
            }
            /* 注意！！！
            >= k 时，不要 mid-1，只要mid ！！！
            == k 时，不要马上 return mid，而是非要等到最后 high 和 low重合再return！！！这样的目的是
            找到实际存在于matrix里的第k个数！！！！！而非某个大于matrix里的第k个数而小于matrix里的第k+1个数 的数！！！ */
        }
        // 最后return highValue或者lowValue都是一样的
        // 因为按照此处二分法的实施方式，它们两最后一定相等
        return highValue;
    }
}
