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
 
// 方法1：用2个数组，一个记录hit时间，一个记录各秒钟的hit次数。都是循环数组
// Ref: https://discuss.leetcode.com/topic/48758/super-easy-design-o-1-hit-o-s-gethits-no-fancy-data-structure-is-needed
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
}
