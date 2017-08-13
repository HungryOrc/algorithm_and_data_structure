/* 'A'到'Z'这26个大写字母分别代表 1-26 这26个数字。注意没有字母代表0. 给一个char数组，求把它转化成A到Z的字母，共有多少种转化方法。
比如：
12：可以转化为 A,B（1,2），L（12），一共2种方法
123：可以转化为 A,B,C（1,2,3），L,C（12,3），A,W（1,23），一共3种方法
2119：可以转化为 B,A,A,I（2,1,1,9），U,A,I（21,1,9），U,S（21,19），B,K,I（2,11,9），B,A,S（2,1,19），一共5种方法 */


/* 思路：DP
int[] dp = new int[charArray.length]，dp[i] 的意思是 要表示 从index=0到index=i的所有的chars，一共有多少种表示方法。
初始：dp[0] = 1。
递推：dp[i] = dp[i - 1] + dp[i - 2]
加号左边的一项 dp[i - 1] 是一定存在的，这一项意味着当前的最后一位即index=i处的数字被一个字母代表
加号右边的一项 dp[i - 2] 未必存在，这一项意味着当前的最后两位即index=i-1和i处的数字，作为一个两位数，被一个字母代表，那么可以看出，
这一项存在的条件是，index=i-1和i这两个数字综合起来的两位数必须 <= 26，才可能被一个字母来表示 */

public class Solution {

	public int numbersToLetters(char[] chars){
		if (chars == null || chars.length == 0) {
			return 0;
		}
		// 如果有任何char不是数字，或者是'0'，都违规，立刻 out
		for (char num : chars) {
			if (num - '0' <= 0 || num - '0' > 9) {
				return 0;
			}
		}
		
		int n = chars.length;
		int[] dp = new int[n];
		dp[0] = 1;
		
		for (int i = 1; i < n; i++) {
			// Case 1: 如果当前的最后一位即index=i处的数字被一个字母代表
			dp[i] = dp[i - 1];
			
			// Case 2: 如果当前的最后两位即index=i-1和i处的数字，作为一个两位数，被一个字母代表
			int lastDigit = chars[i] - '0';
			int secondLastDigit = chars[i - 1] - '0';
			int lastTwoDigits = secondLastDigit * 10 + lastDigit;
			if (lastTwoDigits <= 26) {
				if (i == 1) {
					dp[1] += 1;
				} else {
					dp[i] += dp[i - 2];
				}
			}
		}
		return dp[n - 1];
	}
}
