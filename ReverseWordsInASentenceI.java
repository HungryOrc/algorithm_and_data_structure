/* Reverse the words in a sentence.

Assumptions:
Words are separated by single space
There are no heading or tailing white spaces
If the given string is null, we do not need to do anything

Examples:
“I love Google” → “Google love I” */


// 方法1：先reverse整个string，再逐个reverse每个单词（用 左右双指针 的做法）
// I love Google -> elgooG evol I -> Google love I
// 注意 ！！！这个方法在各种关于string的更难的问题里，是有拓展性的 ！！！
// 虽然这一题用这样的方法看起来很不直觉，很别扭，很杀鸡用牛刀 ！
public class Solution {
  
  public String reverseWords(String input) {
    if (input == null || input.length() <= 1) {
      return input;
    }
    
    char[] cArray = input.toCharArray();
    
    // reverse整个string
    reverseSection(cArray, 0, cArray.length - 1);
    
    // 逐个reverse每个单词
    int slow = 0;
    int fast = 0;
    while (fast < cArray.length) {
      while (fast < cArray.length && cArray[fast] != ' ') {
        fast ++;
      }
      reverseSection(cArray, slow, fast - 1);
      
      fast ++;
      slow = fast;
    }
    
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


// 方法2：土办法。用 java 的 split 函数。但这种方法没有拓展性，不值得用
public class Solution {
  
  public String reverseWords(String input) {
    if (input == null || input.length() <= 1) {
      return input;
    }
    
    String[] words = input.split(" ");
    
    StringBuilder sb = new StringBuilder();
    for (int i = words.length - 1; i >= 0; i--) {
      sb.append(words[i]);
      sb.append(" ");
    }
    
    // remove the last " "
    sb.deleteCharAt(sb.length() - 1);
    return sb.toString();
  }  
}
