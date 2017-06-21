/* Given a sorted array A, find a pair (i, j) such that A[j] - A[i] is identical to a target number(i != j).
If there does not exist such pair, return a zero length array.
Assumptions: The given array is not null and has length of at least 2.

Examples:
A = {1, 2, 3, 6, 9}, target = 2, return {0, 2} since A[2] - A[0] == 2.
A = {1, 2, 3, 6, 9}, target = -2, return {2, 0} since A[0] - A[2] == -2.
被减数 - 减数 = 差，可见题目要求的返回的结果里，减数放在前面，被减数放在后面

注意：给定的差值，可能是 零，也可能是 负数 ！！ 
所以最后的结果，被减数可能在减数的左边，也可能是右边！ 
但无论如何二者不可重合，必须是不同的数 */


/* 思路：同向双指针，都从数组的左端点开始往右边走。时间 O(n)
1. 如果当前差大于目标差，则减数要变大，即减数的index要往右边走一位。因为数组是sorted，从小到大。
   如果当前被减数就在减数的右边一位，则减数的index不是+1，而是减数index = 被减数index + 1.
2. 如果当前差小于目标差，则被减数要变大，即被减数的index要往右边走一位。
   如果当前减数就在被减数的右边一位，则被减数index = 减数index + 1.
3. 特别注意这个处理 ！！！
   因为被减数和减数不能重合，所以一开始我们要把它们的indexes一个设为0，一个设为1。但是谁0谁1是有陷阱在里面的 ！！！
   如果最后的目标差target > 0，则减数的初始index必须为0，被减数为1，
   因为被减数最终要比减数大，减数从index = 0 开始才可能穷尽所有的减数的可能性 ！！！ 否则减数取不到最左边的第一个数 ！！！
   反之，如果taget < 0，则被减数的初始index必须为0，减数为1，这是因为同理，
   必须保证被减数要取到所有的可能值，包括左边第一个数。
   举例：
   给的数组为 {1, 2, 2, 3, 4}，给的target差为-3，则如果设减数的初始index为0，被减数的初始index为1，则永远不能得到结果，
   因为最后符合条件的解是 被减数的index为0（值为1），减数的index为4（值为4）：1 - 4 = -3.     */

public class Solution {
  
  public int[] twoDiff(int[] array, int target) {
    int n = array.length;
    
    int i = 0, j = 0;
    if (target >= 0) {
      i = 1;
    } else { // target < 0
      j = 1;
    }
    
    while (i < n && j < n) {
      if (array[i] - array[j] > target) { // then we need array[j] to be bigger
        if (i == j + 1) {
          j = i + 1;
        } else {
          j ++;
        }
      } else if (array[i] - array[j] < target) { // then we need array[i] to be bigger
        if (j == i + 1) {
          i = j + 1;
        } else {
          i ++;
        }
      } else { // == target
        return new int[]{j, i};
      }
    }
    
    return new int[0];
  }
}
