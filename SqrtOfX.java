/* Implement int sqrt(int x). Compute and return the square root of x.
Runtime requirement: O(log(x)).

Example:
sqrt(3) = 1
sqrt(4) = 2
sqrt(5) = 2
sqrt(102) = 10 */

// 用二分法做
class Solution {
    /**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        
        if (x < 0) {
            return -1;
        }
        else if (x == 0) {
            return 0;
        }
        
        // 注意！把left, right, mid 都设为long！
        // 这样可以避免 mid * mid 时超过int的范围！
        
        // right 可以从 x 开始，
        // 但仔细想 x = 1, 2, 3...的情形，可以发现从 x/2 开始是ok的
        long left = 1, right = x / 2;
        while (left < right) {
            long mid = left + (right - left) / 2;
            if (mid * mid <= x) {
                if ((mid + 1) * (mid + 1) > x) {
                    return (int)mid;
                } else {
                    left = mid + 1;
                }
            }
            else {
                right = mid - 1;
            }
        }
        // now we must have left == right
        return (int)left;
    }
}
