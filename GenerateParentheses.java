/* Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]  */

public class Solution 
{
    // 笨办法。把所有的组合都列出来。然后一一筛选哪些合理
    public List<String> generateParenthesis(int n) 
    {
        ArrayList<String> permutations = new ArrayList<>();
        findAllPermutation(permutations, "", n, n*2);
        removeInvalidStrings(permutations, n);
        
        return permutations;
    }
    private static void findAllPermutation(
        ArrayList<String> permutations, String curString, int remainLeftParen, int remainLength)
    {
        if (remainLength == 0)
        {
            if (remainLeftParen == 0)
                permutations.add(curString);
            return;
        }
        
        findAllPermutation(permutations, curString+"(", remainLeftParen-1, remainLength-1);
        findAllPermutation(permutations, curString+")", remainLeftParen, remainLength-1);
    }
    private static void removeInvalidStrings(ArrayList<String> permutations, int n)
    {
        for (int i = 0; i < permutations.size(); i++)
        {
            String s = permutations.get(i);
            Stack<Character> cStack = new Stack<>();
            int completePairs = 0;
            
            for (char c : s.toCharArray())
            {
                if (c == '(')
                    cStack.push(c);
                else // if (c == ')')
                {
                    if (cStack.isEmpty())
                        break; // invalid
                    else
                    {
                        cStack.pop();
                        completePairs ++;
                    }
                }
            }
            if (completePairs != n) // invalid
            {
                permutations.remove(i);
                i--;
            }
        }
    }
    
    
    // 很巧妙的方法！！把不合理的直接去掉了
    // https://discuss.leetcode.com/topic/8724/easy-to-understand-java-backtracking-solution
    public List<String> generateParenthesis(int n) 
    {
        List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, n);
        return list;
    }
    public void backtrack(List<String> list, String str, int open, int close, int max)
    {    
        if(str.length() == max*2){
            list.add(str);
            return;
        }
        
        // 精华在此 ！！！哈哈哈哈 ！！！
        if(open < max)
            backtrack(list, str+"(", open+1, close, max);
        if(close < open)
            backtrack(list, str+")", open, close+1, max);
    }
    
}
