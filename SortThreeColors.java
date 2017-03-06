/* Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, 
with the colors in the order red, white and blue.
Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
You are not suppose to use the library's sort function for this problem. 
You should do it in-place (sort numbers in the original array).

Example
Given [1, 0, 1, 2], sort it in-place to [0, 1, 1, 2].

Challenge 
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, 
then 1's and followed by 2's.
Could you come up with an one-pass algorithm using only constant space? */

// 方法1：我的方法，两个指针，先整体扫一遍，把2放到右半部分；然后再用两个指针把左半部分扫一遍，把1和0分开
class Solution {
    
    int RED = 0;
    int WHITE = 1;
    int BLUE = 2;
    
    public void sortColors(int[] nums) {
        
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            while (nums[left] != BLUE) {
                left ++;
            }
            while (nums[right] == BLUE) {
                right --;
            }
            
            // 注意！这里再判断一次是否 left < right ！！！
            // 否则到了最后，left和right都已经交叉了，还再swap一次，就错了！
            if (left < right) {
                swap(nums, left, right);
            }
        } 
        // 最终效果一定是 left 和 right 交叉！！！确切地说是 left = right + 1
        // 因为 left 最终会指向 最左边的一个是BLUE的位置！！！
        // right 最终会指向 最右边的一个不是BLUE的位置！！！
        
        left = 0;
        // right 不变！！！因为right此时已经指向最右边的一个不是BLUE的位置！！！
        while (left < right) {
            while (nums[left] != WHITE) {
                left ++;
            }
            while (nums[right] == WHITE) {
                right --;
            }
            
            if (left < right) {
                swap(nums, left, right);
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
}
