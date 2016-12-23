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

// 我的朴素想法，速度不算慢。用一个容量为300的数组来做。不断将300秒以前的记录置零
// 要getHits的时候就将整个数组从头到尾加和
// 要注意的是！不仅每次有新的hit时要将300以前的记录置零，每次getHits时也要这么做！！
public class HitCounter
{
    /** Initialize your data structure here. */
    int[] latest300;
    int beginCountSlot;
    int prevTimeStamp;
    
    public HitCounter() {
        this.latest300 = new int[300];
        this.beginCountSlot = 0;
        this.prevTimeStamp = 0;
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp)
    {
        int curHitSlot = updateHitsInTheLatest300Seconds(timestamp);
        
        // record this hit
        latest300[curHitSlot] += 1;
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) 
    {
        updateHitsInTheLatest300Seconds(timestamp);
        
        int sum = 0;
        for (int n : this.latest300)
            sum += n;
        return sum;
    }
    
    private int updateHitsInTheLatest300Seconds(int timestamp)
    {
        int diffTimeStamp = timestamp - this.prevTimeStamp;
        int curSlot = this.beginCountSlot + diffTimeStamp;
        
        if (curSlot == this.beginCountSlot) // namely diffTimeStamp == 0
        {} // do nothing
        else if (curSlot > beginCountSlot && curSlot <= 299)
        {
            for (int i = beginCountSlot+1; i <= curSlot; i++)
                latest300[i] = 0;
        }
        else if (curSlot > 299 && curSlot <= 599)
        {
            for (int i = beginCountSlot+1; i <= 299; i++)
                latest300[i] = 0;
            
            curSlot -= 300;
            for (int j = 0; j <= curSlot; j++)
                latest300[j] = 0;
        }
        else // curSlot > 599
        {
            this.latest300 = new int[300];
            curSlot = curSlot % 300;
        }
        
        // update the slot at which the next counting for the next hit begins
        this.beginCountSlot = curSlot;
        
        // update the time stamp of the latest hit
        this.prevTimeStamp = timestamp;
        
        return curSlot;
    }
}

