/* Design a logger system that receive stream of messages along with its timestamps, 
each message should be printed if and only if it is not printed in the last 10 seconds.
Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, 
otherwise returns false.
It is possible that several messages arrive roughly at the same time.

Example:
Logger logger = new Logger();
// logging string "foo" at timestamp 1
logger.shouldPrintMessage(1, "foo"); returns true; 
// logging string "bar" at timestamp 2
logger.shouldPrintMessage(2,"bar"); returns true;
// logging string "foo" at timestamp 3
logger.shouldPrintMessage(3,"foo"); returns false;
// logging string "bar" at timestamp 8
logger.shouldPrintMessage(8,"bar"); returns false;
// logging string "foo" at timestamp 10
logger.shouldPrintMessage(10,"foo"); returns false;
// logging string "foo" at timestamp 11
logger.shouldPrintMessage(11,"foo"); returns true;
*/

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
public class Logger {

    // 用 HashMap 做，message 为 Key，上次出现的时间为 Value
    //
    private HashMap<String, Integer> hashMap_TimeAndMessage;
    
    /** Initialize your data structure here. */
    public Logger() {
        this.hashMap_TimeAndMessage = new HashMap<String, Integer>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        
        if (hashMap_TimeAndMessage.containsKey(message) &&
            timestamp - hashMap_TimeAndMessage.get(message) < 10)
                return false;
        
        hashMap_TimeAndMessage.put(message, timestamp);
        return true;
    }
    
    
    // 一种小改进：HashMap里不记录上次的到达时间，而是只记录下次可以开始记录的时间（这样每次纪录时就要+10）
    // 那么每次在 if 那里就不用再做 +10 的运算了，只要比较大小即可
    // 理论上会快一点点，实测好像也没啥区别
    //
    public boolean shouldPrintMessage_ByNextAvailableTime(int timestamp, String message) {
        
        if (hashMap_MessageAndNextTime.containsKey(message) &&
            timestamp < hashMap_MessageAndNextTime.get(message))
                return false;
        
        hashMap_MessageAndNextTime.put(message, timestamp + 10);
        return true;
    }
    
}





