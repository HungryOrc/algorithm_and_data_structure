/* There is an integer array which has the following features:
The numbers in adjacent positions are different.
And we have:
A[0] < A[1] && A[A.length - 2] > A[A.length - 1].

上面这个条件意义很大！
首先：peak不会出现在数组的第一个或最后一个元素。
即数组不会从第一个元素开始就不断往下走，也不会从最后一个元素开始往回走时不断往下走。
那么这就带来一个很大的便利，即：

如果我们发现某个元素a比它左边相邻的元素小，那么在a与数组的开头之间，一定存在至少一个peak！！
如果我们发现某个元素b比它右边相邻的元素小，那么在b与数组的结尾之间，一定存在至少一个peak！！
如果某个元素比它左右的元素都大，那么它自己就是一个peak了，就它了，game over

We define a position P is a peek if:
A[P] > A[P-1] && A[P] > A[P+1]
Find any peak element in this array. Return the index of the peak. 
The array may contains multiple peeks, find any of them.

Example:
Given [1, 2, 1, 3, 4, 5, 7, 6]
Return index 1 (which is number 2) or 6 (which is number 7). */

class Solution {
    /**
     * @param A: An integers array.
     * @return: return any of peek positions. */
    
    // Ref: http://www.jiuzhang.com/solutions/find-peak-element/
    // 九章式二分
    public int findPeak(int[] A) {
        
        int start = 1, end = A.length-2; // 1.答案在之间，2.不会出界 
        
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            
            // 注意，题意说了，相邻元素之间不存在相等的情况
            if(A[mid] < A[mid - 1]) {
                end = mid;
            } 
            else if(A[mid] < A[mid + 1]) // 而且 mid > mid - 1
            {
                start = mid;
            } else // mid > mid - 1 而且 mid > mid + 1，所以mid就是一个peak了！
            {
                return mid;
            }
        }
        
        if(A[start] < A[end]) {
            return end;
        } else { 
            return start;
        }
    }
}
