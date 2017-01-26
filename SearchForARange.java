/* Given a sorted array of n integers, find the starting and ending position of a given target value.
If the target is not found in the array, return [-1, -1].
Challenge: O(log n) time.

Example
Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4]. */

public class Solution {
    /** 
     *@param A : an integer sorted array
     *@param target :  an integer to be searched
     *return : a list of length 2, [index1, index2]
     */
     
    // 注意！！！这一题如果不用九章模板，即 while(start + 1 < end)，
    // 而是用一般模板，即 while(start < end) 的话，
    // 会导致第二个while loop可能陷入死循环！！！具体来说，就是比如
    // 在第二个while里只剩下2个元素，且其中的第一个==target的话，那么
    // start会被无限次地重置为第一个元素！就成了死循环！
    public int[] searchRange(int[] nums, int target) {
        
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        
        int firstOccur = -1, lastOccur = -1;
        
        // find the index of the first occurence
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else { // ==
                end = mid;
            }
        }
        if (nums[start] == target) {
            firstOccur = start;
        } else if (nums[end] == target) {
            firstOccur = end;
        } else {
            return new int[]{-1, -1};
        }
        
        // find the index of the last occurrence
        end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else { // ==, 这里不可能再<target了
                start = mid;
            }
        }
        if (nums[end] == target) {
            lastOccur = end;
        } else { // nums[start] == target
            lastOccur = start;
        }

        return new int[]{firstOccur, lastOccur};
    }
}

