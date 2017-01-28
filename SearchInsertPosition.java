/* Given a sorted array and a target value, return the index if the target is found. 
If not, return the index where it would be if it were inserted in order.
You may assume NO duplicates in the array.

Runtime requirement: O(log(n)).

Example
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0 */

public class Solution {
    /* param A : an integer sorted array
     * param target :  an integer to be inserted
     * return : an integer */
    
    // 二分法
    public int searchInsert(int[] A, int target) {
        
        if (A == null || A.length == 0) {
            return 0;
        }
        
        // 找target，找不到的话，实际上是返回第一个大于target的数的index
        // 两种特殊情况：如果数组里最后一个数都小于target的话，要
        // 返回最后一个数的index + 1；
        // 如果数组里第一个数都大于target的话，要返回0
        if (A[A.length - 1] < target) {
            return A.length;
        } else if (A[0] > target) {
            return 0;
        }
        
        int start = 0, end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] > target) {
                end = mid;
            } else if (A[mid] < target) {
                start = mid;
            } else { // ==
                return mid;
            }
        }
        
        // 后期处理
        if (A[start] == target) {
            return start;
        } else if (A[end] == target) {
            return end;
        } else if (A[start] > target) { // 找第一个大于target的数
            return start;
        } else { // A[end] > target，必然如此，细想可知，剩下的只能是这个情况
            return end;
        }
    }
}

