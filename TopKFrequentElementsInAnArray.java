/* Given a non-empty array of integers, return the k most frequent elements.
For example, Given [1,1,1,2,2,3] and k = 2, return [1,2].
Note: 
* You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
* Your algorithm's time complexity must be better than O(n log n), where n is the array's size. */

public class Solution 
{
    // 我的朴素方法，速度还不错
    public List<Integer> topKFrequent(int[] nums, int k) 
    {
        HashMap<Integer,Integer> numCountsMap = new HashMap<>();
        for (int n : nums)
            numCountsMap.put(n, numCountsMap.getOrDefault(n,0)+1);
        
        ArrayList<Map.Entry<Integer,Integer>> numCountsAL = new ArrayList<>();
        for (Map.Entry<Integer,Integer> curEntry : numCountsMap.entrySet())
            numCountsAL.add(curEntry);
        
        // sort the ArrayList of HashMap Entries by the descending order of the Value
        // in each Entry
        Collections.sort(numCountsAL, new Comparator<Map.Entry<Integer,Integer>>()
        {
            @Override
            public int compare(Map.Entry<Integer,Integer> entry1, 
                               Map.Entry<Integer,Integer> entry2)
            {
                int value1 = entry1.getValue();
                int value2 = entry2.getValue();
                if (value1 > value2)
                    return -1;
                else if (value1 < value2)
                    return 1;
                else // ==
                    return 0;
            }
        });
        
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++)
            result.add(numCountsAL.get(i).getKey());
        return result;
    }
}
