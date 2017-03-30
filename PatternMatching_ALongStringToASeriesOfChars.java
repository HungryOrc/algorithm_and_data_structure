/* Given a pattern and a string input - find if the string follows the same pattern and return true or false.
Examples:
Pattern : "abab", input: "redblueredblue" should return true.
Pattern: "aaaa", input: "asdasdasdasd" should return true.
Pattern: "aabb", input: "xyzabcxzyabc" should return false. */

// 方法：backtracking，很赞 ！！！
// Ref: https://discuss.leetcode.com/topic/26161/pattern-matching

public class Solution {
  
  public boolean isMatch(String str, String pat) {
    Map<Character, String> map = new HashMap<>();
    return isMatch(str, 0, pat, 0, map);
  }
  
  boolean isMatch(String str, int i, String pat, int j, Map<Character, String> map) {
    // base case
    if (i == str.length() && j == pat.length()) return true;
    if (i == str.length() || j == pat.length()) return false;
    
    // get current pattern character
    char c = pat.charAt(j);
    
    // if the pattern character exists
    if (map.containsKey(c)) {
      String s = map.get(c);
      
      // then check if we can use it to match str[i...i+s.length()]
      if (i + s.length() > str.length() || !str.substring(i, i + s.length()).equals(s)) {
        return false;
      }
      
      // if it can match, great, continue to match the rest
      return isMatch(str, i + s.length(), pat, j + 1, map);
    }
    
    // pattern character does not exist in the map
    for (int k = i; k < str.length(); k++) {
      // create or update the map
      map.put(c, str.substring(i, k + 1));
      
      // continue to match the rest
      if (isMatch(str, k + 1, pat, j + 1, map)) {
        return true;
      }
    }
    
    // we've tried our best but still no luck
    map.remove(c);
    
    return false;
  }
  
}
