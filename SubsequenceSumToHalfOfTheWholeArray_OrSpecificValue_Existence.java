/* 给一个数组，里面的数字可正可负，可重复。问能否把这个数组里的数分成两个subsequence，即每一部分里的数都不用连续，
要满足这两个subsequence里面的数的和相等。如果能做到，则返回true，否则返回false。
提示：这个和不很大。（DP或者bucket类做法的hint）

举例：给数组 {5, 10, 5, 1, 1}，返回 true。因为 1 + 10 = 5 + 5 + 1.

换一个角度阐述这一题，就是：
能否找到一个subsequence，它里面的数的和是整个数组的和的一半。
这一题的进一步的变种是，能否找到一个subsequence，它里面的数的和等于某个给定的值。  */


/* 思路：二维 DP
boolean[][] dp = new boolean[n][sum / 2 + 1]，其中n是给定的数组里的元素的个数，sum是整个数组里所有元素之和
boolean[i][j] 的意思是，使用从index=0开始到index=i为止的这些数，能否得到总和恰好等于j  */

public class Solution {

	public boolean existHalfSum(int[] array) {
		if (array == null || array.length == 0) {
			return false;
		}
		
		int sum = 0;
		for (int num : array) {
			sum += num;
		}
		
		if (sum % 2 == 1) {
			return false;
		}
		
		int n = array.length;
		// range of sum: [0, sum/2]
		boolean dp[][] = new boolean[n][sum / 2 + 1];
		
		for (int i = 0; i < n; i++) {
			dp[i][0] = true;
		}
		
		if (array[0] <= sum / 2) {
			dp[0][array[0]] = true;
		}
		
		for (int i = 1; i < n; i++) {
			for (int j = 1; j <= sum / 2; j++) {
				if (array[i] <= j) {
					dp[i][j] = dp[i - 1][j] || dp[i - 1][j - array[i]];
				} else {
					dp[i][j] = dp[i - 1][j];
				}
				
				if (dp[i][sum / 2] == true) {
					return true;
				}
			}
		}
		return false;
	}
}
