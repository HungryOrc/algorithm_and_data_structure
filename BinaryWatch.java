/* A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
Each LED represents a zero or one, with the least significant bit on the right.
Given a non-negative integer n which represents the number of LEDs that are currently on, 
return all possible times the watch could represent.
Example: Input: n = 1, Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
Note:
* The order of output does not matter.
* The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
* The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02". */

public class Solution
{
   // Just go through the possible times and collect those with the correct number of one-bits
   // Ref: https://discuss.leetcode.com/topic/59374/simple-python-java/1
   public List<String> readBinaryWatch (int num)
   {
      List<String> times = new ArrayList<>();
      for (int h = 0; h <= 11; h++)
      {
         for (int m = 0; m <= 59; m++)
         
            // 注意！static method of Integer Class: Integer.bitCount(int i)
            // Returns the number of one-bits in the two's complement binary representation of the specified int value
            if (Integer.bitCount(h * 64 + m) == num)
               // 注意！ %02d 的意思是：整数，最多2位，十位不存在时用0补足
               times.add (String.format("%d:%02d", h, m));
         }
         return times;
      }
   }


   // 用排列组合来做。比如一共要有 num 个1，则小时栏要有 i 个1，分钟栏要有 num-i 个1
   // Ref: https://discuss.leetcode.com/topic/59494/3ms-java-solution-using-backtracking-and-idea-of-permutation-and-combination
   public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        int[] nums1 = new int[]{8, 4, 2, 1}, nums2 = new int[]{32, 16, 8, 4, 2, 1};
        for(int i = 0; i <= num; i++) {
            List<Integer> list1 = generateDigit(nums1, i);
            List<Integer> list2 = generateDigit(nums2, num - i);
            for(int num1: list1) {
                if(num1 >= 12) continue;
                for(int num2: list2) {
                    if(num2 >= 60) continue;
                    res.add(num1 + ":" + (num2 < 10 ? "0" + num2 : num2));
                }
            }
        }
        return res;
    }
    private List<Integer> generateDigit(int[] nums, int count) {
        List<Integer> res = new ArrayList<>();
        generateDigitHelper(nums, count, 0, 0, res);
        return res;
    }
    private void generateDigitHelper(int[] nums, int count, int pos, int sum, List<Integer> res) {
        // 如果剩余需要分配的1个个数归零了，则意味着分配完毕，可以把这个结果(sum)加到result的list里面去
        // 否则，count>0意味着还有该分配的1没有分配完，就到了位数的尽头了，不能再分了，则这个组合失败，这个结果就不能加到最终结果里去
        if(count == 0) {
            res.add(sum);
            return;
        }
        // i=0意味着第一位就为1，后面的再分配
        // i=1意味着第一位为0，第二位为1，后面的再分配
        // i=2意味着第一位和第二位为0，第三位为1，后面的再分配
        // ...
        // 这样的分配方式，涵盖了所有情况，但不能保证分到最后(位数的尽头)正好把count个1都分配完
        // 没有分配完就意味着这个组合是失败的。而失败与否是靠上面那个 if(count==0) 来判定的
        for(int i = pos; i < nums.length; i++) {
            // 1的个数count-1，设置为1的位数i+1，当前各个为1的位的暂时总和为sum+nums[i]
            generateDigitHelper(nums, count - 1, i + 1, sum + nums[i], res);    
        }
    }
       
}
