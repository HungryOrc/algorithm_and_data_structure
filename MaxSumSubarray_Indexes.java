// 返回最大sum的subarray的起始和终止indexes

// DP
public class Solution {
	  
	  public int[] largestSum(int[] nums) {
	    if (nums == null || nums.length == 0) {
	      return new int[2];
	    }
	    
	    int preMaxSum = nums[0];
	    int curMaxSum = Integer.MIN_VALUE;
	    
	    int maxSum = nums[0];
	    
	    int left = 0, right = 0;
	    int finalLeft = 0, finalRight = 0;
	    
	    for (int i = 1; i < nums.length; i++) {
	      if (preMaxSum >= 0) {
	        curMaxSum = preMaxSum + nums[i];
	        right = i;
	      } else {
	        curMaxSum = nums[i];
	        left = i;
	        right = i;
	      }
	      
	      if (curMaxSum > maxSum) {
	    	  maxSum = curMaxSum;
	    	  finalLeft = left;
	    	  finalRight = right;
	      }
	    
	      preMaxSum = curMaxSum;
	    }
	    
	    return new int[]{finalLeft, finalRight};
	  }
}
