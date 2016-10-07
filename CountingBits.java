/* Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num,
calculate the number of 1's in their binary representation and return them as an array.
Example: For num = 5 you should return [0,1,1,2,1,2], which includes 6 elements.   
Try to make the Runtime ~ O(n) and Space cost ~ O(n).   */

public class CountingBits
{
    
    // 方法：位运算 + 递归。看最右边的一位是否是零（i & 1），然后右移一位（i >> 1）
    // Runtime: 应该是 O(n*logn)，但实际表现非常快
    // Reference: https://discuss.leetcode.com/topic/40162/three-line-java-solution/2
    //
    public int[] countBits(int num)
    {
        int[] outputIntArray = new int[num + 1];
        
        for (int i = 1; i <= num; i++)
            outputIntArray[i] = outputIntArray[i >> 1] + (i & 1);
        
        return outputIntArray;
    }

    
    // 方法：位运算：(自己 - 1) 按位并 (自己)，经由几次把自己变为 0，则自己含有几个 1
    // 比如，11010100，减1等于11010011，按位并自己等于11010000，相当于去掉了一个1。每次这么搞一次就去掉一个1
    // Runtime: 应该是 O(n*logn)，应该比上面的 “位运算 + 递归” 的方法更快。但实际表现较慢，可能是位操作速度不快？
    // 
    public int[] countBits(int num)
    {
        int[] outputIntArray = new int[num + 1];
        
        for (int curNum = 0; curNum <= num; curNum++)
        {
            int curNum_Leftover = curNum;
            
            if (curNum == 0)
                outputIntArray[curNum] = 0;
                
            else
            {    
                while (curNum_Leftover != 0)
                {
                    // 经由几次把自己变为 0，则自己含有几个 1
                    outputIntArray[curNum] ++;
                    
                    // (自己 - 1) 按位并 (自己)
                    curNum_Leftover = (curNum_Leftover - 1) & curNum_Leftover;
                }
            }
        }
        
        return outputIntArray;
    }
    

    // 方法：除以2，再减去2^m ---- 这两步交替进行，直到本数成为0
    // Runtime：O(n*logn)，很慢，不符合题意 O(n) 的要求
    //
    public int[] countBits(int num)
    {        
        int[] outputIntArray = new int[num + 1];
        
        for (int curNum = 0; curNum <= num; curNum++)
        {
            int curCountOfOnes = 0;
            int curNum_Leftover = curNum;
            
            while (curNum_Leftover >= 1)
            {
                curCountOfOnes ++;
                
                int curPower  = (int) (Math.log(curNum_Leftover) / Math.log(2));
                curNum_Leftover = curNum_Leftover - (int)Math.pow(2, curPower);
                
                if (curNum_Leftover == 0)
                    break;
            }
            
            outputIntArray[curNum] = curCountOfOnes;
        }
        
        return outputIntArray;
    }




}
