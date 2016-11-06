/* Given a string which consists of lowercase or uppercase letters, 
find the length of the longest palindromes that can be built with those letters.
This is case sensitive, for example "Aa" is not considered a palindrome here.
Note: Assume the length of given string will not exceed 1,010.
Example: Input: "abccccdd", Output: 7
Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
*/

public class Solution {
    public int longestPalindrome(String s) {
        
        int result = 0;
        int[] charCounts = new int[100];
        
        for (char c : s.toCharArray())
            charCounts[c - 'A'] ++;
        
        int foundOddCount = 0;
        for (int count : charCounts)
        {
            // 注意！奇数里的偶数部分也要加进来！别忘了他们也可以为党国尽忠！
            if (count % 2 == 1)
            {
                result += count / 2 * 2;
                foundOddCount = 1;
            }
            else if (count != 0 && count % 2 == 0)
                result += count;
        }
        return result + foundOddCount;
    }
}
