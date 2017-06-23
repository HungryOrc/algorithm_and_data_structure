/* Given two sorted arrays of integers in ascending order, find the median value.

Assumptions
The two given array are not null and at least one of them is not empty
The two given array are guaranteed to be sorted

Examples
A = {1, 4, 6}, B = {2, 3}, median is 3
A = {1, 4}, B = {2, 3}, median is 2.5
 
思路 Ref: http://www.cnblogs.com/yuzhangcmu/p/4138184.html
代码 Ref: http://www.jiuzhang.com/solutions/median-of-two-sorted-arrays/

方法：
用 find Kth Smallest Number 的思想。k 就是median所在的序号。
如果两个数组的总长度 total length 是奇数，则 k = totalLen / 2 + 1；
如果是偶数，则median是最中间的两个数之和除以二，即 median = ( 第(totalLen/2)小的数 + 第(totalLen/2+1)小的数 ) / 2

为了达到Log的复杂度，我们可以：
每次在A，B取前k/2个元素。有以下这些情况：
1) 如果A里的元素不够k/2个，丢弃B里的前k/2个元素. 反之亦然
2) 如果 A[mid] <= B[mid], (mid = k/2 - 1），丢弃A的前k/2个元素。反之亦然
3) 一开始k=k，然后k=k/2，k=k/4... 到最后k=1的时候，那么2个数组里下一个最小的数就是我们要的数了
4) 如果两个数组里的任何一个被丢弃光了，则自然剩下的数都在另一个数组里找 */

public class Solution {
  
  public double median(int[] A, int[] B) {
    int totalLen = A.length + B.length;
    
    if (totalLen % 2 == 1) { // median's index: k = totalLen / 2 + 1
      return findKthSmallest(A, 0, B, 0, totalLen / 2 + 1);
    } else { // median's index k = (totalLen / 2 + totalLen / 2 + 1) / 2;
      return (findKthSmallest(A, 0, B, 0, totalLen / 2) + findKthSmallest(A, 0, B, 0, totalLen / 2 + 1)) / 2.0;
    }
  }
  
  // find the k-th smallest number of 2 ascending sorted arrays
  // we remove k/2 smallest numbers, k/4 smallest numbers... 1 smallest number in each time
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
    
    // 如果A里剩下的的元素不够k/2个，丢弃B里的前k/2个元素. 反之亦然
    if (startIndexA + k/2 - 1 >= A.length) {
      return findKthSmallest(A, startIndexA, B, startIndexB + k/2, k - k/2);
    }
    if (startIndexB + k/2 - 1 >= B.length) {
      return findKthSmallest(A, startIndexA + k/2, B, startIndexB, k - k/2);
    }
    
    // 如果 A[mid] <= B[mid], (mid = k/2 - 1），丢弃A的前k/2个元素。反之亦然
    if (A[startIndexA + k/2 - 1] >= B[startIndexB + k/2 - 1]) {
      return findKthSmallest(A, startIndexA, B, startIndexB + k/2, k - k/2);
    } else {
      return findKthSmallest(A, startIndexA + k/2, B, startIndexB, k - k/2);
    }
  }
}
