/* Given two arrays, write a function to compute their intersection.
Example: Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
Note:
1. Each element in the result must be unique.
2. The result can be in any order.
*/

public class Solution {
    
    // 用 HashSet
    public int[] intersection(int[] nums1, int[] nums2) {
        
        HashSet<Integer> numsInArrayOne = new HashSet<>();
        ArrayList<Integer> output_ArrayList = new ArrayList<>();
        
        for (int num : nums1)
            // 注意！HashSet 是 add 不是 put！！
            numsInArrayOne.add(num);
        
        for (int num : nums2)
        {
            if (numsInArrayOne.contains(num))
            {
                // 注意！HashSet 的 contains
                output_ArrayList.add(num);
                // 要把查出来是重复的数从 HashSet 里删掉！！
                numsInArrayOne.remove(num);
            }
        }
        int[]output_Array = new int[output_ArrayList.size()];
        for (Integer num : output_ArrayList)
            output_Array[i] = output_ArrayList.get(i);
    
        return output_Array;
    }
    
}
