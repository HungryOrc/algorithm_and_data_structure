/* Given an array of integers, move all the 0s to the right end of the array.
The relative order of the elements in the original array does not need to be maintained.
Assumptions:
The given array is not null.

Examples:
{1} --> {1}
{1, 0, 3, 0, 1} --> {1, 3, 1, 0, 0} or {1, 1, 3, 0, 0} or {3, 1, 1, 0, 0} */

// 我的方法。用“隔板”的思想来做
public class Solution {

  public int[] moveZero(int[] array) {
    
    if (array == null || array.length <= 1) {
      return array;
    }
    
    // to the right side of divider (not including divider), are all 0
    int divider = array.length - 1;
    int index = 0;
    
    while (index < divider) {
      while (index < divider && array[divider] == 0) {
        divider --;
      }
      while (index < divider && array[index] != 0) {
        index ++;
      }
      if (index < divider && array[index] == 0) {
        swap(array, index, divider);
        index ++;
        divider --;
      }
    }
    return array;
  }    
  
  private void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}
