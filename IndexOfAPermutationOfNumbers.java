/* Given a permutation which contains no repeated number, find its index in all the permutations of these numbers, 
which are ordered in lexicographical order. The index begins at 1.

Example
Given [1,2,4], return 1. */

public class Solution {
    
    /* 我的方法
      比如四个数 1、3、4、7，用它们来构造各种排列
      例一：如果给的排列是 [1, 3, 4, 7]
          第一位1，比1小而没有被使用过的数字有0个；
          第二位3，比3小而没有被使用过的数字有0个（1已经被使用过了）；
          第三位4，比4小而没有被使用过的数字有0个（1和3已经被使用过了）；
          第四位不用管了，因为它已经被第三位所决定了；
          所以上述排列是第 0 + 0 + 0 + 1 = 1 个排列
      例二：如果给的排列是 [7, 3, 4, 1]
          第一位7，比7小而没有被使用过的数字有3个（1,3和4），
              每一个都能带 3! 种排列，所以一共是 3 * 3! 种排列在它前面；
          第二位3，比3小而没有被使用过的数字有1个（1），
              每一个都能带 2! 种排列，所以一共是 1 * 2! 种排列在它前面；
          第三位4，比4小而没有被使用过的数字有1个（1）；
              每一个都能带 1! 种排列，所以一共是 1 * 1! 种排列在它前面；
          所以上述排列是第 3*3! + 1*2! + 1*1! + 1 = 22 个排列 */
    
    public long permutationIndex(int[] nums) {
        
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        HashMap<Integer, Integer> orders = new HashMap<>();
        // 这里要复制数组nums，因为记录顺序之前要把本数组排序。而原数组必须保持原顺序
        // 复制array可以用clone()方法！
        recordAscendingOrders(nums.clone(), orders);
        
        long[] factorials = new long[nums.length];
        factorials = calcFactorials(nums.length);
        
        boolean[] used = new  boolean[nums.length];
        
        long result = 0;
        // i < nums.length - 1 是因为最后一位数字的选取是早已内定的，在
        // 倒数第二位数字定下时就已经定下了，所以不再带来新的计数
        for (int i = 0; i < nums.length - 1; i++) {
            // 当前数字的值
            int curNum = nums[i]; 
            // 当前数字在整个数组里排第几位，位数即order越大则本数字越大
            int curOrder = orders.get(curNum); 
            // 在数组里，有多少个比当前数字小的数字还没有被使用
            int unusedSmallerNumbers = 0;
            // 这里-1是因为：比如排行第m的数字，它的inde是m-1
            for (int j = 0; j < curOrder - 1; j ++) {
                if (used[j] == false) {
                    unusedSmallerNumbers ++;
                }
            }
            
            // 当前数字的位置后面还有多少个数字的位置需要填，以满足一个permutation
            int subSlots = nums.length - 1 - i;
            
            result += unusedSmallerNumbers * factorials[subSlots];
        
            used[curOrder - 1] = true;
        }
        return result + 1;
    }
    
    private void recordAscendingOrders(int[] nums, 
                                       HashMap<Integer, Integer> orders) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            orders.put(nums[i], i+1);
        }
    }
    
    private long[] calcFactorials(int n) {
        long[] factorials = new long[n];
        factorials[0] = 1;
        for (int i = 1; i < n; i++) {
            factorials[i] = i * factorials[i - 1];
        }
        return factorials;
    }
    
}
