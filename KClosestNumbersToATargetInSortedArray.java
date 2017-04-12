/* Given a target number, a non-negative integer k and an integer array A sorted in ascending order, 
find the k closest numbers to target in A, sorted in ascending order by the difference between the number and target. 
Otherwise, sorted in ascending order by number if the difference is same.

Example
Given A = [1, 2, 3], target = 2 and k = 3, return [2, 1, 3].
Given A = [1, 4, 6, 8], target = 3 and k = 3, return [4, 1, 6].

Challenge: O(logn + k) time complexity. */

// Time: O(log(n) + k)
public class Solution {
  
  public int[] kClosest(int[] array, int target, int k) {
    if (array == null || array.length == 0) {
      return array;
    }
    if (k == 0) {
      return new int[0];  
    }
    
    // step 1: 
    // find the closest number
    int left = 0, right = array.length - 1;
    int mid = 0;
    while (left + 1 < right) {
      mid = left + (right - left) / 2;
      if (array[mid] > target) {
        right = mid;
      } else if (array[mid] < target) {
        left = mid;
      } else { // ==
        break; // we have found the closest: which is equals target
      }
    }
    
    // if we didn't break within the while loop
    if (left + 1 == right) {
      if (Math.abs(target - array[left]) > Math.abs(target - array[right])) {
         mid = right;
      } else {
        mid = left;
      }
    }
    
    // step 2: 
    // expand from mid to k nearest elements
    int[] result = new int[k];
    result[0] = array[mid];
    int foundNumbers = 1;
    
    left = mid - 1;
    right = mid + 1;
    
    while (foundNumbers < k) {
      if (left < 0 || right > array.length - 1) {
        break;
      } 
      
      if (Math.abs(target - array[left]) > Math.abs(target - array[right])) {
        result[foundNumbers] = array[right];
        right ++;
      } else {
        result[foundNumbers] = array[left];
        left --;
      }
      
      foundNumbers ++;
    }
    
    if (foundNumbers < k) {
      if (left >= 0) {
        while (left >= 0 && foundNumbers < k) {
          result[foundNumbers] = array[left];
          left --;
          foundNumbers ++;
        }
      } else { // right <= array.length - 1
        while(right <= array.length - 1 && foundNumbers < k) {
          result[foundNumbers] = array[right];
          right ++;
          foundNumbers ++;
        }
      }
    }
   
    return result; 
  }
  
}
