/* Given N pairs of parentheses “()”, return a list with all the valid permutations.
Assumptions: N >= 0

Examples:
N = 1, all valid permutations are ["()"]
N = 3, all valid permutations are ["((()))", "(()())", "(())()", "()(())", "()()()"]
N = 0, all valid permutations are [""] 
*/

public class Solution {
  
  // n means there will be n left parenthese, and n right parentheses
  public List<String> validParentheses(int n) {
    List<String> result = new ArrayList<>();
    if (n < 0) {
      return result;
    }
    
    int numL = 0; // number of left parentheses
    int numR = 0; // number of right parentheses
    
    StringBuilder sb = new StringBuilder();
    dfs(sb, numL, numR, n, result);
    return result;
  }
  
  private void dfs(StringBuilder sb, int numL, int numR, int n, List<String> result) {
    if (numL == n && numR == n) {
      result.add(sb.toString());
      return;
    }
    
    if (numL < n) {
      sb.append("(");
      dfs(sb, numL + 1, numR, n, result);
      sb.deleteCharAt(sb.length() - 1);
    }
    
    if (numL > numR) {
      sb.append(")");
      dfs(sb, numL, numR + 1, n, result);
      sb.deleteCharAt(sb.length() - 1);
    }
  }
  
}
