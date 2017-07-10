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
  	
  	int maxLength = 0;
  	int[] dp = new int[n];
  	for (int i = 1; i < n; i++) {
  		for (int j = i - 1; j >= 0; j--) {
  			if (points[j].y < points[i].y) {
  				dp[i] = Math.max(2, dp[i]);
  				dp[i] = Math.max(dp[j] + 1, dp[i]);
  				
  				maxLength = Math.max(dp[i], maxLength);
  			}
  		}
  	}
  	
  	return maxLength;
  }
}
