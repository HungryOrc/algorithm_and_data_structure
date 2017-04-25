/* Find all occurrence of anagrams of a given string s in a given string l. Return the list of starting indices.

Assumptions:
s is not null or empty.
l is not null.

Examples:
l = "abcbac", s = "ab", return [0, 3] since the substring with length 2 starting from index 0/3 are all anagrams of "ab" ("ab", "ba"). */

// 我的方法：Sliding Window + 标记数组
public class Solution {
  
  List<Integer> allAnagrams(String s, String l) {
    List<Integer> result = new ArrayList<>();
    
    if (l == null || l.length() == 0 || l.length() < s.length()) {
      return result;
    }
    
    int[] recordOfS = new int[26];
    for (char c : s.toCharArray()) {
      recordOfS[c - 'a'] ++;
    }
    
    int[] recordOfL = new int[26];
    for (int i = 0; i < s.length(); i++) {
      recordOfL[l.charAt(i) - 'a'] ++;
    }
    
    for (int i = 0; i <= l.length() - s.length(); i++) {
      // 注意！！java内置的比较2个数组是否相等的语法 ！！
      // Arrays.equals(array1, array2)
      if (Arrays.equals(recordOfS, recordOfL)) {
        result.add(i);
      }
      
      // update the array for the next window
      if (i < l.length() - s.length()) {
        recordOfL[l.charAt(i) - 'a'] --;
        recordOfL[l.charAt(i + s.length()) - 'a'] ++;
      }
    }
    
    return result;
  }
  
}
