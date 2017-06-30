/* Design and implement a TwoSum class. It should support the following operations: add and find.
add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,
add(1); add(3); add(5);
find(4) -> true
find(7) -> false    */

// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);


/* 方法1: Use HashMap to store times of number be added.
When find be called, we iterate the keys of HashMap, then find another number minus by value.
Then combine the detections together.
Ref: https://leetcode.com/problems/two-sum-iii-data-structure-design/
*/
public class TwoSum 
{
    private HashMap<Integer, Integer> myMap = new HashMap<Integer, Integer>();
    
    public void add(int number)
    {
        myMap.put(number, myMap.containsKey(number) ? myMap.get(number)+1 : 1);
    }
    public boolean find(int value)
    {
        // 注意！！！Map.Entry类型！！！
        // 注意！！！myMap.entrySet() 方法！！！
        for (Map.Entry<Integer, Integer> entry : myMap.entrySet())
        {
            int num1 = entry.getKey();
            int num2 = value - num1;
            if ((num1==num2 && entry.getValue()>1) || (num1!=num2 && myMap.containsKey(num2)))
                return true;
        }
        return false;
    }
}


// 方法2：我自己的方法。用ArrayList存数。速度慢
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
