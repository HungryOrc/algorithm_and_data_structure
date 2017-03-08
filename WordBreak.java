/* Given a string s and a dictionary of words dict, 
determine if s can be break into a space-separated sequence of one or more dictionary words.

Example
Given s = "lintcode", dict = ["lint", "code"].
Return true because "lintcode" can be break as "lint code". */

public class Solution {

    // 方法1：我的 DFS + 记忆化。速度较慢
    public boolean wordBreak(String s, Set<String> dict) {
        
        if (s == null || s.length() == 0) {
            return true;
        }
        if (dict == null || dict.size() == 0) {
            return false;
        }
        
        Stack<Integer> startIndexes = new Stack<>();
        
        // 关键所在！！！记忆化！！！
        boolean[] validBreakPoints = new boolean[s.length() + 1];
        
        // 这里 i 是 end index
        for (int i = 1; i <= s.length(); i++) {
            // substring方法里，start是inclusive的，end是exclusive的
            if (dict.contains(s.substring(0, i))) {
                // 这一轮的 end index，就是下一轮的start index，
                // 所以把合格的 i们 push到栈里去
                startIndexes.push(i);
            }
        }
            
        while (!startIndexes.isEmpty()) {
            int curStartIndex = startIndexes.pop();
            
            validBreakPoints[curStartIndex] = true;
            
            if (curStartIndex == s.length()) {
                return true;
            }
            
            // 这里 i 是 end index
            for (int i = curStartIndex + 1; i <= s.length(); i++) {
                if (validBreakPoints[i] == false) {
                    if (dict.contains(s.substring(curStartIndex, i))) {
                        startIndexes.push(i);
                        validBreakPoints[i] = true;
                    }
                }
            }
        }
        
        // 到了这里，表明所有的情况都遍历过了，还是没有，就是不可能有了
        return false;
    }
    
    
    // 方法2：九章的DP。没看明白？？？？？？
    // Ref: http://www.jiuzhang.com/solutions/word-break/
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int maxLength = getMaxLength(dict);
        boolean[] canSegment = new boolean[s.length() + 1];

        canSegment[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            canSegment[i] = false;
            for (int lastWordLength = 1;
                     lastWordLength <= maxLength && lastWordLength <= i;
                     lastWordLength++) {
                if (!canSegment[i - lastWordLength]) {
                    continue;
                }
                String word = s.substring(i - lastWordLength, i);
                if (dict.contains(word)) {
                    canSegment[i] = true;
                    break;
                }
            }
        }

        return canSegment[s.length()];
    }
    
    private int getMaxLength(Set<String> dict) {
        int maxLength = 0;
        for (String word : dict) {
            maxLength = Math.max(maxLength, word.length());
        }
        return maxLength;
    }
    
}
