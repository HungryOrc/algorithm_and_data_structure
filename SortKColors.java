/* Given an array of n objects with k different colors (numbered from 1 to k), sort them so that objects of the same color are adjacent, 
with the colors in the order 1, 2, ... k. (k <= n)
You are not suppose to use the library's sort function for this problem.

Example
Given colors=[3, 2, 2, 1, 4], k=4, your code should sort colors in-place to [1, 2, 2, 3, 4].

Challenge 
A rather straight forward solution is a two-pass algorithm using counting sort. That will cost O(k) extra memory. 
Can you do it without using extra memory? */

// 方法1：朴素的 quick sort 方法。速度会稍微低一点
class Solution {
    
    public void sortColors2(int[] colors, int k) {
        
        if (colors == null || colors.length <= 1) {
            return;
        }
        
        partition(colors, 0, colors.length - 1);
    }
    
    private void partition(int[] colors, int leftIndex, int rightIndex) {
        
        if (leftIndex >= rightIndex) {
            return;
        }
        
        int originalLeftIndex = leftIndex;
        int originalRightIndex = rightIndex;
        int midIndex = leftIndex + (rightIndex - leftIndex) / 2;
        int pivot = colors[midIndex];
        
        while (leftIndex <= rightIndex) {
            while (leftIndex <= rightIndex && colors[leftIndex] < pivot) {
                leftIndex ++;
            }
            while (leftIndex <= rightIndex && colors[rightIndex] > pivot) {
                rightIndex --;
            }
            
            if (leftIndex <= rightIndex) {
                swap(colors, leftIndex, rightIndex);
                leftIndex ++;
                rightIndex --;
            }
        }
        // 到此，二者交叉了，即rightIndex在leftIndex的左边一位
        
        partition(colors, originalLeftIndex, rightIndex);
        partition(colors, leftIndex, originalRightIndex);
    }
    
    private void swap(int[] colors, int i, int j) {
        int temp = colors[i];
        colors[i] = colors[j];
        colors[j] = temp;
    }
}


// 方法2：在 quick sort 的基础上进行了改进，速度快了些
// 核心是加入了每次partition的起始色值和终止色值，这样能提高pivot的效率，加快速度
// Ref: http://www.jiuzhang.com/solutions/sort-colors-ii/
class Solution {
    
    public void sortColors2(int[] colors, int k) {
        
        if (colors == null || colors.length <= 1) {
            return;
        }
        
        partition(colors, 0, colors.length - 1, 1, k);
    }
    
    private void partition(int[] colors, int leftIndex, int rightIndex,
        int startColor, int endColor) { // 这两个参数是本方法的独到之处！！！
        
        if (leftIndex >= rightIndex) {
            return;
        }
        
        if (startColor == endColor) {
            return;
        }
        
        int originalLeftIndex = leftIndex;
        int originalRightIndex = rightIndex;
        int pivotColor = startColor + (endColor - startColor) / 2;
        
        while (leftIndex <= rightIndex) {
            // 注意！！！这里是 <= ！！！为了配合下一层的partition的分段 ！！！
            // 那时 endColor 将会等于 pivotColor
            while (leftIndex <= rightIndex && colors[leftIndex] <= pivotColor) {
                leftIndex ++;
            }
            // 注意！！！这里是 > ！！！为了配合下一层的partition的分段 ！！！
            // 那时 startColor 将会等于 pivotColor + 1
            while (leftIndex <= rightIndex && colors[rightIndex] > pivotColor) {
                rightIndex --;
            }
            
            if (leftIndex <= rightIndex) {
                swap(colors, leftIndex, rightIndex);
                leftIndex ++;
                rightIndex --;
            }
        }
        // 到此，二者交叉了，即rightIndex在leftIndex的左边一位
        
        partition(colors, originalLeftIndex, rightIndex, startColor, pivotColor);
        partition(colors, leftIndex, originalRightIndex, pivotColor + 1, endColor);
    }
    
    private void swap(int[] colors, int i, int j) {
        int temp = colors[i];
        colors[i] = colors[j];
        colors[j] = temp;
    }
}


// 方法3，九章的另一种方法。还没看？？？？？？？？？
// Ref: http://www.jiuzhang.com/solutions/sort-colors-ii/
// version 2: O(nk), not efficient, will get Time Limit Exceeded error. 
// But you should try to implement the following algorithm for practicing purpose.
class Solution {

    public void sortColors2(int[] colors, int k) {
        int count = 0;
        int start = 0;
        int end = colors.length - 1;
        while (count < k) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            
            for (int i = start; i <= end; i++) {
                min = Math.min(min, colors[i]);
                max = Math.max(max, colors[i]);
            }
            int left = start;
            int right = end;
            int cur = left;
            while(cur <= right) {
                if (colors[cur] == min) {
                    swap(left, cur, colors);
                    cur++;
                    left++;
                } else if (colors[cur] > min && colors[cur] < max) {
                    cur++;
                } else {
                    int tmp = colors[cur];
                    swap(cur, right, colors);
                    right--;
                }
            }
            count += 2;
            start = left;
            end = right;
        }
    }
    
    void swap(int left, int right, int[] colors) {
        int tmp = colors[left];
        colors[left] = colors[right];
        colors[right] = tmp;
    }
}
