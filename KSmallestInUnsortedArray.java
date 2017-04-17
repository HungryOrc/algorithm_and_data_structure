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
