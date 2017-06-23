/* Given two sorted arrays of integers, find the Kth smallest number.
Assumptions:
The two given arrays are not null and at least one of them is not empty
K >= 1, K <= total lengths of the two sorted arrays

Examples:
A = {1, 4, 6}, B = {2, 3}, the 3rd smallest number is 3.
A = {1, 2, 3, 4}, B = {}, the 2nd smallest number is 2. */


// 思路：详细解释请看我总结的另一题：Median of Two Sorted Arrays

public class Solution {
  
  public int kth(int[] A, int[] B, int k) {
    return findKthSmallest(A, 0, B, 0, k);
  }
  
  // 这个helper function，和 Median of Two Sorted Arrays 那一题里的 helper function 是一模一样的 ！！
  private int findKthSmallest(int[] A, int startIndexA, int[] B, int startIndexB, int k) {
    
    // if we have past the end of array A, then we totally count on array B
    if (startIndexA == A.length) {
      return B[startIndexB + k - 1];
    }
    // if we have past the end of array B, then we totally count on array A
    if (startIndexB == B.length) {
      return A[startIndexA + k - 1];
    }
    
    // if we have already removed k-1 smallest numbers in these 2 arrays, 
    // namely the next smallest number will be our target
    if (k == 1) {
      return Math.min(A[startIndexA], B[startIndexB]);
    }
    
    // 
    if (startIndexA + k/2 - 1 >= A.length) {
      return findKthSmallest(A, startIndexA, B, startIndexB + k/2, k - k/2);
    }
    if (startIndexB + k/2 - 1 >= B.length) {
      return findKthSmallest(A, startIndexA + k/2, B, startIndexB, k - k/2);
    }
    
    //
    if (A[startIndexA + k/2 - 1] >= B[startIndexB + k/2 - 1]) {
      return findKthSmallest(A, startIndexA, B, startIndexB + k/2, k - k/2);
    } else {
      return findKthSmallest(A, startIndexA + k/2, B, startIndexB, k - k/2);
    }
  }
}
