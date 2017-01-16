/* Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. 
The number of elements initialized in nums1 and nums2 are m and n respectively. */

public class Solution {
    
    // Ref: https://discuss.leetcode.com/topic/10257/3-line-java-solution
    // 非常利落干净！
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        
        while (i >= 0 && j >= 0)
        {
            if (nums1[i] >= nums2[j])
            {
                nums1[k] = nums1[i];
                i --;
            }
            else
            {
                nums1[k] = nums2[j];
                j --;
            }
            k --;
        }
        
        while (j >= 0) // && i < 0
        {
            nums1[k] = nums2[j];
            j --;
            k --;
        }
        
        // while (i >= 0 && j < 0), do nothing
    }
    
    
    // 我自己的二比一点的办法
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        
        // 把所有的nums1里的元素往后依次挪n位，给之后的merge腾位置
        // 选取被挪的元素时，要从后往前！不然就覆盖了！
        for (int i = m-1; i >= 0; i--)
            nums1[i+n] = nums1[i];
        
        int index1 = n;
        int index2 = 0;
        int curIndex = 0;
        
        while (index1 < m+n && index2 < n)
        {
            if (nums1[index1] <= nums2[index2])
            {
                nums1[curIndex] = nums1[index1];
                index1 ++;
            }
            else
            {
                nums1[curIndex] = nums2[index2];
                index2 ++;
            }
            curIndex++;
        }
        
        // 如果nums1数组里的所有元素都填到新的数组里去了，但是nums2的可能还没填完
        // 选取被挪的元素时，要从后往前！不然就覆盖了！
        if (index1 == m+n)
        {
            for (int i = n-1; i >= index2; i--)
                nums1[m + i] = nums2[i];
        }
        // 如果nums2里的元素都填进去了，那什么都不用再做了
        // 因为nums1里的元素早都往后放过了，nums1里最大的哪些元素都顶格往右放了
        else // index2 == n
        { }
        
    }
}
