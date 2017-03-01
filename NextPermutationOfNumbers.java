/* Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1 */

/* 答案：https://discuss.leetcode.com/topic/14124/sharing-my-clean-and-easy-understand-java-code-with-explanation
在当前序列中，从尾端往前寻找两个相邻元素，前一个记为first，后一个记为second，并且满足first 小于 second。
然后再从尾端寻找另一个元素number，如果满足first 小于number，即将第first个元素与number元素对调，
并将second元素之后（包括second）的所有元素颠倒排序，即求出下一个序列

example:
6，3，4，9，8，7，1
此时 first ＝ 4，second = 9
从尾巴到前找到第一个大于first的数字，就是7
交换4和7，即上面的swap函数，此时序列变成6，3，7，9，8，4，1
再将second＝9以及以后的序列重新排序，让其从小到大排序，使得整体最小，即reverse一下（因为此时肯定是递减序列）
得到最终的结果：6，3，7，1，4，8，9
同样按上述做法，可得：9, 8, 7, 6, 4, 3, 1 的下一个 permutation 是 1, 3, 4, 6, 7, 8, 9. */

public class Solution {
    
    // 答案看着虽然长，其实思路挺简单，只是切分成了多个函数
    public void nextPermutation(int[] nums) {
        
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        int indexOfTarget = findTheLastNumThatIsSmallerThanItsNextNum(nums);
        int indexOfSwap = findTheLastNumThatIsBiggerThanTheTarget(nums, indexOfTarget);
        
        swap(nums, indexOfTarget, indexOfSwap);
        reverseSubArrayTillTheEnd(nums, indexOfTarget + 1);
    }
    
    private int findTheLastNumThatIsSmallerThanItsNextNum(int[] nums) {
        int len = nums.length;
        int indexOfTarget = len - 2; 
        int indexOfNext = len - 1;
        
        while(indexOfTarget >= 0) {
            if (nums[indexOfTarget] < nums[indexOfNext]) {
                return indexOfTarget;
            }
            indexOfTarget --;
            indexOfNext --;
        }
        return -1; // 一直找到数组的开头，都没有符合要求的target。说明数组是从头到尾递减的
    }
    
    private int findTheLastNumThatIsBiggerThanTheTarget(int[] nums, int indexOfTarget) {
        if (indexOfTarget == -1) { // 如果数组从头到尾递减，则什么也不做
            return -1;
        }
        
        int target = nums[indexOfTarget];
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > target) {
                return i;
            }
        }
        
        return -1; // 其实我们无论任何情况下都不会到这里
    }
    
    private void swap(int[] nums, int i1, int i2) {
        if (i1 == -1) { // 如果数组从头到尾递减，则什么也不做
            return;
        }
        
        int temp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = temp;
    }
    
    private void reverseSubArrayTillTheEnd(int[] nums, int startIndex) {
        for (int i = startIndex, j = nums.length - 1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
    
}
