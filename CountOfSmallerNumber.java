/* Give you an integer array (index from 0 to n-1, where n is the size of this array, value from 0 to 10000) and an query list. 
For each query, give you an integer, return the number of element in the array that are smaller than the given integer.
Example: For array [1,2,7,8,5], and queries [1,8,5], return [0,4,2]. 

We suggest you finish problem Segment Tree Build and Segment Tree Query II first:
http://www.lintcode.com/en/problem/count-of-smaller-number/#

Alternatively, you can also use Binary Search to solve this problem. */

public class Solution {
   /**
     * @param A: An integer array
     * @return: The number of element in the array that 
     *          are smaller that the given integer */
    
    // Sort, and then Binary Search
    public ArrayList<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        
        int queriesLen = queries.length;
        ArrayList<Integer> result = new ArrayList<>();
        
        if (A == null || A.length == 0) {
            for (int i = 0; i < queries.length; i++) {
                result.add(0);
            }
            return result;
        }
        
        Arrays.sort(A);
        
        for (int i = 0; i < queries.length; i++) {
            result.add(countSmaller(A, queries[i]));
        }
        return result;
    }
    private int countSmaller(int[] nums, int target) {
        int count = 0;
        
        // 处理特殊情况
        if (nums[0] >= target) {
            return 0;
        }
        
        // 就是找最后一个小于target的数
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                end = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else { // ==
                end = mid;
            }
        }
        // now start + 1 == end
        if (nums[start] < target && nums[end] >= target) {
            // there will be this number of numbers (in the array) that are smaller than the target
            return start + 1; 
        } else { // nums[end] > target
            return end + 1;
        }
    }
}
