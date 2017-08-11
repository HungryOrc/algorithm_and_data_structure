/* 堆叠木板。更详细的解释请看 Longest Ascending Subsequence 
堆叠的条件：每块木板的长度要小于它下面那块的长度，宽度要小于它下面那块的宽度。是小于，不是小于等于。*/

class Wood {
	int x, y;
	public Wood(int x, int y) {
		this.x = x;
		this.y = y;
	} 
}

public class Solution {

	public int bottomLeftToTopRight(Wood[] woods) {
		if (woods == null || woods.length == 0) {
			return 0;
		}
		
		// Step 1: sort the array of points,
		// first by x ascendingly, then by y ascendingly
		Arrays.sort(woods, new Comparator<Wood>(){
			@Override
			public int compare(Wood p1, Wood p2) {
				if (p1.x > p2.x) {
					return 1;
				} else if (p1.x < p2.x) {
					return -1;
				} else { // p1.x == p2.x
					if (p1.y == p2.y) {
						return 0;
					} else {
						return p1.y > p2.y ? 1 : -1;
					}
				}
			}
		});
		
		// Step 2: Run "Longest Ascending Subsequence" on the sorted array of points
		return longestAscendingSubsequence(woods);
	}
	
	private int longestAscendingSubsequence(Wood[] woods) {
		int n = woods.length;
		
		int[] dp = new int[n];
		dp[0] = 1;
		int maxLen = 1;
		
		for (int i = 1; i < n; i++) {
			dp[i] = 1;
			
			for (int j = 0; j < i; j++) {
				Wood pi = woods[i];
				Wood pj = woods[j];
				
				// 递推关系 ！！
				if (pi.x > pj.x && pi.y > pj.y) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			maxLen = Math.max(maxLen, dp[i]);
		}
		return maxLen;
	}
}
