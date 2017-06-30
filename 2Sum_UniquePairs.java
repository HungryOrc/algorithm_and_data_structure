/* Given an array of integers, find how many unique pairs in the array such that their sum is equal to a specific target number. 
Please return the number of pairs.

Example
Given nums = [1,1,2,23,23,23,44,45,46], target = 46. Return 3:
1 + 45 = 46
2 + 44 = 46
23 + 23 = 46 注意这个情况！
(1) 两个加数相同！！
(2) 而且原题中有3个23，也只能算一对！！ */

// 本方法使用 HashSet，不用给数组排序 ！！
public class Solution {
     
    public int twoSum_UniquePairs(int[] nums, int target) {
        
        int count = 0;
        HashSet<Integer> records = new HashSet<>();
        boolean foundHalfValue = false; // 特别注意这个flag！！它就是为了判断是否出现2个23，其和等于46这种情况！！出现一次或三次都不行！！
        
        for (int n : nums) {
            if (!records.contains(n)) {
                if (records.contains(target - n)) {
                    count ++;
                }
                records.add(n);
            } else {
                // 如果下面这个条件满足，则意味着当前的数n，是target number的一半的数值第二次出现在数组里 ！！！
                if (n * 2 == target && foundHalfValue == false) {
                    count ++;
                    foundHalfValue = true;
                }
            }
        }
        
        return count;
    }
}
