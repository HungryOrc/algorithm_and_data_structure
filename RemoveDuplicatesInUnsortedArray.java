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

    // 方法1：先把数组排序，用时间 O(n*logn)，然后再按照 Remove Duplicated in Sorted Array 那一题的方法来做
    // 时间：O(n*logn)
    // 空间：In-place的，即 O(1)
    
    
    // 方法2：用 HashSet 做
    // 时间 O(n)，空间 O(n)

}
