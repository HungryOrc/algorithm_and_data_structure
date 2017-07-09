/* 给一个int数组，其中的每一个元素代表一块pizza的size。我和另一个人都从这一系列int的左端或右端拿pizza，轮流拿，一次拿一块。
我可以自由地在最左端或者最右端拿一块。另一个人必须拿剩下的部分的左右端点中更大的那一块。
那么问，如果我先拿，我是否有可能赢？ 
假设：int数组里没有重复元素。即任何subarray都不会出现它的左右端的元素相等的情况。   */


/* 思路：自然是用 DP 做
二维DP数组，每一个元素 dp[i][j] 的意思是，在整形数组里，从index为i的元素开始，到index为j的元素为止，这一段subarray上，
我先手，我所可能拿到的最大的 sum of pizzas。
所以，在这个矩阵里，只有 j >= i 的元素是有意义的。

Base Case:
一块pizza：dp[i][i] = array[i]
两块pizza：dp[i][i + 1] = max(array[i], array[i + 1])

Induction Rule:

Case 1: If I take the left most pizza of the current remaining section:
    theMostICanGet_IfIPickLeftFirst = array[left] + 
        dp[left + 1][right - 1];  // if array[right] > array[left + 1], namely the other man will pick array[right]
        // or
        dp[left + 2][right];  // if array[left + 1] > array[right], namely the other man will pick array[left + 1]

Case 1: If I take the right most pizza of the current remaining section:
    theMostICanGet_IfIPickRightFirst = array[right] + 
        dp[left][right - 2];  // if array[right - 1] > array[left], namely the other man will pick array[right - 1]
        // or
        dp[left + 1][right - 1];  // if array[left] > array[right - 1], namely the other man will pick array[left]

According to the 2 cases above, we have:
dp[left][right] = Math.max(theMostICanGet_IfIPickRightFirst, theMostICanGet_IfIPickLeftFirst);

时间：O(n^2)   */

public class Solution {
	  
	public boolean cutPizza(int[] nums) {
		if (nums == null || nums.length == 0) {
			return false;
		}
	    
		int n = nums.length;
		
		int[][] dp = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			dp[i][i] = nums[i];
		}
		
		for (int i = 0; i < n - 1; i++) {
			dp[i][i + 1] = Math.max(nums[i], nums[i + 1]);
		}
		
		// 这一类题的巧妙之处，不同于其他DP之处在此 ！！！
    // 因为我们要研究的是从短段到长段，没有短为基础，长则为无本之木，所以我们的双层for-loop的顺序，是
    // 外层循环：目前这一步要研究的sections系列的长度，从3,4,5... 到最大的可能长度 ！！！
    // 内层循环：本次loop的起始位置index
    // ！！！
		for (int remainPieces = 2; remainPieces < n; remainPieces++) {
			for (int startIndex = 0; startIndex < n - 2; startIndex++) {
				
				int endIndex = startIndex + remainPieces - 1;
				if (endIndex < n) { // 别忘了判断这个 ！
				
					int maxSum_PickLeft = nums[startIndex] + 
						(nums[startIndex + 1] > nums[endIndex] 
							? dp[startIndex + 2][endIndex] : dp[startIndex + 1][endIndex - 1]);
					
					int maxSum_PickRight = nums[endIndex] + 
							(nums[endIndex - 1] > nums[startIndex] 
							? dp[startIndex][endIndex - 2] : dp[startIndex + 1][endIndex - 1]);
					
					dp[startIndex][endIndex] = Math.max(maxSum_PickLeft, maxSum_PickRight);
				}
			}
		}
		
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		
		return dp[0][n - 1] > sum / 2; 
	}
}
