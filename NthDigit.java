/* Find the n-th digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
Note: n is positive and will fit within the range of a 32-bit signed integer (n < 2^31).

Example 1:
Input: 3
Output: 3

Example 2:
Input: 11
Output: 0
Explanation: The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10. */

public class Solution {
    
    // 最直觉化的方法    
    // 尽量别用 Math.pow(n,m)！！会非常慢的！！
    public int findNthDigit(int n) {
        
        // 当前处于几位数。1意味着1-9(本题中不含0)，2意味着10-99，3意味着100-999……
        int curDigit = 1;
        
        // 用几个 long！否则 int 会不够用！
        
        // 当前的位数里，一共包含多少个数，比如1位数包含9个，2位数包含90个，3位数包含900个……
        long numOfNumbersInCurDigit = 9;
        // 当前的位数里，一共包含多少位，比如1位数包含9位，2位数包含180位，3位数包含2700位……
        long totalDigitsOfNumbersInCurDigit = 0;
        // 给的数n，经过上述的逐位数的盘剥后，还剩多少
        int remainingN = n;
        // 累计经过了多大的数
        long cumulativeNum = 0;

        while (remainingN > numOfNumbersInCurDigit * curDigit)
        {
            totalDigitsOfNumbersInCurDigit = numOfNumbersInCurDigit * curDigit;
            remainingN -= totalDigitsOfNumbersInCurDigit;
            
            cumulativeNum += numOfNumbersInCurDigit; // 9, 99, 999, 9999...
            numOfNumbersInCurDigit *= 10; // 9, 90, 900, 9000...
            curDigit++;
        }
        
        // 这表示我们最终停止在哪个数上。这个数是前期的累计数往后再挪几个
        // 比如在99的基础上再挪3个数就是(100, 101,) 102
        cumulativeNum += remainingN / curDigit;
        int remainingDigitsInCurNumber = remainingN % curDigit;
        // 如果n里余下的位数不能被 curDigit 整除的话，那么还得再往前挪一个数
        if (remainingDigitsInCurNumber > 0)
             cumulativeNum++; 
        
        char[] curNumberToCharArray = String.valueOf(cumulativeNum).toCharArray();
        
        int result = 0;
        if (remainingDigitsInCurNumber == 0)
            // 如果整除了，那么我们要的就是当前数的最后一位
            result = curNumberToCharArray[curNumberToCharArray.length - 1];
        else
            result = curNumberToCharArray[remainingDigitsInCurNumber - 1];
        
        return Character.getNumericValue(result); 
    }
    
}
