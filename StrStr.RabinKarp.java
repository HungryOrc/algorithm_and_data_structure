/* Implement strStr function in O(n + m) time.
strStr return the first index of the target string in a source string. 
The length of the target string is m and the length of the source string is n.
If target does not exist in source, just return -1. */

// Rabin-Karp 算法的时间复杂度是 O(m + n)，与 KMP 相同，但理解起来比 KMP 简单
// 这个算法还能加深对 HashMap 的理解
// Ref:
// https://en.wikipedia.org/wiki/Rabin%E2%80%93Karp_algorithm
// http://www.jiuzhang.com/video/rabin-karp

class Solution {

    // 10^6, this can be 10^7, 10^8 or whatever
    // 以10的n次方作为base进行mod的话，可以完美地满足：先加减乘除再mod == 先mod再加减乘除
    public int BASE = 1000000; 
    public int HASH_MAGIC_NUMBER = 31; // 惯例如此
    
    public int strStr2(String source, String target) {
        if (source == null || target == null) {
            return -1;
        }
        if (target.length() == 0) {
            return 0;
        }
            
        int m = target.length();
        int n = source.length();
        
        // 算出 31^m，每次去掉最左边的字母的时候要用
        int topPower = 1;
        for (int i = 0; i < m; i++) {
            topPower = (topPower * HASH_MAGIC_NUMBER) % BASE;
            // 每次都执行一次 % BASE，是保证绝对不越int的界
        }
    
        // 计算 target 的 Hash Code
        int targetHashCode = 0;
        for (int i = 0; i < m; i++) {
            targetHashCode = (targetHashCode * HASH_MAGIC_NUMBER + target.charAt(i)) % BASE;
        }
        
        // 逐个检查source里每一段substring的 Hash Code
        int hashCode = 0;
        for (int i = 0; i < n; i++) {
            
            // Step 1
            // "abc" + 'd'
            hashCode = (hashCode * HASH_MAGIC_NUMBER + source.charAt(i)) % BASE;
            if (i <= m - 1) {
                continue;
            }
            
            // Step 2
            // "abcd" - 'a'
            if (i >= m) {
                hashCode = hashCode - (source.charAt(i - m) * topPower) % BASE; 
                
                // 下面这一段特别关键 ！！！
                // -----------------------------------------------------------------------------
                // 以上的减号两端的数都在0到BASE之间，但减出来的结果可能 <0 ！！
                // 那么需要用下面的小trick把hashCode变回成正数
                if (hashCode < 0) {
                    hashCode += BASE;
                }
                // -----------------------------------------------------------------------------
            }
            
            // Step 3
            // double check, in case there was a HashCode Conflict,
            // meaning that different Strings had the same HashCode
            if (hashCode == targetHashCode) {
                // substring的右端是不包含的，所以得 +1
                if (source.substring(i - m + 1, i + 1).equals(target)) {
                    return i - m + 1; // the starting index
                }
            }
        }
        return -1;
    }
}
