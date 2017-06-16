/* Given a list of integers representing the lengths of urls, find the 95 percentile of all lengths 
(95% of the urls have lengths <= returned length).
Assumptions:
The maximum length of valid url is 4096
The list is not null and is not empty and does not contain null

Examples:
[1, 2, 3, ..., 95, 96, 97, 98, 99, 100], 95 percentile of all lengths is 95. */

// 思路：类似于 bucket sort 的思想
  
public class Solution {
  
  public int percentile95(List<Integer> lengths) {
    int n = lengths.size();
    
    int[] records = new int[4097]; // the range of lengths is: [0, 4096]
    
    for (int len : lengths) {
      records[len] ++;
    }
    
    double bar = n * 0.05;
    int cumulative = 0;
    
    for (int i = 4096; i >= 0; i--) {
      cumulative += records[i];
      if (cumulative > bar) { // 看题目里的例子，1到100，返回95而非返回96. 所以这里要用 >，而非 >=
        return i;
      }
    }
    
    return -1; // it's impossible to reach here
  } 
}
