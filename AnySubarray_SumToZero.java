/* Given an integer array, find a subarray where the sum of numbers is zero. 
Your code should return the index of the first number and the index of the last number.

Example
Given [-3, 1, 2, -3, 4], return [0, 2] or [1, 3]. */

// 方法: Prefix Sum
public class Solution {

    public ArrayList<Integer> subarraySum(int[] nums) {
        ArrayList<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        // <prefixSum, index>
        HashMap<Integer, Integer> prefixSumsAndIndexes = new HashMap<>();
        
        // 特别注意 ！！！不能少了这个 ！！！
        // 这个是为了处理：从 index=0 开始的subarray ！！！
        prefixSumsAndIndexes.put(0, -1);
        
        int prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            
            // 如果map里已经有了，就是说在当前的 i 之前是有的！！！
            // 不可能和当前的撞车！！！因为当前这次的记录还没加进去呢！！！
            if (prefixSumsAndIndexes.containsKey(prefixSum)) {
                result.add(prefixSumsAndIndexes.get(prefixSum) + 1); // 起始index
                result.add(i); // 终止index
                return result;
            }
            
            prefixSumsAndIndexes.put(prefixSum, i);
        }
        
        return result;
    }
}
