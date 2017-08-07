// 每一条visit log上有visit者的id，以及他的visit 时间。给一批最新的logs，要找出最近 n秒内，至少访问了 m次 的所有人的 id。

class Log {
  String id;
  int time;
  public Log(String id, int time){
    this.id = id;
    this.time = time;
  }
}

public class Solution {
  
  public static HashSet<String> getBots(List<Log> logs, int n, int m, int curTime){
    HashMap<String, Queue<Integer>> map = new HashMap<>();
    HashSet<String> result = new HashSet<>();
    
    for(Log log : logs) {
      String id = log.id;
      int time = log.time;
      
      if(result.contains(id)) {
        continue;
      }
      
      if(!map.containsKey(id)) {
        Queue<Integer> q = new LinkedList<>();
        q.add(time);
        map.put(id, q);
      } else {
        // 每次来一个新的log，都要用这个最新log的最新time，来衡量所有之前已经放到map里的logs ！！！
        // 看这些logs里面有没有任何log是超时了的 ！！！ 发现一个犬决一个 ！！！
        while(!map.get(id).isEmpty() && time - map.get(id).peek() > n) { // 看是看头部
          map.get(id).poll(); // 扔是扔头部，因为先进先出嘛
        }
        
        map.get(id).add(time); // 加是加在尾部
      }
      
      if(map.get(id).size() >= m) {
        result.add(id);
      }
    }
    return result;
  }
}
