/* You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, 
the only constraint stopping you from robbing each of them is that 
adjacent houses have security system connected and it will automatically contact the police 
if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police. */

// Dynamic Programming 动态规划
// Ref: https://leetcode.com/articles/house-robber/

/* A natural way to approach this problem is to work on the simplest case first.
Let us denote that:
f(k) = Largest amount that you can rob from the first k houses.
Ai = Amount of money at the ith house.

Let us look at the case n = 1, clearly f(1) = A1.
For n = 2, f(2) = max(A1, A2).

For n = 3, you have basically the following two options:
(i) Rob the third house, and add its amount to the first house's amount.
(ii) Do not rob the third house, and stick with the maximum amount of the first two houses.
Clearly, you would want to choose the larger of the two options at each step.
So:
f(3) = max(f(1)+A3, f(2))

Therefore, we could summarize the formula as following:
f(k) = max(f(k – 2) + Ak, f(k – 1))

We choose the base case as f(–1) = f(0) = 0, which will greatly simplify our code as you can see.
The answer will be calculated as f(n):
*/

public class Solution {
    
    public int rob(int[] nums) {
        
        int prevprevMax = 0;
        int prevMax = 0;
        int curMax = 0;
        
        for (int curNum : nums)
        {
            curMax = Math.max(prevprevMax + curNum, prevMax);
            prevprevMax = prevMax;
            prevMax = curMax;
        }
        return curMax;
    }
}

另法：先写下面这个，再写上面那个更高级的空间的
dp[0] = nums[0];
dp[1] = Math.max(nums[0], nums[1]);
dp[2]...
