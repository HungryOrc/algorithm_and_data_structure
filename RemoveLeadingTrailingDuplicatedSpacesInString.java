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
public class Solution {
  
  public String removeSpaces(String input) {
    if (input == null || input.length() == 0) {
      return "";
    }
    
    int slow = 0;
    int fast = 0;
    int wordCount = 0;
    char[] cArray = input.toCharArray();
    
    while (true) {
      // 消灭所有的空格
      while (fast < cArray.length && cArray[fast] == ' ') {
        fast ++;
      }
      // 注意！可能此时消灭的是最后的一个或几个空格！那么现在就应该判断是否到了尾部！！！
      // 如果现在不判断，后面才判断，是会出错的！！！
      if (fast == cArray.length) {
        break;
      }
      // 如果不是第一个单词，则要在这单词的前面加一个空格
      if (wordCount > 0) {
        cArray[slow] = ' ';
        slow ++;
      }
      // 如果不是空格，则加到结果里去
      while (fast < cArray.length && cArray[fast] != ' ') {
        cArray[slow] = cArray[fast];
        slow ++;
        fast ++;
      }
      // 单词个数+1
      wordCount ++;
    }
    
    return new String(cArray, 0, slow);
  }

}
