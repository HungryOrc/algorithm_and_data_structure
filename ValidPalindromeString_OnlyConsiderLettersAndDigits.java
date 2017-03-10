/* Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.
Challenge: O(n) time without extra memory

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.
For the purpose of this problem, we define null and empty strings as valid palindromes. */

// Ref: http://www.jiuzhang.com/solutions/valid-palindrome/
public class Solution {

    public boolean isPalindrome(String s) {
        
        if (s == null || s.length() == 0) {
            return true;
        }
        
        int left = 0, right = s.length() - 1;
        
        while (left < right) {
            // if the char is neither a letter nor a digit number
            while (left <= s.length() - 1 && !isEitherLetterOrDigit(s.charAt(left))) {
                left ++;
            }
            if (left >= right) {
                return true;
            }
            
            while (right >= 0 && !isEitherLetterOrDigit(s.charAt(right))) {
                right --;
            }
            // 到此为止，左右2个char都一定是字母（包括大写或小写）或者数字了
            
            // 注意这个方法！！！
            // Character.toLowerCase(char c)
            // 它除了可用于 字母char，也可以用于其它种类的char！包括 数字char！！！
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }  else { // ==
                left ++;
                right --;
            }
        }
        return true;
    }
    
    // 注意这两个方法！！！
    // Character.isLetter(char c)
    // Character.isDigit(char c)
    private boolean isEitherLetterOrDigit(char c) {
        return (Character.isLetter(c) || Character.isDigit(c));
    }
}
