/* Design a hit counter which counts the number of hits received in the past 5 minutes.
Each function accepts a timestamp parameter (in seconds granularity) and 
you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). 
You may assume that the earliest timestamp starts at 1.
It is possible that several hits arrive roughly at the same time.

Example:
HitCounter counter = new HitCounter();
// hit at timestamp 1.
counter.hit(1);
// hit at timestamp 2.
counter.hit(2);
// hit at timestamp 3.
counter.hit(3);
// get hits at timestamp 4, should return 3.
counter.getHits(4);
// hit at timestamp 300.
counter.hit(300);
// get hits at timestamp 300, should return 4.
counter.getHits(300);
// get hits at timestamp 301, should return 3.
counter.getHits(301); 

Follow up:
What if the number of hits per second could be very large? Does your design scale?

Your HitCounter object will be instantiated and called as such:
    HitCounter obj = new HitCounter();
    obj.hit(timestamp);
    int param_2 = obj.getHits(timestamp); */


// 方法1：用一个 Queue (LinkedList) 记录hit的时间，一个 HashMap 记录各秒上的hit次数
// 这样做的空间复杂度  大  于  双数组方法。因为 HashMap需要的额外空间是很大的，虽然量级还是 O(n)
// 时间复杂度小于双数组方法（经过实测检验的），详见下面代码中的注释
// https://instant.1point3acres.com/thread/203793

public class HitCounter {

    // 按本题的follow up问题：如果一秒以内的累计hit次数可能非常大，则用long应可解决
    private HashMap<Integer, Long> hitTimesAndCounts;
    private Queue<Integer> hitTimes;
    
    public HitCounter() {
        hitTimesAndCounts = new HashMap<>();
        hitTimes = new LinkedList<>();
    }
    
    // Record a hit at the current timestamp
    // 时间复杂度：O(最近300秒之外有hit的秒数)
    public void hit(int timestamp) {
        if (hitTimesAndCounts.containsKey(timestamp)) { // 如果是同一秒内的连续多次hit
            hitTimesAndCounts.put(timestamp, hitTimesAndCounts.get(timestamp) + 1);
        } else {
            hitTimesAndCounts.put(timestamp, 1L); // 这里要写 1L，因为是 long ！
            hitTimes.offer(timestamp);
        }
        
        // 及时消除无用记录，节约空间。用来防止这种情况：巨量的hits，就是不getHits
        removeOutdatedRecords(timestamp); // 时间复杂度：O(最近300秒之外有hit的秒数)
    }
    
    // Return the number of hits in the past 5 minutes
    // 时间复杂度：O(最近300秒之外有hit的秒数 + 最近300秒之内有hit的秒数)
    public int getHits(int timestamp) {
        
        removeOutdatedRecords(timestamp); // 时间复杂度：O(最近300秒之外有hit的秒数)
        
        int hitsInTheLatest300 = 0;
        for (int time : hitTimes) { // 时间复杂度：O(最近300秒之内有hit的秒数)
            hitsInTheLatest300 += hitTimesAndCounts.get(time);
        }
        return hitsInTheLatest300;
    }
    
    // helper method
    // 时间复杂度：O(最近300秒之外有hit的秒数)
    private void removeOutdatedRecords(int timestamp) {
        while (!hitTimes.isEmpty()) {
            // hitTimes是一个queue，对于queue的peek是看到它的开头处而非结尾处，正是我们需要的
            if (timestamp - hitTimes.peek() >= 300) { 
                int timeToBeRemoved = hitTimes.poll(); // remove from the queue
                hitTimesAndCounts.remove(timeToBeRemoved); // remove from the map
            } else { // < 300，到此则终止此method的运行
                return;
            }
        }
    }
}


// 方法2：用2个数组，一个记录hit时间，一个记录各秒钟的hit次数。都是循环数组
// Ref: https://discuss.leetcode.com/topic/48758/super-easy-design-o-1-hit-o-s-gethits-no-fancy-data-structure-is-needed

import java.util.*; 
import java.io.*;

public class HitCounter {

    private int[] hitTimes;
    private int[] hitCounts;
    
    public HitCounter() {
        hitTimes = new int[300];
        hitCounts = new int[300];
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    // O(1) time
    public void hit(int timestamp) {
        int index = timestamp % 300;
        if (hitTimes[index] == timestamp) { // 如果是同一秒内的连续多次hit
            hitCounts[index] ++;
        } else { // 如果上一次本index的记录是300秒的某个整数倍之前的时间
            hitTimes[index] = timestamp;
            hitCounts[index] = 1;
        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    // O(300) time
    public int getHits(int timestamp) {
        int hitsInTheLatest300 = 0;
        for (int i = 0; i < 300; i++) {
            if (hitTimes[i] != 0) {
                if (timestamp - hitTimes[i] >= 300) {
                    hitTimes[i] = 0;
                    // hitCounts[i] = 0; // 这个处理可以没有。有了就显得干净点
                } else {
                    hitsInTheLatest300 += hitCounts[i];
                }
            }
        }
        return hitsInTheLatest300;
    }
    
    // main
    public static void main(String[] args) { 
        
        HitCounter obj = new HitCounter(); 
        
        obj.hit(1); 
        obj.hit(2); 
        obj.hit(3); 
        obj.hit(300); 
        System.out.println(obj.getHits(300)); // 4
        System.out.println(obj.getHits(301)); // 3
    }
}
