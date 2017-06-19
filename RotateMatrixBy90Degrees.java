/* Rotate an N * N matrix clockwise 90 degrees.
Assumptions: The matrix is not null and N >= 0

Examples:
{ {1,  2,  3}
  {8,  9,  4},
  {7,  6,  5} }
after rotation is:
{ {7,  8,  1}
  {6,  9,  2},
  {5,  4,  3} }   */

/* 思路：剥洋葱
比如上面的例子里，1到2,3到4,5到6,7到8分别作为不同的4个版块。这4个版块组成了最外面的第一层，表示为layer=0.
我们不断地增加layer，即往更深入的地方剥。
在一个layer里，我们逐个地置换4个位置上的相应元素，具体来说：
先把1存为int tmp，然后把7填到1的位置上，把5填到7的位置上，把3填到5的位置上，然后把tmp即1填到3的位置上；
然后把2存为tmp，然后把8填到2的位置上，把6填到8的位置上，把4填到6的位置上，最后把2填到4的位置上。这样layer=0即最外层就完成了rotation。
最后到了最核心的时候，如果整个正方形矩阵一共有偶数行即偶数列，则不用特殊处理。如果是奇数行即奇数列，则最中心的一个元素不管他就行。  */

public class Solution {
  
  public void rotate(int[][] matrix) {
    int n = matrix.length;
    
    // for each layer, outer to inner
    for (int layer = 0; layer < n / 2; layer++) {
      // for each element in each side of a layer
      for (int i = layer; i < n - 1 - layer; i++) {        
        int tmp = matrix[layer][i];
        matrix[layer][i] = matrix[n - 1 - i][layer];
        matrix[n - 1 - i][layer] = matrix[n - 1 - layer][n - 1 - i];
        matrix[n - 1 - layer][n - 1 - i] = matrix[i][n - 1 - layer];
        matrix[i][n - 1 - layer] = tmp;
      }
    }
  }
}
