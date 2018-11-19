/* You can make unlimited transactions, but each sell or buy has a fixed cost.
For example:
Given the prices [1, 3, 6, 5, 8], cost = 1, the max profit is 5. To see this, 
you can buy at 1 and sell at 8 to get a profit of 7, minus the transaction cost 2, and the final result is 5. */


// 由于状态 i 仅仅与状态 i-1 有关，所以不必整一个长度n的数组出来存状态 ！！
// 只要 O(1) 的空间复杂度即可 ！！时间复杂度是 O(n)
public class Solution {
    
    public int maxProfit(int[] prices, int buyCost, int sellCost) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        int hold = 0, empty = 0;
        int holdPrev = 0, emptyPrev = 0;
        
        if (prices.length >= 1) {
            // 根据hold的定义，当日结束时必须持有股票，所以对于第一天来说，当日结束必须持股意味着第一天必须买，
            // 所以 profit = 第一天的股价 乘以 -1，再减去 buyCost
            hold = prices[0] * -1 - buyCost;
            // 根据empty的定义，当日结束时必须空仓，另外没有买就无法卖，
            // 所以对于第一天来说，空仓意味着第一天必须什么也没做，所以 proft = 0
            empty = 0;
        }
        
        if (prices.length >= 2) {
            // 从第二天开始，一直到世界的尽头
            for (int i = 1; i < prices.length; i++) {
                holdPrev = hold;
                emptyPrev = empty;
            
                hold = Math.max(holdPrev, emptyPrevPrev - prices[i] - buyCost);
                empty = Math.max(emptyPrev, holdPrev + prices[i] - sellCost);
            }
        }
        
        return empty;
    }
}
