/* You are given an integer array nums and you have to return a new counts array. 
The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example: Given nums = [5, 2, 6, 1]
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0]. */

// 很巧妙的方法！
// 从尾部开始做 + ArrayList + 二分查找
// Ref: https://discuss.leetcode.com/topic/31173/my-simple-ac-java-binary-search-code
/* Traverse from the back to the beginning of the array, maintain an sorted array of numbers have been visited. 
Use findIndex() to find the first element in the sorted array which is larger or equal to target number. 
For example, [5,2,3,6,1], when we reach 2, we have a sorted array[1,3,6], 
findIndex() returns 1, which is the index where 2 should be inserted and is also the number smaller than 2. 
Then we insert 2 into the sorted array to form [1,2,3,6]. */

public class Solution {
    
    public List<Integer> countSmaller(int[] nums) {
        Integer[] ans = new Integer[nums.length];
        List<Integer> sorted = new ArrayList<Integer>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int index = findIndex(sorted, nums[i]);
            ans[i] = index;
            sorted.add(index, nums[i]);
        }
        return Arrays.asList(ans);
    }
    
    private int findIndex(List<Integer> sorted, int target) {
        if (sorted.size() == 0) return 0;
        int start = 0;
        int end = sorted.size() - 1;
        if (sorted.get(end) < target) return end + 1;
        if (sorted.get(start) >= target) return 0;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (sorted.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        if (sorted.get(start) >= target) return start;
        return end;
    }
}
