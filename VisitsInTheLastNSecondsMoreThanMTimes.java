// 每一条visit log上有visit者的id，以及他的visit 时间。给一批最新的logs，要找出最近 n秒内，至少访问了 m次 的所有人的 id。

class Log{
  String id;
  int time;
  public Log(String id, int time){
    this.id = id;
    this.time = time;
  }
}

public class Solution{
  public static HashSet<String> getBots(List<Log> logs, int n, int m){
    HashMap<String, Queue<Integer>> map = new HashMap<>();
    HashSet<String> set = new HashSet<>();
    for(Log log : logs){
      String id = log.id;
      int time = log.time;
      if(set.contains(id)){
        continue;
      }
      if(!map.containsKey(id)){
        Queue<Integer> q = new LinkedList<>();
        q.add(time);
        map.put(id, q);
      }else{
        while(!map.get(id).isEmpty() && time - map.get(id).peek() > n){
          map.get(id).remove();
        }
        map.get(id).add(time);
      }
      if(map.get(id).size() >= m){
          set.add(id);
      }
    }
    return set;
  }
}
