/* 给出一个正整数奇数，比如 5，打印出：
  _ _ *
  _ * * *
  * * * * *
其中 _ 表示空格。这样打印出来三角形或者菱形（钻石形）都是同理的。要求最好用 Recursion 来做  */

// 方法1：Recursion
public class Solution {

	public void printDiamond(int odd) {
		printLine(1, odd);
	}
	
	private void printLine(int numOfStars, int odd) {
		if (numOfStars > odd) {
			return;
		}
		
		int numOfSpaces = (odd - numOfStars) / 2;
		for (int i = 1; i <= numOfSpaces; i++) {
			System.out.print(" ");
		}
		for (int j = 1; j <= numOfStars; j++) {
			System.out.print("*");
		}
		System.out.println();
		
		printLine(numOfStars + 2, odd);
	}
}

// 方法2：Iteration
public class Solution {

	public void printDiamond(int odd) {
		int radius = (odd - 1) / 2;
		
		for (int i = 0; i <= radius; i++) {
			for (int j = 1; j <= radius - i; j++)
				System.out.print(" ");
			for (int k = 1; k <= i * 2 + 1; k++)
				System.out.print("*");
			System.out.println();
		}
	}
}
