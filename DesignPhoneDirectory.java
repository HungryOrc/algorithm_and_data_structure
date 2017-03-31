/* Design a Phone Directory which supports the following operations:
get: Provide a number which is not assigned to anyone.
check: Check if a number is available or not.
release: Recycle or release a number.

Example:
// Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
PhoneDirectory directory = new PhoneDirectory(3);
// It can return any available phone number. Here we assume it returns 0.
directory.get();
// Assume it returns 1.
directory.get();
// The number 2 is available, so return true.
directory.check(2);
// It returns 2, the only number that is left.
directory.get();
// The number 2 is no longer available, so return false.
directory.check(2);
// Release number 2 back to the pool.
directory.release(2);
// Number 2 is available again, return true.
directory.check(2);

/* Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number); */

// 方法1：用2个HashSet。速度没有 queue + HashSet 快
public class PhoneDirectory {

    HashSet<Integer> usedNumbers;
    HashSet<Integer> unusedNumbers;
    
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        
        usedNumbers = new HashSet<>();
        
        unusedNumbers = new HashSet<>();
        for (int i = 0; i < maxNumbers; i++) {
            unusedNumbers.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (unusedNumbers.size() == 0) {
            return -1;
        } else {
            // 注意 ！！！
            // 下面三行，是在HashSet里的iterator，用它来取Set里的第一个value ！！！
            Iterator myIterator = unusedNumbers.iterator();
            Object firstObj = myIterator.next();
            int firstNum = (int)first;
            
            unusedNumbers.remove(firstNum);
            usedNumbers.add(firstNum);
            
            return firstNum;
        }
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return (unusedNumbers.contains(number));
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        usedNumbers.remove(number);
        unusedNumbers.add(number);
    }
}
