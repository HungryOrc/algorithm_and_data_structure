/* Given a list of integers, which denote a permutation. Find the previous permutation in ascending order.
The list may contains duplicate integers.

Example
For [1,3,2,3], the previous permutation is [1,2,3,3]
For [1,2,3,4], the previous permutation is [4,3,2,1] */

// 我自己的方法。灵感来源于类似的题目：Next Permutation of Numbers，
// 具体的思路解释，见我的GitHub上那一题的注释
// 下面的函数看起来很多，其实思路很简明易懂
public class Solution {

    public ArrayList<Integer> previousPermuation(ArrayList<Integer> nums) {
        if (nums == null || nums.size() <= 1) {
            return nums;
        }

        int targetIndex = findTheLastNumThatIsBiggerThanItsNextNum(nums);
        int swapIndex = findTheLastNumThatIsSmallerThanTheTargetNum(nums, targetIndex);

        swap(nums, targetIndex, swapIndex);

        reverseListTillTheEnd(nums, targetIndex + 1);

        return nums;
        }
    
    private int findTheLastNumThatIsBiggerThanItsNextNum(ArrayList<Integer> nums) {
        int latterIndex = nums.size() - 1;
        int formerIndex = nums.size() - 2;
        
        while (formerIndex >= 0) {
            if (nums.get(formerIndex) > nums.get(latterIndex)) {
                return formerIndex;
            }
            formerIndex --;
            latterIndex --;
        }
        
        return -1;
    }
    
    private int findTheLastNumThatIsSmallerThanTheTargetNum(ArrayList<Integer> nums, int targetIndex) {
        if (targetIndex == -1) {
            return -1;
        }
        
        for (int i = nums.size() - 1; i >= 0; i--) {
            if (nums.get(i) < nums.get(targetIndex)) {
                return i;
            }
        }
        return -1; // 其实永远不会到达这里
    }
    
    private void swap(ArrayList<Integer> nums, int i, int j) {
        if (i == -1) {
            return; // do nothing
        }
        
        int temp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, temp);
    }
    
    private void reverseListTillTheEnd(ArrayList<Integer> nums, int startIndex) {
        for (int i = startIndex, j = nums.size() - 1; i < j; i++, j--) {
            swap(nums, i, j);
        }
    }
    
}
