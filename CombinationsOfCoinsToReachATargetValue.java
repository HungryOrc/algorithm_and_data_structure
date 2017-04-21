/* Given a number of different denominations（面值） of coins (e.g., 1 cent, 5 cents, 10 cents, 25 cents), 
get all the possible ways to pay a target number of cents.

Arguments:
* coins - an array of positive integers representing the different denominations of coins, 
there are no duplicate numbers and the numbers are sorted by descending order, eg. {25, 10, 5, 2, 1}
* target - a non-negative integer representing the target number of cents, eg. 99

Assumptions:
* coins is not null and is not empty, all the numbers in coins are positive
* target >= 0
* You have infinite number of coins for each of the denominations, you can pick any number of the coins.

Return:
* a list of ways of combinations of coins to sum up to be target.
* each way of combinations is represented by list of integer, 
the number at each index means the number of coins used for the denomination at corresponding index.

Examples:
coins = {2, 1}, target = 4, the return should be
[
  [0, 4],   (4 cents can be conducted by 0 * 2 cents + 4 * 1 cents)
  [1, 2],   (4 cents can be conducted by 1 * 2 cents + 2 * 1 cents)
  [2, 0]    (4 cents can be conducted by 2 * 2 cents + 0 * 1 cents)
] */

public class Solution {
  
  // 中心思想：不是思考下一个coin可以选取面值为几的coin，
  // 而是更高层次地决定：在当前这一个组合里，当前面值的coin可以存在几个，从最少的存在个数（即0）到最大的存在个数都顾及到，
  // 然后考虑下一个面值的coin。
  // 考虑到最后一个面值的coin的时候，先要看当前的剩余总额能否整除当前面值，
  // 如果不能，就放弃本次组合。如果能，就直接做除法，结束本次的组合
  //
  // 注意：数组 coins 里面，列的是所有的币种面值。这些面值不需要排序，无论是从大到小还是从小到大。乱序即可。不影响本代码的执行
  
  public List<List<Integer>> combinations(int target, int[] coins) {
    List<List<Integer>> result = new ArrayList<>();
    if (coins == null || coins.length == 0) {
      return result;
    }
    
    ArrayList<Integer> curCombination = new ArrayList<>(); // 本次的组合。但其实它依次客串了所有的组合
    
    dfs_NumberOfCoinsForEachKind(coins, 0, curCombination, target, result);
    return result;
  }
  
  private void dfs_NumberOfCoinsForEachKind(int[] coins, int curKindOfCoin,
      ArrayList<Integer> curCombination, int remainTarget, List<List<Integer>> result) {
   
    int curCoinDenomi = coins[curKindOfCoin]; // 当前币种的币值
   
    // 如果当前就是最后一个币种了
    if (curKindOfCoin == coins.length - 1) {
      // 看残余的目标总值是否能整除当前的币种面值，如果不能，就什么也不做，return
      if (remainTarget % curCoinDenomi == 0) {
        // 如果能整除，就做除法，出结果。结束本次组合。其他的组合还要继续
        
        curCombination.add(remainTarget / curCoinDenomi);
        // 注意 ！！！虽然再下一步会复原，但这里add的还是必须要 new 一个 list ！！！
        result.add(new ArrayList(curCombination)); 
        curCombination.remove(curCombination.size() - 1); // 复原
      }
      return;
    }
    
    // 如果还没到最后一个币种。那就要考虑当前币种在当前组合里，所有可能被允许的出现次数，
    // 即最小出现 0 次，最多出现 remainTarget / curCoinDenomi 次
    for (int i = 0; i <= remainTarget / curCoinDenomi; i++) {
      
      curCombination.add(i);
      dfs_NumberOfCoinsForEachKind(coins, curKindOfCoin + 1, curCombination,
          remainTarget - curCoinDenomi * i, result);
      curCombination.remove(curCombination.size() - 1);
    }
  }
}
