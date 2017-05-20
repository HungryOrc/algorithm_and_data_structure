/* Given a string in compressed form, decompress it to the original string. 
The adjacent repeated characters in the original string are compressed to have the character followed by 
the number of repeated occurrences. If the character does not have any adjacent repeated occurrences, it is not compressed.

Assumptions:
The string is not null
The characters used in the original string are guaranteed to be ‘a’ - ‘z’
There are no adjacent repeated characters with length > 9

Examples:
“acb2c4” → “acbbcccc”   */


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
      if (i < cArray.length - 1 && 
        cArray[i + 1] - '0' >= 0 && cArray[i + 1] - '0' <= 9) {
        repeatChar = cArray[i];
        repeatTimes = cArray[i + 1] - '0';
        for (int repeat = 1; repeat <= repeatTimes; repeat++) {
          sb.append(repeatChar);
        }
      } 
      else if (cArray[i] - '0' < 0 || cArray[i] - '0' > 9) {
        sb.append(cArray[i]);
      }
    }
    
    return sb.toString();
  }
  
}
