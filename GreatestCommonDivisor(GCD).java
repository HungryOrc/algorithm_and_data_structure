/* 最大公约数

用“辗转相除法”做。当然用“辗转相减法”做也行，就是稍慢一点 
Ref: https://www.kancloud.cn/kancloud/data-structure-and-algorithm-notes/72924

设函数 gcd(a, b) 是a, b的最大公约数，
不妨设a > b, 则有 a = b*p + q, 
那么可知：b 和 q 的最大公约数 gcd(b, q) 既能整除 b, 又能整除 a
如此反复最后得到 gcd(a, b) = gcd(c, 0), 第二个数为0时直接返回 c. 
如果最开始 a < b, 那么 gcd(b, a % b) = gcd(b, a) = gcd(a, b % a).

时间复杂度：对数级别。证明略 */

class Solution {

    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b); // 这样把 b 放前面，a % b 放后面，就实现了a与b的不断反复的辗转！！！
        }
    }

}
