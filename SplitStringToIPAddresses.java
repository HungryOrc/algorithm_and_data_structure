/* Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",
return ["255.255.11.135", "255.255.111.35"] */

// 我的方法。DFS
public class Solution {
    
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) {
            return result;
        }
        
        getAllPossibleDividings_DFS(s.toCharArray(), 0, 0, "", result);
        return result;
    }
    
    // number of sections 最终必须是3，不能多不能少
    // startIndex 是当前要放的dot最左能放在哪里，
    // 比如startIndex=0的话，即当前的dot最左能放在 charArray[0] 和 charArray[1] 之间
    private void getAllPossibleDividings_DFS(char[] cArray, int startIndex, int numOfSections,
        String curString, List<String> result) {
        
        if (numOfSections == 4) {
            if (startIndex == cArray.length) {
                result.add(curString);
            }
            return;
        }
        
        int maxIndex = Math.min(startIndex + 2, cArray.length - 1); // 下面这次loop，最右端最多到哪里
        StringBuilder thisSection = new StringBuilder();
        
        for (int i = startIndex; i <= maxIndex; i++) {
            thisSection.append(cArray[i]);
            
            if (isValidSection(thisSection)) {
                getAllPossibleDividings_DFS(cArray, i + 1, numOfSections + 1, 
                    curString + thisSection.toString() + (numOfSections <= 2 ? "." : ""), result);
                // 对于前三节，后面要加dot。注意这里要用 numOfSections<=2，不要用<=3 ！
                // 这里把curString的更新写成这样存在于实参里的 A + B + C 的形式，
                // 是为了不用在DFS之前把curString复制一遍再加东西，然后在DFS之后又把加上的东西去掉，
                // 免去这些操作，能省很多时间！
            }
        }
    }
    
    private boolean isValidSection(StringBuilder sb) {
        String s = sb.toString();
        if (s.length() > 1 && s.charAt(0) == '0') { // 这是为了防范某一节为 035 或者 02 这种样子！
            return false;
        }
        
        int num = Integer.valueOf(s);
        if (num >= 0 && num <= 255) { // 0.0.0.1 这样的ip是可以的
            return true;
        }
        return false;
    }
    
}
