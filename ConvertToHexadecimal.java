/* Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.
Note:
All letters in hexadecimal (a-f) must be in lowercase.
The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
The given number is guaranteed to fit within the range of a 32-bit signed integer.
You must not use any method provided by the library which converts/formats the number to hex directly.
Example 1:
Input:
26
Output:
"1a"
Example 2:
Input:
-1
Output:
"ffffffff"

负数，比如-1，其二进制表示为：1的二进制表示，取反，加一
负数的十六进制表示，为其二进制表示直接按照每四位进行转换的方式，转为十六进制，如下所示：
Decimal: 1
 Binary: 00000000 00000000 00000000 00000001
    Hex: 00 00 00 01

Decimal: -1
 Binary: 11111111 11111111 11111111 11111111
    Hex: FF FF FF FF

Decimal: -2
 Binary: 11111111 11111111 11111111 11111110
    Hex: FF FF FF FE
    
位运算符：>>> "zero fill right shift"
Shift right zero fill operator. The left operands value is moved right by the number of bits specified by the right operand 
and shifted values are filled up with zeros.	
E.g.: A = 60, A >>>2 will give 15 which is 0000 1111    */

public class Solution {
  
  // Assumption: number >= 0
  public String hex(int number) {
    if(number == 0) {
      return "0x0"; // 要特殊处理 0 的情况！下面的代码无法处理！
    }
    
    StringBuilder sb = new StringBuilder();
    
    while (number > 0) {
      int remainder = number % 16;
      char thisHexaDigit = ' ';
      
      if (remainder <= 9) {
        thisHexaDigit = (char)('0' + remainder);
      } else { // 9 < remainder <= 15
        thisHexaDigit = (char)('A' + remainder - 10); // 别忘了 -10 ！！
      }
      sb.append(thisHexaDigit);
      
      number /= 16;
    }
    
    // 注意 StringBuilder.reverse() 方法！！
    return "0x" + sb.reverse().toString(); // 别忘了加 前缀 0x
  }
  
}
