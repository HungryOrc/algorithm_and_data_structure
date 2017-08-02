/* Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
注意，是大于，而非大于等于 1/3.
The algorithm should run in linear time and in O(1) space. */


// 参考 Majority Element_One Half 那一题

// 方法1：对消的方法
// Time: O(n), Space: O(1)

/* 思路：用别的数 “对消” 目前计数最 “冒尖” 的数 ！！！ 最后符合要求的数可能不止一个
   从数组左端开始，找最先出现的2个不同的数作为candidates，其计数count初始都记为 1. 然后，从后面的数开始，
   如果它等于candidates中的任何一个，就把那个candidate的count++，其他candidate的count不变；
   如果它不等于当前的任何一个candidate，
       如果当前有任何一个candidate的count是0，那么就将这个candidate改设为当前的数，它的count重新记为 1；
       如果当前所有的candidates的counts都 > 0，则所有的counts都 -1   
        
   总之，可以这么理解：
   每一次累加，都是对于当前candidate的增强，
   而每一次削减，都是  3个不同的数  同时削减 ！！！ 最后的赢家 理  应  能 Survive 这种削减　！！！　
   而且这样的赢家最多只能有2个，因为是大于1/3   */

public class Solution {
    
    public List<Integer> majorityElement(int[] nums) {
    	if (nums == null || nums.length == 0) {
    		return new ArrayList<Integer>();
      }
      
    	List<Integer> result = new ArrayList<Integer>();
      int len = nums.length;
       
      // 注意这里的trick ！！ 先把number1和2都设为 nums[0]，把count1和2都设为 0 而非 1 ！！
      // 然后下面的for loop里，从 i=0 开始，而非从 i=1 开始 ！！
      // 在 i=0 那里，只把 count1 + 1，不要把 count2 也 + 1 了
    	int number1 = nums[0], number2 = nums[0];
      int count1 = 0, count2 = 0;
    	
      for (int i = 0; i < len; i++) {
         
        // 特别注意 ！！！
        // 下面所有这些情况，都是  并  列  的 ！！！ 
        // 都要用 else 连接起来 ！！！
         
        if (nums[i] == number1)
          count1++;
        else if (nums[i] == number2)   
          count2++;
              
        else if (count1 == 0) {
          number1 = nums[i];
          count1 = 1;
        } else if (count2 == 0) {
          number2 = nums[i];
          count2 = 1;
                     
        } else {
          count1--;
          count2--;
        }
    	}
    	
      // re-check
    	count1 = 0;
    	count2 = 0;
    	for (int i = 0; i < len; i++) {
    	  if (nums[i] == number1)
    	    count1++;
    	  else if (nums[i] == number2)
    	    count2++;
    	}
    	if (count1 > len / 3)
    	  result.add(number1);
    	if (count2 > len / 3)
    	  result.add(number2);
    	return result;
    }
}


// 方法2：排序数组然后二分查找。也很巧妙！！！
// 先排序数组，然后考察数组里位于 1/3 和 2/3 的这两个位置的数。
// 首先，看从左到右1/3位置处的那个数，设为a，然后在整个数组里，用二分查找，寻找第一个a和最后一个a的位置，
// 如果她们相隔的距离 >= 数组长度/3，则a就是我们要找的数。
// 如果a不满足，就找2/3位置处的那个数，设为b，重复上面的做法，看b是否满足。如果b也不满足，则要找的数不存在。
// Time: O(n logn + logn)，后面那个 logn 是（四次）二分查找所需的时间


// 方法3：HashMap
// Time: O(n), Space: O(n)
