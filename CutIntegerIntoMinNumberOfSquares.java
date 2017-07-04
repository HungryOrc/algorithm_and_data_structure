/* 比如：4 = 2^2 or 1^2 + 1^2 + 1^2 + 1^2，前者意思是切分为1块，后者为4块，所以答案是最小可能的切分是1块
10 = 3^2 + 1^2，答案是2 
假设：输入的数字 > 1          */

/* 思路：DP
Time: O(n^2), since we used a two-layer for-loop.
Space: O(n), we used a dp array of length n.   */
 
public class Solution {
	
	public int cut(int num) {
		int[] dp = new int[num + 1];		
 
		// round down the double value to get an int
    int sqrRoot = (int) Math.sqrt(num);
        // set the dp value of all the square numbers to be 1
        for (int i = 1; i <= sqrRoot; i++) { 
          dp[i * i] = 1;
        }

        for (int i = 2; i <= num; i++) {
            if (dp[i] == 0) { // 0 was the default value
              dp[i] = i; // at least we can cut i into all 1’s
            }			

            for (int j = 1; j <= i - 1; j++) {
                dp[i] = Math.min(dp[i], dp[j] + dp[i - j]);
            }
        }

        return dp[num];
    }
}
