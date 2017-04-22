/* Repeatedly remove all adjacent, repeated characters in a given string from left to right.
No adjacent characters should be identified in the final string.

Examples:

"abbbaaccz" → "aaaccz" → "ccz" → "z" 
注意！
3个重复的b，全都删除了，没有一个b留下
一开始不相邻的1个a和后面的2个a，因为b的消除，连接在一起了，然后这3个a在下一步里都被删除了
2个连续在一起的c也都被删除了

"aabccdc" → "bccdc" → "bdc" */





// 方法2：我的朴素方法，Recursion。空间上不是in place
public class Solution {
  
  public String deDup(String input) {
    if (input == null || input.length() <= 1) {
      return input;
    }

    StringBuilder sb = new StringBuilder();
    char[] cArray = input.toCharArray();
    sb.append(cArray[0]);
    
    char duplicatedChar = ' ';
    boolean removingDuplicate = false;
    
    int i = 1;
    for (; i < cArray.length; i++) {
      if (removingDuplicate == false && cArray[i] != cArray[i - 1]) {
    	  sb.append(cArray[i]);	
      }
      if (removingDuplicate == false && cArray[i] == cArray[i - 1]) {
    	  duplicatedChar = cArray[i - 1];
    	  sb.deleteCharAt(sb.length() - 1); // remove cArray[i-1] from sb
    	  removingDuplicate = true;
      }
      if (removingDuplicate == true && cArray[i] != duplicatedChar) {
    	  break;
      }
    }
    
    for (int j = i; j < cArray.length; j++) {
    	sb.append(cArray[j]);
    }
    
    String output = sb.toString();
    if (!output.equals(input)) {
    	return deDup(output);
    } else {
    	return input;
    }
  }
  
}
