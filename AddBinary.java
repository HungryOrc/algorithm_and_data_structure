/* Given two binary strings, return their sum (also a binary string).
For example,
a = "11"
b = "1"
Return "100". */

public class Solution
{
    // 朴素的解法。速度还行
    public String addBinary(String a, String b)
    {
        int maxLen = Math.max(a.length(), b.length()) + 1;
        int[] intArrayA = new int[maxLen];
        int[] intArrayB = new int[maxLen];
        int[] sumArray = new int[maxLen];
        
        int indexA = a.length()-1;
        int indexB = b.length()-1;
        for (int i = maxLen - 1; i >= 0; i--)
        {
            // 注意！！！char 转 int，用 Character.getNumericValue ！！！
            // 别用 Integer.valueOf ！！！会出错！！！
            if (indexA >= 0)
            {
                intArrayA[i] = Character.getNumericValue(a.charAt(indexA));
                indexA--;
            }
            if (indexB >= 0)
            {
                intArrayB[i] = Character.getNumericValue(b.charAt(indexB));
                indexB --;
            }
        }
        
        int carry = 0;
        for (int i = maxLen - 1; i >= 0; i--)
        {
            if (intArrayA[i] + intArrayB[i] + carry == 3)
            {
                sumArray[i] = 1;
                carry = 1;
            }
            else if (intArrayA[i] + intArrayB[i] + carry == 2)
            {
                sumArray[i] = 0;
                carry = 1;
            }
            else if (intArrayA[i] + intArrayB[i] + carry == 1)
            {
                sumArray[i] = 1;
                carry = 0;
            }
            else // (intArrayA[i] + intArrayB[i] + carry == 0)
            {
                sumArray[i] = 0;
                carry = 0;
            }
        }
        
        // 注意！！！连接String，用 StringBuilder 做！！！比String或者charArray快很多！！！
        StringBuilder sb = new StringBuilder();
        int i = 0;
        if (sumArray[0] == 0) i = 1;
        for (; i< maxLen; i++)
            sb.append(sumArray[i]);
        return sb.toString();
    }
}
