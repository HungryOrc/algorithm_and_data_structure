/* Given a column title as appear in an Excel sheet, return its corresponding column number.
For example:
    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
*/

public class Solution {

    // 最自然的思维顺序的解法。需要用 Math.pow(int1, int2)。后面有妙法
    // Time: O(n).
    public int titleToNumber(String s) {
        
        char[] charArray = s.toCharArray();
        int output = 0;
        
        for (int i = 0; i < charArray.length; i++)
        {
            int power = (int)Math.pow(26, charArray.length - 1 - i);
            output += (charArray[i] - 'A' + 1) * power;
        }
        return output;
    }
    
    // 从左往右算。每一位的处理：result = result * 26 + cur位。很妙！虽然运行时间没感觉比前一个快多少
    // Ref: https://discuss.leetcode.com/topic/6886/here-is-my-java-solution
    // Time: O(n).
    public int titleToNumber(String s) {
        
        char[] charArray = s.toCharArray();
        int result = 0;
        
        for (int i = 0; i < charArray.length; i++)
            result = result * 26 + (charArray[i] - 'A' + 1);
        return result;
    }
    
}
    
