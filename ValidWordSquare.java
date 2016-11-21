/* Given a sequence of words, check whether it forms a valid word square.
A sequence of words forms a valid word square if the kth row and column read the exact same string, 
where 0 ≤ k < max(numRows, numColumns).

Note:
The number of words given is at least 1 and does not exceed 500.
Word length will be at least 1 and does not exceed 500.
Each word contains only lowercase English alphabet a-z.

Example 1:
Input:
[
  "abcd",
  "bnrt",
  "crmy",
  "dtye"
]
Output: true
Explanation:
The first row and first column both read "abcd".
The second row and second column both read "bnrt".
The third row and third column both read "crmy".
The fourth row and fourth column both read "dtye".
Therefore, it is a valid word square.

Example 2:
Input:
[
  "abcd",
  "bnrt",
  "crm",
  "dt"
]
Output: true
Explanation:
The first row and first column both read "abcd".
The second row and second column both read "bnrt".
The third row and third column both read "crm".
The fourth row and fourth column both read "dt".
Therefore, it is a valid word square.
*/

// Ref: https://discuss.leetcode.com/topic/63387/java-ac-solution-easy-to-understand/4
public class Solution {
    
    public static boolean validWordSquare(List<String> words)
    {
        if(words == null || words.size() == 0)
            return true;
        
        // 如果第一行与第一“列”的长度就不同，那就直接判为挂了
        if (words.size() != words.get(0).length())
            return false;
        
        // 注意！！用 List 构建 ArrayList 的如下语法！！！
        ArrayList<String> wordsArray = new ArrayList<>(words);
        // 这么做是因为，如果给的 words 是一个 LinkedList（也是List的一种），而不是 ArrayList，那么会很慢！！
        
        int n = wordsArray.size();
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < wordsArray.get(i).length(); j++)
            {
                
                if (j >= n || // 中间每行的String的长度都不可超过第一行的String的长度！否则就不可能角对称了！！
                    wordsArray.get(j).length() <= i || // make sure words.get(j).charAt(i) is safe
                    wordsArray.get(i).charAt(j) != wordsArray.get(j).charAt(i))
                    return false;
            }
        }
        return true;
    }
}
