/* Given a 0-1 array, you can flip at most k zeroes to ones. Find the longest subarray that consists of all ones.

思路：sliding window，快慢指针
找到最多含有 k 个 0 的 最长的 window，除了这个 k 个 0 以外，都是 1
注意 ！ 是最多含有 k 个 0 ！ 不是一定含有 k 个 0 ！ */

public class Solution {

    public int flipZeroToOne(int[] input, int k) {
        if (input == null || input.length == 0 || k < 0) {
            return 0;
        }
    
        int maxLength = 0;
        int numOfZeroes = 0;
        int slow = 0, fast = 0;
        
        while (fast < input.length) {
        
            if (numOfZeroes <= k) {
                if (input[fast] == 0) {
                    numOfZeros ++;
                }
                fast ++;
            }
            else { // numOfZeroes > k
                if (input[slow] == 0) {
                    numOfZeroes --;
                }
                slow ++;
            }
            maxLength = Math.max(maxLength, fast - slow);
        }
        return maxLength;
    }
}
