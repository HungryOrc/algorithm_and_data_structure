/* Suppose a sorted array is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
You are given a target value to search. If found in the array return its index, otherwise return -1.
You may assume no duplicate exists in the array.
Runtime requirement: O(logN) time.

Example
For [4, 5, 1, 2, 3] and target=1, return 2.
For [4, 5, 1, 2, 3] and target=0, return -1. */

public class Solution {
    /** 
     *@param nums: an integer rotated sorted array
     *@param target :  an integer to be searched
     *return : an integer */
    
    // 两次二分的做法
    public int search(int[] nums, int target) {
        
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int start = 0, end = nums.length - 1;
        
        // 如果数组真的被翻转过
        if (nums[0] > nums[nums.length - 1])
        {
            // find the start of the right half array
            int rightHalfEndValue = nums[nums.length - 1];
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] > rightHalfEndValue) {
                    start = mid + 1;
                } else { // if (mid <= rightHalfEndValue)
                    end = mid;
                }
            }
            // now start == end
            int rightHalfBeginValue = nums[start];

            // locate the half in which the target is in
            if (target < rightHalfEndValue) {
                // start 不变，此时start就等于right half的起始index
                end = nums.length - 1;
            } else if (target > rightHalfEndValue) {
                start = 0;
                end -= 1;
            } else { // ==
                return nums[nums.length - 1];
            }
        }
    
        // find the index of the target
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else { // ==
                return mid;
            }
        }
        // now start == end
        if (nums[start] == target) {
            return start;
        } else {
            return -1;
        }
    }
    
    
    // 一次二分的做法。很巧妙！！！
    // Ref: http://www.jiuzhang.com/solutions/search-in-rotated-sorted-array/
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;
        int mid;
        
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            
            // 注意！！！精华所在！！！
            // 接下来就不直接比较mid和target的值了！！！
            // 而是先要比较start和mid的值，以先确认start与mid所在的分段的情况！！！
            // 然后才是比较start，mid，end与target的值！！！
            
            // 第一种情况，start和mid在同一段上，这里又包含2种子情况：
            // 它们都在左半分段上，或者它们都在右半分段上
            // 但这2种子情况都可以用下述的方式统一处理：
            if (nums[start] < nums[mid]) {
                // 如果target处于start与mid之间
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            
            // 第二种情况，start和mid不在同一段上；确切地说：
            // start在左半分段上，mid在右半分段上
            // 此时，可知 mid与end一定在同一个分段上！！！
            // 这一点是我们继续做下去的支柱所在！！！
            } else {
                // 如果target处于mid与end之间
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }
        
}
