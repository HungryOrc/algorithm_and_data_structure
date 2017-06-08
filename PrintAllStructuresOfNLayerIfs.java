/* 比如，3 层的 if 语句一共有如下的几种可能性：

if() {
  if() {
    if() {
    }
  }
}


if() {
  if() {
  }
  if() {
  }
}


if() {
  if() {
  }
}
if() {
}


if() {
}
if() {
  if() {
  }
}


if() {
}
if() {
}
if() {
}

思路：其实和 valid 左右括号的所有组合方式，是一类题目
所以我们先把所有可行的n层的左右括号做出来，再基于它，给出一样的结构的 if 层级结构   */

public class Solution {
	 
	private List<String> generateParenthesis(int n) {
		List<String> result = new ArrayList<String>();
		dfs("", 0, 0, n, result);
		return result;
	}
	 
	private void dfs(String str, int numL, int numR, int n, List<String> result)  {    
		if (str.length() == n * 2) {
			result.add(str);
			return;
		}
	 
		if (numL < n)
			dfs(str+"(", numL + 1, numR, n, result);
		if (numR < numL)
			dfs(str+")", numL, numR + 1, n, result);
	}
	 
	public void printAllIfs(int n) {
	 
		List<String> allParenthesis = generateParenthesis(n);
		 
		for (String s : allParenthesis) {
			char[] cArray = s.toCharArray();
			int curLayer = 0;
			
			for (char c : cArray) {
				if (c == '(') {
					for (int i = 1; i <= curLayer; i++) {
						System.out.print("  "); // 2 spaces
					}
					System.out.print("if() {");
					System.out.println();
		 
					curLayer ++;
				}		
				else { // c == ')'
					curLayer --;
					
					for (int i = 1; i <= curLayer; i++) {
						System.out.print("  "); // 2 spaces
					}
					System.out.print("}");
					System.out.println();
				}	
			}
	 
			System.out.println();
			System.out.println();
		}
	}
}  
