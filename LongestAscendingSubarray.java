/* Given an unsorted array, find the length of the longest subarray in which the numbers are in ascending order. 
这里的 ascending 必须是 大于，不可 大于等于。
Assumptions: The given array is not null.
区别：Subarray 必须是连续的。Sub-sequence 可以是不连续的，但还是得按照原数组里的相对顺序来。

Examples:
{7, 2, 3, 1, 5, 8, 9, 6}, longest ascending subarray is {1, 5, 8, 9}, length is 4.
{1, 2, 3, 3, 4, 4, 5}, longest ascending subarray is {1, 2, 3}, length is 3.  */


// 方法1：DP
// DP数组 longest[i] 的意思是：以原数组里第 i 个元素结尾的，起始位置任意的，最长的单调上升Subarray的长度
// Time: O(n)
// Space: O(n)，且可优化为 O(1)
public class Solution {

  public int longest(int[] array) {
    if (array == null || array.length == 0) {
      return 0;
    }
    
    int maxLength = 1;
    
    int[] longest = new int[array.length];
    longest[0] = 1;
    for (int i = 1; i < array.length; i++) {
      if (array[i] > array[i - 1]) {
        longest[i] = longest[i - 1] + 1;
        maxLength = Math.max(maxLength, longest[i]);
      } else {
        longest[i] = 1;
      }
    }
    
    return maxLength;
  }  
}


// 方法2：Sliding Window。思维与上面的 DP 有相似之处
// Time: O(n)
// Space: O(1)
public class Solution {

  public int longest(int[] array) {
    if (array == null || array.length == 0) {
      return 0;
    }
    
    int maxLength = 1;
    
    for (int left = 0, right = 1; right < array.length; right ++) {
      
      if (array[right] > array[right - 1]) {
        maxLength = Math.max(maxLength, right - left + 1);
      } else {
        left = right;
      }
    }
    
    return maxLength;
  } 
}
