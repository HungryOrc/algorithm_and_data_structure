// 给一个 array，找出里面所有的 subarrays，对每一个subarray求和，然后把所有这些和再加在一起，return这个 sum of sums

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
            
            long count = (1 + numPrev) * (1 + numSubs);
            
            result += count * arr[i];
        } 
        
        return result;
    }
}
