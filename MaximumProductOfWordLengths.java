/* Given a string array words, find the maximum value of length(word[i]) * length(word[j]) 
where the two words do not share common letters. 
You may assume that each word will contain only lower case letters. 
If no such two words exist, return 0.

Example 1:
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:
Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words. */

public class Solution 
{
    // 很巧妙的方法：用位运算，26位的二进制数，从右到左分别代表a-z，1表示本word含有此字母，0表示不含
    // Ref: https://discuss.leetcode.com/topic/35539/java-easy-version-to-understand
    public static int maxProduct(String[] words) 
    	{
    	if (words == null || words.length == 0)
    		return 0;
    	int len = words.length;
    	int[] value = new int[len];
    	for (int i = 0; i < len; i++) {
    		String tmp = words[i];
    		value[i] = 0;
    		for (int j = 0; j < tmp.length(); j++) {
    			value[i] |= 1 << (tmp.charAt(j) - 'a');
    		}
    	}
    	int maxProduct = 0;
    	for (int i = 0; i < len; i++)
    		for (int j = i + 1; j < len; j++) {
    			if ((value[i] & value[j]) == 0 && (words[i].length() * words[j].length() > maxProduct))
    				maxProduct = words[i].length() * words[j].length();
    		}
    	return maxProduct;
    }
}
