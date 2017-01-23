/* You are a product manager and currently leading a team to develop a new product. 
Unfortunately, the latest version of your product fails the quality check. 
Since each version is developed based on the previous version, all the versions after a bad version are also bad.
Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, 
which causes all the following ones to be bad.
You are given an API bool isBadVersion(version) which will return whether version is bad. 
Implement a function to find the first bad version. You should minimize the number of calls to the API.

The isBadVersion API is defined in the parent class VersionControl:
      boolean isBadVersion(int version); 
*/
public class Solution extends VersionControl 
{
    public int firstBadVersion(int n) 
    {
        int start = 1, end = n;
        
        while (start < end) 
        {
            // 不要用 (start+end)/2 ！！！以免出现 int overflow ！！！
            int mid = start + (end - start) / 2;
            
            // 集中精力找不是bad的version，然后紧邻它后面的一个version是否bad先不管！！！
            // 通过后面的start = mid+1，来向后逼近
            // 最终 start 与 end 重合时即为答案！！！
            if (!isBadVersion(mid)) 
                start = mid + 1;
                
            else // isBadVersion(mid)
                // 这里不要再 -1 了！！！因为 mid 本身可能就是BadVersion
                end = mid;            
        }        
        
        // 最后 start 与 end 重合（这是一定会发生的），返回 start
        return start;
    }


    // 九章式二分
    public int findFirstBadVersion(int n) {
        
        int start = 1, end = n;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;            
            if (isBadVersion(mid)) {
                end = mid;
            }
            else {
                start = mid;
            }
        }
        
        if (isBadVersion(start)) {
            return start;
        } else {
            return end;
        }
    }
    
}
