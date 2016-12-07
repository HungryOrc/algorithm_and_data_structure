/* Given a string s consists of upper/lower-case alphabets and empty space characters ' ', 
return the length of last word in the string.
If the last word does not exist, return 0.
Note: A word is defined as a character sequence consists of non-space characters only.

For example, 
Given s = "Hello World", return 5.
Given s = "    ", return 0.
Given s = "  ba  ", return 2. */

public class Solution {
    
    public int lengthOfLastWord(String s) {
        
        if (s == null || s.equals(""))
            return 0;
            
        char[] charArray = s.toCharArray();
        int len = 0;
        int curIndex = s.length()-1;
        
        while(curIndex >= 0)
        {
            if (charArray[curIndex] != ' ')
            {
                len++;
                
                if (curIndex == 0 || charArray[curIndex-1] == ' ')
                    return len;
            }
            curIndex--;
        }
        // we will actually never reach here
        return len;
    }
}
