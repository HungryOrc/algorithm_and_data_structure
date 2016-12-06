/* Count the number of segments in a string, 
where a segment is defined to be a contiguous sequence of non-space characters.
Please note that the string does not contain any non-printable characters.

Example:
Input: "Hello, my name is John"
Output: 5 */

// 注意，这个String有可能前后有一个或多个空格，中间的空格间隔可能也会由一个或多个空格组成，
// 还可能整个String就是一个或多个空格！
public class Solution {
    
    // 最朴素的想法：一个一个char看，当前char是空格，前一个不是，即计数++
    // Ref: https://leetcode.com/problems/number-of-segments-in-a-string/
    public int countSegments(String s) {
        
        int count = 0;
        
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) != ' ' && (i==0 || s.charAt(i-1)==' '))
                count++;
        }
        return count;
    }
}
