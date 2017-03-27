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
        
        // 按照九章的 quick sort 的模板套路来办理 ！！！
        while (left <= right) {
            while (left <= right && nums[left] != BLUE) {
                left ++;
            }
            while (left <= right && nums[right] == BLUE) {
                right --;
            }

            if (left <= right) {
                swap(nums, left, right);
                left ++;
                right --;
            }
        } 
        // 最终效果一定是 left 和 right 交叉！！！确切地说是 left = right + 1
        // left 最终会指向 最左边的一个是BLUE的位置！！！
        // right 最终会指向 最右边的一个不是BLUE的位置！！！
        
        left = 0;
        // right 不变！
        while (left <= right) {
            while (left <= right && nums[left] != WHITE) {
                left ++;
            }
            while (left <= right && nums[right] == WHITE) {
                right --;
            }
            
            if (left <= right) {
                swap(nums, left, right);
                left ++;
                right --;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}


// 方法2：one pass
// Ref: http://www.jiuzhang.com/solutions/sort-colors/
/* 很巧妙！三个隔板：
   redDivider的左边，不含它自己，保证一定是0
   blueDivider的右边，不含它自己，保证一定是2
   whiteDivider的左边，不含它自己，保证一定是0或1。
   whiteDivider如果与redDivider不重合，则whiteDivider的左边（不含它自己）到redDivier的右边（含它自己）一定是1
   whiteDivider的右边（含它自己）到blueDivider的左边（含它自己）的区域可能含有0,1,2中的任何值 */
   
class Solution {
    int RED = 0;
    int WHITE = 1;
    int BLUE = 2;
    
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        int divider0 = 0; // red divider
        int divider1 = 0; // white divider
        int divider2 = nums.length - 1; // blue divider
        
        // 要一直做到 = ！不能停止于 < ！否则那一个位置就没有被处理！
        while (index <= right) {
            
            if (nums[divider1] == RED) {
                swap(nums, divider1, divider0);
                divider0 ++;
                // 这里divider1可以跟着divider0一起都 ++，是因为这题里面只有3种颜色，
                // 从divider0那里swap过来的颜色，一定是1，只可能是1，1也就是divider1要隔到它左边去的颜色
                divider1 ++;
            }
            
            else if (nums[divider1] == WHITE) {
                divider1 ++;
            }
            
            else { // == BLUE
                swap(nums, divider1, divider2);
                divider2 --;
                // 这里不要 divider1 ++ 了！！！因为现在还不能知道这次从divider2调换过来的是什么颜色！！！
                // 必须留待下一个loop才能查到！！！
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }   
}
