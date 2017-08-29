// 给一个 array，找出里面所有的 subarrays，对每一个subarray求和，然后把所有这些和再加在一起，return这个 sum of sums

// 思路：看这个array里，每个字母出现了多少次。
// 对于每个字母，假设它前面有 p 个字母，不含它自己；它后面有 q 个字母，不含它自己，
// 那么一共有 (p + 1) 种可能的开头的subarray会包含它，一共有 (q + 1) 种可能的结尾的subarray会包含它，
// 综合起来看，一共有 (p + 1) * (q + 1) 种subarray会包含它，
// 也就是说，它在最后的 sum of sums 里面，会出现 (p + 1) * (q + 1) 次

// 要求 return 的是 long ！
public class Solution {

    public long subarraySum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0L;
        }
        
        int n = arr.length;
        long result = 0L;
        
        for (int i = 0; i < n; i++) {
            long numPrev = i;
            long numSubs = n - 1 - i;
            
            // 注意 ！ 这里要设为 long ！ 避免任何可能的超限 ！
            long count = (1 + numPrev) * (1 + numSubs);
            
            result += count * arr[i];
        } 
        
        return result;
    }
}
