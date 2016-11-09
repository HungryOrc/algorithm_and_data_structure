/* Say you have an array for which the ith element is the price of a given stock on day i.
If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
design an algorithm to find the maximum profit.

Example 1:
Input: [7, 1, 5, 3, 6, 4]; Output: 5
max difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
Example 2:
Input: [7, 6, 4, 3, 1]; Output: 0
In this case, no transaction is done, i.e. max profit = 0.
*/

public class Solution {
    
    // 一次过的方法，Time: O(n)
    // Ref: https://leetcode.com/articles/best-time-buy-and-sell-stock/
    // 从左往右一个一个看这些数。留下当前所经过的最低的数optimalBuyPrice，记录它后面的所有数和它的差里最大的maxProfit。
    // 如果出现比现在记录的最低数更低的数，则更新这个最低数。但不消除maxProfit，而是留着，继续与新的optimalBuyPrice之后的各个profits比大小
    public int maxProfit(int[] prices) {
        
        int optimalBuyPrice = Integer.MAX_VALUE; // 注意这个最大的数的表示方法！！
        int maxProfit = 0;
        
        for (int i = 0; i < prices.length; i++)
        {
            if (prices[i] < optimalBuyPrice)
                optimalBuyPrice = prices[i];
            else if (prices[i] - optimalBuyPrice > maxProfit)
                maxProfit = prices[i] - optimalBuyPrice;
        }
        return maxProfit;
    }
    
    
}
