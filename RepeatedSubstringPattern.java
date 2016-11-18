/* Given a non-empty string check if it can be constructed by taking a substring of it and 
appending multiple copies of the substring together. 
You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

Example 1:
Input: "abab"
Output: True
Explanation: It's the substring "ab" twice.

Example 2:
Input: "aba"
Output: False

Example 3:
Input: "abcabcabcabc"
Output: True
Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.) */

public class Solution {
    
    // 最直觉的解法，逐个比较char。不过设置了一些标准，以减少比较次数
    // Time: O(n^2)
    public boolean repeatedSubstringPattern(String str) {
        
        int strLength = str.length();
        char[] charArray = str.toCharArray();
        
        for (int i = 0; i < strLength / 2; i++)
        {
            // 注意这3条判断标准！
            if (strLength % (i+1) == 0 && 
                charArray[0] == charArray[i+1] && 
                charArray[i] == charArray[strLength-1])
            {
                boolean repeated = true;
                int j = 0;
                for (j = 0; j <= i; j++)
                {
                    for (int k = 1; k <= strLength/(i+1) - 1; k++)
                    {
                        if (charArray[j] != charArray[j+k*(i+1)])
                        {
                            repeated = false;
                            break;
                        }
                    }
                    if (repeated == false)
                        break;
                }
                if (repeated == true)
                    return true;
            }
        }
        return false;
    }
    
    
    // 思路与上同，改为用内置的 substring 函数来做。代码上简明点。时间差不多
    // Ref: 
    public boolean repeatedSubstringPattern(String str)
    {
        int len = str.length();
    	  for(int i=len/2 ; i>=1 ; i--)
        {
            if(len % i == 0)
            {
                int m = len/i;
                String subS = str.substring(0,i); // 0 is inclusive, i is EXCLUSIVE!
                int j;
                for(j=1; j<m; j++)
                {
                    // 千万要记得，对于String，要用equals来比较！不要用==
                    if(!subS.equals(str.substring(j*i, i+j*i)))
                        break;
                }
                if(j==m)
                    return true;
            }
        }
        return false;
    }
    
    
    // KMP算法。比较深奥。算法思路与Table生成方式看懂了，代码还没细看
    // 关于KMP算法的详细说明：https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm
    // 以下代码的出处：https://discuss.leetcode.com/topic/67590/java-o-n
    public boolean repeatedSubstringPattern(String str) {
        //This is the kmp issue
        int[] prefix = kmp(str);
        int len = prefix[str.length()-1];
        int n = str.length();
        return (len > 0 && n%(n-len) == 0);
    }
    private int[] kmp(String s){
        int len = s.length();
        int[] res = new int[len];
        char[] ch = s.toCharArray();
        int i = 0, j = 1;
        res[0] = 0;
        while(i < ch.length && j < ch.length){
            if(ch[j] == ch[i]){
                res[j] = i+1;
                i++;
                j++;
            }else{
                if(i == 0){
                    res[j] = 0;
                    j++;
                }else{
                    i = res[i-1];
                }
            }
        }
        return res;
    }
    
}
