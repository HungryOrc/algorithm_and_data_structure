/* The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string. */

public class Solution {
    
    // 用 StringBuilder 连接 Strings，会比用 String 快很多！！！
    // 仅这一个改进，就从 beat 8% 前进到 beat 56%
    public String countAndSay(int n) {
    
        StringBuilder s = new StringBuilder("1"); // StringBuilder的初始化
        StringBuilder formerS = new StringBuilder(); // 空白StringBuilder的初始化
        
        if (n == 1)
            return s.toString(); // StringBuilder to String
        
        // 一共进行n-1次操作。n=1的情况这里不管，前面已经直接return了
        for (int i = 2; i <= n; i++)
        {
            formerS = s;
            int formerLen = s.length();
            s = new StringBuilder();
            int curCount = 1;
            
            for (int j = 1; j < formerLen; j++)
            {
                if (formerS.charAt(j) == formerS.charAt(j-1))
                    curCount++;
                else
                {
                    // StringBuilder可以   级   联   Append ！！
                    s.append(curCount).append(formerS.charAt(j-1));
                    curCount = 1;
                }
            }
            // 加上最后的一批相同的char(s)
            s.append(curCount).append(formerS.charAt(formerLen-1));
        }
        return s.toString(); // StringBuilder to String
    }
}
