/* Given a collection of intervals, merge all overlapping intervals.
For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].

/* Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * } */

// 方法1：根据starting point来对所有interval从小到大排序。然后从左到右一个一个interval做检查处理。速度不快
// Ref: https://discuss.leetcode.com/topic/4319/a-simple-java-solution
public class Solution {
    
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }
        
        // sort the Intervals in the list by the starting points of the Intervals
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval inter1, Interval inter2) {
                if (inter1.start > inter2.start) {
                    return 1;
                } else if (inter1.start < inter2.start) {
                    return -1;
                } else { // ==
                    return 0;
                }
            }
        });
        
        
        int prevStart = intervals.get(0).start;
        int prevEnd = intervals.get(0).end;
        
        for (int i = 1; i < intervals.size(); i++) {
            int curStart = intervals.get(i).start;
            int curEnd = intervals.get(i).end;
            
            if (curStart <= prevEnd) { // 如果有重叠
                prevEnd = Math.max(prevEnd, curEnd); // 注意！prevEnd 和 curEnd 谁大还不一定呢！！
            } else { // 如果没有重叠
                result.add(new Interval(prevStart, prevEnd));
                
                prevStart = curStart;
                prevEnd = curEnd;
            }
        }
        // 特别注意！最后一个interval到此为止还没加进result呢！！
        result.add(new Interval(prevStart, prevEnd));
        return result;
    }
}


// 方法2：start和end分别排序，然后按下面的代码处理。很巧妙！速度很快
// Ref: https://discuss.leetcode.com/topic/38628/beat-98-java-sort-start-end-respectively
public class Solution {
    
    public List<Interval> merge(List<Interval> intervals) {
    	// sort start&end
    	int n = intervals.size();
    	int[] starts = new int[n];
    	int[] ends = new int[n];
    	
    	for (int i = 0; i < n; i++) {
    		starts[i] = intervals.get(i).start;
    		ends[i] = intervals.get(i).end;
    	}
    	Arrays.sort(starts);
    	Arrays.sort(ends);
    	
    	List<Interval> res = new ArrayList<Interval>();
    	for (int i = 0, j = 0; i < n; i++) {
    		if (i == n - 1 || starts[i + 1] > ends[i]) {
    			res.add(new Interval(starts[j], ends[i]));
    			j = i + 1;
    		}
    	}
    	return res;
    }
}
