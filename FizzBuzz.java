/* Write a program that outputs the string representation of numbers from 1 to n.
But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. 
For numbers which are multiples of both three and five output “FizzBuzz”. */

// 本题看起来没啥特别的方法了，也就这样了
public class Solution {
    public List<String> fizzBuzz(int n) {
        ArrayList<String> outputList = new ArrayList<String>();
        
        for (int i = 1; i <= n; i++)
        {
            if (i%3 == 0 && i%5 == 0)
                outputList.add("FizzBuzz");
            else if (i%3 == 0)
                outputList.add("Fizz");
            else if (i%5 == 0)
                outputList.add("Buzz");
            else
                outputList.add(String.valueOf(i));
        }
        return outputList;
    }
}
