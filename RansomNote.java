/* Given an arbitrary ransom note string and another string containing letters from all the magazines (弹匣，字母库), 
write a function that will return true if the ransom note can be CONSTRUCTED from the magazines ; otherwise, it will return false.
Each letter in the magazine string can only be used once in your ransom note.
注意：这题的意思是，magazine里的字母种类及个数够用于拼凑ransomNote就行，不必是按照一样的出现次序，这里不是要在String里找SubString
Note: You may assume that both strings contain only lowercase letters.
canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true
/*

public class Solution {
    
    // 用HashMap，在magazine里出现则+1，在ransomNote里出现则-1
    // 注意：HashMap.getOrDefault(key, defaultValue) 意为：找到则get value of that key，没找到则return the defaultValue
    public boolean canConstruct(String ransomNote, String magazine) {
        
        HashMap<Character, Integer> letterCounts = new HashMap<>();
        
        for (Character c : magazine.toCharArray())
        {
            int curLetterCount = letterCounts.getOrDefault(c, 0);
            letterCounts.put(c, curLetterCount+1);
        }
        for (Character c : ransomNote.toCharArray())
        {
            int curLetterCount = letterCounts.getOrDefault(c, 0);
            curLetterCount --;
            if (curLetterCount < 0)
                return false;
            else
                letterCounts.put(c, curLetterCount);
        }
        return true;
    }
}
