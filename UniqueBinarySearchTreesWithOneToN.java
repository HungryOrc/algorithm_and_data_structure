/* 用正整数 1 到 n 能组成多少种不同的 BST，返回种类数

思路：DP。很巧妙 ！！！
dp[i] 表示 n=i 时一共有多少种不同的 BST

n = 0 时，相当于没有数字
但注意 ！ 还是有一种BST ！ 即 null 树 ！！！ 这在后面的 DP 过程里也是有用的 ！！！ 即表示叶子节点下的null节点 ！！！ dp[0] = 1

n = 1 时，即用1来组成BST
自然有且仅有一种方式，即1为root，然后左右子node都是null，或者说左右子node都是 n=0 的情况 ！！！ dp[1] = 1

n = 2 时，即用1和2来组成BST。这样有两种情况，1为root或者2为root：
           1                                  2
          / \                                / \
  放0个数字   放1个数字(即2)      放1个数字(即1)   放0个数字
   即dp[0]     即dp[1]              即dp[1]       即dp[0]
   
dp[2] = dp[0]*dp[1] + dp[1]*dp[0] = 2

注意！！！ 精妙之处在于，虽然 dp[1] 原本是表示 n = 1 时有多少种 BST，但是可以想到，
它同时能表示有且只有一个node时，有多少种 BST ！！！ 不管这个node本身的值是 1 还是 2 还是 3 还是...... 任何值 ！！！

n = 3 时，即用 1,2和3 来组成BST。这样有3种情况，即1,2和3分别作为root：
           1                                  2                                  3                        
          / \                                / \                                / \
  放0个数字  放2个数字(即2,3)     放1个数字(即1)  放1个数字(即3)     放2个数字(即1,2)  放0个数字
   即dp[0]     即dp[2]              即dp[1]       即dp[1]              即dp[2]      即dp[0]
   
dp[3] = dp[0]*dp[2] + dp[1]*dp[1] + dp[2]*dp[0] = 5

n = 4 时，1到4分别为root，4种情况，
dp[4] = dp[0]*dp[3] + dp[1]*dp[2] + dp[2]*dp[1] + dp[3]*dp[0] = 14

n = 5 时，1到5分别为root，5种情况，
dp[5] = dp[0]*dp[4] + dp[1]*dp[3] + dp[2]*dp[2] + dp[3]*dp[1] + dp[4]*dp[0] = 42. 以此类推   */

public class Solution {

	public int uniqueBST(int n){
		if (n < 0) {
			return 0;
		} else if (n <= 1) {
			return 1;
		}
		
		int[] dp = new int[n + 1];
		dp[0] = 1;
		dp[1] = 1;
		
		for (int i = 2; i <= n; i++) {
			
			for (int root = 1; root <= i; root++) {
				
				int numOfBSTsWithThisRoot = dp[root - 1] * dp[i - root];
				
				dp[i] += numOfBSTsWithThisRoot;
			}
		}
		return dp[n];
	}
}
