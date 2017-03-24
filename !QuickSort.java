// 九章模板
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


// 经典模板
public class QuickSort {
    
    public int[] quickSort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        
        quickSort(array, 0, array.length - 1);
        return array;
    }
    
    // Overload quickSort method
    public void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        
        // the following method complete 2 tasks:
        // 1. get and return the pivotIndex
        // 2. partition the array using this pivot
        int pivotIndex = partition(array, left, right);

        // the pivot is already at the position where it should be, so 
        // when we do the recursion on the two partitions, pivot should NOT be included in any of them
        quickSort(array, left, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, right);
    }

    private int partition(int[] array, int left, int right) {
        int pivotIndex = findPivotIndex(left, right);
        int pivot = array[pivotIndex];
        
        // swap the pivot element to the rightmost position
        swap(array, pivotIndex, right);
        
        int leftIndex = left;
        int rightIndex = right - 1;
        
        while (leftIndex <= rightIndex) {
            if (array[leftIndex] < pivot) {
                leftIndex ++;
            } else if (array[rightIndex] > pivot) {
                rightIndex --;
            } else {
                swap(array, leftIndex++, rightIndex--);
            }
        }
        
        // swap back the pivot element to the position that it should be
        // 特别注意 ！！！
        // pivot 是和 left 换！！！
        swap(array, leftIndex, right);
        
        // 特别注意 ！！！
        // 最后是 return left ！！！不是 return right ！！！
        return leftIndex;
    }
    
    // random in the range of [left, right], inclusive in both ends
    // Math.random() 得出的是：[0, 1)
    private findPivotIndex(int left, int right) {
        return left + (int)(Math.random() * (right - left + 1));
    }

    private void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
