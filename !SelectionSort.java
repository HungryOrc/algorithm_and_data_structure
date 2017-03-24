// 每loop一次，就把本轮loop所找到的min放到左边第一位、左边第二位、左边第三位……
public class Solution {
  
  public int[] solve(int[] array) {
    
    if (array == null || array.length <= 1) {
      return array;
    }
    
    for (int i = 0; i < array.length - 1; i++) {
      int indexOfMinInThisLoop = i;
      
      for (int j = i + 1; j < array.length; j++) {
        if (array[j] < array[indexOfMinInThisLoop]) {
          indexOfMinInThisLoop = j;
        }
      }
      
      swap(array, i, indexOfMinInThisLoop);
    }
    return array;
  }
  
  private void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
  
}
