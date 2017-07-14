/* 这一类题也叫 “Naive背包问题”，或 “0/1背包问题”，因为每个item最多取一次。
每个item有自己的size（或者weight）。背包有最大的容量（对于size或weight）。求最大可能的总装载量（对于size或weight）。

题目出处：http://www.lintcode.com/en/problem/backpack/
Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?
You function should return the max size we can fill in the given backpack.
Notice: You can not divide any item into small pieces.

Example:
If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], 
so that the max size we can fill this backpack is 10. If the backpack size is 12. 
we can select [2, 3, 7] so that we can fulfill the backpack. */


/* 思路：


*/

public class Solution {
     
    public int backPack(int capacity, int[] sizes) {
        int n = sizes.length;
        
        boolean[][] canSumTo = new boolean[n + 1][capacity + 1];
        canSumTo[0][0] = true;
        
        // 这里的i是指第几个item ！！！ 而非int[] sizes 里的index ！！！
        for (int i = 1; i <= n; i++) {
            int curItemSize = sizes[i - 1];
            
            for (int sum = 0; sum <= capacity; sum++) {
                
                if (canSumTo[i - 1][sum]) {
                    canSumTo[i][sum] = true;
                } else if (sum - curItemSize >= 0 && sum - curItemSize <= capacity && // 别忘了检查越界 ！！！
                    canSumTo[i - 1][sum - curItemSize]) {
                    canSumTo[i][sum] = true;
                }
            }
        }
        
        for (int sum = capacity; sum >= 1; sum--) {
            if (canSumTo[n][sum]) {
                return sum;
            }
        }
        return 0; // actually we will never reach here
    }
}
