/* Reverse the words in a sentence.

Assumptions:
Words are separated by single space
There are no heading or tailing white spaces
If the given string is null, we do not need to do anything

Examples:
“I love Google” → “Google love I” */

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
