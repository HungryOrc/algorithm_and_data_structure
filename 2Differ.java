/* 一个数组比如 11, 0, 2，4，-3, 6，8，10，和一个数字target比如是6，求有多少组 unique pairs 满足相差6，比如2和8就是这么一个pair。 

Assumptions:
数组里没有重复数字。当然有的话也没关系，去重或者忽视重复数字就行
target是一个正数。当然如果有可能是负数也没关系，分情况处理就行     */

public class Solution {

    // 用 hashset 做，O(n) 的时间
    public static int differ(int[] nums, int target) { // 先不考虑 corner cases了
      Arrays.sort(nums); // 排序是这个方法的先决条件！
    	
      HashSet<Integer> visited = new HashSet<>();
    	int result = 0;
    	
    	for (int num : nums) {
        // 因为数组已经从小到大排序了，所以只可能是后面的数减去前面的数 == target，记得target是正数
    		if (visited.contains(num - target)) {
    			result ++;
    		}
    		visited.add(num);
    	}
    	return result;
    }
}
