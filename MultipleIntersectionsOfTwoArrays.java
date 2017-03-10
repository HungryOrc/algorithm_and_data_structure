/* Given two arrays, write a function to compute their intersection.
Example: Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
Note:
1. Each element in the result must be unique.
2. The result can be in any order. */

public class Solution {
    
    // 方法1：Two HashSets. Time: O(n)
    // 不要用 一个HashSet 加上 一个ArrayList！！！那样太慢！！！
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return null;    
        }
        
        HashSet<Integer> numsIn1 = new HashSet<>();
        HashSet<Integer> intersections = new HashSet<>();
        
        for (int num : nums1) {
            numsIn1.add(num);
        }
        
        for (int num : nums2) {
            if (numsIn1.contains(num) && !intersections.contains(num)) { 
                intersections.add(num);
            }
        }
        
        int[] result = new int[intersections.size()];
        int i = 0;
        for (int num : intersections) {
            result[i] = num;
            i++;
        }
        return result;
    }
    
    
    // 方法2：Sort both Arrays, use 2 pointers for 2 arrays respectively, both of them go from left to right
    // Ref: https://discuss.leetcode.com/topic/45685/three-java-solutions
    // Time: O(n*log(n)). 后面处理重复数的过程只用O(n)，但前面排序数组用了O(n*log(n))
    public int[] intersection(int[] num1, int[] num2) {
        HashSet<Integer> interNums = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j])
                i++;
            else if (nums2[j] < nums1[i])
                j++;
            else { // nums1[i] == nums2[j]
                interNums.add(nums1[i]);
                i ++;
                j ++;
            }
        }
        
        int[] result = new int[interNums.size()];
        int i = 0;
        for (Integer num : interNums)
            result[i++] = num;
        return result;
    }
    
    
    // 方法3：Binary Search
    // Ref: https://discuss.leetcode.com/topic/45685/three-java-solutions
    // Time: O(n*log(n)). 后面处理重复数的过程只用O(log(n))，但前面排序数组用了O(n*log(n))
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> interNums = new HashSet<>();
        Arrays.sort(nums2);
        // 这个方法，只用排列2个数列中的一个即可。被排列的这个要用于binary search
        
        for (Integer num : nums1) {
            if (binarySearch(nums2, num))
                interNums.add(num);
        }
        
        int i = 0;
        int[] result = new int[interNums.size()];
        for (Integer num : interNums) {
            result[i] = num;
            i++;
        }
        return result;
    }
    
    private boolean binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target)
                return true;
            else if (nums[mid] > target)
                high = mid - 1;
            else if (nums[mid] < target)
                low = mid + 1;
        }
        return false;
    }
    
}
