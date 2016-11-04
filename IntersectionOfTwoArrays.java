/* Given two arrays, write a function to compute their intersection.
Example: Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
Note:
1. Each element in the result must be unique.
2. The result can be in any order.
*/

public class Solution {
    
    // 用 HashSet
    // Time: O(1)
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
    
    
    // Sort both Arrays, use 2 pointers from head and tail respectively
    // Ref: https://discuss.leetcode.com/topic/45685/three-java-solutions
    // Time: O(n*log(n)). 后面处理重复数的过程只用O(1)，但前面排序数组用了O(n*log(n))
    public int[] intersection(int[] num1, int[] num2)
    {
        HashSet<Integer> interNums = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length)
        {
            if (nums1[i] < nums2[j])
                i++;
            else if (nums2[j] < nums1[i])
                j++;
            else // nums1[i] == nums2[j]
            {
                interNums.add(nums1[i]);
                i ++;
                j ++;
            }
        }
        int[] output = new int[interNums.size()];
        int k = 0;
        // 注意！HashSet 也可以用这种 Enhanced For Loop ！！
        for (Integer num : interNums)
            output[k ++] = num;
        return output;
    }
    
    
    
    
    
    
}
