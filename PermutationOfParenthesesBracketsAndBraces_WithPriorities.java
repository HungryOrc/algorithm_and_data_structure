/* 这一题的思路解释，请看 Permutation Of Parentheses Brackets And Braces 那一题

这一题是在那一题的基础上的进一步要求：( [ { 分别有优先级，越来越高。
{ 的里面可以有 [ 和 (，[ 的里面可以有 (。反之不行。

比如，用1个小括号，1个中括号，1个大括号，一共可以组成以下的valid permutations:
()[]{}
(){[]}
(){}[]
[()]{}
[](){}
[]{()}
[]{}()
{()[]}
{()}[]
{[()]}
{[]()}
{[]}()
{}()[]
{}[()]
{}[]()

思路：
总体思路和基本的三种括号做排列的思路一样，进化之处在于：
每次要往 string builder 里append char之前，要看 stack 顶部的char的优先级，必须高于当前要加的char的优先级才行 ！！！ */

public class Solution {

	public List<String> allValidCombinations(int parenthesis, int bracket, int bracelet) {
		List<String> result = new ArrayList<>();
		
		HashMap<Character, Integer> priorities = new HashMap<>();
		priorities.put('(', 1);
		priorities.put(')', 1);
		priorities.put('[', 2);
		priorities.put(']', 2);
		priorities.put('{', 3);
		priorities.put('}', 3);
		
		dfs(parenthesis, 0, bracket, 0, bracelet, 0, 
		    priorities, new Stack<Character>(), new StringBuilder(), result);
		
		return result;
	}
	
	private void dfs(int parenthesis, int n1, int bracket, int n2, int bracelet, int n3,
		HashMap<Character, Integer> priorities, Stack<Character> cStack, StringBuilder cur, List<String> result) {
		
		// 别忘了这里要乘以2 ！ 因为是一对一对的 ！
		if (cur.length() == (parenthesis + bracket + bracelet) * 2) {
			result.add(cur.toString());
			return;
		}
		
		// 加三种左括号
		if (n1 < parenthesis && (cStack.isEmpty() || priorities.get(cStack.peek()) > 1)) {
			cStack.push('(');
			cur.append('(');
			dfs(parenthesis, n1 + 1, bracket, n2, bracelet, n3, priorities, cStack, cur, result);
			cStack.pop();
			cur.deleteCharAt(cur.length() - 1);
		}
		if (n2 < bracket && (cStack.isEmpty() || priorities.get(cStack.peek()) > 2)) {
			cStack.push('[');
			cur.append('[');
			dfs(parenthesis, n1, bracket, n2 + 1, bracelet, n3, priorities, cStack, cur, result);
			cStack.pop();
			cur.deleteCharAt(cur.length() - 1);
		}
		if (n3 < bracelet && cStack.isEmpty()) {
			cStack.push('{');
			cur.append('{');
			dfs(parenthesis, n1, bracket, n2, bracelet, n3 + 1, priorities, cStack, cur, result);
			cStack.pop();
			cur.deleteCharAt(cur.length() - 1);
		}
		
		// 加三种右括号
		if (!cStack.isEmpty() && cStack.peek() == '(') {
			cStack.pop();
			cur.append(')');
			dfs(parenthesis, n1, bracket, n2, bracelet, n3, priorities, cStack, cur, result);
			cStack.push('(');
			cur.deleteCharAt(cur.length() - 1);
		} else if (!cStack.isEmpty() && cStack.peek() == '[') {
			cStack.pop();
			cur.append(']');
			dfs(parenthesis, n1, bracket, n2, bracelet, n3, priorities, cStack, cur, result);
			cStack.push('[');
			cur.deleteCharAt(cur.length() - 1);
		} else if (!cStack.isEmpty() && cStack.peek() == '{') {
			cStack.pop();
			cur.append('}');
			dfs(parenthesis, n1, bracket, n2, bracelet, n3, priorities, cStack, cur, result);
			cStack.push('{');
			cur.deleteCharAt(cur.length() - 1);
		}		
	}
}
