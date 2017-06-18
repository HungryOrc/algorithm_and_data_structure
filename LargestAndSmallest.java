/* Use the least number of comparisons to get the largest and smallest number in the given integer array. 
Return the largest number and the smallest number.
Assumptions: The given array is not null and has length of at least 1

Examples:
{2, 1, 5, 4, 3}, the largest number is 5 and smallest number is 1. return [5, 1].  */

/* 思路：
把数组里的数字两两配对。找出每一对里的较大值和较小值。时间 O(0.5n)
然后在较大的那一半里找一遍，得到最大值。时间 O(0.5n)
最后在较小的那一半里找一遍，得到最小值。时间 O(0.5n)
一共是 O(1.5n)

具体做法：
把数组里的第一个和最后一个相比、第二个和倒数第二个相比…… 把较大的swap到后面去，较小的swap到前面去。
然后在前半个数组里找到最小的那个，在后半个数组里找到最大的那个。
如果数组一共有奇数个数字，那么中间那个数在第一步的配对里不会被触及，而它却是有可能是最大值也有可能是最小值的，所以两头都
不能少了它 ！！ 即最后前半段数组里的比较也要包含它，最后后半段数组里的比较也要包含它。   */

public class Solution {
  
  public int[] largestAndSmallest(int[] array) {
    int n = array.length;
    
    for (int i = 0; i < n / 2; i++) {
      if (array[i] > array[n - 1 - i]) {
        swap(array, i, n - 1 - i);
      }
    }
    
    int min = Integer.MAX_VALUE;
    for (int i = 0; i <= n / 2; i++) {
      if (array[i] < min) {
        min = array[i];
      }
    }
    
    int max = Integer.MIN_VALUE;
    for (int i = n / 2; i < n; i++) {
      if (array[i] > max) {
        max = array[i];
      }
    }
    
    return new int[]{max, min};
  }
  
  private void swap(int[] array, int i, int j) {
    int tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }
}
