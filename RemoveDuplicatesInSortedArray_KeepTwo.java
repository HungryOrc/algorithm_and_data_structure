/* Given a sorted integer array, remove duplicate elements. 
For each group of elements with the same value keep at most two of them. Do this in-place, 
using the left side of the original array and maintain the relative order of the elements of the array. 
Return the array after deduplication.
Assumptions: The given array is not null
关键：每一堆连续重复的数字里，保留两个，而非保留一个。

Examples:
{1, 2, 2, 3, 3, 3} → {1, 2, 2, 3, 3} */

public class Solution {
  
  public int[] dedup(int[] array) {
    if (array == null || array.length <= 2) {
      return array;
    }
    
    int slow = 2, fast = 2;
    while (fast < array.length) {
      // 精华所在 ！！！ 与普通的数组去重题的 唯一的区别之处 ！！！
      // 即：用 slow - 2 代替 slow - 1
      while (fast < array.length && array[fast] == array[slow - 2]) {
        fast ++;
      }
      if (fast < array.length && array[fast] != array[slow - 2]) {
        array[slow] = array[fast];
        slow ++;
        fast ++;
      }
    }
    
    return Arrays.copyOf(array, slow); // index slow is excluded
  }
}
