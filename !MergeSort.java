// 经典模板
// Time: O(n*logn)
// Space: O(n)，因为根据 Recursion Tree，每一层 call stack 都新建了 helper array，一共有 logn 层call stack，
// 各个call stack所新建的 helper array的长度分别是 n/2, n/4, n/8... 2, 1
// 它们加在一起是 n 的长度

public class MergeSort {
  
  public int[] mergeSort(int[] array) {
    if (array == null || array.length <= 1) {
      return array;
    }
    
    // allocate helper array to deal with the merging step
    // so the space complexity is O(n)
    int[] helperArray = new int[array.length];
    mergeSort(array, helperArray, 0, array.length - 1);
    return array;
  }
  
  private void mergeSort(int[] array, int[] helperArray, int start, int end) {
    if (start >= end) { // when there is only 1 element or 0 element
      return;
    }
    
    int mid = start + (end - start) / 2;
    mergeSort(array, helperArray, start, mid);
    mergeSort(array, helperArray, mid + 1, end);
    
    merge(array, helperArray, start, mid, end);
  }
  
  private void merge(int[] array, int[] helperArray, int start, int mid, int end) {
    if (start >= end) { // when there is only 1 element or 0 element
      return;
    }
    
    // copy the section between start and end in the array to the helper array, 
    // then we will merge the 2 parts in the helper array back to the original array
    for (int i = start; i <= end; i++) {
      helperArray[i] = array[i];
    }
    
    // let the merge begin!
    int left = start;
    int right = mid + 1;
    int index = start;
    
    while (left <= mid && right <= end) {
      if (helperArray[left] <= helperArray[right]) {
        array[index] = helperArray[left];
        index ++;
        left ++;
      } else {
        array[index] = helperArray[right];
        index ++;
        right ++;
      }
    }
    
    // if there are still some elements left at the left side, we need to copy them
    while (left <= mid) {
      array[index] = helperArray[left];
      index ++;
      left ++;
    }
    // if there are still some elements left at the right side, we do nothing,
    // since they are already in their rightious position, haha
  }
}
