/* Given an original string input, and two strings S and T, replace all occurrences of S in input with T.
Assumptions: input, S and T are not null, S is not empty string

Examples
input = "appledogapple", S = "apple", T = "cat", input becomes "catdogcat"
input = "dodododo", S = "dod", T = "a", input becomes "aoao" */

// 方法1：StringBuilder + mainString.indexOf(subString, fromIndex)
// Ref: Laioffer 
public class Solution {
  
  public String replace(String input, String s, String t) {
    // skip the corner cases
    
    StringBuilder sb = new StringBuilder();
    
    int fromIndex = 0;
    int matchIndex = input.indexOf(s, fromIndex);
    
    while (matchIndex != -1) {
      sb.append(input, fromIndex, matchIndex).append(t);
      
      fromIndex = matchIndex + s.length();
      matchIndex = input.indexOf(s, fromIndex);
    }
    
    sb.append(input, fromIndex, input.length()); // 注意结尾部分的这个处理 ！别忘了 ！！
    return sb.toString();
  }
}


/* 方法2：所谓的 in place 方法
设将要被替换的substring是 s，要替换成的substring是 t。那么要实现 in place 的话，要分2种情况

(1) 如果 s.length() >= t.length
那么先把 input string 过一遍，用一个integer arraylist记录下 input 里所有出现过 s 的位置，注意这里要记录每个 s 出现的 起始位置！
然后再 input 里从左到右扫，用两个指针一快一慢。到了我们记录过的 s 出现的起始位置，就把 s 换成 t。此时string的总长度会有相应的塌缩。
每替换完一个 t，后面的不是 s 的部分，就逐次把一个一个char填进来

(2) 如果 s.length() < t.length()
还是要先把 input string 过一遍，用一个integer arraylist记录下 input 里所有出现过 s 的位置，注意这里要记录每个 s 出现的 结束位置！
如果出现了 m 次 s，则建立一个新的 char array，它的长度等于：
input.length() + m * (t.length() - s.length())
然后对于这个 char array，从  右  往  左  走 ！！一快一慢两个指针
到了我们记录过的 s 出现的终止位置，就把 s 换成 t   */
