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
    
    
    // 方法2：Iteration with Stack
    // Ref: http://www.jiuzhang.com/solutions/expression-expand/
    public String expressionExpand(String s) {
        
        // 注意！数字和字符和字符串都放在同一个Stack里面 ！！！
        Stack<Object> stackOfEverything = new Stack<>();
        int multiplicationOfSubstring = 0;
        
        for (char c : s.toCharArray()) {
            
            if (c >= '0' && c <= '9') {
                multiplicationOfSubstring = multiplicationOfSubstring * 10 + c - '0';
            }
            
            else if (c == '[') {
                stackOfEverything.push(multiplicationOfSubstring);
                multiplicationOfSubstring = 0;
            }
            
            else if (c == ']') {
                String substring = popSubstringInOriginalOrder(stackOfEverything);
                // 找到规律！！一个以']'结尾的子字符串的前面，一定有且只有一个数字！！！
                multiplicationOfSubstring = (int)stackOfEverything.pop();
                
                for (int i = 1; i <= multiplicationOfSubstring; i++) {
                    stackOfEverything.push(substring);
                }
                multiplicationOfSubstring = 0;
            }
            
            else { // among 'a' to 'z'
                stackOfEverything.push(String.valueOf(c));
            }
        }
        return popSubstringInOriginalOrder(stackOfEverything);
    }
    
    private String popSubstringInOriginalOrder(Stack<Object> stackOfEverything) {
        Stack<String> buffer = new Stack<>();
        
        // 注意！Object instanceof String ！！！判断是否为一个String的方法！！！
        // 在这里，原stack停止pop的条件有两种：一种是遇到数字了，一种是本身空了
        while (!stackOfEverything.isEmpty() && (stackOfEverything.peek() instanceof String)) {
            buffer.push((String)stackOfEverything.pop());
        }
        
        StringBuilder sb = new StringBuilder();
        while (!buffer.isEmpty()) {
            sb.append(buffer.pop());
        }
        return sb.toString();
    }
    
}
