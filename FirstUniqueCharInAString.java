/* Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
Examples:
s = "leetcode", return 0.
s = "loveleetcode", return 2.
*/

public class Solution {
    
    // 最直接的想法
    // Time: O(n)
    public int firstUniqChar(String s) {
        
        char[] charArray = s.toCharArray();
        HashMap<Character, Integer> charCounts = new HashMap<>();
        ArrayList<Integer> uniqueCharsIndice = new ArrayList<>();
        
        for (int i = 0; i < charArray.length; i++)
        {
            char curChar = charArray[i];
            if (!charCounts.containsKey(curChar))
            {
                charCounts.put(curChar, i);
                // ！注意！Integer.valueOf(i) 是新创造了一个 Integer Object！！
                uniqueCharsIndice.add(Integer.valueOf(i));
            }
            else // charCounts.containsKey(curChar) == true
            {
                int curIndex = charCounts.get(curChar);
                if (uniqueCharsIndice.contains(curIndex))
                    // ！注意！remove 的必须是 Object！如果这里填 int，那就成了remove int这个index位置上的Object了！
                    uniqueCharsIndice.remove(Integer.valueOf(curIndex));
            }
        }   
        if (uniqueCharsIndice.size() == 0)
            return -1;
        else
            return uniqueCharsIndice.get(0);
    }
    
    
    // 用一个长度为26的int数组来做。巧妙。
    // Ref: https://discuss.leetcode.com/topic/55148/java-7-lines-solution-29ms
    public int firstUniqChar (String s)
    {
        int[] freq = new int[26];
        // ！String可以直接求长度！String.length() ！
        for (int i = 0; i < s.length(); i++)
            freq[s.charAt(i) - 'a'] ++;
        for (int i = 0; i < s.length(); i++)
        {
            if (freq[s.charAt(i) - 'a'] == 1)
                return i;
        }
        return -1;
    }
    
}
