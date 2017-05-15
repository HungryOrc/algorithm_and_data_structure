/* Repeatedly remove all adjacent, repeated characters in a given string from left to right.
No adjacent characters should be identified in the final string.

Examples:
(1) "abbbaaccz" → "a aaccz" → "ccz" → "z" 
    注意！
    3个重复的b，全都删除了，没有一个b留下
    一开始不相邻的1个a和后面的2个a，因为b的消除，连接在一起了，然后这3个a在下一步里都被删除了
    2个连续在一起的c也都被删除了
(2) "aabccdc" → "bccdc" → "bdc" */


// 方法1：我自己的方法，用一个模拟的栈（用数组的左半段模拟一个栈的行为），再加上两个指针一快一慢。真正上场的时候用这个方法。
// 这个方法是源于 Laioffer 的方法
public class Solution {
  public String deDup(String input) {
    if (input == null || input.length() <= 1) {
        return input;
    }
  
    char[] cArray = input.toCharArray();
    int slow = -1; // 当前的“栈”顶
    int fast = 0; // 当前在char数组里访问到第几个char
    
    while (fast < cArray.length) {
      // slow==-1 是当前stack空了的情况
      if (slow == -1 || cArray[fast] != cArray[slow]) { 
        slow ++;
        cArray[slow] = cArray[fast];
        fast ++;
      }
      // 当前栈不空，而且当前在char array里正在被访问的char 等于 “栈”顶的char
      else { 
        char duplicatedChar = cArray[slow];
        slow --; // 把当前栈顶的char弹出去不要了。注意！只用弹一次！因为再往下的一个一定不同了
        // 把数组里从现在位置开始往右的所有等于栈顶char的char都略过
        while (fast < cArray.length && cArray[fast] == duplicatedChar) { 
          fast ++;
        }
      }
    }
    // slow指向的是当前栈顶我们要保留的char，所以要再往右边一格，因为上限是exclusive的
    return new String(cArray, 0, slow + 1); 
  }
}


// 方法2：Laioffer的方法。很巧妙。用数组的左半边模拟了一个栈 ！！！
// 空间上 in place ！！！时间上 one pass ！！！不存在recursion
public class Solution {
  
    public String deDup(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }
      
        char[] cArray = input.toCharArray();
      
        // use the left side of the char array as a "stack",
        // the index "end" in the array marks the top of the stack
        // end = 0 means the 1st element in the arary, end = -1 means the stack is empty
        int end = 0;
        // loop starting from the 2nd element in the array (i=1)
        for (int i = 1; i < cArray.length; i++) {
            
            // if the stack is empty (end == -1), or there is no duplicate chars,
            // we push a new char into the stack
            if (end == -1 || cArray[i] != cArray[end]) {
                end ++;
                cArray[end] = cArray[i];
            }
            // else if we find the 1st occurence of duplication, 
            else {
                // we must pop the top element of the stack, namely the 1st element that is duplicated, 
                end --;
                // and then ignore all the consecutive duplicated chars that follows
                // 注意！下面这个也能处理 本来不相连但后来相连了的两段相同的char的情况 ！！！
                while (i < cArray.length - 1 && cArray[i] == cArray[i + 1]) {
                    i ++;
                }
            }
        }
      
        return new String(cArray, 0, end + 1);
    }
}


// 方法3：我的朴素方法，Recursion。空间上不是in place
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
