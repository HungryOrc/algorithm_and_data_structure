/* Given an array of 2D coordinates of points (all the coordinates are integers), 
find the largest number of points that can be crossed by a single line in 2D space.

Assumptions:
The given array is not null and it has at least 2 points

Examples:
<0, 0>, <1, 1>, <2, 3>, <3, 3>, the maximum number of points on a line is 3(<0, 0>, <1, 1>, <3, 3> are on the same line)  */


/* 思路：
直线有2类，一种是 y = ax + b，一种是垂直于x轴的即其上的每一点的x坐标都相同。
对于第一类直线，我们设立一个 Map<List<Double>, HashSet<Integer>>，
    前面的list里放的依次是 a 和 b 的值，后面的set放的是本point在point array里的index。
    特别注意 ！！！ list是可以作为key放到hashmap/hashset里的 ！！！ 它的equals方法是没问题的 ！！！ 会逐个比较list里的元素的值 ！！！
对于第二类直线，我们设立一个 Map<Integer, HashSet<Integer>>，
    前面的integer里放的是本point的x坐标值，后面的set放的是本point在point array里的index。

时间：O(n^2)   */

/* class Point {
*   public int x;
*   public int y;
*   public Point(int x, int y) {
*     this.x = x;
*     this.y = y;
*   }
* } */

public class Solution {
  
    public int most(Point[] points) {
    	if (points == null || points.length <= 1) {
    		return 0;
    	}
    	
    	int n = points.length;
    	// non-verticle lines
    	// <List<斜率, 截距>, 本直线上的points的indexes>
    	Map<List<Double>, HashSet<Integer>> lines = new HashMap<>();
    	// verticle lines
    	// <x坐标, 本直线上的points的indexes>
    	Map<Integer, HashSet<Integer>> verticles = new HashMap<>();
    	
    	for (int i = 0; i < n - 1; i++) {
    		for (int j = i + 1; j < n; j++) {
    			
    			Point p1 = points[i];
    			Point p2 = points[j];
    			
    			if (p1.x == p2.x) {
    				HashSet<Integer> indexes = verticles.get(p1.x);
    				if (indexes != null) {
    				  indexes.add(i);
    				  indexes.add(j);
    					verticles.put(p1.x, indexes);
    				} else {
    					indexes = new HashSet<>();
    					indexes.add(i);
    					indexes.add(j);
    					verticles.put(p1.x, indexes);
    				}
    			}
    			
    			else {
	    			double slope = (double)(p2.y - p1.y) / (double)(p2.x - p1.x);
	    			double dist = p1.y - p1.x * slope;
	    			
	    			List<Double> parameters = new ArrayList<>();
	    			parameters.add(slope);
	    			parameters.add(dist);

	    			HashSet<Integer> indexes = lines.get(parameters);
	    			if (indexes != null) {
	    			  indexes.add(i);
	    			  indexes.add(j);
	    				lines.put(parameters, indexes);
	    			} else {
	    				indexes = new HashSet<>();
	    				indexes.add(i);
	    				indexes.add(j);
	    				lines.put(parameters, indexes);
	    			}
    			}
    		}
    	}
    
    	int maxCount = 2;
    	for (Map.Entry<List<Double>, HashSet<Integer>> entry : lines.entrySet()) {
    		maxCount = Math.max(entry.getValue().size(), maxCount);
    	}
    	for (Map.Entry<Integer, HashSet<Integer>> entry : verticles.entrySet()) {
    		maxCount = Math.max(entry.getValue().size(), maxCount);
    	} 
        return maxCount;
    }
}
