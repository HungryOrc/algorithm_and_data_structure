/* Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.
For the purpose of this problem, we define empty string as valid palindrome. */

// 注意下面例子中的2个函数！！！
// Character.isLetterOrDigits(char)
// Character.toLowerCase(char)
//
public class Solution 
{
    public boolean isPalindrome(String s) {
        
        if (s == null || s.length()==0)
            return true;
        
        int i = 0;
        int j = s.length() - 1;
        while(j > i)
        {
            while(i<s.length() && !Character.isLetterOrDigit(s.charAt(i)))
                i++;
            while(j>=0 && !Character.isLetterOrDigit(s.charAt(j)))
                j--;
            
            if (i == s.length() || j == -1)
                return true;

            else if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
                return false;
            
            // 别忘了这个！while loop里不自带 ++ ！！！
            i++;
            j--;
        }
        return true;
    }
}
