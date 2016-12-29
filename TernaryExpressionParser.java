/* Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression. 
You can always assume that the given expression is valid and only consists of digits 0-9, ?, :, T and F 
(T and F represent True and False respectively).

Note:
The length of the given string is ≤ 10000.
Each number will contain only one digit.
The conditional expressions group right-to-left (as usual in most languages).
The condition will always be either T or F. That is, the condition will never be a digit.
The result of the expression will always evaluate to either a digit 0-9, T or F.

Example 1:
Input: "T?2:3"
Output: "2"

Example 2:
Input: "F?1:T?4:5"
Output: "4"
Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
             "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
          -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
          -> "4"                                    -> "4"

Example 3:
Input: "T?T?F:5:3"
Output: "F"
Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
             "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
          -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
          -> "F"                                    -> "F"
*/

public class Solution 
{
    public String parseTernary(String expression) 
    {
        Stack<Character> allChars = new Stack<>();
        Stack<Character> popped = new Stack<>();
        
        // 实现了最右边的char放在栈顶，最左边的char放在栈底
        for (char c : expression.toCharArray())
            allChars.add(c);
        
        while (!allChars.isEmpty())
        {
            char curChar = allChars.pop();
            if (curChar != ':' && curChar != '?')
                popped.add(curChar);
            else if (curChar == '?')
            {
                char secondChar = popped.pop();
                char thirdChar = popped.pop();
                
                char firstChar = allChars.pop();
                if (firstChar == 'T')
                    popped.add(secondChar);
                else // firstChar == 'F'
                    popped.add(thirdChar);
            }
        }
        char finalResult = popped.pop();
        return Character.toString(finalResult);
    }
}
