/* Given an array of integers, find if the array contains any duplicates. 
Your function should return true if any value appears at least twice in the array, 
and it should return false if every element is distinct. */

public class Solution {
    
    // HashSet
    // Time: O(n), Space: O(n)
    public boolean containsDuplicate(int[] nums) {
        
        HashSet<Integer> containedNums = new HashSet<>();
        for (int num : nums)
        {
            if (containedNums.contains(num))
                return true;
            else
                containedNums.add(num);
        }
        return false;
    }
    
    
    // 排序之后逐个比
    // Time: O(n*log(n)), Space: O(1)
    public boolean containsDuplicate(int[] nums) {

        Arrays.sort(nums);
        
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }
}
