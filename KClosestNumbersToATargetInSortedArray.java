/* Given a target number, a non-negative integer k and an integer array A sorted in ascending order, 
find the k closest numbers to target in A, sorted in ascending order by the difference between the number and target. 
Otherwise, sorted in ascending order by number if the difference is same.

Example
Given A = [1, 2, 3], target = 2 and k = 3, return [2, 1, 3].
Given A = [1, 4, 6, 8], target = 3 and k = 3, return [4, 1, 6].

Challenge: O(logn + k) time complexity. */

// 我的方法。哦也！
public class Solution {

    public int[] kClosestNumbers(int[] A, int target, int k) {
        
        int[] result = new int[k];
        if (A == null || A.length == 0 || k <= 0 || k > A.length) {
            return result;
        }
        
        int nearestIndex = getNearestIndex(A, target);
        int higherIndex = nearestIndex + 1;
        int lowerIndex = nearestIndex - 1;
        
        result[0] = A[nearestIndex];
        int indexInResult = 0;

        while (higherIndex < A.length && lowerIndex >= 0 && indexInResult < k - 1) {
            
            int diffOfHigherIndex = Math.abs(A[higherIndex] - target);
            int diffOfLowerIndex = Math.abs(A[lowerIndex] - target);
            indexInResult ++;
            
            if (diffOfHigherIndex > diffOfLowerIndex) {
                result[indexInResult] = A[lowerIndex];
                lowerIndex --;
            } else if (diffOfHigherIndex < diffOfLowerIndex) {
                result[indexInResult] = A[higherIndex];
                higherIndex ++;
            } else { // ==
                // 差距相等时，向result里先放入小的元素。A[lowerIndex]必然比A[higherIndex]小
                result[indexInResult] = A[lowerIndex];
                lowerIndex --;
            }
        }
        
        // result填满了
        if (indexInResult == k - 1) {
            return result;
        } 
        // result没填满，其实是 higherIndex 或 lowerIndex 越界了
        else {

            if (lowerIndex < 0) {
                for (int i = indexInResult + 1; i < k; i++) {
                    result[i] = A[higherIndex];
                    higherIndex ++;
                }
            } else if (higherIndex >= A.length) {
                for (int i = indexInResult + 1; i < k; i++) {
                    result[i] = A[lowerIndex];
                    lowerIndex --;
                }
            }
            
            return result;
        }
    }
    
    private int getNearestIndex(int[] A, int target) {
        int left = 0, right = A.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (A[mid] > target) {
                right = mid;
            } else if (A[mid] < target) {
                left = mid;
            } else { // ==
                return mid;
            }
        }
        
        if (Math.abs(A[left] - target) > Math.abs(A[right] - target)) {
            return right;
        } else if (Math.abs(A[left] - target) < Math.abs(A[right] - target)) {
            return left;
        } else { // ==
            // 二者与target之差相等时，要取left即较小的那一个先填入之后的result数组里！
            return left;
        }
    }
}
