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
    
    // 最直觉化的方法。经过的test case都通过了，但时间效率低，所以不足以遍历所有test case    
    // 尽量别用 Math.pow(n,m)！！会非常慢的！！
    public int findNthDigit(int n) {
        
        // 当前处于几位数。1意味着1-9(本题中不含0)，2意味着10-99，3意味着100-999……
        int curDigit = 1;
        // 当前的位数里，一共包含多少个数，比如1位数包含9个，2位数包含90个，3位数包含900个……
        int numOfNumbersInCurDigit = 9;
        // 当前的位数里，一共包含多少位，比如1位数包含9位，2位数包含180位，3位数包含2700位……
        int totalDigitsOfNumbersInCurDigit = 0;
        // 给的数n，经过上述的逐位数的盘剥后，还剩多少
        int remainingN = n;
        // 累计经过了多大的数
        int cumulativeNum = 0;
        
        // 如果remainingN > numOfNumbersInCurDigit * curDigit，
        // 意味着 n 在完全铺满本位数之后，还“有得剩”，即我们要找的数是大于当前位数的数
        while (remainingN > numOfNumbersInCurDigit * curDigit)
        {
            totalDigitsOfNumbersInCurDigit = numOfNumbersInCurDigit * curDigit;
            remainingN -= totalDigitsOfNumbersInCurDigit;
            
            numOfNumbersInCurDigit *= 10; // 9, 90, 900, 9000...
            cumulativeNum += numOfNumbersInCurDigit; // 9, 99, 999, 9999...
            curDigit++;
        }
        
        // 这表示我们最终停止在哪个数上（下面的其实是最终数的前一个数）。这个数由两部分加和而成：
        // 第一部分表示n能超越的最大的整个位数，比如9、99、999之类的
        // 第二部分表示n在当前的最后的位数里，又前进了几个数。比如在99的基础上再加3个数就是(100, 101,) 102
        int curNumber = cumulativeNum + Math.ceil(remainingN / curDigit); 
        char[] curNumberToCharArray = String.valueOf(curNumber).toCharArray();
        
        // 这表示我们经过了最后的一个整的数以后，还需要最后前进几位
        int remainingDigitsInCurNumber = remainingN % curDigit;
        int result = 0;
        // 如果最后需要前进 0 位，那么我们要的就是当前数的最后一位
        if (remainingDigitsInCurNumber == 0)
            result = curNumberToCharArray[curNumberToCharArray.length - 1];
        else
            result = curNumberToCharArray[remainingDigitsInCurNumber - 1];
        
        // char 转为 int 的方式！
        return Character.getNumericValue(result); 
    }
    
}
