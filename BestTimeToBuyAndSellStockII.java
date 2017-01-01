/* Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. 
You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). 
However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again). */

public class Solution 
{
    // 我的朴素思路。但是速度很慢！回头看看答案里的快速解法！？？？
    // 股票曲线是折线。获得每一个向上的折线段，将它们加在一起，就是答案。具体来说：
    // 如果没有买入，且如果下一天的股价高于今天的，则买入
    // 如果买入了，且如果下一天的股价低于今天的，则卖出
    // 其它的情况，不做任何操作。即安静持仓。或安静空仓
    public int maxProfit(int[] prices) 
    {
        boolean bought = false;
        int boughtPrice = 0;
        int soldPrice = 0;
        int cumulativeProfit = 0;
        
        for (int i = 0; i < prices.length-1; i++)
        {
            if (prices[i+1] > prices[i] && bought==false)
            {
                boughtPrice = prices[i];
                bought = true;
            }
            else if (prices[i+1] < prices[i] && bought==true)
            {
                soldPrice = prices[i];
                cumulativeProfit += (soldPrice - boughtPrice);
                bought = false;
            }
        }
        // 如果最后一天结束时还有买着的股票没卖
        // 就以最后一天的价格卖掉
        if (bought==true)
            cumulativeProfit += (prices[prices.length-1] - boughtPrice);
            
        return cumulativeProfit;
    }
}
