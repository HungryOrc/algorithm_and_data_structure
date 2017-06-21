/* Given two integer arrays A1 and A2, sort A1 in such a way that the relative order among the elements 
will be same as those are in A2.
For the elements that are not in A2, append them in the right end of the A1 in an ascending order.

Assumptions:
A1 and A2 are both not null.
There are no duplicate elements in A2.

Examples:
A1 = {2, 1, 2, 5, 7, 1, 9, 3}, A2 = {2, 1, 3}, A1 is sorted to {2, 2, 1, 1, 3, 5, 7, 9}   */

// 方法：Laioffer，自己搞一个 custom comparator ！！
public class Solution {
  
  // custom comparator
  static class MyComparator implements Comparator<Integer> {
    private Map<Integer, Integer> map;
    
    // Constructor
    public MyComparator(int[] array) {
      map = new HashMap<>();
      for (int i = 0; i < array.length; i++) {
        map.put(array[i], i);
      }
    }
    
    @Override
    public int compare(Integer i1, Integer i2) {
      Integer index1 = map.get(i1);
      Integer index2 = map.get(i2);
      
      if (index1 != null && index2 != null) {
        return index1.compareTo(index2);
      } else if (index1 == null && index2 == null) {
        return i1.compareTo(i2);
      } else {
        return index1 != null ? -1 : 1;
      }
    }
  }
  
  public int[] sortSpecial(int[] A1, int[] A2) {
    Integer[] A1Integer = toIntegerArray(A1); // int array => Integer array
    Arrays.sort(A1Integer, new MyComparator(A2));
    toIntArray(A1Integer, A1); // Integer array => int array
    return A1;
  }
  
  // helper function
  private Integer[] toIntegerArray(int[] array) {
    Integer[] result = new Integer[array.length];
    for (int i = 0; i < array.length; i++) {
      result[i] = array[i];
    }
    return result;
  }
  
  // helper function
  private void toIntArray(Integer[] array, int[] result) {
    for (int i = 0; i < array.length; i++) {
      result[i] = array[i];
    }
  }
}
