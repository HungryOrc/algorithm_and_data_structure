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
    
    // 极为巧妙的方法：Moore voting algorithm
    // 数学证明见：http://www.cs.utexas.edu/~moore/best-ideas/mjrty/
    public int majorityElement(int[] nums) {

        int major = nums[0], count = 1;
        for(int i = 1; i < nums.length; i++)
        {
            if(count == 0){
                count = 1;
                major = nums[i];
            }else if(major == nums[i])
                count++;
            else count--;
        }
        return major;
    }
}