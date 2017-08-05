/* 在一个Matrix里面找一个单词。找的过程可以向上下左右四个直的方向走，不可以走斜向。matrix里的每个cell最多只能被用一次。返回能否找到的boolean。

比如：在下面的矩阵里找BACK的话，返回true：
A B D E
C A R R
T C K L      */


// 思路：DFS
public class Solution {

	public boolean searchWordInMatrix(char[][] matrix, String target) {
		int n = matrix.length;
		int m = matrix[0].length;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {

				if (dfs(matrix, target, 0, i, j, new boolean[n][m])) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean dfs(char[][] matrix, String target, int index, int x, int y, boolean[][] visited) {
		if (index == target.length()) {
			return true;
		}
		
		if (matrix[x][y] != target.charAt(index)) {
			return false;
		}

		visited[x][y] = true;
		
		int n = matrix.length;
		int m = matrix[0].length;
		
		// 四个方向
		if (x + 1 < n && visited[x + 1][y] == false) {
			if (dfs(matrix, target, index + 1, x + 1, y, visited)) {
				return true;
			}
		}
		if (x - 1 >= 0 && visited[x - 1][y] == false) {
			if (dfs(matrix, target, index + 1, x - 1, y, visited)) {
				return true;
			}
		}
		if (y + 1 < m && visited[x][y + 1] == false) {
			if (dfs(matrix, target, index + 1, x, y + 1, visited)) {
				return true;
			}
		}
		if (y - 1 >= 0 && visited[x][y - 1] == false) {
			if (dfs(matrix, target, index + 1, x, y - 1, visited)) {
				return true;
			}
		}
		
		visited[x][y] = false; // 别忘了这个 ！！！ 很容易忽略 ！！！ 
		
		return false;
	}
}
