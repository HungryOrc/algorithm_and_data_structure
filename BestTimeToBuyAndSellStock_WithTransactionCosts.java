/* Say you have an array (int[] prices) for which the i-th element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
(ie, buy one and sell one share of the stock multiple times) with the following restictions:
(1) You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
(2) After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:
prices = [1, 2, 3, 0, 2], maxProfit = 3
transactions = [buy, sell, cooldown, buy, sell] 
明确几点：
(1) 这一题只考察最后的累计 profit，不管本金多少。所以这一题在数学上来说，本金是 0，还是 1000亿，都没区别
    然后只交易一种股票。最多持有一手，最少持有零手。
(2) 持仓时，所持股票的当前价格不算在资产里，当然也就不算在盈利里；持仓股票是以买入时的价格作为一种支出而存在的，
    买入以后，这个支出就不变了，不去管这股票当前价格是怎么变化的。只有再卖出时，才兑现盈利/亏损。
    比如，一个股票在5元买入，则 profit 记为 -5。卖出以前，无论股价跳到 3 还是 6，当前profit都是 -5 不变。
    最后卖的时候，如果 7 卖，则profit = 2，如果 2 卖，则 profit = -3。
(3) 一天之内重复 买m次 再 卖m次 是没有意义的。因为股价在一天之内认为不变。买卖交易也没有成本。
    所以一天之内，只有一次操作是有意义的：
    * 今天开始时有持仓，今天结束时没有持仓，即今天卖了
    * 今天开始时没有持仓，今天结束时有持仓，即今天买了
    * 今天什么也没做。今天结束时的持仓状况与今天开始时的相同 */


/* 思路：这题明显应该用 DP 做。这题的关键在于，今天卖的话，后天才能再买
States: 分成两种状态：
在第i天结束时 有持仓，截至此时的 最大累计profit 记为：hold[i]
在第i天结束时 无持仓，截至此时的 最大累计profit 记为：empty[i]
Transition Functions:
hold[i] = Math.max(hold[i - 1], empty[i - 2] - prices[i])
empty[i] = Math.max(empty[i - 1], hold[i - 1] + prices[i])
Return:
empty[prices.length - 1]
因为股票卖掉才能让手里的 本次profit 兑现，实现最大的 累计profit */


// 我的方法。符合直觉。很便于理解，所以显得稍长

// 关键：因为递推公式里，有一项 empty[i - 2]，所以只有到了第三天，才能让这两个递推公式正常滴工作 ！！
// 所以对于第一天和第二天，我们必须先进行独特的处理，之后才是搭便车 ！！

// 另外，对于这一题，由于状态 i 仅仅与状态 i-1 及 状态 i-2 有关，所以不必整一个长度n的数组出来存状态 ！！
// 只要 O(1) 的空间复杂度即可 ！！时间复杂度是 O(n)
public class Solution {
    
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        int hold = 0, empty = 0;
        int holdPrev = 0, emptyPrev = 0, emptyPrevPrev = 0;
        
        if (prices.length >= 1) {
            // 根据hold的定义，当日结束时必须持有股票，所以对于第一天来说，当日结束必须持股意味着第一天必须买，
            // 所以 profit = 第一天的股价 乘以 -1
            hold = prices[0] * -1;
            // 根据empty的定义，当日结束时必须空仓，另外没有买就无法卖，
            // 所以对于第一天来说，空仓意味着第一天必须什么也没做，所以 proft = 0
            empty = 0;
        }
        
        if (prices.length >= 2) {
            holdPrev = hold;
            emptyPrev = empty;
            // 第二天结束时持有股票，这种情况下的 max profit，是在两种情况中取max：要么第一天买了，要么第二天买了
            hold = Math.max(prices[1] * -1, prices[0] * -1);
            // 第二天结束时空仓，这种情况下的 max profit，是在两种情况中取max：要么这两天都啥也没做，要么第一天买了第二天卖了
            empty = Math.max(0, prices[1] - prices[0]);
        }
        
        if (prices.length >= 3) {
            // 终于来到了第三天！终于可以从此开始过上递推公式的幸福生活了！！直到世界的尽头！！
            for (int i = 2; i < prices.length; i++) {
                holdPrev = hold;
                emptyPrevPrev = emptyPrev;
                emptyPrev = empty;
            
                hold = Math.max(holdPrev, emptyPrevPrev - prices[i]);
                empty = Math.max(emptyPrev, holdPrev + prices[i]);
            }
        }
        
        return empty;
    }
}
