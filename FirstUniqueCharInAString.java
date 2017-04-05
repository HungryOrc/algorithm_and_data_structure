/* Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
Examples:
s = "leetcode", return 0.
s = "loveleetcode", return 2.
*/

public class Solution {
    
    // 用一个长度为26的int数组来做。巧妙。
    // Ref: https://discuss.leetcode.com/topic/55148/java-7-lines-solution-29ms
    public int firstUniqChar (String s)
    {
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a'] ++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1)
                return i;
        }
        return -1;
    }
    
}
