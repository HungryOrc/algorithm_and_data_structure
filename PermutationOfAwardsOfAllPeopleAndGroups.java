/* 给总人数n，求一共有多少种获奖方式，所有人都可以单独获奖，也可以和任何个数的其他人结尾一个团体获奖。注意奖项是有前后名次的，所以是排列。
但是每个团体以内是组合，而非排列。不同人数的团体和个人之间进行排列，这个情况确实不常见，但也不是没有。

举例：

总人数 n = 2。设为 A，B 两个人。
共有 3 种获奖排列（排在前面算是一等奖，排在后面算是二等奖，比如AB意为A获得一等奖B获得二等奖，(AB)意为AB作为一个团体获得一等奖）：
AB    (AB)
BA

总人数 n = 3。设为 A，B，C 三个人。
共有 13 种获奖排列：
ABC    A(BC)    (ABC)
ACB    (BC)A
BAC    B(AC)
BCA    (AC)B
CAB    C(AB)
CBA    (AB)C

对于任意n个人的情况，我们可以用这样的角度来归类：
首先看获得一等奖的团体或者个人是谁，把获得一等奖的作为一个整体。然后后面的人或者团体作为另一方面，它们可以被任意切分（或者不切分）。
那么我们用 dp[n] 表示一共有n个人的话，一共有多少种获奖的排列，就是题意里所指的包括所有的团体和个人互相交杂的排列情况。
那么上面这个划分里，获得一等奖的团体或者个人，就要看从n个人里取出m个人一共有多少种取法，这就是一个组合 C_m^n；
一等奖以后的那些人们和团体们，一共有多少种可能性，我们用 dp[n - m] 就可知了。是不是很意外？很刺激？很惊喜？

这样，再以 n = 3 来举例：
Case 1: 获得一等奖的是3个人，那么 C_3^3 = 1，dp[3 - 3] = dp[0] = 1（dp[0]即没有人的情况，自然是1种获奖的可能），所以 1 * 1 = 1
    这里就包括了 (ABC) 这一种情况
Case 2: 获得一等奖的是2个人，那么 C_3^2 = 3, dp[3 - 2] = dp[1] = 1，所以 3 * 1 = 3
    这里就包括了 (AB)C，(AC)B，(BC)A 这三种情况
Case 3: 获得一等奖的是1个人，那么 C_3^1 = 3, dp[3 - 1] = dp[2] = 3（dp[2] = 3 是我们在前面就得到过的），所以 3 * 3 = 9
    这里就包括了以下这些情况（把一等奖用空格隔开了，更便于观看）：
    A (BC)    B (AC)    C (AB)
    A BC      B AC      C AB
    A CB      B CA      C BA
    再次注意，一个团体里的成员们，互相之间是不排列的！即有了 A(BC)以后，就没有意义再列 A(CB)了，CB在这里是一个团体，它们内部不再排序！
所以一共是 1 + 3 + 9 = 13 种获奖的排列方式。
*/

// 这是非常有趣，非常巧妙的一题

public class Solution {

	public int allKindsOfAwardPermutations(int n) {
		// range from 0 to n
		int[] dp = new int[n + 1];

		dp[0] = 1; // 没有人
		dp[1] = 1; // 一共1个人
		
		// people: total number of people in this game
		for (int people = 2; people <= n; people++) {
			
			// numOf1st: number of people that won the first prize (ranked No.1 together or alone)
			for (int numOf1st = people; numOf1st >= 1; numOf1st--) {
				
				dp[people] += combination_P_Q(numOf1st, people) * dp[people - numOf1st];
			}
		}
		
		return dp[n];
	}
	
	// 辅助函数，算组合：number of combinations to pick P items out of Q items
	private int combination_P_Q(int p, int q) {
		int result = 1;
		for (int i = q - p + 1; i <= q; i++) {
			result *= i;
		}
		for (int j = 2; j <= p; j++) {
			result /= j;
		}
		return result;
	}
}
