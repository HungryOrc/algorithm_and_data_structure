// Given an integer n, return the number of trailing zeroes in n!.
// Note: Your solution should be in logarithmic time complexity.

// 数一个数的结尾有多少个零。最简单的方法当然是不断地 mod 10. 但是这一题要求log时间复杂度！！ 这样就要用二分法来做 ！！！
// 很巧妙！ 我没想到！

public class Solution {

	public int findTheFirstZeroInTheTrailingZeroes(int input) {
		// int 的范围是 -2,147,483,648 到 2,147,483,647，所以最右端最多有 9个零。哦也 ！
		// 0 这个参数表示在最右端最少有0个零
		return find(input, 0, 9);
	}
	
	private int find(int input, int minNumberOfZeroes, int maxNumberOfZeroes) {
		
		// 用 +1 的方式，是为了避免死循环 ！！！
		// 如果不+1的话，比如min=1且max=2的时候mid=1，如果这数正好有且只有1个0的话，就会永远循环下去
		while (minNumberOfZeroes + 1 < maxNumberOfZeroes) {
			int mid = minNumberOfZeroes + (maxNumberOfZeroes - minNumberOfZeroes) / 2;
			int divident = (int)Math.pow(10, mid);
			
			if (input % divident == 0) {
				minNumberOfZeroes = mid;
			} else {
				maxNumberOfZeroes = mid - 1;
			}
		}
		
		// 到了这里，一定能保证，min + 1 == max
		if (input % (int)Math.pow(10, maxNumberOfZeroes) == 0) {
			return maxNumberOfZeroes;
		} else { // input % (int)Math.pow(10, minNumberOfZeroes) == 0
			return minNumberOfZeroes;
		}
	}
}
