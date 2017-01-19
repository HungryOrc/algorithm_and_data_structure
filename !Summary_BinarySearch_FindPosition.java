/**
 * @param nums: an integer array sorted in ascending order
 * @param target: an integer
 * @return: an integer
 */

// Template for Binary Search - Find Position
// 九章版本
// Ref: http://www.jiuzhang.com/solutions/binary-search/
public int findPosition(int[] nums, int target) {
  
    if (nums == null || nums.length == 0) {
        return -1;
    }  
  
    int start = 0, end = nums.length - 1;
    // 注意！！while 条件的选择
    while (start + 1 < end) {
        // 不用 (start + end) / 2，是为了避免数据超出int的范围
        int mid = start + (end - start) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            start = mid; // 注意！！
        } else {
            end = mid; // 注意！！
        }
    }
    
    if (nums[start] == target) {
        return start;
    } else if (nums[end] == target) {
        return end;
    }
    return -1;
}


// Template for Binary Search - Find Position
// 普世价值版本
public int findPosition(int[] nums, int target) {

    if (nums == null || nums.length == 0) {
        return -1;
    }
    
    int start = 0, end = nums.length - 1;
    while (start < end) {
        int mid = start + (end - start) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            start = mid + 1;
        } else {
            end = mid - 1;
        }
    }
    
    if (nums[start] == target) { // 此时应有 start == end 了
        return start;
    }
    return -1;
}
