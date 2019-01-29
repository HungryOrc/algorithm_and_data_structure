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




// 一个queue加一个set
public class PhoneDirectory {

    HashSet<Integer> usedNumbers;
    Queue<Integer> unusedNumbers;
    int max;
    
    public PhoneDirectory(int maxNumbers) {
        
        usedNumbers = new HashSet<>();
        
        unusedNumbers = new LinkedList<>();
        for (int i = 0; i < maxNumbers; i++) {
            unusedNumbers.offer(i);
        }
        
        max = maxNumbers;
    }
    
    public int get() {
        if (unusedNumbers.size() == 0) {
            return -1;
        } else {
            int firstNum = unusedNumbers.poll();
            usedNumbers.add(firstNum);
            
            return firstNum;
        }
    }

    public boolean check(int number) {
        if (number < 0 || number > max) { // 别忘了检验输入的number的合法性 ！！
            return false;
        }
        return (!usedNumbers.contains(number));
    }
    
    public void release(int number) {
        if (usedNumbers.contains(number)) { // 别忘了我们要release的数可能不存在 ！！！
            usedNumbers.remove(number);
            unusedNumbers.offer(number);
        }
    }
}
