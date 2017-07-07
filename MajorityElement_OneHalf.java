/* Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
You may assume that the array is non-empty and the majority element always exist in the array  */

public class Solution {
    
    // 方法1：极为巧妙的方法：Moore voting algorithm
    // 数学证明见：http://www.cs.utexas.edu/~moore/best-ideas/mjrty/
    // Time: O(n), Space: O(1)
    
    /* 思路：用别的数 “对消” 目前计数最 “冒尖” 的数 ！！！ 所谓 “枪打出头鸟”：
       先把数组里的第一个数作为candidate，其计数count初始记为 1. 然后，从第二个数开始，
       如果它等于当前的candidate，就count++；
       如果它不等于当前的candidate，
           如果count当前已经是0了，那么就改换门庭！将candidate改设为当前的数，count重新记为 1；
           如果count当前 > 0，则 count--   
        
       总之，可以这么理解：
       每一次累加，都是对于当前candidate的增强，
       而每一次削减，都是  2个不同的数  同时削减 ！！！ 最后的赢家 理  应  能 Survive 这种削减　！！！　*/
    
    public int majorityElement(int[] nums) {

        int candidate = array[0];
        int count = 1;

        for (int i = 1; i < array.length; i++) {
          if (array[i] != candidate) {
            if (count == 0) {
              candidate = array[i];
              count = 1;
            } else {
              count --;
            }
          } else {
            count ++;
          }
        }

        // 以下部分是用来检查，我们选出的candidate，到底它的出现次数是否大于 n/2
        count = 0;
        for (int num : array) {
          if (num == candidate) {
            count ++;
          }
        }
        if (count > array.length/2) {
          return candidate;
        } 

        return Integer.MAX_VALUE; // 如果超过n/2的出现次数的众数不存在
    }
    
    
    // 方法2：HashMap 方法
    // Time: O(n), Space: O(n)
    public int majorityElement(int[] nums) {       
        int halfLength = nums.length / 2;
        HashMap<Integer, Integer> numCounts = new HashMap<>();
        
        for (int num : nums) {
            numCounts.put(num, numCounts.getOrDefault(num, 0) + 1);
            if (numCounts.get(num) > halfLength)
                return num;
        }
        // actually we will never reach here
        return Integer.MIN_VALUE;
    }
    
    
    // 方法3：比较巧妙的先 sort 再取中间那个数即majority的方法
    // Time: O(n logn)
    public int majoriryElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }   
}
