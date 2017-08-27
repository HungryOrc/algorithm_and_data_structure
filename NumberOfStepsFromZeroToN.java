// 通过2种操作：要么 +1，要么 乘以2，需要多少步从 0 变到 n。
// n 是一个long型整数。注意它的后面要加 L，比如 n = 22222222222222L 这样是可以的。如果没有 L，编译器会认为这是一个 int 而非 long。

// 思路：从后往前看，从 n 出发，需要多少步能缩小到 0.
// 那么显然，n 如果是偶数，第一步就除以2是最快缩小的方法，如果 n 是奇数，则不可能除以2，只能先 -1 了。以此类推。
public class Solution {

    public static int fromZeroToN(long n) {
    
      // 这里既是 corner case，又是 recursion 的 base case
      // base case 就是 n = 0 的时候
    	if (n <= 0) {
    		return 0;
    	}
    	
    	if (n % 2 == 1) {
    		return fromZeroToN(n - 1) + 1;
    	} else {
    		return fromZeroToN(n / 2) + 1;
    	}
    }
}
