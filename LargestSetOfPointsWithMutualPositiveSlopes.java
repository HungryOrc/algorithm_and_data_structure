/* Given an array of 2D coordinates of points (all the coordinates are integers), 
find the largest number of points that can form a set such that any pair of points in the set can form a line with positive slope. 
Return the size of such a maximal set.
Note: if there does not even exist 2 points can form a line with positive slope, should return 0.

Assumptions:
The given array is not null

Examples:
<0, 0>, <1, 1>, <2, 3>, <3, 3>, the maximum set of points are {<0, 0>, <1, 1>, <2, 3>}, the size is 3.   */

/* class Point {
*   public int x;
*   public int y;
*   public Point(int x, int y) {
*     this.x = x;
*     this.y = y;
*   }
* } */


/* 思路：两个步骤。Laioffer的方法。核心思路：能构成正斜率的点，iff 我的x小于你，则我的y也必须小于你。
那么，能构成这么一个set的points，必须所有的points两两之间满足上面这个条件 ！！！

第一步：Sort the input points according to their x-coordinates by ascending order, 
    if 2 points have the same x, than sort them by y by ascending order.
    Time: O(n long)
第二步：Find the "Longest Ascending Sub-sequence" in the sorted points array, according to their y-coordinates.
    Time: O(n^2)
    
Time: O(n^2)   */

public class Solution {

	public int largest(Point[] points) {
  	if (points == null || points.length <= 1) {
  		return 0;
  	}
  	
  	int n = points.length;

  	Arrays.sort(points, new Comparator<Point>(){
  		@Override
  		public int compare(Point p1, Point p2) {
  			if (p1.x > p2.x) {
  				return 1;
  			} else if (p1.x < p2.x) {
  				return -1;
  			} else {
  				if (p1.y == p2.y) {
  					return 0;
  				} else {
  					return p1.y > p2.y ? 1 : -1;
  				}
  			}
  		}
  	});
  	
  	int maxLength = 0; // 注意！这一题不是返回 dp[n - 1] ！
  	int[] dp = new int[n];
  	for (int i = 1; i < n; i++) {
  		for (int j = i - 1; j >= 0; j--) {
  			if (points[j].y < points[i].y) {
				// 注意 ！！！ 一个点不构成一个 slope ！
				// 所以一开始不能设任何 dp[i] = 1，它们必须都默认为 0 ！
				// 而一旦找到任何两点构成一个 positive slope，则马上设 dp[i] = 2 ！！！
				// 这时候如果只做 dp[i] = dp[j] + 1 是不够的！因为dp[j]很可能只是 0 ！
  				dp[i] = Math.max(2, dp[i]);
  				dp[i] = Math.max(dp[j] + 1, dp[i]);
  				
  				maxLength = Math.max(dp[i], maxLength);
  			}
  		}
  	}
  	
  	return maxLength;
  }
}
