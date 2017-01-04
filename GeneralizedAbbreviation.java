/* Write a function to generate the generalized abbreviations of a word.
Example:
Given word = "word", return the following list (order does not matter):
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]

Ref: https://leetcode.com/problems/generalized-abbreviation/
很巧妙的思维。我没有想到。word里的每一个字母要么被缩写，要么不被缩写，所以最终一共有2的n次方种方式，n=word.length().
The idea is: for every character, we can keep it or abbreviate it. 
To keep it, we add it to the current solution and carry on backtracking. 
To abbreviate it, we omit it in the current solution, but increment the count, 
which indicates how many characters have we abbreviated. 
When we reach the end or need to put a character in the current solution, 
and count is bigger than zero, we add the number into the solution. */
// 关键！这种采用分支来处理问题的方法，一定要每次都开设新的StringBuilder来承接上一步的阶段性结果！

public class Solution 
{
    // 根据上面的思路，我自己的一种朴素实现。速度较慢
    // 每遇到一个char，就把它留下，或者把它转化成'1'然后放到StringBuilder里
    // 最后再把每个StringBuilder里的1综合成各个数字
    public List<String> generateAbbreviations(String word)
    {
        ArrayList<String> result = new ArrayList<>();
        
        permutation(word, 0, "", result);
        
        for (int i = 0; i < result.size(); i++)
        {
            String curString = result.get(i);
            curString = convertOnesToNumber(curString);
            result.set(i, curString);
        }
        return result;
    }
    private static void permutation(String word, int curPos, String curStr, ArrayList<String> result)
    {
        if (curPos == word.length())
            result.add(curStr);
        else
        {
            // the char at index "curPos" in "word" is not abbreviated
            StringBuilder sb1 = new StringBuilder();
            sb1.append(curStr);
            sb1.append(word.charAt(curPos));
            permutation(word, curPos+1, sb1.toString(), result);
            
            // the char at index "curPos" in "word" is abbreviated
            StringBuilder sb2 = new StringBuilder();
            sb2.append(curStr);
            sb2.append(1);
            permutation(word, curPos+1, sb2.toString(), result);
        }
    }
    private static String convertOnesToNumber(String str)
    {
        StringBuilder sb = new StringBuilder();
        int curCount = 0;
        for (char c : str.toCharArray())
        {
            if (c != '1')
            {
                if (curCount > 0)
                    sb.append(curCount);
                curCount = 0;
                sb.append(c);
            }
            else // c == '1'
                curCount ++;
        }
        
        // 如果str的最后一个或几个char曾被转化成1
        if (curCount > 0)
            sb.append(curCount);
            
        return sb.toString();
    }
    
}
