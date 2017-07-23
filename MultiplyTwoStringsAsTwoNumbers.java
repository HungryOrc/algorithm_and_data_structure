/* 两个String，s1和s2，代表两个非负整数。要求它们的乘积，也用一个String来表示。
这两个String可能很长，超过int，long 甚至 double 的范围，就是说认为它们用任何java的内置数据类型都无法表示。

如果存在负数，也很好处理，就是看最后要不要加一个-在最前头就可以了。下面的思路同样可以处理两个小数（都以String的形式出现）的乘积。

思路：
把两个String里的每一个char分别两两相乘。
比如s1的个位和s2的个位，乘出来一个数，它的最小位落在个位上，如果>=10则它的最大位落在十位上，它最多只可能有两位；
s1的个位和s2的十位，乘积的最小位落在十位上，如果>=10则它的最大位落在百位上......
......
s1的百位和s2的千位，乘积的最小位落在十万位上，如果>=10则它的最大位落在百万位上......
......
......

要注意的是，每次往结果里两个char的乘积，首先这个乘积可能是两位数！！ 
第二这个乘积要先加上最终结果在这一位上当前已经有过的值，即前面处理过的那些char pairs的乘积在这一位上的投影 ！！
第三这个乘积加上来以后，可能会造成连锁的进位 ！！！

这一题做的时候，先把s1和s2反转一下，以便于个位在index 0 处，十位在 index 1 处，这样后面确定每两个char的product所在的位比较方便。
处理完以后，再把最终结果反转一下。
这么做完全是为了方便，不是必须的 ！！

下面的代码较长，其实思路比较简明，就是上面的意思 */


public class Solution {

	public String multiplyTwoStrings(String s1, String s2) {
		if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
			return "0";
		}
		
		int n1 = s1.length();
		int n2 = s2.length();
		int[] intArray1 = new int[n1];
		int[] intArray2 = new int[n2];
		for (int i = 0; i < n1; i++) {
			intArray1[i] = s1.charAt(i) - '0';
		}
		for (int i = 0; i < n2; i++) {
			intArray2[i] = s2.charAt(i) - '0'; 
		}
		
		reverse(intArray1);
		reverse(intArray2);
	
		int n = n1 + n2;
		int[] intArray3 = new int[n];
		
		for (int i = 0; i < n1; i++) {
			for (int j = 0; j < n2; j++) {
				int a1 = intArray1[i];
				int a2 = intArray2[j];
				
				int product = a1 * a2;
				int digit = i + j;
				
				insert(intArray3, digit, product);
			}
		}
		
		reverse(intArray3);
		
		StringBuilder sb = new StringBuilder();
		int i = 0;
		if (intArray3[0] == 0) {
			i = 1;
		}
		for (; i < n; i++) {
			sb.append(intArray3[i]);
		}
		return sb.toString();
	}

	private void reverse(int[] array) {
		int left = 0, right = array.length - 1;
		while (left < right) {
			int tmp = array[left];
			array[left] = array[right];
			array[right] = tmp;
			left ++;
			right --;
		}
	}
	
	// 注意 ！ 这里需要做recursion ！ 不是一次性地加进去就完了 ！ 可能会连锁进位 ！！！
	private void insert(int[] intArray, int digit, int product) {
		int newValue = intArray[digit] + product;
		intArray[digit] = newValue % 10;
		if (newValue / 10 >= 1) {
			insert(intArray, digit + 1, newValue / 10);
		}
	}
}
