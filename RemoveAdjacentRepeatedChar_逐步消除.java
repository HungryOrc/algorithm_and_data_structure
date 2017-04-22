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
    sb.append(cArray[0]); // 先把第一个char放到sb里去
    
    char duplicatedChar = ' ';
    boolean removingDuplicate = false; // 一开始没有发现任何的重复char
    
    int i = 1; // 从第二个char开始考察
    for (; i < cArray.length; i++) {
      // 情况一：之前没有发现重复char，当前的char也不和前一个相邻char重复，则将当前char加入sb
      if (removingDuplicate == false && cArray[i] != cArray[i - 1]) {
    	  sb.append(cArray[i]);	
      }
      // 情况二：之前没有发现重复char，但当前的char与它之前相邻的一个char重复了，于是重复就发生了！
      if (removingDuplicate == false && cArray[i] == cArray[i - 1]) {
    	  sb.deleteCharAt(sb.length() - 1); // remove cArray[i-1] from sb
    	  removingDuplicate = true; // 标记重复 boolean flag
        duplicatedChar = cArray[i - 1]; // 记录重复的char值
      }
      // 情况三：之前发现了重复char，当前char与之前标定的重复char相同，则什么也不做，继续往后走
      // 情况四：之前发现了重复char，当前char与之前标定的重复char不同，那么就是本段重复chars结束了！我们应结束本次for loop！
      // 这里就要跳出loop，把包含当前char在内的后面的所有char都放到sb里去，然后迎接下一次recursion！
      if (removingDuplicate == true && cArray[i] != duplicatedChar) {
    	  break;
      }
    }
    
    // 把包括当前char（j=i）在内的所有char都放到sb里去，
    // 目的就是把本段重复chars以后的所有char都原原本本放到下一阶段的String里去
    for (int j = i; j < cArray.length; j++) {
    	sb.append(cArray[j]);
    }
    
    // 注意我的这个思路！
    // 如果String经过recursion处理后还是不变，证明没什么可再精简的重复了，证明recursion可以结束了 ！！！
    String output = sb.toString();
    if (!output.equals(input)) {
    	return deDup(output);
    } else {
    	return input;
    }
  }
  
}
