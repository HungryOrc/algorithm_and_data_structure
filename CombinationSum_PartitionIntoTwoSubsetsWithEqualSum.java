/* Given a non-empty array containing only positive integers, 
find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Notice:
Each of the array element will not exceed 100.
The array size will not exceed 200.

Example:
Given nums = [1, 5, 11, 5], return true, two subsets: [1, 5, 5], [11]
Given nums = [1, 2, 3, 9], return false */


// 思路：很巧妙的DP方法 ！！！
public class Solution {
    
    /* @param nums a non-empty array only positive integers
     * @return return true if can partition or false */
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }
        
        sum = sum / 2; // half sum
        
        // DP array
        boolean[] canSumTo = new boolean[sum + 1];
        
        for (int i = 0; i < nums.length; i++) {
            // 如果某个元素大于所有元素之和的一半，则一定不可能完成题意！
            // 这个小trick不仅是为了加快运行速度，也是为了避免
            // 在下面这类情况下，boolean 数组不够长的bug：
            // 比如input array只有2个元素，一个1，一个9，所有元素之和的一半是5，小于9
            if (nums[i] > sum) {
                return false;
            }
            
            canSumTo[nums[i]] = true;
            
            for (int trySum = sum; trySum > nums[i]; trySum--) {
                if (canSumTo[trySum - nums[i]] == true) {
                    canSumTo[trySum] = true;
                }
            }
            
            if (canSumTo[sum] == true) { // 如果提前满足条件，就可以提前结束程序
                return true;
            }
        }
        return false;
    }
}
