/* Design a standard heater with fixed warm radius to warm all the houses.
Now, you are given positions of houses and heaters on a horizontal line, 
find out minimum radius of heaters so that all houses could be covered by those heaters.
So, your input will be the positions of houses and heaters seperately, 
and your expected output will be the minimum radius standard of heaters.

Note:
Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
As long as a house is in the heaters' warm radius range, it can be warmed.
All the heaters follow your radius standard and the warm radius will the same.

Example 1:
Input: [1,2,3],[2]. Output: 1
The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
Example 2:
Input: [1,2,3,4],[1,4]. Output: 1
The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed. */

// Ref: https://discuss.leetcode.com/topic/71429/java-easy-solution/3
// Runtime: O(m+n)
// 注意！！这其实是一个线性过程！！不是 m*n 的问题！！
// 如果第 i 个房子的最近暖气是第 j 个暖气，那么，第 i+1 个房子的最近暖气只可能是 j 或者 j+1, j+2...，不可能是 j 以前的暖气！！！
public class Solution
{
    public int findRadius(int[] houses, int[] heaters) {
       
        if(houses == null || houses.length == 0)
            return 0;  
        
        Arrays.sort(houses);
        Arrays.sort(heaters);
        
        int minRadius = 0;
        int i = 0;
        int j = 0;
        
        while(i < houses.length)
        {
            while (j+1 < heaters.length && (Math.abs(heaters[j+1] - houses[i]) <= Math.abs(heaters[j] - houses[i]) ))
                j++;
            
            radius = Math.max(minRadius, Math.abs(heaters[j] - houses[i])); 
            i++;
        }
        return minRadius;
    }
}
