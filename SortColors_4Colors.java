// 这一题基于 Sort Three Colors 而来，更多的基本思路和解释请看那一题的注释


// 方法0：用类似于 Counting Sort 的方法


// 方法1：先用 Sort 3 Colors 的方法，把整个数组sort一遍，忽略其中的一种颜色。然后再sort这最后一种颜色


// 方法2
public class SortColors {

    // 四个颜色的标志数值分别为：1, 2, 3, 4。每一种颜色都可能存在0个或任意个
    // 最后要排列成类似于 1, 1, 1, 2, 2, 3, 3, 3, 3, 3, 4 这样的样子
    public void sortFourColors(int[] colors) {
        if (colors == null || colors.length <= 1) {
            return;
        }
        
        int divider1 = 0; // 除了最后一个divider以外，其它的divider全部放到最左边
        int divider2 = 0;
        int divider3 = 0; // 倒数第二个divider除了是第三种颜色的分隔位以外，还是当前处理到了数组里的第几个元素的指示位
        int divider4 = colors.length - 1; // 最后一个divider放到最右边
        
        // 相等时还要进行操作！止于交叉时，而非止于相等时
        while (divider3 <= divider4) {
            
            if (colors[divider3] == 1) {
                swap(colors, divider3, divider1);
                
                if (divider2 == divider1) {
                    divider2 ++;
                }
                if (divider3 == divider1) {
                    divider3 ++;
                }
                divider1 ++;
            }
            
            if (colors[divider3] == 2) {
                swap(colors, divider3, divider2);
                
                if (divider3 == divider2) {
                    divider3 ++;
                }
                divider2 ++;
            }
        
            if (colors[divider3] == 3) {
                divider3 ++;
            }
        
            if (colors[divider3] == 4) {
                swap(colors, divider3, divider4);
                
                divider4 --;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }   
}
