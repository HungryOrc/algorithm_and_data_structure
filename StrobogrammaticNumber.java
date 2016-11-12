/* A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Write a function to determine if a number is strobogrammatic. The number is represented as a string.
For example, the numbers "69", "88", and "818" are all strobogrammatic.

自己变自己：0、1、8 （2不算，虽然计算器上的显示像是算）
自己变另一个：6和9
*/

public class Solution {
    
    public boolean isStrobogrammatic(String num) {
        int numLength = num.length();
        char[] charArray = num.toCharArray();
        
        // 如果只有一位数
        if (numLength == 1)
        {
            if (charArray[0] == '0' || charArray[0] == '1' || charArray[0] == '8')
                return true;
            else 
                return false;
        }
        // 如果是奇数位数，那么中间那位数必须能自己变自己
        else if (numLength % 2 == 1)
        {
            if (charArray[numLength / 2] != '0' && charArray[numLength / 2] != '1' && charArray[numLength / 2] != '8')
                return false;
        }
        
        // 考察其他位上的数
        for (int i = 0; i < numLength / 2; i++)
        {
            if (charArray[i] == '0')
            {
                if (charArray[numLength - 1 - i] != '0')
                    return false;
            }
            else if (charArray[i] == '1')
            {
                if (charArray[numLength - 1 - i] != '1')
                    return false;
            }
            else if (charArray[i] == '6')
            {
                if (charArray[numLength - 1 - i] != '9')
                    return false;
            }
            else if (charArray[i] == '8')
            {
                if (charArray[numLength - 1 - i] != '8')
                    return false;
            }
            else if (charArray[i] == '9')
            {
                if (charArray[numLength - 1 - i] != '6')
                    return false;
            }
            else
                return false;
        }
        return true;
    }
}
