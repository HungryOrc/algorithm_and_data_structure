/* Given two non-negative numbers num1 and num2 represented as string, return the sum of num1 and num2.
Note:
The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly. */


// C++版本
char* AddAsciiIntegers( const char* X, const char* Y)
{
    int lengthX = strlen(X);
    int lengthY = strlen(Y);
    int expandedLength = max(lengthX, lengthY) + 1;
    
    char *expandedX = new char[expandedLength];
    char *expandedY = new char[expandedLength];
    char *sumArray = new char[expandedLength];
    
    for (int i = 0; i < expandedLength - lengthX; i++) {
        expandedX[i] = '0';
    }
    for (int i = expandedLength - lengthX; i < expandedLength; i++) {
        expandedX[i] = X[i - expandedLength + lengthX];
    }
    
    for (int i = 0; i < expandedLength - lengthY; i++) {
        expandedY[i] = '0';
    }
    for (int i = expandedLength - lengthY; i < expandedLength; i++) {
        expandedY[i] = Y[i - expandedLength + lengthY];
    }
    
    int carry = 0;
    for (int i = expandedLength - 1; i >= 0; i--) {
        
        // 注意 ！！！C++ 里从 char 到 int，减 '0' 即可 ！！！
        int sum = (expandedY[i] - '0') + (expandedX[i] - '0') + carry;
        
        // 注意 ！！！C++ 里从 int 到 char，加 '0' 即可 ！！！
        sumArray[i] = sum % 10 + '0';
        
        carry = sum / 10;
    }
    return sumArray;
}


// Java版本
public class Solution {
    
    public String addStrings(String num1, String num2) {
        
        int length1 = num1.length();
        int length2 = num2.length();
        int longerLength = Math.max(length1, length2);

        String num1ComplementedString = num1;
        String num2ComplementedString = num2;
        for (int i = 1; i <= longerLength + 1 - length1; i++)
            num1ComplementedString = "0" + num1ComplementedString;
        for (int i = 1; i <= longerLength + 1 - length2; i++)
            num2ComplementedString = "0" + num2ComplementedString;
        char[] num1ComplementedArray = num1ComplementedString.toCharArray();
        char[] num2ComplementedArray = num2ComplementedString.toCharArray();
        
        int[] sumArray = new int[longerLength + 1]; 
        int[] carries = new int[longerLength + 1];
        
        for (int i = longerLength; i >= 1; i--)
        {
            int curNumIn1 = num1ComplementedArray[i] - '0';
            int curNumIn2 = num2ComplementedArray[i] - '0';
            sumArray[i] = (curNumIn1 + curNumIn2 + carries[i]) % 10;
            carries[i-1] = (curNumIn1 + curNumIn2 + carries[i]) / 10;
        }
        sumArray[0] = carries[0];
        
        if (sumArray[0] == 1)
        {
            char[] sumArray_chars = new char[longerLength + 1];
            for (int i = 0; i < longerLength + 1; i++)
                // ！特别注意！这里将 int 0-9 转为 char 的方法！先 String，再 char！！
                // 如果直接 (char) int，因为只有一位int，所以似乎会出错！
                sumArray_chars[i] = Integer.toString(sumArray[i]).charAt(0);
            return new String(sumArray_chars);
        }
        else // if (sumArray[0] == 0)
        {
            char[] sumArray_chars = new char[longerLength];
            for (int i = 0; i < longerLength; i++)
                sumArray_chars[i] = Integer.toString(sumArray[i + 1]).charAt(0);
            return new String(sumArray_chars);
        }
        
    }
}
