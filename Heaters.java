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
The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.

// Ref: https://discuss.leetcode.com/topic/71429/java-easy-solution/3
/* Initially it is necessary to sort both houses and heaters by their coordinates. 
Then assign two pointers, one for houses and another for heaters. Then start traversing the houses. 
If the ith house is located between j-1th heater and jth heater, 
then take distance to the closest one and check whether it is the maximum radius found so far. 
The corner cases are when a house is located before the 1st heater, and when a house is located after the last heater. 
At the corner case position, there are only distance to consider. That's it. I think code will clarify the idea more. */
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
            if(j+1 < heaters.length && Math.abs(heaters[j] - houses[i]) >= Math.abs(heaters[j+1] - houses[i]))
            {
                j++;
                continue;
            }
            radius = Math.max(radius, Math.abs(heaters[j] - houses[i])); 
            i++;
        }
        return radius;
    }
}
