/* Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not. */

public class Solution {
    
    public boolean isValid(String s) {
        
        Stack<Character> parenthesesStack = new Stack<>();
        for (char c : s.toCharArray())
        {
            if (c == '(' || c == '[' || c == '{')
                parenthesesStack.push(c);
            else
            {
                // 注意！如果来的是右括号，然后目前stack里空了，也是不行的！
                // 而且，空的stack不能执行pop或者peek！只能老老实实写 stack.isEmpty()！
                if (c == ')' && (parenthesesStack.isEmpty() || parenthesesStack.pop()!= '('))
                    return false;
                else if (c == ']' && (parenthesesStack.isEmpty() || parenthesesStack.pop()!= '['))
                    return false;
                else if (c == '}' && (parenthesesStack.isEmpty() || parenthesesStack.pop()!= '{'))
                    return false;
            }
        }
        
        // if there is still something in the stack
        if (!parenthesesStack.isEmpty())
            return false;
            
        return true;
    }
}
