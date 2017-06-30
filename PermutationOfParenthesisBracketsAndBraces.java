/* Get all valid permutations of l pairs of (), m pairs of [] and n pairs of {}.
Assumptions: l, m, n >= 0

Examples:
l = 1, m = 1, n = 0, all the valid permutations are ["()[]", "([])", "[()]", "[]()"]   */


/* 思路：
这一题的关键点：
1. 如基本的只含有小括号()的题目相同，左括号的数量必须小于等于规定的括号对数，右括号的数量必须小于等于左括号的数量
2. 这一题的特点在于，有3种不同的括号，即大中小括号。那么比如 ([)] 这样的非法嵌套就是不行的。
   为了避免这种非法嵌套，我们就要把合法的嵌套都隐去，比如 ( xxxxxx ) 这样的结构，中间的 xxxxxx 如果是合法嵌套，我们就把它们都隐去，
   只留下最左边的那个 (，那么再来一个char的话，只能是 )，其它的都会构成非法。
   这样“回头看”的处理过程，自然是想到 Stack 做了 ！！！

具体做法：
Case 1: 如果来的是 ( 或 [ 或 { ，且没有超过相应的括号种类的对数限制的话，
    把这个左括号append到StringBuilder里去，并push到stack里去
Case 2: 如果来得时 ) 或 ] 或 } ，我们就看当前stack顶部的元素是否为相应种类的左括号，如果是，就ok。不必check括号对数限制的问题了。
    2.1 如果peek栈顶部的元素是相应的左括号，则pop栈顶元素，append当前右括号到StringBuilder里去
    2.2 如果不是相应的左括号，则当前右括号不可放置，否则即构成不匹配的非法嵌套。这样的话跳过当前右括号，不做任何事。考虑下一种括号的情况。 */

public class Solution {
  
  // (): parenthesis, []: bracket, {}: brace
  public List<String> validParentheses(int l, int m, int n) {
    List<String> result = new ArrayList<>();
    bfs(l, 0, 0, m, 0, 0, n, 0, 0, 
      new StringBuilder(), new Stack<Character>(), result);
    return result;
  }
  
  private void bfs(int l, int parenL, int parenR,
    int m, int bracketL, int bracketR,
    int n, int braceL, int braceR, 
    StringBuilder cur, Stack<Character> charStack, List<String> result) {
    
    if (parenR == l && bracketR == m && braceR == n) { // 结束条件
      result.add(cur.toString());
      return;
    }
    
    // 一共6种Case：
    // Case 1/6: 尝试放一个 ( 进来
    if (parenL < l) {
      cur.append('(');
      charStack.push('(');
      bfs(l, parenL + 1, parenR, m, bracketL, bracketR, n, braceL, braceR,
        cur, charStack, result);
      cur.deleteCharAt(cur.length() - 1);
      charStack.pop();
    }
    
    // Case 2/6: 尝试放一个 ) 进来
    if (charStack.size() > 0 && charStack.peek() == '(') {
      cur.append(')');
      charStack.pop();
      bfs(l, parenL, parenR + 1, m, bracketL, bracketR, n, braceL, braceR,
        cur, charStack, result);
      cur.deleteCharAt(cur.length() - 1);
      charStack.push('(');
    }
    
    // Case 3/6: 尝试放一个 [ 进来
    if (bracketL < m) {
      cur.append('[');
      charStack.push('[');
      bfs(l, parenL, parenR, m, bracketL + 1, bracketR, n, braceL, braceR,
        cur, charStack, result);
      cur.deleteCharAt(cur.length() - 1);
      charStack.pop();
    }
    
    // Case 4/6: 尝试放一个 ] 进来
    if (charStack.size() > 0 && charStack.peek() == '[') {
      cur.append(']');
      charStack.pop();
      bfs(l, parenL, parenR, m, bracketL, bracketR + 1, n, braceL, braceR,
        cur, charStack, result);
      cur.deleteCharAt(cur.length() - 1);
      charStack.push('[');
    }
    
    // Case 5/6: 尝试放一个 { 进来
    if (braceL < n) {
      cur.append('{');
      charStack.push('{');
      bfs(l, parenL, parenR, m, bracketL, bracketR, n, braceL + 1, braceR,
        cur, charStack, result);
      cur.deleteCharAt(cur.length() - 1);
      charStack.pop();
    }
    
    // Case 6/6: 尝试放一个 } 进来
    if (charStack.size() > 0 && charStack.peek() == '{') {
      cur.append('}');
      charStack.pop();
      bfs(l, parenL, parenR, m, bracketL, bracketR, n, braceL, braceR + 1,
        cur, charStack, result);
      cur.deleteCharAt(cur.length() - 1);
      charStack.push('{');
    }
  }
}
