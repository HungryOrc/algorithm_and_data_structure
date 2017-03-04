/* Given an expression s includes numbers, letters and brackets. 
Number represents the number of repetitions inside the brackets(can be a string or another expression)．
Please expand expression to be a string.
Challenge: Can you do it without recursion?

Example
s = abc3[a] return abcaaa
s = 3[abc] return abcabcabc
s = 4[ac]dy, return acacacacdy
s = 3[2[ad]3[pf]]xyz, return adadpfpfpfadadpfpfpfadadpfpfpfxyz */

public class Solution {
    
    // 方法1：Recursion
    // Ref: http://www.jiuzhang.com/solutions/expression-expand/
    
    /* @param s  an expression includes numbers, letters and brackets
     * @return a string */
    public String expressionExpand(String s) {
        
        StringBuilder resultSB = new StringBuilder();
        
        int multiplicationOfSubstring = 0;
        int recordedNumOfParenthesises = 0;
        String substringInParenthesis = "";
        
        for (char c : s.toCharArray()) {
            
            if (c == '[') {
                if (recordedNumOfParenthesises == 0) {
                    recordedNumOfParenthesises ++;
                } else { // > 0
                    substringInParenthesis += "[";
                    recordedNumOfParenthesises ++;
                }
            }
            
            else if (c == ']') {
                recordedNumOfParenthesises --;
                
                if (recordedNumOfParenthesises == 0) {
                    String substringAfterExpand = expressionExpand(substringInParenthesis);
                    for (int i = 1; i <= multiplicationOfSubstring; i++) {
                        resultSB.append(substringAfterExpand);
                    }
                    
                    // 相关数据清零
                    multiplicationOfSubstring = 0;
                    substringInParenthesis = "";
                } else { // > 0
                    substringInParenthesis += "]";
                }
            }
            
            // 注意！！char 可以直接与 '0' 到 '9' 这么比！！
            else if (c >= '0' && c <= '9') {
                if (recordedNumOfParenthesises == 0) {
                    multiplicationOfSubstring = 10 * multiplicationOfSubstring + c - '0';
                } else { // > 0
                    substringInParenthesis += c;
                }
            }
            
            else { // among 'a' to 'z'
                if (recordedNumOfParenthesises == 0) {
                    resultSB.append(c);
                } else { // > 0
                    substringInParenthesis += c;
                }
            }
        }
        return resultSB.toString();
    }
    
    
    
}
