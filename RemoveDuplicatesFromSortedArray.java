/* Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array nums = [1,1,2],
Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. 
It doesn't matter what you leave beyond the new length. */

public class Solution {

  // 方法1: 快慢指针，同向而行。slow指针的左边不含它自身是最终要保留的部分
  // 时间：O(n)，空间：In-place
  public int[] dedup(int[] array) {
    if (array == null || array.length == 0) {
      return new int[0];
    }
    
    int slow = 1, fast = 1;
    while (fast < array.length) {
      while (fast < array.length && array[fast] == array[slow - 1]) {
        fast ++;
      }
      if (fast < array.length && array[fast] != array[slow - 1]) {
        array[slow] = array[fast];
        slow ++;
        fast ++;
      }
    }
    
    return Arrays.copyOfRange(array, 0, slow); // index slow is excluded
  }

	
  // 方法2: 快慢指针，同向而行。slow指针自己（含）及其左边是最终要保留的部分
  // 时间：O(n)，空间：In-place
  public int[] dedup(int[] array) {
    if (array == null || array.length == 0) {
      return new int[0];
    }
    
    // 注意，以下有几处与上面方法的不同之处	  
    int slow = 0, fast = 0;
    while (fast < array.length) {
      while (fast < array.length && array[fast] == array[slow]) {
        fast ++;
      }
      if (fast < array.length && array[fast] != array[slow]) {
        slow ++;
        array[slow] = array[fast];
        fast ++;
      }
    }
    
    return Arrays.copyOfRange(array, 0, slow + 1); // index slow is included
  }
	
}
