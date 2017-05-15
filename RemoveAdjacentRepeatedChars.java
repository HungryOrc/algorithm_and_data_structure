/* Remove adjacent, repeated characters in a given string, leaving only one character for each group of such characters.
Assumptions: Try to do it in place.

Examples:
"aaaabbbc" -> "abc"
"aabcccabbdd -> "abcabd"   */

// 快慢指针的做法
public class Solution {
  
  public String deDup(String input) {
    if (input == null || input.length() == 0) {
      return input;
    }
    
    char[] cArray = input.toCharArray();
    int slow = 0;
    int fast = 0;
    
    while (fast < cArray.length) {
      if (cArray[fast] != cArray[slow]) {
        slow ++;
        cArray[slow] = cArray[fast];
      }
      fast ++;
    }
    
    return new String(cArray, 0, slow + 1);
  } 
  
}
