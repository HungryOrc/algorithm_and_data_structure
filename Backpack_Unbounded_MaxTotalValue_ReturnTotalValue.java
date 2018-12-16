
-----------
DFS做法：我自己补充完整，实测看看！

public void dfs(int[] A, int[] V, int id, int cur_size, int cur_V) {
	if (cur_size < 0) return;
	if (cur_size == 0 || id == A.length) {
		g_max = Math.max(g_max, cur_V);
		return;
	}
	
	g_max = Math.max(g_max, cur_V);

	// put this item into backpack
	dfs(A, V, id + 1, cur_size - A[id], cur_V + V[id]);  // -> dp[i - 1][j - A[i]] + V[i]

	// don’t put it
	dfs(A, V, id + 1, cur_size, cur_V);		     // -> dp[i - 1][j]
}
