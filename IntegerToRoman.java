/* Given an integer, convert it to a roman numeral.
Input is guaranteed to be within the range from 1 to 3999.

Roman Numerals Chart:
I      1   |	X      10  |	C      100  |	M          1000
II     2   |	XX     20  |	CC     200  |	M M        2000
III    3   |	XXX    30  |	CCC    300  |	M M M      3000
IV     4   |	XL     40  |	CD     400  |	M M M M    4000
V      5   |	L      50  |	D      500  |	M M M M M  5000
VI     6   |	LX     60  |	DC     600  |	etc...
VII    7   |	LXX    70  |	DCC    700  |
VIII   8   |	LXXX   80  |	DCCC   800  |
IX     9   |	XC     90  |	CM     900  |
*/

public class Solution 
{
    // 我的方法。代码很长，但速度还挺快。思路很简明
    public String intToRoman(int num) 
    {
        StringBuilder sb = new StringBuilder();
        
        // 1000
        int thousand = num / 1000;
        if (thousand > 0)
        {
            for (int i = 1; i <= thousand; i++)
                sb.append("M");
        }
        num %= 1000;
        
        // 900 ~ 100
        int hundred = num / 100;
        if (hundred > 0)
        {
            if (hundred == 9)
                sb.append("CM");
            else if (hundred <= 8 && hundred >= 5)
            {
                sb.append("D");
                for (int i = 1; i <= hundred-5; i++)
                    sb.append("C");
            }
            else if (hundred == 4)
                sb.append("CD");
            else // hundred ~ [1,2,3]
            {
                for (int i = 1; i <= hundred; i++)
                    sb.append("C");
            }
        }
        num %= 100;
        
        // 90 ~ 10
        int ten = num / 10;
        if (ten > 0)
        {
            if (ten == 9)
                sb.append("XC");
            else if (ten <= 8 && ten >= 5)
            {
                sb.append("L");
                for (int i = 1; i <= ten-5; i++)
                    sb.append("X");
            }
            else if (ten == 4)
                sb.append("XL");
            else // ten ~ [1,2,3]
            {
                for (int i = 1; i <= ten; i++)
                    sb.append("X");
            }
        }
        num %= 10;
        
        // 9 ~ 1
        int one = num;
        if (one > 0)
        {
            if (one == 9)
                sb.append("IX");
            else if (one <= 8 && one >= 5)
            {
                sb.append("V");
                for (int i = 1; i <= one-5; i++)
                    sb.append("I");
            }
            else if (one == 4)
                sb.append("IV");
            else // one ~ [1,2,3]
            {
                for (int i = 1; i <= one; i++)
                    sb.append("I");
            }
        }

        return sb.toString();    
    }
    
    
    // 别人的方法。看似有点二。但其实这个问题的本质也就是这样的
    // 代码比我的短很多。速度比我的微弱地快一点点
    // Ref: https://leetcode.com/problems/integer-to-roman/
    public static String intToRoman(int num) 
    {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }
    
}
