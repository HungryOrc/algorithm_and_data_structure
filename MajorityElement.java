/* Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
You may assume that the array is non-empty and the majority element always exist in the array.
*/

public class Solution {
    
    // 最普通的 HashMap 方法
    public int majorityElement(int[] nums) {
        
        int halfLength = nums.length / 2;
        HashMap<Integer, Integer> numCounts = new HashMap<>();
        
        for (int num : nums)
        {
            numCounts.put(num, numCounts.getOrDefault(num, 0) + 1);
            if (numCounts.get(num) > halfLength)
                return num;
        }
        return -11111111;
    }
    
    // 比较巧妙的先 sort 再取中间那个数即majority的方法
    public int majoriryElement(int[] nums)
    {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
