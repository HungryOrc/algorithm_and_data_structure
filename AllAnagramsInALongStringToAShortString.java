/* Find all occurrence of anagrams of a given string s in a given string l. Return the list of starting indices.

Assumptions:
s is not null or empty.
l is not null.

Examples:
l = "abcbac", s = "ab", return [0, 3] since 
the substring with length 2 starting from index 0/3 are all anagrams of "ab" ("ab", "ba"). */


// 方法1：比较普适而严谨的方法。看起来挺长，其实思路很直觉很简明。后面有2个helper function所以显得长
public class Solution {
  
  int numOfMatches;
  
  List<Integer> allAnagrams(String s, String l) {
    List<Integer> result = new ArrayList<>();
    if (l == null || l.length() == 0 || l.length() < s.length()) {
      return result;
    }
    
    HashMap<Character, Integer> records = new HashMap<>();
    
    for (char c : s.toCharArray()) {
      records.put(c, records.getOrDefault(c, 0) + 1);
    }
    
    int kindsOfCharsInS = records.size();
    numOfMatches = 0;
    
    char[] cArray = l.toCharArray();
    for (int i = 0; i < s.length(); i++) {
      removeCharFromHashMap(cArray, i, records);
    }
    if (numOfMatches == kindsOfCharsInS) {
      result.add(0);
    }
    
    for (int i = 1; i <= l.length() - s.length(); i++) {
      
      addCharToHashMap(cArray, i - 1, records);
      removeCharFromHashMap(cArray, i + s.length() - 1, records);
      
      if (numOfMatches == kindsOfCharsInS) {
        result.add(i);
      }
    }
    
    return result;
  }
  
  private void removeCharFromHashMap(char[] cArray, int i,
    HashMap<Character, Integer> records) {
    Integer frequency = records.get(cArray[i]);
      
    if (frequency == null) {
      return;
    } else {
      if (frequency == 1) {
        numOfMatches ++;
      } else if (frequency == 0) {
        numOfMatches --;
      }
      records.put(cArray[i], frequency - 1);
    }
  }
  
  private void addCharToHashMap(char[] cArray, int i,
    HashMap<Character, Integer> records) {
    Integer frequency = records.get(cArray[i]);
      
    if (frequency == null) {
      return;
    } else {
      if (frequency == -1) {
        numOfMatches ++;
      } else if (frequency == 0) {
        numOfMatches --;
      }
      records.put(cArray[i], frequency + 1);
    }
  } 
}


// 方法2：比较投机取巧的方法：Sliding Window + 26位的标记数组
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
