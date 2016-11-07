/* Given two arrays, write a function to compute their intersection.
Example: Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
Note:
* Each element in the result should appear as many times as it shows in both arrays.
* The result can be in any order.

Follow up questions:
1. What if the given array is already sorted? How would you optimize your algorithm?
   用两个光标的方法，见后文
2. What if nums1's size is small compared to nums2's size? Which algorithm is better
   我认为是用较短的array来构建HashMap，用较长的那个来打擂HashMap
3. What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
   // Ref: https://discuss.leetcode.com/topic/45992/solution-to-3rd-follow-up-question
   * If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, 
     read chunks of array nums2 that fit into the memory, and record the intersections.
   * If both nums1 and nums2 are so huge that neither fit into the memory, SORT them individually (external sort), 
     then read 2 elements from each array at a time in memory, record intersections.  */   

public class Solution {
    
    // HashMap，在nums1出现一次+1，在nums2出现一次-1并记入结果，减到0则删除之
    // Time: O(n).
    public int[] intersect(int[] nums1, int[] nums2) {
        
        ArrayList<Integer> interNums_List = new ArrayList<>();
        HashMap<Integer, Integer> numCounts1 = new HashMap<>();
        
        for (int num : nums1)
        {
            if (numCounts1.containsKey(num))
                numCounts1.put(num, numCounts1.get(num)+1);
            else
                numCounts1.put(num, 1);
        }
        for (int num : nums2)
        {
            if (numCounts1.containsKey(num))
            {
                interNums_List.add(num);
                if (numCounts1.get(num) == 1)
                    numCounts1.remove(Integer.valueOf(num));
                else
                    numCounts1.put(num, numCounts1.get(num)-1);
            }
        }
        
        int[] result = new int[interNums_List.size()];
        for (int i = 0; i < interNums_List.size(); i++)
            result[i] = interNums_List.get(i);
        return result;
    }
    
    
    // 将两个array排序。然后用两个光标来比对
    // Time: O(n*log(n))
    public int[] intersect(int[] nums1, int[] nums2) 
    {
       ArrayList<Integer> result = new ArrayList<>();
       Arrays.sort(nums1);
       Arrays.sort(nums2);
       int index1 = 0, index2 = 0;
       
       while ((index1 < nums1.length) && (index2 < nums2.length))
       {
          if (nums1[index1] < nums2[inde2])
             index1 ++;
          else if (nums1[index1] > nums2[index2])
             index2 ++;
          else // nums1[index1] == nums2[index2]
          {
             result.add(nums1[index1]);
             index1++;
             index2++;
          }
       }
       int[] result_arr = new int[result.size()];
       for (int i = 0; i < result.size(); i++)
          result_arr[i] = result.get(i);
       return result_arr;
    }
    
    
}
