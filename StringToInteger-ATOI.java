/* Implement atoi to convert a string to an integer.
Requirements for atoi:
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. 
Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, 
and interprets them as a numerical value.
The string can contain additional characters after those that form the integral number, 
which are ignored and have no effect on the behavior of this function.
If the first sequence of non-whitespace characters in str is not a valid integral number, 
or if no such sequence exists because either str is empty or it contains only whitespace characters, 
no conversion is performed.
If no valid conversion could be performed, a zero value is returned. 
If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned. */

public class Solution 
{
    public int myAtoi(String str) 
    {
        if (str == null || str.length()==0)
            return 0;
        
        char[] cArray = str.toCharArray();
        double result = 0; // 这题test case里long不够用，只有用更大范围的double
        boolean isNegative = false;
        int i = 0;
        
        while (i < cArray.length && cArray[i] == ' ')
            i++;
        if (i == cArray.length) // 整个str全部由空格组成
            return 0;
    
        if (cArray[i] == '+')
            ; // do nothing
        else if (cArray[i] == '-')
            isNegative = true;
        else if (Character.isDigit(cArray[i])) // 这一位是数字
            result += (cArray[i] - '0');
        else // 如果这一位是其他东西，就都不行了
            return 0;
        
        // 处理接下来可能出现的n位数字
        i++;    
        while (i < cArray.length && Character.isDigit(cArray[i]))
        {
            result *= 10;
            result += (cArray[i] - '0');
            i++;
        }
        
        if (isNegative) // 如果是负数
            result *= -1;
        if (result > Integer.MAX_VALUE) // 处理溢出int范围的情况
            return Integer.MAX_VALUE;
        else if (result < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        return (int)result;
    }
}
