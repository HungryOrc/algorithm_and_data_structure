/* Given an array A of non-negative integers, you are initially positioned at index 0 of the array. 
A[i] means the maximum jump distance from index i (you can only jump towards the end of the array). 
Determine the minimum number of jumps you need to reach the end of array. If you can not reach the end of the array, return -1.
Assumptions: The given array is not null and has length of at least 1.

Examples:
{3, 3, 1, 0, 4}, the minimum jumps needed is 2 (jump to index 1 then to the end of array)
{2, 1, 1, 0, 2}, you are not able to reach the end of array, return -1 in this case. */

// DP
// Time: O(n^2)
// Space: O(n). This question cannot be simplified to a O(1) space complexity.
public class Solution {
  
  public int minJump(int[] array) {
    
    int[] numOfHops = new int[array.length];
    for (int i = 0; i < array.length - 1; i++) {
      numOfHops[i] = Integer.MAX_VALUE;
    }
    numOfHops[array.length - 1] = 0; // it is already at the destination
    
    for (int i = array.length - 2; i >= 0; i--) {
      int maxHop = array[i];
      int maxDestination = Math.min(array.length - 1, i + maxHop);
      
      for (int j = i + 1; j <= maxDestination; j++) {
        if (numOfHops[j] < Integer.MAX_VALUE) {
          numOfHops[i] = Math.min(numOfHops[i], numOfHops[j] + 1);
        }
      }
    }
    
    if (numOfHops[0] == Integer.MAX_VALUE) {
      return -1;
    } else {
      return numOfHops[0];
    }
  } 
}
