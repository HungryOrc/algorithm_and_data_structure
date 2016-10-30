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
        int sLength = charArray.length;
        
        // ！注意！尖括号里都要用 Wrapper Class！！
        HashMap<Character, Integer> charCounts_HashMap = new HashMap<Character, Integer>();
        
        for (int i = 0; i < sLength; i++)
        {
            char curChar = charArray[i];
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
        
        int numOfEvenOccurChars = 0, numOfOddOccurChars = 0;
        for (int occurTimes : charCount_Array)
        {
            if (occurTimes % 2 == 0)
                numOfEvenOccurChars ++;
            else
                numOfOddOccurChars ++;
        }
        
        // 最终进行判断
        if (numOfOddOccurChars == 0)
            return true;
        else if (numOfOddOccurChars == 1 && sLength % 2 == 1)
            return true;
        else
            return false;
    }
}
