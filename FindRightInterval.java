/* Given a set of intervals, for each of the interval i, 
check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i, 
which can be called that j is on the "right" of i.

For any interval i, you need to store the minimum interval j's index, 
which means that the interval j has the minimum start point to build the "right" relationship for interval i. 
If the interval j doesn't exist, store -1 for the interval i. Finally, 
you need output the stored value of each interval as an array.

Note:
You may assume the interval's end point is always bigger than its start point.
You may assume none of these intervals have the same start point.

Example 1:
Input: [ [1,2] ]
Output: [-1]
Explanation: There is only one interval in the collection, so it outputs -1.

Example 2:
Input: [ [3,4], [2,3], [1,2] ]
Output: [-1, 0, 1]
Explanation: There is no satisfied "right" interval for [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point;
For [1,2], the interval [2,3] has minimum-"right" start point.

Example 3:
Input: [ [1,4], [2,3], [5,7] ]
Output: [-1, 2, -1]
Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
For [2,3], the interval [5,7] has minimum-"right" start point.

注意 TreeMap 数据结构！
TreeMap: A Red-Black tree based NavigableMap implementation. 
The map is sorted according to the natural ordering of its keys, or by a Comparator provided at map creation time, 
depending on which constructor is used.

Java clear O(n logn) solution based on TreeMap
Ref: https://discuss.leetcode.com/topic/65817/java-clear-o-n-logn-solution-based-on-treemap */

/* Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * } */

public class Solution 
{
    public int[] findRightInterval(Interval[] intervals) 
    {
        int len = intervals.length;
        int[] result = new int[len];
        TreeMap<Integer,Integer> intervalTreeMap = new TreeMap<>();
        
        for (int i = 0; i < len; i++)
            intervalTreeMap.put(intervals[i].start, i);
            
        for (int i = 0; i < len; i++)
        {
            // ceilingEntry(K key)
            // Returns a key-value mapping associated with the least key greater than or equal to 
            // the given key, or null if there is no such key
            Map.Entry<Integer,Integer> entry = intervalTreeMap.ceilingEntry(intervals[i].end);
            if (entry != null)
                result[i] = entry.getValue();
            else // null
                result[i] = -1;
        }
        return result;
    }
}
