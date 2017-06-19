/* Use the least number of comparisons to get the largest and 2nd largest number in the given integer array. 
Return the largest number and 2nd largest number.
Assumptions: The given array is not null and has length of at least 2

Examples:
{2, 1, 5, 4, 3}, the largest number is 5 and 2nd largest number is 4.  */


// 思路：初始设两个int，一个largest，一个secondLargest，然后
// 从左到右扫一遍数组，如果 num > largest，则 secondLargest = largest，largest = num；
// else if (num > secondLargest), 则 secondLargest = num

public class Solution {
  
  public int[] largestAndSecond(int[] array) {
    int largest = Integer.MIN_VALUE;
    int secondLargest = Integer.MIN_VALUE;
    
    for (int num : array) {
      if (num > largest) {
        secondLargest = largest; // 注意！这一句一定要在 largest = num 的前面！
        largest = num;
      }
      else if (num > secondLargest) {
        secondLargest = num;
      }
    }
    
    return new int[]{largest, secondLargest};
  }
}
