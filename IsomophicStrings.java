/* Isomophic: 同构的
Given two strings s and t, determine if they are isomorphic.
Two strings are isomorphic if the characters in s can be replaced to get t.
All occurrences of a character must be replaced with another character while preserving the order of characters. 
No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.
Given "foo", "bar", return false.
Given "paper", "title", return true.

Note: You may assume both s and t have the same length. */

public class Solution {
    
    // 极为巧妙的方法！！！我没能想到
    // Ref: https://discuss.leetcode.com/topic/12981/my-6-lines-solution/4
    public boolean isIsomorphic(String s, String t) {
            
        // 精华一！！！
        // 用一个char所对应的int，作为一个index，以此在一个整形数组里记录这个char上次在string里出现的位置
        //
        // 标准ASCII码有128个，拓展后的ASCII码有256个，但后128个都比较生僻，所以一般前128位就够用
        int[] lastShowUpIndexOfTnisChar_InS = new int[128];
        int[] lastShowUpIndexOfTnisChar_InT = new int[128];
        
        for (int i = 0; i < s.length(); i++) 
        {
            // 精华二！！！
            // 比较当前同样位置的char，在各自的string里各自上一次出现时，它们的位置是否相同！！！！
            // 我们要的是位置相同，不是累计出现次数的相同。前者才叫“同构”
            // 再注意！！！
            // 这个if语句放在后面的赋值语句前面执行！如果放到赋值语句的后面，就失去作用了，因为那样就相当于永远 if(true)
            // 我们要的是判断前一个的位置二者之间是否相等，不是当前的位置
            // 而且，可见，后面的两个赋值语句是不需要对最后的位置的char做操作的，因为如果2个string里分别在最后位置的2个char，
            // 它们分别在各自的string里上一次出现的位置相同，然后当前的位置当然也相同（都在array末尾），所以就已经done了
            if (lastShowUpIndexOfTnisChar_InS[s.charAt(i)] != lastShowUpIndexOfTnisChar_InT[t.charAt(i)])
                return false;
                
            lastShowUpIndexOfTnisChar_InS[s.charAt(i)] = i+1;
            lastShowUpIndexOfTnisChar_InT[t.charAt(i)] = i+1;
        }
        return true;
    }
    
}
