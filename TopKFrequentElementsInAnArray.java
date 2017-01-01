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
    
    
    // 我的改进方法。比朴素的方法速度又快了一些
    // 做一个长度为n+1的数组。如果一个数x出现的次数为m，就在这个数组的第m个index上存下x
    // 但还有可能另一个数y也出现了m次，也需要记在index = m的位置
    // 所以前述的数组，必须是其中每个元素都是一个ArrayList
    // 记录完以后，从这个数组的尾部开始往前捋。捋满了k个数，即完成了题目所要求的
    public List<Integer> topKFrequent(int[] nums, int k) 
    {
        HashMap<Integer,Integer> numCountsMap = new HashMap<>();
        for (int n : nums)
            numCountsMap.put(n, numCountsMap.getOrDefault(n,0)+1);
        
        int n = nums.length;
        ArrayList<Integer>[] indexIsCount = new ArrayList[n+1];
        for (int i = 0; i < n+1; i++)
        {
            ArrayList<Integer> temp = new ArrayList<>();
            indexIsCount[i] = temp;
        }
        
        for (Map.Entry<Integer,Integer> curEntry : numCountsMap.entrySet())
        {
            int curNum = curEntry.getKey();
            int curCount = curEntry.getValue();
            
            indexIsCount[curCount].add(curNum);
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        int stillNeedToPick = k;
        for (int i = n; i >= 0 && stillNeedToPick > 0; i--)
        {
            if (indexIsCount[i].size() > 0)
            {
                for (int j = 0; j < indexIsCount[i].size() && stillNeedToPick > 0; j++)
                {
                    result.add(indexIsCount[i].get(j));
                    stillNeedToPick --;
                }
            }
        }
        return result;
    }
}
