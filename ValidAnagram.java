/* Given two strings s and t, write a function to determine if t is an anagram of s.
For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.
Note: You may assume the string contains only lowercase alphabets.
Follow up: What if the inputs contain unicode characters? How would you adapt your solution to such case?
*/

public class Solution {

    public boolean isAnagram(String s, String t) {
        
        int[] letterCounts = new int[26];
        for (char c : s.toCharArray())
            letterCounts[c - 'a']++;
        for (char c : t.toCharArray())
            letterCounts[c - 'a']--;
        
        for (int count : letterCounts)
        {
            if (count != 0)
                return false;
        }
        return true;
    }
}
