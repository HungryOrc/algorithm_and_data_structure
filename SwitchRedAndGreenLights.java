/* 给一个String，只由R和G组成，比如“RRGGGGRGRGGR”，其中 G代表绿灯，R代表红灯。
要在这个String里找到一个substring，将里面的R变成G，G变成R，使得变换后 G-R 最大。求这个substring

思路：
动态规划。不过此题的方法的本质其实只是记录了中间结果而已，即所谓的记忆化。时间 O(n^2)。暴力姐的时间是 O(n^3)。

变换后G-R最大，即要求变换前R-G最大。设 input string的长度为 n，
我们设dp数组为：dp[n][n]，其中 dp[i][j] 的意思是从input string里 index=i 的char开始，到index=j 的char结束，两头都包含，
这段substring上 R的总个数 - G的总个数 的值。

所以，对于长度为1的各个substring：dp[i][i] = 1，if(input.charAt(i)=='R')；dp[i][i] = -1, if(input.charAt(i)=='G')。
对于长度 >1 的substring，dp[i][j] = dp[i][j - 1] + dp[j][j]   */

public class Solution {

	public String redAndGreen(String input) {
		int n = input.length();
		int start = 0, end = 0;
		int maxDiff = -1;
		
		int[][] dp = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			if (input.charAt(i) == 'R') {
				dp[i][i] = 1;
				
				if (maxDiff < 1) {
					maxDiff = 1;
					start = i;
					end = i;
				}
			} else {
				dp[i][i] = -1;
			}
		}
		
    // 注意 ！！！ 诀窍在此 ！！！
    // 先loop从小到大的substring长度，再loop从左到右的起始点 ！！！ 这样做就不用记录哪些[i][j]是算过的，哪些是没算过的 ！！！
		for (int sectionLen = 2; sectionLen <= n; sectionLen ++) {
			for (int beginIndex = 0; beginIndex + sectionLen - 1 < n; beginIndex ++) {
				
				int lastIndex = beginIndex + sectionLen - 1;
				dp[beginIndex][lastIndex] = dp[beginIndex][lastIndex - 1] + dp[lastIndex][lastIndex];
				
				if (dp[beginIndex][lastIndex] > maxDiff) {
					maxDiff = dp[beginIndex][lastIndex];
					start = beginIndex;
					end = lastIndex;
				}
			}
		}
		return input.substring(start, end + 1);
	}
}
