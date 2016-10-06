/* Takes a string as input and returns the string reversed.
Example: Given s = "hello", return "olleh"   */

public class ReverseString {
    
    // 方法：用 String.toCharArray() 方法，把 String 转化为 Char Array，
    // 然后 sway the first and last Chars in the Array, in place
    // 这个方法的速度相当快
    //
    public String reverseString_SwapFirstAndLastChars(String givenString)
    {
        // 注意 String.toCharArray() 方法！
        char[] myCharArray = givenString.toCharArray();
        
        int i = 0;
        int j = givenString.length() - 1;
        
        while (i < j) {
            char tempChar = myCharArray[i];
            myCharArray[i] = myCharArray[j];
            myCharArray[j] = tempChar;
            i++;
            j--;
        }
        
        // 注意 String的这种构造函数！ new String(charArray);
        String outputString = new String(myCharArray);
        return outputString;
    }
    
    
    // 方法：递归 Recursion，分别对左右两个子字符串进行反转操作，然后“右放左、左放右”
    // 此法构思巧妙，代码精炼。但运算速度颇慢，估计是 Recursion 的原因
    //
    public String reverseString(String givenString) {
        int stringLength = givenString.length();
        
        if (stringLength <= 1) 
            return givenString;
        
        // String.substring(lb, ub) 方法：lowerBound inclusive, upperBound exclusive
        // int division "/": round DOWN
        String leftHalfStr = givenString.substring(0, length / 2);
        String rightHalfStr = givenString.substring(length / 2, length);
        
        // 注意！这里“右放左、左放右”！
        return reverseString(rightHalfStr) + reverseString(leftHalfStr);
    }
    
    
    // 方法：位运算 ---- 以后再细看
    // Reference: https://discuss.leetcode.com/topic/43296/many-acceptable-answers
    public String reverseString_SwapWithByte(String s) {
        byte[] bytes = s.getBytes();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            byte temp = bytes[i];
            bytes[i] = bytes[j];
            bytes[j] = temp;
            i++;
            j--;
        }
        return new String(bytes);
    }
    public String reverseString_SwapWithByteWithoutTemp(String s) {
        byte[] bytes = s.getBytes();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            bytes[i] = (byte)(bytes[i] ^ bytes[j]);
            bytes[j] = (byte)(bytes[i] ^ bytes[j]);
            bytes[i] = (byte)(bytes[i] ^ bytes[j]);
            i++;
            j--;
        }
        return new String(bytes);
    }
    
    
    // 方法：Cheating Method using StringBuilder.reverse()
    // Reference: https://discuss.leetcode.com/topic/43296/many-acceptable-answers
    // 但它的运算速度也不是特别快
    //
    public String reverseString_ByStringBuilderReverse(String givenString)
    {
        // 注意 StringBuilder 的这种 构造函数！ new StringBuilder(String)
        // 可以直接由 String 而生成 StringBuilder
        StringBuilder myStringBuilder = new StringBuilder(givenString);
        
        // StringBuilder.reverse() 方法
        return  myStringBuilder.reverse().toString();
    }
    
    
    // 方法：用 StringBuilder.append(char) 来构造输出的String，比用 String += char 的速度快很多，
    // 取 char 用 String.charAt(i)，但这个取法的速度也很低
    // Runtime: O(n)
    //
    public String reverseString_ByCharAt(String givenString) {
        
        StringBuilder myStringBuilder = new StringBuilder();
        
        for (int i = givenString.length()-1; i >= 0; i--)
        {
            myStringBuilder.append(givenString.charAt(i));
            /* 如果用：
            String reversedString = "";
            reversedString += givenString.charAt(i);
            的话，会慢很多   */
        }
        return myStringBuilder.toString();
    }
}
