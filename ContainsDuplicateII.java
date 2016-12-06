/* Given an array of integers and an integer k, 
find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and 
the difference between i and j is at most k. */

public class Solution {
    
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        
        // 存每个 num 最近一次在 数组nums 里出现的index
        HashMap<Integer, Integer> numMap = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++)
        {
            if (!numMap.containsKey(nums[i]))
                numMap.put(nums[i], i);
            else
            {
                if (Math.abs(i - numMap.get(nums[i])) <= k)
                    return true;
                else
                    numMap.put(nums[i], i);
            }
        }
        return false;
    }
    
    
    // 用HashSet 而非 HashMap 的方法。只存最近的k个值。很巧妙！！！速度也更快
    // Ref: https://leetcode.com/articles/contains-duplicate-ii/
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}


