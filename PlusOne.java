/* Given a non-negative number represented as an array of digits, plus one to the number.
The digits are stored such that the most significant digit is at the head of the list. */

public class Solution {
    
    public int[] plusOne(int[] digits) {

        for (int i = digits.length-1; i >= 0; i--)
        {
            if (digits[i] == 9)
                digits[i] = 0;
            else
            {
                digits[i] += 1;
                return digits;
            }
        }
        
        // 到了这一步，说明digits的最高位是9，且仍带有进位1。即digits的每一位都是9，现在要总体增加一位了
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }
}
