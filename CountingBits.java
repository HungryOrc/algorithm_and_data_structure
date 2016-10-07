/* Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num,
calculate the number of 1's in their binary representation and return them as an array.
Example: For num = 5 you should return [0,1,1,2,1,2], which includes 6 elements.   
Try to make the Runtime ~ O(n) and Space cost ~ O(n).   */

public class CountingBits
{



    // 方法：除以2，再减去2^m ---- 这两步交替进行，直到本数成为0
    // Runtime：O(n*logn)，很慢，不符合题意 O(n) 的要求
    //
    public int[] countBits(int num) {
        
        int[] outputIntArray = new int[num+1];
        
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
