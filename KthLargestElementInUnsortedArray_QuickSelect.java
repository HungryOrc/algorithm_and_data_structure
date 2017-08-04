/* Find the kth largest element in an unsorted array. 
Note that it is the kth largest element in the sorted order, not the kth distinct element.
For example, Given [3,2,1,5,6,4] and k = 2, return 5.

Note: You may assume k is always valid, 1 ≤ k ≤ array's length. */


// 方法1：Quick Select
// 时间：O(n)
public class Solution {
    
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        
        // 注意！找 第k个最大，就是找 第length-k+1个最小 ！！
        return quickSelect_PthMin(nums, 0, nums.length - 1, nums.length - k + 1);
    }
    
    // quick select 和 quick sort 的区别在于，quick select只排序我们要的那一半，不管另一半
    // 最后一个参数 p 的意思是，找第 p 小的数
    private int quickSelect_PthMin(int[] nums, int start, int end, int p) {
        
        // partition 这个函数，一方面会把小于pivot的数都放到左半边，另一方面会把pivot的index返回来
        // 在后面的partition里，pivot的选取是选数组里的最右边的数
        int chosenIndex = partition(nums, start, end);
        
        if (chosenIndex < p - 1) { // 注意！！第 p 小的数的 index 是 p - 1 ！！！
            return quickSelect_PthMin(nums, chosenIndex + 1, end, p);
        } else if (chosenIndex > p - 1) {
            return quickSelect_PthMin(nums, start, chosenIndex - 1, p);
        } else { // chosenIndex == p - 1
            return nums[chosenIndex];
        }
    }
    
    // 和一般的 quick sort 的partition一样
    private int partition(int[] nums, int start, int end) {
        int left = start;
        int right = end - 1; // 别忘了 - 1 ！！因为 pivot取为最后一个数了！
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


// 方法2：把元素都放到 max heap 里面去，最后从heap里pop出来 k 个
// 时间：O(n logn)，这种方法过度处理了


// 方法3：排序整个数组，从小到大。最后取后面的k个
// 时间：O(n logn)，这种方法过度处理了
