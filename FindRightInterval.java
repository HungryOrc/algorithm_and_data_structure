

public class Solution {
    public int[] findRightInterval(Interval[] intervals) {
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
