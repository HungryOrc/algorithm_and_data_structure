/* Given a digit string, return all possible letter combinations that the number could represent.
A mapping of digit to letters (just like on the telephone buttons) is given below.

题图见：
https://leetcode.com/problems/letter-combinations-of-a-phone-number/#/description

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Note: In your answer, the Strings can be placed in any order you want. */

// 我的方法。DFS
public class Solution {
    
    public List<String> letterCombinations(String digits) {
        
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        
        HashMap<Character, Character[]> keysAndChars = new HashMap<>();
        keysAndChars.put('2', new Character[]{'a', 'b', 'c'});
        keysAndChars.put('3', new Character[]{'d', 'e', 'f'});
        keysAndChars.put('4', new Character[]{'g', 'h', 'i'});
        keysAndChars.put('5', new Character[]{'j', 'k', 'l'});
        keysAndChars.put('6', new Character[]{'m', 'n', 'o'});
        keysAndChars.put('7', new Character[]{'p', 'q', 'r', 's'});
        keysAndChars.put('8', new Character[]{'t', 'u', 'v'});
        keysAndChars.put('9', new Character[]{'w', 'x', 'y', 'z'});
        
        String validDigits = validInput(digits);
        char[] cArray = validDigits.toCharArray();
        
        getAllPossibleStrings(cArray, 0, "", keysAndChars, result);
        return result;
    }
    
    private void getAllPossibleStrings(char[] cArray, int curIndex, String curString,
                                      HashMap<Character, Character[]> keysAndChars,
                                      List<String> result) {
        if (curIndex == cArray.length) {
            result.add(curString);
            return;
        }
        
        char curDigit = cArray[curIndex];
        Character[] curLetters = keysAndChars.get(curDigit);
        
        for (char c : curLetters) {
            getAllPossibleStrings(cArray, curIndex + 1, curString + c, keysAndChars, result);
        }
    }
    
    private String validInput(String digits) {
        StringBuilder sb = new StringBuilder();
        for (char c : digits.toCharArray()) {
            if (c == '1' || c == '*' || c == '0' || c == '#') {
                continue;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    
}
