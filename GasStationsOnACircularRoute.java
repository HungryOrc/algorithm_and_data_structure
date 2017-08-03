/* https://leetcode.com/problems/gas-station/description/

There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
You begin the journey with an empty tank at one of the gas stations.
Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
Note: The solution is guaranteed to be unique. 这就是说：
在这一题里，可能没有答案，那么返回零，然后如果有答案，则题义保证答案只有一个  */


// 非常巧妙的答案 ！！！ 比我能想到的DP还要快速，而且简明很多。时间代价只有 O(n)
// https://discuss.leetcode.com/topic/5088/my-ac-is-o-1-space-o-n-running-time-solution-does-anybody-have-posted-this-solution

public class Solution {
    
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int[] diff = new int[n];
        
        for (int i = 0; i < n; i++) {
            diff[i] = gas[i] - cost[i];
        }
        
        int start = n - 1;
        int end = 0;
        
        int sum = diff[n - 1];
        
        while (start > end) {
            
            if (sum >= 0) {
                sum += diff[end];
                end ++;
            } else {
                start --;
                sum += diff[start];
            }
        }
        
        return sum >= 0 ? start : -1;
    }
}
