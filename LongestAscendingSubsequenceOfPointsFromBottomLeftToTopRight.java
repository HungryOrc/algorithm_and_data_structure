/* 二维平面上有n个点，它们各自有x和y坐标。要求在这些点上连一条从左下到右上方向的线，经过的点的个数最多。返回它经过的点的个数。

所谓的从左下到右上方向，除了斜率为正数的情况以外，下面两种方向也可以：
1. 向一个点的正右方水平走，就是说下一个点和当前点的y坐标相同，x坐标大于当前点的x坐标
2. 向一个点的正上方竖直走，就是说下一个点和当前点的x坐标相同，y坐标大于当前点的y坐标 */


/* 思路：这一题用DP做
第一步：把这n个点排序，优先级是：先按x从小到大排序，再按y从小到大排序
第二步：对这些点做 Longest Ascending Subsequence。递推关系是：
    if (pi.x >= pj.x && pi.y >= pj.y) {
		    dp[i] = Math.max(dp[i], dp[j] + 1);
		}                                                                  */
    
class Point {
	int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	} 
}

public class Solution {

	public int bottomLeftToTopRight(Point[] points) {
		if (points == null || points.length == 0) {
			return 0;
		}
		
		// Step 1: sort the array of points,
		// first by x ascendingly, then by y ascendingly
		Arrays.sort(points, new Comparator<Point>(){
			@Override
			public int compare(Point p1, Point p2) {
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
		return longestAscendingSubsequence(points);
	}
	
	private int longestAscendingSubsequence(Point[] points) {
		int n = points.length;
		
		int[] dp = new int[n];
		dp[0] = 1;
		int maxLen = 1;
		
		for (int i = 1; i < n; i++) {
			dp[i] = 1;
			
			for (int j = 0; j < i; j++) {
				Point pi = points[i];
				Point pj = points[j];
				
				// 别忘了这个情况 ！ 如果点i和点j重合，则continue到下一个j
				if (pi.x == pj.x && pi.y == pj.y) {
					continue;
				}
				
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
