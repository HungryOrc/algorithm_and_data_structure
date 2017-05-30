/* Given an unsorted array, find the starting and ending index of the longest subarray in which the numbers are in ascending order. 
这里的 ascending 必须是 大于，不可 大于等于。
Assumptions: The given array is not null.
区别：Subarray 必须是连续的。Sub-sequence 可以是不连续的，但还是得按照原数组里的相对顺序来。

Examples:
{7, 2, 3, 1, 5, 8, 9, 6}, longest ascending subarray is {1, 5, 8, 9}, return the subarray's indexes: {3, 7}.  */

// DP
// DP数组 longest[i] 的意思是：以原数组里第 i 个元素结尾的，起始位置任意的，最长的单调上升Subarray的长度
// Time: O(n)
// Space: O(n)，且可优化为 O(1)
public class Solution {

  public int[] longest(int[] array) {
    if (array.length == 0) { // it's guaranteed that the array is not null
      return new int[2];
    }
    
    int left = 0;
    int right = 0;
    int finalLeft = left;
    int finalRight = right;
    int maxLength = 1;
    
    int[] longest = new int[array.length];
    longest[0] = 1;
    for (int i = 1; i < array.length; i++) {
      if (array[i] > array[i - 1]) {
        longest[i] = longest[i - 1] + 1;
        right = i;
        
        if (longest[i] > maxLength) {
          maxLength = longest[i];
          
          finalLeft = left;
          finalRight = right;
        }
      } else { // array[i] <= array[i - 1]
        left = i;
        right = i;
        longest[i] = 1;
      }
    }
    
    return new int[]{finalLeft, finalRight};
  }  
}
