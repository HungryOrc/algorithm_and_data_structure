/* Given a big sorted array with positive integers sorted by ascending order. 
The array is so big so that you can not get the length of the whole array directly, 
and you can only access the kth number by ArrayReader.get(k) (or ArrayReader->get(k) for C++). 
Find the first index of a target number. Your algorithm should be in O(log k), where k is the first index of the target number.
Return -1, if the number doesn't exist in the array.

Notice: If you accessed an inaccessible index (outside of the array), ArrayReader.get will return 2,147,483,647.

Example:
Given [1, 3, 6, 9, 21, ...], and target = 3, return 1.
Given [1, 3, 6, 9, 21, ...], and target = 4, return -1.

* Definition of ArrayReader:
* class ArrayReader {
*      // get the number at index, return -1 if index is less than zero.
*      public int get(int index);
* } */

public class Solution {
    /**
     * @param reader: An instance of ArrayReader.
     * @param target: An integer
     * @return : An integer which is the index of the target number
     */
     
    // 我的土法。速度很慢，虽然也是二分
    public int searchBigSortedArray(ArrayReader reader, int target) {
        
        int OVERFLOW = 2147483647;
        int start = 0, end = 1, rightMost = Integer.MAX_VALUE;
        
        // 找这个数组的一个右边界，只要它的值大于等于target就行，
        // 不必是这个数组的最右边的元素
        while (!(reader.get(end)<OVERFLOW && reader.get(end+1)==OVERFLOW)
               || start == end) {
            if (reader.get(end) < target) {
                start = end;
                end = Math.min(end * 2, rightMost);
            }
            else if (reader.get(end) == OVERFLOW) {
                rightMost = Math.min(end, rightMost);
                end = start + (rightMost - start) / 2;
            }
            else if (reader.get(end) >= target && reader.get(end) < OVERFLOW) {
                break;
            }
        }
        // now start == end
        if (reader.get(end) < target) {
            return -1;
        }        
        
        // 用二分法找最终的答案
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (reader.get(mid) == target) {
                if (mid == 0 || reader.get(mid - 1) != target) {
                    return mid;
                } else {
                    end = mid - 1;
                }
            } else if (reader.get(mid) > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        // now start == end
        if (reader.get(end) == target) {
            return end;
        } else {
            return -1;
        }
    }
    
}
