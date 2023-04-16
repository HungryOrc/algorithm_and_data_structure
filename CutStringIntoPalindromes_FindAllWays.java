/* Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.

Example
Given s = "aab", return:
[
  ["aa","b"],
  ["a","a","b"]
] */

// DFS
public class Solution {
    
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }        
        
        dfs(s, s.toCharArray(), 0, new ArrayList<String>(), result);
        return result;
    }
    
    private void dfs(String s, char[] cArray, int start, ArrayList<String> permutation, List<List<String>> result) {
        if (start == cArray.length) {
            result.add(new ArrayList<String>(permutation));
            return;
        }

        for (int end = start; end < cArray.length; end ++) {
            if (isPalindrome(cArray, start, end)) {
              
                permutation.add(s.substring(start, end + 1));
                
                // 注意！下一次 DFS 的开始位置，是这一次的subString的结束位置+1
                dfs(s, cArray, end + 1, permutation, result);
                
                permutation.remove(permutation.size() - 1);
            }
        }
    }
    
    private boolean isPalindrome(char[] cArray, int i, int j) {
        while(i < j) {
            if (cArray[i] != cArray[j]) {
                return false;
            }
            ++i;
            --j;
        }
        return true;
    }
    
}
