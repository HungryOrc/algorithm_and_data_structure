public class Solution {

    public void sortIntArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }
    
    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        
        int left = start, right = end;
        int pivot = nums[start + (end - start) / 2];

        // 注意 ！！！ every time you compare left & right, it should be left <= right, not left < right
        // 在整个程序中的所有地方，只要是比较left和right，都要遵循这个原则 ！！！
        while (left <= right) {
            
            // 注意 ！！！ nums[left] < pivot, not nums[left] <= pivot
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            
            // 注意 ！！！ nums[right] > pivot, not nums[right] >= pivot
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                
                // 注意 ！！！ 在这里也要 left++ 以及 right-- ！！！
                left++;
                right--;
            }
        }
        // 特别注意 ！！！
        // 这个 while 循环结束以后，left 和 right 的关系是：
        // right + 1 = left ！！！ 即 right 在 left 的左边一位 ！！！
        
        // 这里没有必要判断左右index的大小关系，下一个recursion里的头部会做。当然如果一定要在这里做，也ok
        quickSort(nums, start, right);
        quickSort(nums, left, end);
    }
}
