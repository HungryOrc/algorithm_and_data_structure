/* Given a non-empty array of integers, return the k most frequent elements.
For example, Given [1,1,1,2,2,3] and k = 2, return [1,2].
Note: 
* You may assume k >= 1
* 但是 k 可能小于 input 数组里的 distinct number 的个数 ！！所以最后的答案的长度可能会小于 k ！！
* Your algorithm's time complexity must be better than O(n log n), where n is the array's size. */

public class Solution 
{
    // 方法1：速度较快。Laioffer的方法
    // 用 hashmap 来装各个string的出现次数，然后关键在于：用 PriorityQueue (min heap) 来处理最高频的 k 个 Map Entry ！！！
    public String[] topKFrequent(String[] composition, int k) {
    
        HashMap<String, Integer> wordCounts = new HashMap<>();
        for (String s : composition) {
          wordCounts.put(s, wordCounts.getOrDefault(s, 0) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> minHeap = 
          new PriorityQueue(k, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
              // in this case,
              // we can directly reuse the compareTo method of Integer class !!!
              return entry1.getValue().compareTo(entry2.getValue());
            }
          });

        // put the map entries into the min heap
        // if the number of map entries is < k, it's still ok, by the following code,
        // the size of the min heap will be < k then
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
          if (minHeap.size() < k) {
            minHeap.offer(entry);
          } else {
            if (entry.getValue() > minHeap.peek().getValue()) {
              minHeap.poll();
              minHeap.offer(entry);
            }
          }
        }

        String[] result = new String[minHeap.size()];
        // in the result array, we must place the values in descending order,
        // so we fill in the array starting from the tail of the array
        for (int i = minHeap.size() - 1; i >= 0; i--) {
          result[i] = minHeap.poll().getKey(); // 注意这里要get key！即要的是String！不要getValue
        }
        return result;
    }
    
    
    // 方法2：我的朴素方法，用ArrayList装MapEntry，然后再用 custom comparator 来 sort ArrayList，
    // 虽然思路很原始，数据结构也很直觉很简单，但速度还不错
    public List<Integer> topKFrequent(int[] nums, int k) 
    {
        HashMap<Integer,Integer> numCountsMap = new HashMap<>();
        for (int n : nums) {
            numCountsMap.put(n, numCountsMap.getOrDefault(n, 0) + 1);
        }
        
        ArrayList<Map.Entry<Integer,Integer>> numCountsAL = new ArrayList<>();
        for (Map.Entry<Integer,Integer> curEntry : numCountsMap.entrySet()) {
            numCountsAL.add(curEntry);
        }
        
        // sort the ArrayList of HashMap Entries by the descending order of the Value in each Entry
        // 注意构造 new Comparator 的语法！！！
        Collections.sort(numCountsAL, new Comparator<Map.Entry<Integer,Integer>>() {
            @Override
            public int compare(Map.Entry<Integer,Integer> entry1, Map.Entry<Integer,Integer> entry2) {
                int value1 = entry1.getValue();
                int value2 = entry2.getValue();
                // 要求降序排列 ！
                if (value1 > value2)
                    return -1;
                else if (value1 < value2)
                    return 1;
                else // ==
                    return 0;
            }
        });
        
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < Math.min(k, numCountsAL.size()); i++)
            result.add(numCountsAL.get(i).getKey());
        return result;
    }
    
    
    // 方法3：我的另一种朴素方法，比上一个朴素方法快一些。
    // 做一个长度为n+1的数组。如果一个数x出现的次数为m，就在这个数组的第m个index上存下x
    // 但还有可能另一个数y也出现了m次，也需要记在index = m的位置，所以这个数组，其中的每个元素都得是一个ArrayList
    // 记录完以后，从这个数组的尾部开始往前捋。捋满了k个数，或者捋到了数组的头部以后，即得到答案
    public List<Integer> topKFrequent(int[] nums, int k) 
    {
        HashMap<Integer,Integer> numCountsMap = new HashMap<>();
        for (int n : nums)
            numCountsMap.put(n, numCountsMap.getOrDefault(n, 0) + 1);
        
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
