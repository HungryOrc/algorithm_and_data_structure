/* Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Examples:
0.1 < 1.1 < 1.2 < 13.37
1.1 > 1.0.2
1.0 = 1
*/

public class Solution 
{
    public int compareVersion(String version1, String version2) 
    {
        // 注意！！用 "\\." 表示一个点！！而非用 "." ！！
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int minLen = Math.min(v1.length, v2.length);
        
        int num1 = 0, num2 = 0;
        for (int i = 0; i < minLen; i++)
        {
            num1 = Integer.parseInt(v1[i]);
            num2 = Integer.parseInt(v2[i]);
            if (num1 > num2)
                return 1;
            else if (num1 < num2)
                return -1;
        }
        
        // 相同的长度的位都比完了以后
        if (v1.length == v2.length)
            return 0;
        // 以下注意 "1.0" vs "1" 这类的情况！！
        else if (v1.length > v2.length) 
        {
            int sum = 0;
            for (int i = minLen; i < v1.length; i++)
                sum += Integer.parseInt(v1[i]);
            if (sum > 0)
                return 1;
            else
                return 0;
        }
        else // v1.length < v2.length
        {
            int sum = 0;
            for (int i = minLen; i < v2.length; i++)
                sum += Integer.parseInt(v2[i]);
            if (sum > 0)
                return -1;
            else
                return 0;
        }
    }
}
