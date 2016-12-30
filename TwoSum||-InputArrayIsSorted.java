/* Given an array of integers that is already sorted in ascending order, 
find two numbers such that they add up to a specific target number.
The function twoSum should return indices of the two numbers such that they add up to the target, 
where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2 */

public class Solution 
{
    public int[] twoSum(int[] numbers, int target) 
    {
        int former = 0;
        int latter = numbers.length - 1;
        int[] result = new int[2];
        
        while (former < latter)
        {
            if (numbers[former] + numbers[latter] == target)
            {
                result[0] = former + 1;
                result[1] = latter + 1;
                return result;
            }
            else if (numbers[former] + numbers[latter] < target)
                former ++;
            else // if (numbers[former] + numbers[latter] > target)
                latter --;
        }
        
        // if the search failed
        result[0] = result[1] = -1;
        return result;
    }
}
