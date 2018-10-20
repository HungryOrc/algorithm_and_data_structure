// 数组里的元素不重复
// 在一个组合里，每个元素都可以出现任意次
// 在一个组合里，总的元素个数不限
// 在一个组合里，每个元素的出现顺序如果改变，就视为新的组合

/* Given an integer array with all positive numbers and no duplicates, 
find the number of possible combinations that add up to a positive integer target.

Example:
nums = [1, 2, 3]
target = 4
The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
Note that different sequences are counted as different combinations.
注意！！！不同的顺序算是不同的组合！！比如 (1, 1, 2) 和 (1, 2, 1) 算是两种组合！！
Therefore the output is 7.

Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers? */

// Ref: https://discuss.leetcode.com/topic/52302/1ms-java-dp-solution-with-detailed-explanation

/* Imagine we only need one more number to reach target, this number can be any one in the array, right? 
So the # of combinations of target, 
comb[target] = sum(comb[target - nums[i]]), where 0 <= i < nums.length, and target >= nums[i].
In the example given, we can actually find the # of combinations of 4 with the # of combinations of 
3(4 - 1), 2(4- 2) and 1(4 - 3). As a result, comb[4] = comb[4-1] + comb[4-2] + comb[4-3] = comb[3] + comb[2] + comb[1].
Then think about the base case. 
The problem says that target is a positive integer that makes me feel it's unclear to put it in the above way. 
Since target == 0 only happens when in the previous call, target = nums[i], 
we know that this is the only combination in this case, so we return 1.  */

public class Solution 
{
    // 方法1: Swap + Recursion 看起来巧妙其实不巧妙！太慢了！无法通过网评
    // 每当要加入一个新的数的时候，都是把数组 nums 里的每一个数都拿出来试一下，每一个数都有平等的在这一位出场的权力
    public int combinationSum4(int[] nums, int target) 
    {
        // 数组里全是正数，所以target不可能一开始就是0
        // 如果target是0，只可能是运算到后面时，剩余的target sum降到0了
        // 此时也就是一个valid组合被找到了
        if (target == 0)
            return 1;
        
        int result = 0;
        for (int n : nums) {
            if (n <= target) {
                result += combinationSum4(nums, target - n);
            }
        }
        return result;
    }
    
    
    /* For a DP solution, we just need to figure out a way to store the intermediate results, 
    to avoid the same combination sum being calculated many times. 
    We can use an array to save those results, and check if there is already a result before calculation. 
    We can fill the array with -1 to indicate that the result hasn't been calculated yet. 
    0 is not a good choice because it means there is no combination sum for the target. */
    
    // 方法2: Bottom-Up DP 
    public int combinationSum4(int[] nums, int target) 
    {
        int[] comb = new int[target + 1]; // 序号从0到target
        comb[0] = 1;
        
        for (int i = 1; i < comb.length; i++) { // i 模拟从 1 到 target 的各种sum
            for (int j = 0; j < nums.length; j++) { // j 指代数组 nums 里的各个数
                if (i - nums[j] >= 0)
                    comb[i] += comb[i - nums[j]];
            }
        }
        return comb[target];
    }
    
    
    // 方法3: Top-Down DP
    private int[] dp;
    public int combinationSum4(int[] nums, int target) {
        dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        return helper(nums, target);
    }
    private int helper(int[] nums, int target) {
        if (dp[target] != -1) {
            return dp[target];
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += helper(nums, target - nums[i]);
            }
        }
        dp[target] = res;
        return res;
    }
    
}
