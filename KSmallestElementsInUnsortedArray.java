/* Find the K smallest numbers in an unsorted integer array A. The returned numbers should be in ascending order.
Assumptions:
A is not null
K is >= 0 and smaller than or equal to size of A

Examples:
A = {3, 4, 1, 2, 5}, K = 3, the 3 smallest numbers are {1, 2, 3} */

// 方法1： 搞一个 max heap，放我们要的k个最小值。是的！客官你没有看错哦！用 max heap 放最小的那些数！
// 关键在于：我们不是要把所有n个数都放进heap里去，而是要用它maintain最小的k个数，所以很多数根本就没进去heap过
// 具体做法：先把数组里的前k个数放进heap，然后看后面的数，如果比heap的max小，则放进去。当然放进去之前要poll heap一次
public class Solution {
  
  public int[] kSmallest(int[] array, int k) {
    if (array.length == 0 || k == 0) {
      return new int[0];
    }
    
    // k is the initial size of the heap
    // 自定义的 comparator 使得 java 默认的 min heap priority queue 变成了我们要的 max heap
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
      @Override
      public int compare(Integer i1, Integer i2) {
        // don't use == here !!!
        if (i1.equals(i2)) {
          return 0;
        } else if (i1 > i2) {
          return -1;
        } else {
          return 1;
        }
      }
    });
    
    for (int i = 0; i < k; i++) {
      maxHeap.offer(array[i]);
    }
    
    for (int i = k; i < array.length; i++) {
      int num = array[i];
      
      if (num < maxHeap.peek()) {
        maxHeap.poll();
        maxHeap.offer(num);
      }
    }
    
    int[] result = new int[k];
    for (int i = 0; i < k; i++) {
      result[i] = maxHeap.poll();
    }
    Arrays.sort(result);
    return result;
  }
}


// 方法2：Quick Select 方法。很赞的方法 ！！！速度也更快
public class Solution {
  
  public int[] kSmallest(int[] array, int k) {
    if (array.length == 0 || k == 0) {
      return new int[0];
    }
    
    // shift the k smallest elements to the left side of the array,
    // this is done recursively
    quickSelect(array, 0, array.length - 1, k);
    
    // copy the first elements into a new array
    int[] result = Arrays.copyOf(array, k);
    Arrays.sort(result);
    return result;
  }  
  
  private void quickSelect(int[] array, int start, int end, int k) {
    int curDivider = partition(array, start, end);
    
    // 整个算法的关键在下面这个if else语句 ！！！
    if (curDivider > k - 1) {
      quickSelect(array, start, curDivider - 1, k);
    } else if (curDivider < k - 1) {
      quickSelect(array, curDivider + 1, end, k);
    } else { // ==
      return;
    }
  }
  
  // 就是常规的 quick sort 式的 partition
  private int partition(int[] array, int start, int end) {
    int pivot = array[end];
    int left = start;
    int right = end - 1;
    
    while (left <= right) {
      while (left <= right && array[left] < pivot) {
        left ++;
      }
      while (left <= right && array[right] >= pivot) {
        right --;
      }
      if (left <= right) {
        swap(array, left, right);
        left ++;
        right --;
      }
    }
    
    // 别忘了这一步！这一步是把pivot放到正好分界的地方！没有的话，接下来进一步的partition会出错！
    swap(array, left, end);
    
    return left;
  }
  
  private void swap(int[] array, int i1, int i2) {
    int temp = array[i1];
    array[i1] = array[i2];
    array[i2] = temp;
  }
}
