/* Given a sorted array of integers nums and integer values a, b and c. 注意！给的数组就已经是sorted的！
Apply a function of the form f(x) = ax2 + bx + c to each element x in the array.
The returned array must be in sorted order.

Expected time complexity: O(n)

Example:
nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,
Result: [3, 9, 15, 33]
nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5
Result: [-23, -5, 1, 7]

Ref: https://leetcode.com/problems/sort-transformed-array/
很巧妙的方法！
如果 a > 0，则是开口向上的抛物线，则两端更大。那么最大值一定在给定的数组nums的一端，而最小值一定在中间的某处。
那么我们就从nums的两端开始，取二者中更大的一个，比如是A，填到输出数组的最后一位。 
然后不管A，看nums目前的两段谁更大，照此往复执行。
如果 a < 0，则是开口向下的抛物线，则中间某处最大，最小值一定在两端中的一端。我们按照类似上述的方法执行，
改变之处在于，取两端中更小的一个，然后放在输出数组的第一位。照此往复执行。  
如果 a = 0，这时成了直线，但想一想可以与a>0或者a<0中的任何一种情况一起处理。因为此时最大值在一端，最小值在另一端。
无论是从输出数组的头部填起，还是尾部填起，都是可以的   */

public class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] res = new int[nums.length];
        int start = 0;
        int end = nums.length - 1;
        int i = a >= 0 ? nums.length - 1 : 0;
        while(start <= end) {
            int startNum = getNum(nums[start], a, b, c);
            int endNum = getNum(nums[end], a, b, c);
            if(a >= 0) {
                if(startNum >= endNum) {
                    res[i--] = startNum;
                    start++;
                }
                else{
                    res[i--] = endNum;
                    end--;
                }
            }
            else{
                if(startNum <= endNum) {
                    res[i++] = startNum;
                    start++;
                }
                else{
                    res[i++] = endNum;
                    end--;
                }
            }
        }
        return res;
    }
    private int getNum(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
}



