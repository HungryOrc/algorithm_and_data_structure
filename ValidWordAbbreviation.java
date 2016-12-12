/* Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.
A string such as "word" contains only the following valid abbreviations:
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". 
Any other string is not a valid abbreviation of "word".

Note: Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Examples:
Given s = "internationalization", abbr = "i12iz4n": Return true.
Given s = "apple", abbr = "a2e": Return false.
Given s = "a", abbr = "2": Return false.
Given s = "a", abbr = "01": Return false. */

public class Solution 
{
    // 朴素的思路
    public boolean validWordAbbreviation(String word, String abbr) 
    {
        if ((word.length()==0 && abbr.length()!=0) || (word.length()!=0 && abbr.length()==0))
            return false;
        
        char[] wordArray = word.toCharArray();
        char[] abbrArray = abbr.toCharArray();
        
        int index_Abbr = 0;
        int index_Word = 0;
        int curNum = 0;
        
        // 以 String abbr 为纲，一个一个char往后捋
        for (; index_Abbr < abbrArray.length; index_Abbr++)
        {
            // 如果 String word 长度少于 abbr 所能代表的长度，后者由 index_Word 所体现
            if(index_Word > word.length()-1)
                return false;
                    
            char curAbbrChar = abbrArray[index_Abbr];
            
            // 这个是为了保证以下的情况：
            // Given s = "a", abbr = "01": Return false
            // 但我觉得这个情况应该规定为 true 更好，我不支持题意对这种情况的false规定
            if (curNum == 0 && curAbbrChar == '0')
                return false;
            
            // if the current char in the abbreviation is among 0 to 9
            // curAbbrChar - '0' >= 0 是为了保证保证 cur char 不会跑到'0'的前面去！！
            if (curAbbrChar - '0' <= 9 && curAbbrChar - '0' >= 0)
            {
                // abbr字符串里的数字可能是多位数！！所以要考虑连续超过一位都是数字的情况
                curNum *= 10;
                curNum += Character.getNumericValue(curAbbrChar);
            }
            // if the current char in the abbreviation is a lowercase letter
            else
            {
                // 把前面累计的，可能含有多个数位的数，归总加到 index_Word 里面去
                index_Word += curNum;
                curNum = 0;
                
                if(index_Word > word.length()-1)
                    return false;
                
                if (wordArray[index_Word] != abbrArray[index_Abbr])
                    return false;
                else
                    index_Word++;
            }
        }
        
        // 如果 abbr 的最后一位或者几位都是数字的话，要把它们加到 index_Word 里面去
        // 否则上面的for循环会最后不管它们，就像它们不存在一样
        if (curNum != 0)
            index_Word += curNum;
        
        // 最后的 index_Word，应该正好等于 word 的长度。多一少一都是 false
        // 应该正好等于 word 的长度，而不是 word 的长度 -1，是因为
        // index_Word 最后指向的是word里要比较的下一位，之前的位全都比较通过了
        // 那么整个word比完了都通过了以后，再下一位其实是 null 了
        // 所以 index_Word 最终应该是等于word.length()
        if (index_Word != word.length())
            return false;
            
        return true;
    }

}

