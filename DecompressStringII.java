/* Given a string in compressed form, decompress it to the original string. 
The adjacent repeated characters in the original string are compressed to have the character followed by the number of repeated occurrences.

Assumptions:
The string is not null
The characters used in the original string are guaranteed to be ‘a’ - ‘z’
There are no adjacent repeated characters with length > 9

Examples:
“a1c0b2c4” → “abbcccc”  */


public class Solution {
  
  public String decompress(String input) {
    if (input == null || input.length() == 0) {
      return input;
    }
    
    char[] cArray = input.toCharArray();
    StringBuilder sb = new StringBuilder();
    int count = 0;
    
    char repeatChar = ' ';
    int repeatTimes = 0;
    
    for (int i = 0; i < cArray.length; i++) {
      if (cArray[i] - '0' < 0 || cArray[i] - '0' > 9) {
        repeatChar = cArray[i];
      } 
      else {
        repeatTimes = cArray[i] - '0';
        
        for (int repeat = 1; repeat <= repeatTimes; repeat++) {
          sb.append(repeatChar);
        }
      }
    }
    
    return sb.toString();
  }
  
}
