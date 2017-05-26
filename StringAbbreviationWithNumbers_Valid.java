/* Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.
A string such as "word" contains only the following valid abbreviations:
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". 
Any other string is not a valid abbreviation of "word".

Note: Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Examples:
Given s = "internationalization", abbr = "i12iz4n": Return true.
Given s = "apple", abbr = "a2e": Return false.
Given s = "a", abbr = "2": Return false.
Given s = "a", abbr = "01": Return false. */

// 我的方法
public class Solution {
  
  public boolean match(String input, String pattern) {
    return match(input, 0, pattern, 0);
  }
  
  private boolean match(String input, int i, String pattern, int j) {
    
    if(i == input.length() && j == pattern.length()) {
      return true;
    }
    else if (i == input.length() || j == pattern.length()) {
      return false;
    }
    
    int count = 0;
    char p = pattern.charAt(j);
    while (p - '0' >= 0 && p - '9' <= 0) {
      count = count * 10 + (p - '0');
      j = j + 1;
      
      if (j < pattern.length()) {
        p = pattern.charAt(j);
      } else {
        break;
      }
    }
    
    if (count > 0) {
      if (i + count - 1 >= input.length()) {
        return false;
      } else {
        return match(input, i + count, pattern, j);
      }
    }
    else { // count == 0
      if (input.charAt(i) != pattern.charAt(j)) {
        return false;
      } else {
        return match(input, i + 1, pattern, j + 1);
      }
    }
  }
  
}
