/* Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();

Your Solution object will be instantiated and called as such:
    Solution obj = new Solution(nums);
    int[] param_1 = obj.reset();
    int[] param_2 = obj.shuffle();   */
 
// 我的朴素做法。速度还不错
public class Solution {

    int[] initArray;
    int n;
    
    public Solution(int[] nums) {
        this.initArray = nums;
        this.n = nums.length;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return initArray;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle()
    {
        int curSize = this.n;
        
        ArrayList<Integer> numsAL = new ArrayList<>();
        for (int num : initArray)
            numsAL.add(num);
        
        int[] shuffledArray = new int[n];
        int i = 0;
        while (curSize > 0)
        {
            Random rand = new Random();
            // get a random integer from [0, curSize)
            int pickedIndex = rand.nextInt(curSize);
            
            shuffledArray[i] = numsAL.get(pickedIndex);
            numsAL.remove(pickedIndex);
            curSize --;
            i++;
        }
        return shuffledArray;
    }
}
