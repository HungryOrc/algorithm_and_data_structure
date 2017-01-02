/* Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
所谓unique digit就是每一位的数字都不相同的数。
Example:
Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99])

n = 0 时，认为就一个数：0
n = 1，1 到 9 都符合要求，再加上前面的0
n = 2，所有的两位数除了11，22，33 - 99。再加上前面的0-9
n = 3 or bigger，在三位数里，以及更多的数位里，第一位可以有9个选择（不能选0），第二位可以有9个选择（可以选0），第三位可以有8个选择，
第四位7个，第五位6个。。。第十位1个选择。如果n大于10，则与n=10的情况相同，不再增加unique digit的数字。
注意，n=i时，要包含n=i-1，n=i-2... n=0的情况 */

public class Solution 
{
    // 我的朴素解法
    public int countNumbersWithUniqueDigits(int n) 
    {
        int[] uniqueNums = new int[11]; // for n = 0 to n = 10, so 11 slots
        uniqueNums[0] = 1; // 0
        uniqueNums[1] = 9 + uniqueInEachDigit[0]; // add new: 1 - 9
        uniqueNums[2] = 81 + uniqueInEachDigit[1]; // add new: 9 * 9
        
        int add = 9*9;
        for (int i = 3; i <= 10; i++)
        {
            uniqueNums[i] = uniqueNums[i-1];
            
            add *= (9-(i-2)); // *8, *7, *6...
            uniqueInEachDigit[i] += add;
        }
        
        if (n < 0)
            return 0;
        else if (n >= 0 && n <= 10)
            return uniqueInEachDigit[n];
        else // n > 10
            return uniqueInEachDigit[10];
    }
}
