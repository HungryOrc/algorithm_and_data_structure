/* 连续上升和下降都算，哪个长返回哪个

Give an integer array，find the longest increasing continuous subsequence in this array.
An increasing continuous subsequence:
Can be from right to left or from left to right.
Indices of the integers in the subsequence should be continuous.

要求：
O(n) time and O(1) extra space.

Example
For [5, 4, 2, 1, 3], the LICS is [5, 4, 2, 1], return 4. 这个例子是返回了降序列 ！
For [5, 1, 2, 3, 4], the LICS is [1, 2, 3, 4], return 4. 这个例子是返回了升序列       */

public class Solution {

    public int longestIncreasingContinuousSubsequence(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        
        int n = array.length;
        
        int[] dpIncrease = new int[n];
        int[] dpDecrease = new int[n];
        dpIncrease[0] = 1;
        dpDecrease[0] = 1;
        
        int maxLenIncrease = 1;
        int maxLenDecrease = 1;
        
        for (int i = 1; i < n; i++) {
            dpIncrease[i] = 1;
            dpDecrease[i] = 1;
            
            if (array[i] > array[i - 1]) {
                dpIncrease[i] = dpIncrease[i - 1] + 1;
            } else if (array[i] < array[i - 1]) {
                dpDecrease[i] = dpDecrease[i - 1] + 1;
            }
            
            maxLenIncrease = Math.max(maxLenIncrease, dpIncrease[i]);
            maxLenDecrease = Math.max(maxLenDecrease, dpDecrease[i]);
        }
        
        return Math.max(maxLenIncrease, maxLenDecrease);
    }
}
