/* Write a function to generate the generalized abbreviations of a word.
Example:
Given word = "word", return the following list (order does not matter):
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]

Ref: https://leetcode.com/problems/generalized-abbreviation/
word里的每一个字母要么被缩写，要么不被缩写，所以最终一共有2的n次方种方式，n=word.length().

The idea is: for every character, we can keep it or abbreviate it. 
To keep it, we add it to the current solution and carry on backtracking. 
To abbreviate it, we omit it in the current solution, but increment the count, 
which indicates how many characters have we abbreviated. 
When we reach the end or need to put a character in the current solution, 
and count is bigger than zero, we add the number into the solution. */

public class Solution {
    
    public List<String> generateAbbreviations(String word) {
        ArrayList<String> result = new ArrayList<>();
        
        permutation(word, 0, 0, "", result);
        return result;
    }
    
    private static void permutation(
        String word, int curPos, int curCount, String curStr, ArrayList<String> result) {
        
        if (curPos == word.length()) {
            if (curCount > 0) { // 如果最后结尾是个数字，则别忘了要把它加上 ！！
                curStr += curCount;
            }
            result.add(curStr);
        }
        else {
            // 情况1：the char at index "curPos" in "word" is NOT abbreviated to number
            permutation(word, curPos + 1, 0, // 注意，到了这里，curCount要归零
                (curCount > 0) ? curStr + curCount + word.charAt(curPos) : curStr + word.charAt(curPos), result);
            
            // 情况2： the char at index "curPos" in "word" is abbreviated to number
            permutation(word, curPos + 1, curCount + 1, curStr, result);
        }
    }   
}
