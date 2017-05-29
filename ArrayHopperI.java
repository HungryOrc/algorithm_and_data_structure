/* Given an array A of non-negative integers, you are initially positioned at index 0 of the array. 
A[i] means the maximum jump distance from that position (you can only jump towards the end of the array). 
Determine if you are able to reach the last index.

Assumptions
1. The given array is not null and has length of at least 1.
2. If the length of the given array is 1, then we define the result to be true, even if array[0] = 0.
3. 数组最后一个元素一定可以达到最后一个元素，就算这个元素的值为0，它也达到了，因为它自己本身就是最后一个，不用走也自然达到

Examples
{1, 3, 2, 0, 3}, we are able to reach the end of array(jump to index 1 then reach the end of the array)
{2, 1, 1, 0, 2}, we are not able to reach the end of array */

/* DP
思路：从后往前搞。dp[i] 表示从元素i 出发，能否到达最后一个元素。最后一个元素一定能达到最后一个元素，然后一个一个往前检查，
对于元素i，如果在它的向后的max hop distance以内的任何元素可以到达最后的元素，则i也可以到达最后的元素，此时此次loop检查就可以终止，
然后开始对元素 i-1 的检查。
Time: O(n^2)
Space: O(n) */

public class Solution { 
  
  public boolean canJump(int[] array) {
    int n = array.length; // we have guaranteed that n >= 1
    if (n == 1) {
      return true; // always true when the length of the given array is 1
    }
    
    boolean[] yesWeCan = new boolean[n];
    yesWeCan[n - 1] = true; // always true for the last element in the array
    
    for (int i = n - 2; i >= 0; i--) {
      int maxJump = array[i];
      
      for (int jump = maxJump; jump >= 1; jump--) {
        int destination = i + jump;
        
        if (destination == n - 1) {
          return true;
        }
        else if (destination < n - 1) { //
          if (yesWeCan[destination] == true) {
            yesWeCan[i] = true;
            break;
          }
        }
      }
      // default value for dp[i]: false
    }
    
    return yesWeCan[0];
  } 
}
