/* Given an array of integers, remove the duplicate numbers in it. You should:
1. Do it in place in the array.
2. Move the unique numbers to the front of the array.
3. Return the total number of the unique numbers.
You don't need to keep the original order of the integers.

Example
Given nums = [1,3,1,4,4,2], you should:
Move duplicate integers to the tail of nums => nums = [1,3,4,2,?,?].
Return the number of unique integers in nums => 4.
Actually we don't care about what you place in ?, we only care about the part which has no duplicate integers.

Challenge 
方法1：Do it in O(n) time complexity.
方法2：Do it in O(nlogn) time without extra space. */

public class Solution {

    // 方法1：空间 in-place，但时间方面较长，需要 O(n*logn)，而非 O(n)
    public int deduplication(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Arrays.sort(nums); // 这种方法必须先把数组排序
        
        int index = 0;
        // i 会比 index 前进得快
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[index]) {
                index ++;
                nums[index] = nums[i];
            }
        }
        
        return index + 1;
    }
    
    
    // 方法2：用 HashSet，时间 O(n)
}
