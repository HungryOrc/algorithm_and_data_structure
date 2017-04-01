/* Given a string s and a dictionary of words dict, add spaces in s to construct a sentence 
where each word is a valid dictionary word.
Return all such possible sentences.

Example
Gieve s = lintcode,
dict = ["de", "ding", "co", "code", "lint"].
A solution is: ["lint code", "lint co de"]. */

// 方法1：朴素的DPS方法。较慢
public class Solution {

    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> result = new ArrayList<String>();
        if (wordDict == null || wordDict.size() == 0) {
            return result;
        }

        StringBuilder sb = new StringBuilder();        
        findWordBreaks(s, 0, sb, wordDict, result);
        return result;
    }
    
    private void findWordBreaks(String s, int curIndex, 
        StringBuilder sb, Set<String> dict, List<String> result) {
        
        if (curIndex == s.length()) {
            String curString = sb.substring(0, sb.length() - 1); // 去掉最后一个空格
            result.add(curString);
            return;
        }
        
        for (int i = curIndex; i < s.length(); i++) {
            String nextString = s.substring(curIndex, i + 1);
            
            if (dict.contains(nextString)) {
                
                int initialLength = sb.length();
                sb.append(nextString);
                sb.append(" ");
                
                findWordBreaks(s, i + 1, sb, dict, result);
                
                sb.delete(initialLength, sb.length());
            }
        }    
    }   
}


// 方法2：用记忆化，DP的思想优化过的 DFS ！！！很厉害 ！！！
// Ref: http://www.jiuzhang.com/solutions/word-break-ii/
public class Solution {
    
    public List<String> wordBreak(String s, Set<String> wordDict) {
        ArrayList<String> result = new ArrayList<String>();
        if (s.length() == 0) {
            return result;
        }
        
        // 以字符i开头含i，以字符j结尾含j的字符串，是不是在dict里存在
        boolean[][] isWord = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String word = s.substring(i, j + 1);
                isWord[i][j] = wordDict.contains(word);
            }
        }
        
        // 字典里是否存在任何以字符i开头的字符串
        boolean[] possible = new boolean[s.length() + 1];
        possible[s.length()] = true;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (isWord[i][j] && possible[j + 1]) {
                    possible[i] = true;
                    break;
                }
            }
        }
        
        List<Integer> path = new ArrayList<Integer>();
        search(0, s, path, isWord, possible, result);
        return result;
    }
    
    private void search(int index, String s, List<Integer> path,
                   boolean[][] isWord, boolean[] possible, List<String> result) {
        
        // 如果dict里面根本不存在任何以字符index开头的单词，则不必再继续下去了 ！！！
        if (!possible[index]) {
            return;
        }
        
        // 如果已经走到s的尾巴的后一位了，那么就是成功得到了答案之一
        if (index == s.length()) {
            StringBuilder sb = new StringBuilder();
            int lastIndex = 0;
            for (int i = 0; i < path.size(); i++) {
                sb.append(s.substring(lastIndex, path.get(i)));
                if (i != path.size() - 1) sb.append(" ");
                lastIndex = path.get(i);
            }
            result.add(sb.toString());
            return;
        }
        
        for (int i = index; i < s.length(); i++) {
            // 如果dict里面根本就不存在以字符index开头，以字符i结尾的单词，就不要继续了 ！！！
            if (!isWord[index][i]) {
                continue;
            }
            path.add(i + 1);
            search(i + 1, s, path, isWord, possible, result);
            path.remove(path.size() - 1);
        }
    }
}
