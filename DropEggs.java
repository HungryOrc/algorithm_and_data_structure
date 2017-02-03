/* There is a building of n floors. If an egg drops from the k th floor or above, it will break. 
If it's dropped from any floor below, it will not break.
You're given two eggs, Find k while minimize the number of drops for the worst case. 
Return the number of drops in the WORST case.

Clarification:
For n = 10, a naive way to find k is drop egg from 1st floor, 2nd floor ... kth floor. 
But in this worst case (k = 10), you have to drop 10 times.
Notice that you have two eggs, so you can drop at 4th, 7th & 9th floor, 
in the worst case (for example, k = 9) you have to drop 4 times.

Example:
Given n = 10, return 4.
Given n = 100, return 14. */

public class Solution {
    /* @param n an integer
     * @return an integer */
     
    // 比较详细的讲述整个 飞蛋问题：
    // Ref: http://datagenetics.com/blog/july22012/index.html
    // 注意：如果一个蛋在某次摔时没碎，它是可以被重复利用的
    // 一个蛋摔一次，不管碎不碎，都算是一次drop
    // 不管一开始有几个蛋，当只剩最后一个蛋时，只能老老实实在当前的目标区间内从低到高一层一层试了
    public int dropEggs(int n) {
        
        // 注意！n 虽然不会超过int的范围，但我们的实验和sum可能超过int的范围！！
        // 比如n正好在int的上限处的话，sum必须>=int的上限才能导致while循环结束
        long sum = 0;
        int i = 1;
        
        while (sum < n) {
            sum += i;
            i++;
        }
        return i - 1;
    }
}
