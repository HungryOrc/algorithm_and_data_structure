// 经典模板
// 时间：O(n*logn)
// 空间：O(logn)。因为一共有logn层 call stack，每一个 call stack 都耗费constant空间，
// 它们都没有创造额外的helper array之类的空间占用者
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

    private int partition(int[] array, int start, int end) {
        int pivotIndex = findPivotIndex(start, end);
        int pivot = array[pivotIndex];
        
        // swap the pivot element to the rightmost position
        swap(array, pivotIndex, end);
        
        int left = start;
        int right = end - 1;
        
        while (left <= right) {
            if (array[left] < pivot) {
                left ++;
            } else if (array[right] > pivot) {
                right --;
            } else {
                swap(array, left++, right--);
            }
        }
        // 这个while loop结束时，一定是：rightIndex 在 leftIndex 的左边一位 ！！！
        
        // 特别注意下面的处理方式 ！！！
        // ------------------------------------------------------------------------------------------------
        // 如果一开始pivot被移动到最右边，那么现在pivot要和leftIndex换，
        // 因为此时leftIndex指向的数一定 >= pivot ！！！
        // 如果一开始pivot被移动到最左边，那么现在pivot要和rightIndex换，
        // 因为此时rightIndex指向的数一定 <= pivot ！！！
        swap(array, left, end);
        // 这么移动之后，pivot 一定就位于我们接下来要return 的index的位置上，
        // 即这个位置的数一定 == pivot，而非含糊地 >= pivot 或 <= pivot ！！！
        // 注意了 ！！！
        // 之所以这里非要等于不可，不能有一丝的含糊，是因为再往后的 quick sort recursion里，
        // 前半段的quick sort 将结束于 pivot index - 1，
        // 后半段的quick sort 将开始于 pivot index + 1，
        // 所以 pivot index 上必须必须是等于 pivot 值的 ！！！因为以后再也不会碰这个 index 上的数了 ！！！
        // 至于 pivot index 之前或者之后的数，目前来说，完全可能含有n个等于pivot的数，这些都无所谓，
        // 但是在pivot位置上必须就是pivot值！！！然后下一步才有继续的基础！！！
        
        // 上面如果是 swap 了 right 和 start，这里就要 return right ！！！
        // 上面如果是 swap 了 left 和 end，这里就要 return left ！！！
        return left;
        // ------------------------------------------------------------------------------------------------
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
