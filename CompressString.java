/* Given a string, replace adjacent, repeated characters with the character followed by the number of repeated occurrences. 
If the character does not has any adjacent, repeated occurrences, it is not changed.

Assumptions:
The string is not null
The characters used in the original string are guaranteed to be ‘a’ - ‘z’
There are no adjacent repeated characters with length > 9

Examples:
“abbcccdeee” → “ab2c3de3”   */


public class Solution {
  
  public String compress(String input) {
    if (input == null || input.length() == 0) {
      return input;
    }
    
    char[] cArray = input.toCharArray();
    StringBuilder sb = new StringBuilder();
    sb.append(cArray[0]);
    
    int count = 1;
    int i = 1;
    
    while (i < cArray.length) {
      
      if (cArray[i] != cArray[i - 1]) {
        if (count > 1) {
          sb.append(count);
        }
        
        sb.append(cArray[i]);
        count = 1; 
      } 
      else {
        count ++;
      }
      i ++;
    }
    
    if (count > 1) { // 注意 ！！！不要遗漏这种情况 ！！！ String的结尾有几个连续相同的char的情况
      sb.append(count);
    }
    
    return sb.toString();
  }
}
