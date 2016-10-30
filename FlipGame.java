/* You are playing the following Flip Game with your friend: 
Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". 
The game ends when a person can no longer make a move and therefore the other person will be the winner.
Write a function to compute all possible states of the string after one valid move.
For example, given s = "++++", after one move, it may become one of the following states:
[
  "--++",
  "+--+",
  "++--"
]
*/

public class Solution {
    
    // 最普通的方法
    public List<String> generatePossibleNextMoves(String s) {
        
        //！注意！从 String 转到 charArray ---- String.toCharArray()
        char[] charArray = s.toCharArray();
        List<String> output = new ArrayList<String>();
        
        for (int i = 0; i < charArray.length-1; i++)
        {
            if (charArray[i] == '+' && charArray[i+1] == '+')
            {
                // 这里一定要新做一个 charArray，不能用 tempCharArray = oldCharArray，那样还是同一个Reference
                char[] tempCharArray = s.toCharArray();
                tempCharArray[i] = '-';
                tempCharArray[i+1] = '-';
                
                // ！注意！从 charArray 转到 String ---- new String(charArray)，不能少了new关键字！
                output.add(new String(tempCharArray));
            }
        }
        return output;
    }
    
    
    // 用 ArrayList.indexOf(Object) 的方法：
    // Reference: https://discuss.leetcode.com/topic/27232/4-lines-in-java
    /* 附：The java.util.ArrayList.indexOf(Object) method 
     returns the index of the first occurrence of the specified element in this list, 
     or -1 if this list does not contain the element.
    */
    public List<String> generatePossibleNextMoves_ByIndexOf(String s) {
        List list = new ArrayList();
      
        for (int i=-1; (i = s.indexOf("++", i+1)) >= 0; )
            list.add(s.substring(0, i) + "--" + s.substring(i+2));
      
        return list;
    }
    
    
}
