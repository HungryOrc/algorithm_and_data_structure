/* Given a positive integer, return its corresponding column title as appear in an Excel sheet.
For example:
    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
*/

public class Solution 
{
    public String convertToTitle(int n) 
    {
        StringBuilder sb = new StringBuilder();
        
        while (n > 0)
        {
            n--; // 注意！！！这一步很关键！！！
            
            int tail = n % 26;
            // 注意 用+一个整数来取字母char的方法，记得最后还要 (char)int
            char curChar = (char)('A' + tail); 
            
            sb.insert(0, curChar); // 注意 StringBuilder 的 insert 函数
            
            n = n / 26;
        }
        return sb.toString();
    }
}

