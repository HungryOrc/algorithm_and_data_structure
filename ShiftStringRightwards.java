/* Right shift a given string by n characters.
Assumptions:
The given string is not null.
n >= 0.

注意！ n 可能会大于等于 input String 的长度 ！！ */

// 一种比较有趣的方法：
public class Solution {
  
  public String rightShift(String input, int n) {
    if (input == null || input.length() <= 1) {
      return input;
    }
    
    n %= input.length(); // don't forget this ！！！
    
    char[] cArray = input.toCharArray();
    
    // step 1: reverse the whole string
    reverseSection(cArray, 0, cArray.length - 1);
    
    // step 2: reverse the leftmost n chars, and the other chars, respectively
    reverseSection(cArray, 0, n - 1);
    reverseSection(cArray, n, cArray.length - 1);
    
    return new String(cArray);
  }
  
  private void reverseSection(char[] cArray, int start, int end) {
    while (start < end) {
      char temp = cArray[start];
      cArray[start] = cArray[end];
      cArray[end] = temp;
      
      start ++;
      end --;
    }
  }
  
}
