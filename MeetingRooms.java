/* Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
determine if a person could attend all meetings.
For example, Given [[0, 30],[5, 10],[15, 20]], return false.

 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
 
public class Solution {

    // 直接两两比较每一对 intervals
    // Ref: https://leetcode.com/articles/meeting-rooms/
    // The straight-forward solution is to compare every two meetings in the array, and see if they conflict with each other 
    // (i.e. if they overlap). Two meetings overlap if one of them starts while the other is still taking place.
    // Time: O(n^2), Space: O(1)
    public boolean canAttendMeetings (Interval[] intervals)
    {
        for (int i = 0; i < intervals.length; i++)
        {
            for (int j = 0; j < intervals.length; j++)
            {
                if (overlap(intervals[i], intervals[j]))
                    return false;
            }
        }
        return true;
    }
    private boolean overlap (Interval i1, Interval i2)
    {
         // 比较巧妙的判断方法
         return (Math.min(i1.end, i2.end) > Math.max(i1.start, i2.start));
         // 比较质朴的判断方法
         // return ((i1.start >= i2.start && i1.start < i2.end) || (i2.start >= i1.start && i2.start < i1.end));
    }


    // 将所有的start和所有的end分别排序为2个数列。速度非常快！beat 95% answers
    // Ref: https://discuss.leetcode.com/topic/31306/easy-java-solution-beat-98/3
    // overlap means there's an interval which start time is earlier than another's end time which begins before this one, 
    // Sort the start time array and end time array to find if this happens
    // Time: O(n*log(n))
    public boolean canAttendMeetings (Interval[] intervals)
    {
        int len = intervals.length;
        if (len == 0)
            return true;
        int[] begin = new int[len];
        int[] stop = new int[len];
        for (int i = 0; i < len; i++)
        {
            begin[i] = intervals[i].start;
            stop[i] = intervals[i].end;
        }
        Arrays.sort(begin);
        Arrays.sort(stop);
        
        for (int i = 1; i < len; i++)
        {
            if (begin[i] < stop[i-1])
                return false;
        }
        return true;
    }
 

    // 用 Comparator 来 sort meetings by starting time
    // Ref: https://leetcode.com/articles/meeting-rooms/
    public boolean canAttendMeetings (Interval[] intervals)
    {
       Arrays.sort (intervals, new Comparator<Interval>()
       {
          public int compare (Interval i1, Interval i2)
             return i1.start - i2.start;
       });
     
       for (int i = 0; i < intervals.length - 1; i ++)
       {
          if (intervals[i].end > intervals[i+1].start)
             return false;
       }
       return true;
    }
 
 
}
