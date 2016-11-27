/* Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20, 100.
The order of output does not matter.
注意！不用正向循环，也不用反向循环！出现同样次数即可！比如 abdc 也算是 abcd 的 anagram（回文）！！

Example 1:
Input: s: "cbaebabacd" p: "abc"
Output: [0, 6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:
Input: s: "abab" p: "ab" 
Output: [0, 1, 2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
*/

// Ref: https://discuss.leetcode.com/topic/64434/shortest-concise-java-o-n-sliding-window-solution/3

/* Same idea from a fantastic sliding window template, please refer:
https://discuss.leetcode.com/topic/30941/here-is-a-10-line-template-that-can-solve-most-substring-problems
上面这个链接还没看过，有空了看一看！！

Time Complexity will be O(n) because the "start" and "end" points will only move from left to right once. */
public class Solution {
    
    public List<Integer> findAnagrams(String s, String p) 
    {
        ArrayList<Integer> list = new ArrayList<>();
        
        if (s == null || s.length() == 0 || p == null || p.length() == 0)
            return list;
        
        int[] hash = new int[256]; //character hash
        
        //record each character in p to hash
        for (char c : p.toCharArray()) {
            hash[c]++;
        }
        //two points, initialize count to p's length
        int left = 0, right = 0, count = p.length();
        
        while (right < s.length())
        {
            //move right everytime, if the character exists in p's hash, decrease the count
            //current hash value >= 1 means the character currently exists in p
            if (hash[s.charAt(right)] >= 1)
                count--;
            hash[s.charAt(right)]--; // 无论有没有，都-1
            right++;                 // 无论有没有，都右移一位
            
            //when the count is down to 0, means we found the right anagram
            //then add window's left to result list
            if (count == 0)
                list.add(left);
                // 为什么这一步要在下一步之前？？？没理解。我试过了倒过来就不行
                
            // 当 right-left 等于 p.length() 的时候，意味着包含right与left在内的长度已经比 p.length() 大一了
            //then we have to move left (narrow the window) to find the new match window
            //++ to reset the hash because we kicked out the left
            //only increase the count if the character is in p
            //the count >= 0 indicate it was original in the hash, cuz it won't go below 0
            if (right - left == p.length())
            {
                if (hash[s.charAt(left)] >= 0) //indicate it was original in the hash
                    count++;
                hash[s.charAt(left)]++;
                left++;
            }
        }
        return list;
    }
}
