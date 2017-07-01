/* Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) 
there are such that A[i] + B[j] + C[k] + D[l] is zero.
To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. 
All integers are in the range of -2^28 to 2^28 - 1 and the result is guaranteed to be at most 2^31 - 1.

Example:
Input:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]
Output:
2

Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0  */

public class Solution 
{
    // 如果只做 D 的HashMap，运行时间就是 O(n^3)
    // 如果如下所示做 C+D 的HashMap，则运行时间就大幅缩短到 O(n^2) ！！
    // 另外，
    // 最后一步直接 counts += mapCD.getOrDefault(diff,0)，而非先判断是否 containsKey(diff)
    // 这样也能明显地提高速度！
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) 
    {
        HashMap<Integer,Integer> mapCD = new HashMap<>();
        for (int c : C)
        {
            for (int d : D)
                mapCD.put(c+d, mapCD.getOrDefault(c+d,0)+1);
        }
        
        int counts = 0;
        for (int a : A)
        {
            for (int b : B)
            {
                int diff = 0 - a - b;
                counts += mapCD.getOrDefault(diff,0);
            }
        }
        return counts;
        
    }
}
