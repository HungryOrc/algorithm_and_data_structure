/* Find the kth largest element in an unsorted array. 
Note that it is the kth largest element in the sorted order, not the kth distinct element.
For example, Given [3,2,1,5,6,4] and k = 2, return 5.

Note: You may assume k is always valid, 1 ≤ k ≤ array's length. */

// Quick Select 方法
public class Solution {
    
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        
        return quickSelect(nums, 0, nums.length - 1, nums.length - k + 1);
    }
    
    // quick select 和 quick sort的区别在于，quick select只排序我们要的那一半，不管另一半
    private int quickSelect(int[] nums, int start, int end, int k) {
        
        int chosenIndex = partition(nums, start, end);
        
        if (chosenIndex < k - 1) {
            return quickSelect(nums, chosenIndex + 1, end, k);
        } else if (chosenIndex > k - 1) {
            return quickSelect(nums, start, chosenIndex - 1, k);
        } else { // chosenIndex == k - 1
            return nums[chosenIndex];
        }
    }
    
    // 和一般的 quick sort 的partition一样
    private int partition(int[] nums, int start, int end) {
        int left = start;
        int right = end - 1;
        int pivot  = nums[end];
        
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left ++;
            }
            while (left <= right && nums[right] >= pivot) {
                right --;
            }
            if (left < right) {
                swap(nums, left, right);
                left ++;
                right --;
            }
        }
        
        swap(nums, left, end);
        return left;
    }
    
    private void swap(int[] nums, int i1, int i2) {
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }
}
