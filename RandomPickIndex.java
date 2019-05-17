/* Given an array of integers with possible duplicates, randomly output the index of a given target number. 
You can assume that the given target number must exist in the array.
Note: The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:
int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);
// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);
// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);

* Your Solution object will be instantiated and called as such:
* Solution obj = new Solution(nums);
* int param_1 = obj.pick(target); */

public class Solution {
    
    int[] nums;
    Random rand;
    
    public Solution(int[] nums) {
        this.nums = nums;
        this.rand = new Random();
    }
    
    public int pick(int target) {
        int chosenIndex = -1;
        int numOfOccurrencesOfTarget = 0;
        
        for (int i = 0; i < this.nums.length; i++) {
            
            if (nums[i] == target) {
                numOfOccurrencesOfTarget ++;
                
                // Random.nextInt(max) method returns a pseudorandom, uniformly distributed int value 
                // between 0 (inclusive) and the specified max value (exclusive)
                /* 特别注意！！！
                   下面的if语句里的==0，是为了保证if的结果为true的概率为1/n ！！！
                   只要是均匀的1/n，那么后面的chosenIndex=i的赋值就是公平的，符合题意的！！！
                   所以回过头来看，if里不一定要是==0，任何在可取范围内的值都可以，比如可以写为==numOfOccerencesOfTarget -1
                */
                if (rand.nextInt(numOfOccurrencesOfTarget) == 0) {
                    chosenIndex = i;
                }
            }
        }
        return chosenIndex;
    }
}

注意！每次pick都要耗时O(n) ！
