/* 我自己想到的题。按从小到大的顺序，给出大于等于 startNum 的 n 个 Palindrome Number。例如：
startNum = 0, n = 4, 答案为 0, 1, 2, 3
startNum = 9, n = 5, 答案为 9, 11, 22, 33, 44
startNum = 97, n = 14, 答案为 99, 101, 111, 121, 131, 141, 151, 161, 171, 181, 191, 202, 212, 222
startNum = 1657, n = 3, 答案为 1661, 1771, 1881
startNum = 1688, n = 3, 答案为 1771, 1881, 1991 

解法：
If we got 1540 as the starting number, then cut it into half, that is 15, then generate 1551, 
and then i add the previous half number 15 by 1, that will be 16, then the next palindrome will be 1661,
so it goes on like:
16 + 1 = 17 -> 1771
17 + 1 = 18 -> 1881
19 -> 1991
20 -> 2002
21 -> 2112
... */

public class Solution
{
    private long[] getNextNPalindromes(long startNum, int totalCount)
    {
        if (startNum < 0 || startNum > 1000000000 || totalCount < 1 || totalCount > 10000)
        {
            return null;
        }
        
        long[] palindromes = new long[totalCount];

        int numOfDigitsOfStartNum = startNum.ToString().Length;
        long firstHalfOfStartNum = getFirstHalf(startNum);

        long smallestPalindromeAfterStartNum =
            generatePalindromeBasedOnHalfNumber(firstHalfOfStartNum, numOfDigitsOfStartNum / 2);
        
        // 比如startNum = 1654，那么下一个回文数是 1661，大于1654，可以用
        // 如果startNum = 1688， 下一个回文数还是1661，小于1688，不行。但是再下一个回文数即1771一定可以了！！
        // 原因是，在前两位里，17 > 16，无论如何也能保证下一个回文数大于startNum。如果startNum是奇数位的，这么做也没问题，
        // 详见下文我们生成回文数的方式
        if (smallestPalindromeAfterStartNum < startNum)
        {
            smallestPalindromeAfterStartNum = getNextPalindrome(smallestPalindromeAfterStartNum);
        }

        palindromes[0] = smallestPalindromeAfterStartNum;

        long curPalindrome = smallestPalindromeAfterStartNum;
        // 已经有一个回文数了，所以接下来只用生成 totalCount - 1 个回文数即可，所以序号从1开始
        for (int i = 1; i < totalCount; i++)
        {
            curPalindrome = getNextPalindrome(curPalindrome);
            palindromes[i] = curPalindrome;
        }
        return palindromes;
    }

    // 如果num是偶数位，比如1688，则它的前半部分取为 16
    // 如果num是奇数位，比如16888，则它的前半部分取为 168，即多取一位，包含正中间那位
    private long getFirstHalf(long num)
    {
        long firstHalfOfNum = num;
        int numOfDigitsOfNum = num.ToString().Length;

        // 举例，如果num有6位，则除以10三次；如果num有7位，则除以10还是三次
        for (int i = 1; i <= numOfDigitsOfNum / 2; i++)
        {
            firstHalfOfNum = firstHalfOfNum / 10;
        }
        return firstHalfOfNum;
    }

    // 举例，如果原num是 1688（四位数），则传进来的halfNum将为 16，halfLen将为 2，本函数的处理将会是 16 连上 6 再连上 1
    // 如果原num是 16880（五位数），则传进来的halfNum将为 168，halfLen将为 2，本函数的处理将会是 168 连上 6 再连上 1
    private long generatePalindromeBasedOnHalfNumber(long firstHalfOfNum, int halfLenOfNum)
    {
        string firstHalfString = firstHalfOfNum.ToString();
        char[] firstHalfCharArray = firstHalfString.ToCharArray();

        StringBuilder sb = new StringBuilder(firstHalfString);
        for (int i = halfLenOfNum - 1; i >= 0; i--)
        {
            sb.Append(firstHalfCharArray[i]);
        }
        string palindromeString = sb.ToString();
        long palindromeInt = Convert.ToInt64(palindromeString);
        return palindromeInt;
    }

    private long getNextPalindrome(long curPalindrome)
    {
        int numOfDigitsOfCurPalindrome = curPalindrome.ToString().Length;
        long firstHalfOfCurPalindrome = getFirstHalf(curPalindrome);

        // check if curPalindrome == 9 or 99 or 999 or 9999...
        // if yes, then the next palindrome should be 11 or 101 or 1001 or 10001...
        int powerOfTens = 1;
        for (int i = 1; i <= numOfDigitsOfCurPalindrome; i++)
        {
            powerOfTens *= 10;
        }
        if (curPalindrome + 1 == powerOfTens)
        {
            return powerOfTens + 1; // 10+1, or 100+1, or 1000+1, or 10000+1...
        }
        
        // 如果不是上述的特殊情况，则按一般的方式走
        return generatePalindromeBasedOnHalfNumber(
            firstHalfOfCurPalindrome + 1, numOfDigitsOfCurPalindrome / 2);
    }

}
