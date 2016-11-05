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
 
 
 
 
 }
