/* 我自己想到的题。按从小到大的顺序，给出大于等于 startNum 的 n 个 Palindrome Number。例如：
startNum = 0, n = 4, 答案为 0, 1, 2, 3
startNum = 9, n = 5, 答案为 9, 11, 22, 33, 44
startNum = 97, n = 14, 答案为 99, 101, 111, 121, 131, 141, 151, 161, 171, 181, 191, 202, 212, 222
startNum = 1657, n = 3, 答案为 1661, 1771, 1881
startNum = 1688, n = 3, 答案为 1771, 1881, 1991 */

public class Solution
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }

    public void GeneratePalindromes(Object sender, EventArgs e)
    {
        lb_Palindromes.Items.Clear();

        long startNum = 0;
        int totalCount = 0;

        try
        {
            startNum = Convert.ToInt64(tb_startNum.Text);
            totalCount = Convert.ToInt32(tb_totalCount.Text);
        }
        catch (FormatException error)
        {
            hiddenErrorMsg.Text = "Please enter a positive integer within range.";
            return;
        }

        if (startNum < 0 || startNum > 1000000000 || totalCount < 1 || totalCount > 100)
        {
            hiddenErrorMsg.Text = "Please enter a positive integer within range.";
            return;
        }

        string[] palindromes = getNextNPalindromes(startNum, totalCount);

        for (int i = 0; i < totalCount; i++)
        {
            lb_Palindromes.Items.Add(palindromes[i]);
        }
    }

    private string[] getNextNPalindromes(long startNum, int totalCount)
    {
        string[] palindromes = new string[totalCount];

        int numOfDigitsOfStartNum = startNum.ToString().Length;
        long firstHalfOfStartNum = getFirstHalf(startNum);

        long smallestPalindromeAfterStartNum =
            generatePalindromeBasedOnHalfNumber(firstHalfOfStartNum, numOfDigitsOfStartNum / 2);
        if (smallestPalindromeAfterStartNum < startNum)
        {
            smallestPalindromeAfterStartNum = getNextPalindrome(smallestPalindromeAfterStartNum);
        }

        palindromes[0] = smallestPalindromeAfterStartNum.ToString();

        long curPalindrome = smallestPalindromeAfterStartNum;
        for (int i = 1; i < totalCount; i++)
        {
            curPalindrome = getNextPalindrome(curPalindrome);
            palindromes[i] = curPalindrome.ToString();
        }
        return palindromes;
    }

    private long getFirstHalf(long num)
    {
        long firstHalfOfNum = num;
        int numOfDigitsOfNum = num.ToString().Length;

        for (int i = 1; i <= numOfDigitsOfNum / 2; i++)
        {
            firstHalfOfNum = firstHalfOfNum / 10;
        }
        return firstHalfOfNum;
    }

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
        return generatePalindromeBasedOnHalfNumber(
            firstHalfOfCurPalindrome + 1, numOfDigitsOfCurPalindrome / 2);
    }

}
