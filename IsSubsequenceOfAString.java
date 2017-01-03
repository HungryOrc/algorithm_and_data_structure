/* Given a string s and a string t, check if s is subsequence of t.
You may assume that there is only lower case English letters in both s and t. 
t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).
A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) 
of the characters without disturbing the relative positions of the remaining characters. 
(ie, "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
s = "abc", t = "ahbgdc". Return true.
Example 2:
s = "axc", t = "ahbgdc". Return false.

Follow up:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, 
and you want to check one by one to see if T has its subsequence. 
In this scenario, how would you change your code?  */

public class Solution 
{
    // 我的朴素解法。两个index，顺着t里一个一个看，逐个找s里的字母
    public boolean isSubsequence(String s, String t) 
    {
        if (s == null || s.length()==0)
            return true;
        
        int indexS = 0;
        int indexT = 0;
        
        while (indexT < t.length())
        {
            if (t.charAt(indexT) == s.charAt(indexS))
            {
                indexS ++;
                if (indexS == s.length())
                    return true;
            }
            indexT ++;
        }
        return false;
    }
    
    
    // 改进的方法。用于follow up question里面对巨量的s的情况
    // Ref: https://leetcode.com/problems/is-subsequence/
    // 先把t扫一遍，找到所有字母在t里曾经出现过的index，把每个字母曾经出现过的index各自串成一个ArrayList
    // 然后把这些List合成一个size为26的Array
    // 然后看s里的逐个char，从左到右。比如s的第一个字母是a，第二个是k，那么我们在上述a的ArrayList里找到
    // a出现的第一个index，然后在k的ArrayList里找k出现过的index只要其中有一个大于a的index，就ok
    // 这个过程不断继续，如果在达到s的最后一个字母前，这个搜索过程无法持续了，就表明不是subsequence
    public boolean isSubsequence(String s, String t) 
    {
        if (s == null || s.length()==0)
            return true;
        
        // accommodate the occurrence indice of char 'a' to 'z' in an Array of ArrayLists
        ArrayList<Integer>[] occurrIndice = new ArrayList[26];
        for (int i = 0; i < 26; i++)
            occurrIndice[i] = new ArrayList<Integer>();
        
        for (int i = 0; i < t.length(); i++)
        {
            char curChar = t.charAt(i);
            occurrIndice[curChar - 'a'].add(i);
        }
        
        int lastIndex = -1;
        for (int j = 0; j < s.length(); j++)
        {
            char curChar = s.charAt(j);
            ArrayList<Integer> curAL = occurrIndice[curChar - 'a'];
            int k = 0;
            for (; k < curAL.size(); k++)
            {
                if (curAL.get(k) > lastIndex)
                {
                    lastIndex = curAL.get(k);
                    break;
                }
            }
            if (k == curAL.size())
                return false;
        }
        return true;
    }
    
    
    // 对上面方法的一点改进：在最后，用二分法来查找一个ArrayList里所存储的哪一个index是我们要的
    public boolean isSubsequence(String s, String t) 
    {
        if (s == null || s.length()==0)
            return true;
        
        // accommodate the occurrence indice of char 'a' to 'z' in an Array of ArrayLists
        ArrayList<Integer>[] occurrIndice = new ArrayList[26];
        for (int i = 0; i < 26; i++)
            occurrIndice[i] = new ArrayList<Integer>();
        
        for (int i = 0; i < t.length(); i++)
        {
            char curChar = t.charAt(i);
            occurrIndice[curChar - 'a'].add(i);
        }
        
        int lastIndex = -1;
        boolean[] theFirstRecordInTheArrayListIsUsed = new boolean[26];
        for (int j = 0; j < s.length(); j++)
        {
            char curChar = s.charAt(j);
            ArrayList<Integer> curAL = occurrIndice[curChar - 'a'];
            
            if (curAL.size() == 0)
                return false;
            else if (curAL.get(0) > lastIndex && 
                     theFirstRecordInTheArrayListIsUsed[curChar - 'a'] == false)
            {
                lastIndex = curAL.get(0);
                theFirstRecordInTheArrayListIsUsed[curChar - 'a'] = true;
                continue;
            }
            else 
            {
                lastIndex = binarySearchNextMatchIndex(curAL, lastIndex, 1, curAL.size()-1);
                if (lastIndex == -1)
                    return false;
            }
        }
        return true;
    }
    private int binarySearchNextMatchIndex(ArrayList<Integer> al, int targetIndex, int start, int end)
    {
        if (start > end)
            return -1;
            
        int mid = (start + end) / 2;

        if (al.get(mid) > targetIndex && al.get(mid-1) <= targetIndex)
            return al.get(mid);
        else if (start == end)
            return -1;
        else if (al.get(mid) <= targetIndex)
            return binarySearchNextMatchIndex(al, targetIndex, mid+1, end);
        else // al.get(mid) > targetIndex
            return binarySearchNextMatchIndex(al, targetIndex, start, mid-1);
    }
    
}
