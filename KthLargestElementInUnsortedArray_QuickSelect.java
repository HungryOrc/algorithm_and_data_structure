/* Find the kth largest element in an unsorted array. 
Note that it is the kth largest element in the sorted order, not the kth distinct element.
For example, Given [3,2,1,5,6,4] and k = 2, return 5.

Note: You may assume k is always valid, 1 ≤ k ≤ array's length. */


// 方法1：Quick Select
public class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        
        // 注意！找 第k个最大，就是找 第length-k+1个最小 ！！
        return quickSelect(nums, 0, nums.length - 1, nums.length - k + 1);
    }
    
    // quick select 这个算法的定义就是 “找第 k 小的数”，k 也就是下面的最后一个参数
    private int quickSelect(int[] nums, int start, int end, int k) {
        int chosenIndex = partition(nums, start, end);
        
        if (chosenIndex < k - 1) { // 第 k 小的数的 index 是 k - 1
            return quickSelect(nums, chosenIndex + 1, end, k);
        } else if (chosenIndex > k - 1) {
            return quickSelect(nums, start, chosenIndex - 1, k);
        } else { // chosenIndex == k - 1
            return nums[chosenIndex];
        }
    }
    
    // 和一般的 quick sort 的 partition函数 一样
    // 这个函数一方面会把小于pivot的数都放到左半边，另一方面会把pivot的index返回来
    // 在下面的 partition 的实现里，pivot的选取是选数组里的最右边的数。可以用别的选法，比如随机
    private int partition(int[] nums, int start, int end) {
        int left = start;
        int right = end - 1; // 别忘了 -1，因为 pivot取为最后一个数了，所以right只能取为倒数第二个
        int pivot  = nums[end];
        
        while (left <= right) {
            if (array[left] < pivot) {
                left ++;
            } else if (array[right] > pivot) {
                right --;
            } else {
                swap(array, left++, right--);
            }
        }
        
        // 最后left要么是紧贴着right并且在right的右边一位，要么是left和right重合
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
