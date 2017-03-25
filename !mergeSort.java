// 经典模板
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
  
  // Overload
  public void mergeSort(int[] array, int[] helperArray, 
                        int start, int end) {
    if (start >= end) {
      return;
    }
    
    int mid = start + (end - start) / 2;
    mergeSort(array, helperArray, start, mid);
    mergeSort(array, helperArray, mid + 1, end);
    
    merge(array, helperArray, start, mid, end);
  }
  
  private void merge(int[] array, int[] helperArray,
                     int start, int mid, int end) {
    if (start >= end) { // when there is only 1 element
      return;
    }
    
    // copy the section between start and end in the array to
    // the helper array, 
    // then we will merge the 2 parts in the helper array back 
    // to the original array
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
