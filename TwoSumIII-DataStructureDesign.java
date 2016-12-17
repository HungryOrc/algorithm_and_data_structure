/* Design and implement a TwoSum class. It should support the following operations: add and find.
add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,
add(1); add(3); add(5);
find(4) -> true
find(7) -> false */

// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);





// 我自己的方法。用ArrayList存数。速度慢
public class TwoSum 
{
    ArrayList<Integer> nums;
    // Constructor
    public TwoSum()
    {
        nums = new ArrayList<Integer>();    
    }
    // Add the number to an internal data structure.
    public void add(int number) {

        if (nums.size()==0 || number > nums.get(nums.size()-1))
            nums.add(number);
        else
        {
            int i = 0;
            while (number > nums.get(i))
                i++;
            nums.add(i, number);
        }
    }
    // Find if there exists any pair of numbers which sum is equal to the value.
    public boolean find(int value) {
        int i = 0, j = nums.size()-1;
        while (i < j)
        {
            if (nums.get(i) + nums.get(j) > value)
                j--;
            else if (nums.get(i) + nums.get(j) < value)
                i++;
            else // sum == value
                return true;
        }
        return false;
    }
}
