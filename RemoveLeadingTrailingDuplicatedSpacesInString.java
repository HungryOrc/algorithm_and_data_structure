/* Given a string, remove all leading/trailing/duplicated empty spaces.
If the input string is null or zero length, return "".

Examples:
" " --> ""
"  a" --> "a"
"   I     love MTV! ! " --> "I love MTV!!" */

// 方法1：我的朴素方法
public class Solution {

  public String removeSpaces(String input) {
    if (input == null || input.length() == 0) {
      return "";
    }
    
    StringBuilder sb = new StringBuilder();
    
    if (input.charAt(0) != ' ') {
      sb.append(input.charAt(0));
    }
    
    for (int i = 1; i < input.length(); i++) {
      if (input.charAt(i) != ' ' ||
          (input.charAt(i) == ' ' && input.charAt(i - 1) != ' ')) {
        sb.append(input.charAt(i));
      }
    }
    
    if (input.charAt(input.length() - 1) == ' ' && sb.length() > 0) {
      sb.deleteCharAt(sb.length() - 1);
    } 
    
    return sb.toString();
  } 
}


// 方法2: 两个挡板，一快一慢。Laioffer 的方法


