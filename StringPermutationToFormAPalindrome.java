/* Given a string, determine if a permutation of the string could form a palindrome.
For example: "code" -> False, "aab" -> True, "carerac" -> True.

Hints:
1. Consider the palindromes of odd vs even length. What difference do you notice?
2. Count the frequency of each character.
3. If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?
*/

public class Solution {

    // 最普通的方法，存 HashMap
    public boolean canPermutePalindrome(String s) {
        
        char[] charArray = s.toCharArray();
        
        // ！注意！HashMap 尖括号里都要用 Wrapper Class！！
        HashMap<Character, Integer> charCounts_HashMap = new HashMap<Character, Integer>();
        
        for (char curChar : charArray)
        {
            if (charCounts_HashMap.containsKey(curChar))
                charCounts_HashMap.put(curChar, charCounts_HashMap.get(curChar)+1);
            else
                charCounts_HashMap.put(curChar, 1);
        }
        
        // public Collection<V> values()
        // HashMap.values() returns a "Collection view" of the values contained in this map
        // ！注意！尖括号里都要用 Wrapper Class！！
        // Collection<Integer> charOccurTimes = charCounts_HashMap.values();
        
        // ！将 Collection 转化为 Array！！
        // ！注意！和 Collection<E> 相关的东西都要用 Wrapper Class！！比如这里的 Integer！用 int 会报错！
        Integer[] charCount_Array = 
            charCounts_HashMap.values().toArray(
                new Integer[charCounts_HashMap.values().size()]);
                // ！注意这里的特殊语法！要在 toArray 后面的括号里先 new 一个足够长的新Array！！
        
        int numOfOddOccurChars = 0;
        for (int occurTimes : charCount_Array)
        {
            if (occurTimes % 2 == 1)
                numOfOddOccurChars ++;
        }
        
        // 最终进行判断
        // 如果只有一个odd occurance char，也true；因为此时总的字符串的长度也一定是odd
        if (numOfOddOccurChars == 0 || numOfOddOccurChars == 1)
            return true;
        else
            return false;
    }
    
    
    // 更巧的办法：用 HashSet 来记录出现奇数次的char，不计具体次数，也不计出现偶数次的char
    // Reference: https://discuss.leetcode.com/topic/22057/java-solution-w-set-one-pass-without-counters
    /* The idea is to iterate over string, adding current character to set if set doesn't contain that character, 
     or removing current character from set if set contains it.
     When the iteration is finished, just return set.size()==0 || set.size()==1.
     set.size()==0 corresponds to the situation when there are even number of any character in the string, and
     set.size()==1 corresponsds to the fact that there are even number of any character except one.
    */
    public boolean canPermutePalindrome(String s) {
        
        char[] charArray = s.toCharArray();
        
        // ！注意！HashSet 尖括号里都要用 Wrapper Class！！
        HashSet<Character> charCounts_HashSet = new HashSet<Character>();
        
        for (char curChar : charArray)
        {
            if (!charCounts_HashSet.contains(curChar))
                charCounts_HashSet.add(curChar);
            else
                charCounts_HashSet.remove(curChar);
        }
        
        // 最终进行判断
        // 如果只有一个odd occurance char，也true；因为此时总的字符串的长度也一定是odd
        if (charCounts_HashSet.size() == 0 || charCounts_HashSet.size() == 1)
            return true;
        else
            return false;
    }
    
}
