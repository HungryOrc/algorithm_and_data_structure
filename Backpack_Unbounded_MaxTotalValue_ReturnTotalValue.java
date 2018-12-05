/* 这一类题叫“Unbounded”的意思是，每个item可以被取用 0次 到 无限次。
每个item有不止一个属性，比如既有size又有value。
背包有最大的容量（对于size或weight）。求怎么填能获得最大的总value。背包不必被填满。 */

/* 思路：二维 DP
int dp[i][s] 的意思是：使用数组里index为0到i的items中的任意几个（可以一个也不选，也可以从中任选几个），
正好组成总 size = s 的前提下，最大可能的总 value 是多少。
所以 int dp[][] = new int[number of items][capacity of the backpack + 1]。

Base Cases:
Base Case 1: 对于数组里的第一个item，
    if(sizes[0] * i <= capacity)，dp[0][sizes[0] * i] = values[0] * i
    其中 i = 1, 2, 3...
    其他的 dp[0][s != sizes[0]] 都 = 0，因为不可能实现这些size，所以自然所能带来的总value也都是 0
Base Case 2: size和为0的情况，对于任何多个items，都是可以的！！ 即什么都不放 ！！ 所以都置为 0，即不放任何item，自然value和为 0
    for (int i = 0; i < n; i++)，dp[i][0] = 0;
    
Induction Rule:
dp[i][s] = max(dp[i - 1][s], dp[i][s - sizes[i]] + values[i])
其中 dp[i - 1][s] 的意思是：i之前的那些items已经可以组成总和正好为 s 的组合了，item i 不参与的话，自然也还是和为s的组合；
dp[i][s - sizes[i]] 的意思是：已经用过了 0次或若干次的 item i，组成了总和为 s - sizes[i] 的组合，
那么 item i (再一次)参与进来后，自然正好就是和为s，新的总value就是之前的总value + values[i]。

Return:
注意，这里不是返回 dp[n - 1][capacity]
因为dp矩阵的第二维的意义是“正好”总size为某某值 ！！ 而获得总value最大时的总size未必是capacity ！！！
    for (int sum = 1; sum <= capacity; sum++) {
        maxTotalValue = Math.max(maxTotalValue, dp[n - 1][sum]);
    }
    return maxTotalValue;
        
Time: O(n * capacity), 其中n是items的个数
Space: O(n * capacity)。可以优化为 O(capacity)，因为dp矩阵里，其实每一次loop只用一行就够了   */


public class Solution {
    public int backPackIII(int[] sizes, int[] values, int capacity) {
        if (values == null || values.length == 0 || sizes == null || sizes.length == 0 || capacity <= 0) {
            return 0;        
        }
	    
	    // 这里还应该考虑两个数组长度不同的情况！！！！！
        
        int n = sizes.length;
        int[][] dp = new int[n][capacity + 1];
        
        // base case 1
        for (int i = 1; i <= capacity / sizes[0]; i++) {
            dp[0][sizes[0] * i] = values[0] * i;
        }
        
        // base case 2 ---- 这个其实可以不写，因为默认都是 0. 写了只是更能解释清楚思路
        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }
        
        for (int i = 1; i < n; i++) {
            for (int size = 1; size <= capacity; size++) {
                
                int curSize = sizes[i];
                int curValue = values[i];
                
                if (size - curSize >= 0) {
                    dp[i][size] = Math.max(dp[i - 1][size], dp[i - 1][size - curSize] + curValue);
                    dp[i][size] = Math.max(dp[i][size], dp[i][size - curSize] + curValue);
                } else {
                    dp[i][size] = dp[i - 1][size];
                }
            }
        }
        
        int maxTotalValue = 0;
        for (int size = 1; size <= capacity; size++) {
            maxTotalValue = Math.max(maxTotalValue, dp[n - 1][size]);
        }
        return maxTotalValue;
    }
}


改进方法：

dp[i][j] = Max(dp[i-1][j], dp[i][j-A[i]] + V[i])
    
一行以内自己填就行，不用k个来loop，一行以内右边的基于左边的来填
然后对于总的矩阵来说，从上到下，从左到右填

上面那个地推公式的空间可以进一步优化：
用滚动数组，上一行的信息留存在dp[j]的前一次occurence里面了！！！
dp[j] = Max(dp[j], dp[j - A[i]] + V[i])
    
public int backpack3(int m, int[] A, int[] V) {
    int[] dp = new int[m + 1];
    int n = A.length;
    
    for (int i = 0; i < n; i++) {
        for (int j = A[i]; j <= m; j++) {
            dp[j] = Math.max(dp[j], dp[j - A[i]] + V[i]);
        }
    }
    return dp[m];
}

Time: O(nm)
Space: O(m), dp array

-------
这题必须从左往右填，不能从右往左填



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
