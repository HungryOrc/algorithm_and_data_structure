/* Given an array of elements, reorder it as follow:
{ N1, N2, N3, …, N2k } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k }
{ N1, N2, N3, …, N2k+1 } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k, N2k+1 }

Try to do it in place.

Assumptions: The given array is not null

Examples:
{ 1, 2, 3, 4, 5, 6} → { 1, 4, 2, 5, 3, 6 }
{ 1, 2, 3, 4, 5, 6, 7, 8 } → { 1, 5, 2, 6, 3, 7, 4, 8 }
{ 1, 2, 3, 4, 5, 6, 7 } → { 1, 4, 2, 5, 3, 6, 7 } */

/* 思路：
(1) 如果一开始有偶数个元素，则把它分成两等分：
    1 2 3 4 5 6 7 8   =>   1 2 3 4   5 6 7 8
    如果一开始有奇数个元素，则按照题意要求的答案，我们忽略最后一个元素，再把剩下的分成两等分：
    1 2 4 4 5 6 7 8 9   =>   1 2 3 4   5 6 7 8   9
(2) 接下来的部分，继续分，一共分成4个部分。
    如果除以4能整除，则：
    1 2   3 4   5 6   7 8
    如果不能被4整除，比如下面这种，则这么处理：
    1 2   3 4 5   6 7   8 9 10
(3) 中间的两段，先自身reverse，然后这两段一起reverse。如下：
    1 2   5 4 3   7 6   8 9 10
    1 2   6 7   3 4 5   8 9 10
(4) 对于左边的半段，和右边的半段，分别进行我们对于最初始的整个数组所进行的事情 ！！！即重做 1 到 3 步 ！！！
    1 2   6 7   =>   1 6   2 7
    这半段完成了
    3 4 5   8 9 10   =>   3 5 4   8 9 10   =>   3 8   4 5 9 10
    然后 4 5 9 10   =>   4 9   5 10
(5) 全段变成   1 6   2 7   3 8   4 9   5 10，完事 */

public class Solution {
  
  public int[] reorder(int[] array) {
    
    if (array.length % 2 == 0) {
      reorder(array, 0, array.length - 1);
    } else { // % 2 == 1
      reorder(array, 0, array.length - 2); // ignore the last element
    }
    
    return array;
  }
  
  // 到了这一步，数组的被选取的长度，即left和right之间含它们两者在内，一定是偶数的长度 ！！
  private void reorder(int[] array, int left, int right) {
    int len = right - left + 1;
    
    if (len <= 2) { // 长度小于等于2的时候，就不必再做任何操作了
      return;
    }
    
    // get the indexes of 3 middle points, for example:
    // 0   1   2   3   4   5   6   7
    //         lm      m       rm
    // 0   1   2   3   4   5   6   7   8   9
    //         lm          m       rm
    int mid = left + len / 2;
    int leftMid = left + len / 4;
    int rightMid = left + len * 3 / 4;
    
    // 两个分段分别自己reverse
    reverse(array, leftMid, mid - 1);
    reverse(array, mid, rightMid - 1);
    // 然后两个分段一起reverse
    reverse(array, leftMid, rightMid - 1);
    
    // 接下来的这两句是精华 ！！！
    reorder(array, left, left + (leftMid - left) * 2 - 1);
    reorder(array, left + (leftMid - left) * 2, right);
  }
  
  private void reverse(int[] array, int left, int right) {
    while (left < right) {
      int tmp = array[left];
      array[left] = array[right];
      array[right] = tmp;
      
      left ++;
      right --;
    }
  }
}
