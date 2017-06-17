/* Given an array of integers, move all the 0s to the right end of the array.
The relative order of the elements in the original array need to be maintained.
Assumptions: The given array is not null.

Examples:
{1} --> {1}
{1, 0, 3, 0, 1} --> {1, 3, 1, 0, 0}   */


// 快慢双指针，同向而行。采用这样同向而行的双指针，原数组里的元素的相对位置  不  变 ！！
// Time: O(n)
// Space: O(1)，In-Place

public class Solution {
  
  public int[] moveZero(int[] array) {
    if (array == null || array.length <= 1) {
      return array;
    }
    
    int slow = 0, fast = 0;
    while (fast < array.length) {
      
      if (array[fast] == 0) {
        fast ++;
      } else {
        array[slow] = array[fast];
        slow ++;
        fast ++;
      }
    }
    
    for (int i = slow; i < array.length; i++) {
      array[i] = 0;
    }
    
    return array;
  } 
}
