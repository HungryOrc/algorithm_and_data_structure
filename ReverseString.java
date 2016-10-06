/* Takes a string as input and returns the string reversed.
Example: Given s = "hello", return "olleh"   */

public class ReverseString {
    
    // 方法：用 StringBuilder 来做，比用 String += char 的运行速度快很多
    // Runtime: O(n)
    //
    public String reverseString(String givenString) {
        
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
