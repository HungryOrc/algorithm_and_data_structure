/* Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.

Example
Given s = "aab", return:
[
  ["aa","b"],
  ["a","a","b"]
] */

public class Solution {
    
    // 我自己的方法：DFS
    public List<List<String>> partition(String s) {
        
        List<List<String>> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }        
        
        dfs(s, 0, new ArrayList<String>(), result);
        return result;
    }
    
    private void dfs(String s, int startIndex, ArrayList<String> permutation,
        List<List<String>> result) {
    
        if (startIndex == s.length()) {
            result.add(new ArrayList<String>(permutation));
            return;
        }
        
        // 因为在Strin.substring函数里，startIndex是inclusive的，endIndex是exclusive的，
        // 所以 endIndex 要起始于 startIndex + 1，终止于 = length 而非 < length！！
        for (int endIndex = startIndex + 1; endIndex <= s.length(); endIndex++) {
            
            String subString = s.substring(startIndex, endIndex);
            
            if (isPalindrome(subString)) {
                permutation.add(subString);
                // 注意！下一次的分析的开始位置，是这一次的subString的结束位置+1，
                // 也就是当前的endIndex位置！！
                dfs(s, endIndex, permutation, result);
                permutation.remove(permutation.size() - 1);
            }
        }
    }
    
    private boolean isPalindrome(String string) {
        char[] cArray = string.toCharArray();
        int len = cArray.length;
        
        for (int i = 0; i < len / 2; i++) {
            if (cArray[i] != cArray[len - i - 1]) {
                return false;
            }
        }
        return true;
    }
    
}
