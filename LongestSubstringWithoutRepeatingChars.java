/* Given a string, find the longest substring without any repeating characters and return the length of it. 
The input string is guaranteed to be not null.
For example, the longest substring without repeating letters for "bcdfbd" is "bcdf", we should return 4 in this case. */

// Ref: https://leetcode.com/articles/longest-substring-without-repeating-characters/

/* 方法1：Sliding Window with HashSet，One-Pass
Time complexity: O(2n) = O(n). In the worst case each character will be visited twice by i and j.
Space complexity: O(min(m, n)). We need O(k) space for the sliding window, where k is the size of the Set, 
and the size of the Set is upper bounded by the size of the string n and the size of the charset/alphabet m. */
public class Solution {
  
  public int longest(String input) {
    
    HashSet<Character> chars = new HashSet<>();
    
    int i = 0, j = 0; // i -> tail of substring, j -> head of substring
    int maxLen = 0;
    
    while (i < input.length() && j < input.length()) {
      
      if (!chars.contains(input.charAt(i))) {
        chars.add(input.charAt(i));
        i ++;
        maxLen = Math.max(maxLen, i - j);
      }
      
      else { // set contains current char
        // remove the char(s) at the head of the substring from the set,
        // until the set no longer contains the current char at i
        chars.remove(input.charAt(j));
        j ++;
      }
    }
    
    return maxLen;
  }
}


/* 方法2：Sliding Window with HashMap, One- Pass
思路的道理，是继承于上面的set的方法的，这里进一步提炼了
Time complexity: O(n). Index i will iterate n times.
Space complexity: O(min(m, n)). Same as the previous approach. */
public class Solution {
  
  public int longest(String input) {
    int maxLen = 0;
    
    HashMap<Character, Integer> map = new HashMap<>();
    
    // try to extend the range [i, j]
    for (int i = 0, j = 0; i < input.length(); i++) {
      
      if (map.containsKey(input.charAt(i))) {
        // j 是当前的substring最早的可被允许开始的地方，这个开始的地方的要求是：
        // 必须是 input.charAt(i) 这个char上次出现的位置的后一位，或者更后；
        // 而且必须 不早于上一轮的 j
        j = Math.max(map.get(input.charAt(i)) + 1, j);
      }
      
      maxLen = Math.max(maxLen, i - j + 1);
      map.put(input.charAt(i), i);
    }
    
    return maxLen;
  }
}
